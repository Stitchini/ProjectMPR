package pl.edu.pjatk.ProjectMPR.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.ProjectMPR.exceptions.CapybaraNotFoundException;
import pl.edu.pjatk.ProjectMPR.exceptions.CapybaraOverwriteException;
import pl.edu.pjatk.ProjectMPR.exceptions.NullCapybaraException;
import pl.edu.pjatk.ProjectMPR.model.Capybara;
import pl.edu.pjatk.ProjectMPR.repository.CapybaraRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CapybaraService {
    private CapybaraRepository repository;
    private StringUtilsService stringService;

//    public CapybaraService(CapybaraRepository repository) {
//        this.repository = repository;
//        this.repository.save(new Capybara("Szymon", "brown"));
//        this.repository.save(new Capybara("Julia", "gray"));
//        this.repository.save(new Capybara("Witold", "beige"));
//    }
    @Autowired
    public CapybaraService(CapybaraRepository repository, StringUtilsService stringService){
        this.repository = repository;
        this.stringService = stringService;
    }

    public List<Capybara> getCapybaraByName(String name) {
        name = name.toUpperCase();
        List<Capybara> foundCapys = this.repository.findByName(name).stream()
                .peek(capybara -> {
                    capybara.setColor(stringService.toFormatString(capybara.getColor()));
                    capybara.setName(stringService.toFormatString(capybara.getName()));
                }).toList();
        if (foundCapys.isEmpty()){
            throw new CapybaraNotFoundException();
        }
        return foundCapys;
    }
    public List<Capybara> getCapybaraByColor(String color){
        color = color.toUpperCase();
        List<Capybara> foundCapys = this.repository.findByColor(color).stream()
                .peek(capybara -> {
                    capybara.setColor(stringService.toFormatString(capybara.getColor()));
                    capybara.setName(stringService.toFormatString(capybara.getName()));
                }).toList();
        if (foundCapys.isEmpty()){
            throw new CapybaraNotFoundException();
        }
        return foundCapys;
    }

    public List<Capybara> getCapybaraList(){
        this.repository.findAll().forEach(capybara -> {
            capybara.setName(stringService.toFormatString(capybara.getName()));
            capybara.setColor(stringService.toFormatString(capybara.getColor()));
        });
        return (List<Capybara>) this.repository.findAll();
    }

    public Capybara getById(Long id) {
        Optional<Capybara> capy = this.repository.findById(id);
        if (capy.isPresent()) {
            Capybara realCapy = capy.get();
            realCapy.setColor(stringService.toFormatString(realCapy.getColor()));
            realCapy.setName(stringService.toFormatString(realCapy.getName()));
            return capy.get();
        }
        else {
            throw new CapybaraNotFoundException();
        }
    }
    public void postCapybara(Capybara capybara){
        if (capybara.getName() == null || capybara.getColor() == null){
            throw new NullCapybaraException();
        }else {
            capybara.setName(stringService.toUpperStrings(capybara.getName()));
            capybara.setColor(stringService.toUpperStrings(capybara.getColor()));
            capybara.setSerialNumber();
            if (repository.findBySerialNumber(capybara.getSerialNumber()).isEmpty()) {
                this.repository.save(capybara);
            } else {
                throw new CapybaraOverwriteException();
            }
        }
    }
    public void editCapybara(Long id, Capybara capy){
         Optional<Capybara> optionalCapybara = repository.findById(id);
         if (optionalCapybara.isPresent()){
             Capybara capybara = optionalCapybara.get();
             capybara.setSerialNumber();
             if (repository.findBySerialNumber(capybara.getSerialNumber()).isEmpty()){
                 if (capybara.getColor() == null || capybara.getName() == null){
                     throw new NullCapybaraException();
                 }else {
                     if (!capybara.getColor().equals(capy.getColor())) {
                         capybara.setColor(capy.getColor());
                     }
                     if (!capybara.getName().equals(capy.getName())) {
                         capybara.setName(capy.getName());
                     }
                     repository.save(capybara);
                 }
             }
             else {
                 throw new CapybaraOverwriteException();
             }
         }
         else {
             throw new CapybaraNotFoundException();
         }
    }
    public void removeCapybara(Long id){
        if (repository.findById(id).isPresent()){
        this.repository.deleteById(id);
        } else {
            throw new CapybaraNotFoundException();
        }
    }


}
