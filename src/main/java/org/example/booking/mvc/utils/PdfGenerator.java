package org.example.booking.mvc.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.booking.core.util.Storage;
import org.example.booking.intro.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Storage.class);

    public ByteArrayInputStream ticketReport(List<Ticket> ticketList) {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfPTable pdfPTable = new PdfPTable(1);
            pdfPTable.setWidthPercentage(80);
            pdfPTable.setWidths(new  int[]{1});

            Font headFont = FontFactory.getFont(FontFactory.COURIER_BOLD);
            PdfPCell head = new PdfPCell(new Phrase("Tickets description", headFont));
            head.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(head);

            Font cellFont = FontFactory.getFont(FontFactory.COURIER);
            for(Ticket ticket : ticketList) {
                PdfPCell cell = new PdfPCell(new Phrase(ticket.toString(), cellFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPTable.addCell(cell);
            }

            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(pdfPTable);
            document.close();

        } catch (DocumentException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }

        return new ByteArrayInputStream(baos.toByteArray());
    }




}
