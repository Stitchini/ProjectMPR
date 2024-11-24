package pl.edu.pjatk.ProjectMPR.controller;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.ProjectMPR.model.Capybara;
import pl.edu.pjatk.ProjectMPR.service.CapybaraService;
import pl.edu.pjatk.ProjectMPR.service.PdfService;

import java.io.ByteArrayOutputStream;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class MyRestController {
    private CapybaraService capybaraService;
    private PdfService pdfService;

    @Autowired
    public MyRestController(CapybaraService capybaraService, PdfService pdfService) {
        this.capybaraService = capybaraService;
        this.pdfService = pdfService;
    }

//    @GetMapping("capybara/all")
//    public List<Capybara> getAll(){
//        return this.capybaraService.getCapybaraList();
//    }
//    @GetMapping("capybara/{id}")
//    public Capybara get(@PathVariable int id){
//        return this.capybaraService.get(id);
//    }
//    @PostMapping("capybara")
//    public void create(@RequestBody Capybara capybara){
//        this.capybaraService.create(capybara);
//    }
//    @DeleteMapping("capybara/{id}")
//    public void delete(@PathVariable int id){
//        this.capybaraService.remove(id);
//    }
//    @PutMapping("capybara/put/{id}")
//    public void update(@PathVariable int id, @RequestBody Capybara capybara){
//        this.capybaraService.update(id, capybara);
//    }
    @GetMapping("/")
    ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello, Welcome to my WebApp");
    }

    @GetMapping("capybara/all")
    public ResponseEntity<List<Capybara>> getAllCapybaras(){
        return new ResponseEntity<>(this.capybaraService.getCapybaraList(), HttpStatus.OK);
    }

    @GetMapping("capybara/name/{name}")
    public ResponseEntity<List<Capybara>> getByName(@PathVariable String name){
        return new ResponseEntity<>(this.capybaraService.getCapybaraByName(name), HttpStatus.OK);
    }
    @GetMapping("capybara/{id}")
    public ResponseEntity<Capybara> get(@PathVariable Long id){
        return new ResponseEntity<>(this.capybaraService.getById(id), HttpStatus.OK);
    }
    @GetMapping("capybara/color/{color}")
    public ResponseEntity<List<Capybara>> getByColor(@PathVariable String color){
        return new ResponseEntity<>(this.capybaraService.getCapybaraByColor(color), HttpStatus.OK);
    }

    @PostMapping("capybara")
    public ResponseEntity<Capybara> create(@RequestBody Capybara capybara){
        this.capybaraService.postCapybara(capybara);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("capybara/put/{id}")
    public ResponseEntity<Capybara> edit(@PathVariable Long id, @RequestBody Capybara capybara){
        this.capybaraService.editCapybara(id, capybara);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("capybara/{id}")
    public ResponseEntity<Capybara> delete(@PathVariable Long id){
        this.capybaraService.removeCapybara(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endpoint??
    @GetMapping("/open-blank-pdf")
    public ResponseEntity<byte[]> openBlankPdf() {
        /*
        Find capybara by ID
        put capybara object into "pdfmaker"
        set headers
        profit
        */
        return new ResponseEntity<>(pdfService.makeBlankPdf(), pdfService.setHeaders(), HttpStatus.OK);
    }
    @GetMapping("/open-hello-pdf")
    public ResponseEntity<byte[]> openHelloPdf(){
        return new ResponseEntity<>(pdfService.makeHelloPdf(), pdfService.setHeaders(), HttpStatus.OK);
    }
    @GetMapping("pdf/{id}")
    public ResponseEntity<byte[]> openCapybaraPdf(@PathVariable Long id){
        return new ResponseEntity<>(pdfService.makeCapybaraPdf(id),pdfService.setHeaders(), HttpStatus.OK);
    }
}
