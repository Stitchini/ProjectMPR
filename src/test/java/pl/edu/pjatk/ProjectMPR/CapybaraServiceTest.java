package pl.edu.pjatk.ProjectMPR;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.pjatk.ProjectMPR.model.Capybara;
import pl.edu.pjatk.ProjectMPR.repository.CapybaraRepository;
import pl.edu.pjatk.ProjectMPR.service.CapybaraService;
import pl.edu.pjatk.ProjectMPR.service.StringUtilsService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CapybaraServiceTest {
    private StringUtilsService stringUtilsService;
    private CapybaraRepository repository;
    private CapybaraService realService;

    @BeforeEach
    public void setup(){
        stringUtilsService = Mockito.mock(StringUtilsService.class);
        repository = Mockito.mock(CapybaraRepository.class);
        realService = new CapybaraService(repository, stringUtilsService);
        Mockito.clearInvocations(repository);
    }

    @Test
    public void postCapybaraInvokesStringUtilsAndSavesToDB(){
        Capybara szymon = new Capybara("Capyabara", "blonde");

        this.realService.postCapybara(szymon);

        Mockito.verify(stringUtilsService, Mockito.times(2)).toUpperStrings(any());
        Mockito.verify(repository, Mockito.times(1)).save(any());
    }

    @Test
    public void patchCapybaraEditsCapybaraAndSaves(){
        Capybara capybara = new Capybara("Capybara", "white");
        Capybara capychange = new Capybara("Capy", "brown");
        when(repository.findById(1L)).thenReturn(Optional.of(capybara));
        realService.editCapybara(1L, capychange);

        assertEquals("Capy", capybara.getName());
        assertEquals("brown", capybara.getColor());
        verify(repository).save(any());
    }
    @Test
    public void patchCapybaraByNameEditsCapybaraAndSaves(){
        Capybara capybara = new Capybara("Capybara", "white");
        Capybara capychange = new Capybara("Capy", "white");
        when(repository.findById(1L)).thenReturn(Optional.of(capybara));
        realService.editCapybara(1L, capychange);

        assertEquals("Capy", capybara.getName());
        assertEquals("white", capybara.getColor());
        verify(repository).save(any());
    }
    @Test
    public void patchCapybaraByColorEditsCapybaraAndSaves(){
        Capybara capybara = new Capybara("Capybara", "white");
        Capybara capychange = new Capybara("Capybara", "brown");
        when(repository.findById(1L)).thenReturn(Optional.of(capybara));
        realService.editCapybara(1L, capychange);

        assertEquals("Capybara", capybara.getName());
        assertEquals("brown", capybara.getColor());
        verify(repository).save(any());
    }
    @Test
    public void patchCapybaraNoChangesEditsCapybaraAndSaves(){
        Capybara capybara = new Capybara("Capybara", "white");
        Capybara capychange = new Capybara("Capybara", "white");
        when(repository.findById(1L)).thenReturn(Optional.of(capybara));
        realService.editCapybara(1L, capychange);

        assertEquals("Capybara", capybara.getName());
        assertEquals("white", capybara.getColor());
        verify(repository).save(any());
    }
    @Test
    public void deleteCapybaraErasesTheCapybara(){
        Capybara capybara = new Capybara("Capy", "white");
        realService.removeCapybara(capybara.getId());

        verify(repository).deleteById(capybara.getId());
    }

    @Test
    public void getCapybaraByNameGetsCapybara(){
        Capybara capybara = new Capybara("Witold", "beige");
        when(repository.findByName("Capy")).thenReturn(List.of(capybara));

        realService.getCapybaraByName("Capy");
        verify(repository).findByName("Capy");
        verify(stringUtilsService).toFormatString("Witold");
        verify(stringUtilsService).toFormatString("beige");
    }

    @Test
    public void getCapybaraByColorGetsCapybara(){
        Capybara capybara = new Capybara("Witold", "beige");
        when(repository.findByColor("beige")).thenReturn(List.of(capybara));

        realService.getCapybaraByColor("beige");
        verify(repository).findByColor("beige");
        verify(stringUtilsService).toFormatString("Witold");
        verify(stringUtilsService).toFormatString("beige");
    }

    @Test
    public void getCapybaraById(){
        Capybara capabara = new Capybara("Witold", "beige");
        when(repository.findById(1L)).thenReturn(Optional.of(capabara));
        when(stringUtilsService.toFormatString("Witold")).thenReturn("Witold");
        when(stringUtilsService.toFormatString("beige")).thenReturn("Beige");
        Capybara result = realService.getById(1L);

        assertEquals("Witold", result.getName());
        assertEquals("Beige", result.getColor());

        verify(repository).findById(1L);
        verify(stringUtilsService, times(2)).toFormatString(any());
    }
}
