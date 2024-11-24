package pl.edu.pjatk.ProjectMPR.exceptions;

public class CapybaraOverwriteException extends RuntimeException{
    public CapybaraOverwriteException(){
        super("Capybara already exists");
    }
}
