package pl.edu.pjatk.ProjectMPR.exceptions;

public class NullCapybaraException extends RuntimeException{
    public NullCapybaraException(){
        super("Capybara has null fields");
    }
}
