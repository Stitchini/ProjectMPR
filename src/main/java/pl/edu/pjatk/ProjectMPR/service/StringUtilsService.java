package pl.edu.pjatk.ProjectMPR.service;

import org.springframework.stereotype.Component;
import pl.edu.pjatk.ProjectMPR.model.Capybara;

@Component
public class StringUtilsService {

    public String toUpperStrings(String string){
        return string.toUpperCase();
    }

    public String toFormatString(String string){
        String letter = string.substring(0, 1);
        letter = letter.toUpperCase();
        String rest = string.substring(1);
        rest = rest.toLowerCase();
        String result = letter + rest;
        return result;
    }
}
