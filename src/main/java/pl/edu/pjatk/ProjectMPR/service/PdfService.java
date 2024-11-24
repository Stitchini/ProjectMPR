package pl.edu.pjatk.ProjectMPR.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.ProjectMPR.exceptions.CapybaraNotFoundException;
import pl.edu.pjatk.ProjectMPR.model.Capybara;
import pl.edu.pjatk.ProjectMPR.repository.CapybaraRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

@Component
public class PdfService {
    private CapybaraRepository repository;

    public PdfService(CapybaraRepository repository){
        this.repository = repository;
    }
    public byte[] makeBlankPdf(){
        try (PDDocument document = new PDDocument();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            PDPage page = new PDPage();
            document.addPage(page);
            document.save(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e); //TODO make new exception for creating PDF
        }
    }

    public byte[] makeHelloPdf(){
        try (PDDocument document = new PDDocument();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            PDPage page = new PDPage();
            PDPageContentStream stream = new PDPageContentStream(document, page);
            stream.beginText();
            stream.setFont(PDType1Font.TIMES_ROMAN, 14);
            stream.newLineAtOffset(50,500);
            stream.showText("Hello World!");
            stream.close();
            document.addPage(page);
            document.save(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e); //TODO make new exception for creating PDF
        }
    }
    public byte[] makeCapybaraPdf(Long id){
        //find capybara
        Optional<Capybara> capy = repository.findById(id);
        Capybara capybara;
        if (capy.isPresent()){
            capybara = capy.get();
        }
        else throw new CapybaraNotFoundException();
        try (PDDocument document = new PDDocument();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            PDPage page = new PDPage();
            PDPageContentStream stream = new PDPageContentStream(document, page);
            stream.setFont(PDType1Font.TIMES_ROMAN, 14);
            int yCoord = 750;
            int spacing = 15;
            String[] lines = capybara.toString().split("\n");
            for (String line : lines){
                stream.beginText();
                stream.newLineAtOffset(25, yCoord);
                stream.showText(line);
                stream.endText();
                yCoord -= spacing;
            }
            stream.close();
            document.addPage(page);
            document.save(outputStream);
            return outputStream.toByteArray();
        }catch (IOException e) {
            throw new RuntimeException(e); //TODO make new exception for creating PDF
        }
    }


    public HttpHeaders setHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=blank.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
        return headers;
    }
}
