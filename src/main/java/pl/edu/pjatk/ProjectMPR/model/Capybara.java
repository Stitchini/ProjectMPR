package pl.edu.pjatk.ProjectMPR.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Capybara {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String color;
    private Integer serialNumber;

    public Capybara(String name, String color) {
        this.name = name;
        this.color = color;
        setSerialNumber();
    }

    public Capybara() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public int generateSerialNumber(String word){
        int sum = 0;
        for (int i = 0; i < word.length(); i++){
            sum += word.charAt(i);
        }
        return sum;
    }
    public void setSerialNumber(){
        this.serialNumber = generateSerialNumber(this.name) + generateSerialNumber(this.color);
    }
    public Integer getSerialNumber(){
        return this.serialNumber;
    }

    @Override
    public String toString(){
        return "id: " + this.getId() + "\nname: " + this.getName() + "\ncolor: " + this.getColor() + "\nserial number: " + this.getSerialNumber() + "\n";
    }
}
