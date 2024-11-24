package pl.edu.pjatk.ProjectMPR;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pjatk.ProjectMPR.service.StringUtilsService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsServiceTest {
    private StringUtilsService service;

    @BeforeEach
    public void setup(){
        service = new StringUtilsService();
    }


    @Test
    public void uppCaseTest(){
        String s = "ThiStOuppERsTring";
        assertEquals("THISTOUPPERSTRING", service.toUpperStrings(s));
    }
    @Test
    public void formatCaseTest(){
        String s = "forMatTEDString";
        assertEquals("Formattedstring", service.toFormatString(s));
    }
}
