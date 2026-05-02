package ro.facultate.gestiune_studenti.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import ro.facultate.gestiune_studenti.model.Nota;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class RaportService {

    public void exportaNotePdf(HttpServletResponse response, List<Nota> note, String numeStudent) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        // Titlul Documentului
        Font fontTitlu = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph titlu = new Paragraph("Situatie Scolara (Carnet de Note)", fontTitlu);
        titlu.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titlu);

        Paragraph subtitlu = new Paragraph("Student: " + numeStudent + "\n\n");
        subtitlu.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(subtitlu);

        // Crearea tabelului cu 4 coloane
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);

        // Capul de tabel
        String[] headere = {"Materie", "Nota", "Profesor", "Data"};
        for (String header : headere) {
            PdfPCell cell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            cell.setBackgroundColor(java.awt.Color.LIGHT_GRAY);
            cell.setPadding(5);
            table.addCell(cell);
        }

        // Adăugarea notelor în tabel
        for (Nota n : note) {
            table.addCell(n.getMaterie().getDenumire());
            table.addCell(String.valueOf(n.getValoare()));
            table.addCell(n.getProfesor().getNume() + " " + n.getProfesor().getPrenume());
            table.addCell(n.getDataAcordarii().toString());
        }

        document.add(table);
        document.close();
    }
}