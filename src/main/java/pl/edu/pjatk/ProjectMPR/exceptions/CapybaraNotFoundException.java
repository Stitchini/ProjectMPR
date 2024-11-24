package pl.edu.pjatk.ProjectMPR.exceptions;

public class CapybaraNotFoundException extends RuntimeException{
    public CapybaraNotFoundException(){
        super("Capybara not found");
    }
}
