package com.yelman.cloudserver.utils.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.yelman.cloudserver.api.dto.PdfSensorDto;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class PdfGenerator {
    public byte[] export(List<PdfSensorDto> dtoList) throws  DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, baos);
        document.open();
        Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Paragraph paragraph = new Paragraph("Weekly Health Report", fontTitle);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        document.add(new Paragraph(" "));
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        addTableHeader(table, "Tag ID");
        addTableHeader(table, "Date");
        addTableHeader(table, "Chewing");
        addTableHeader(table, "Temperature");
        addTableHeader(table, "pulse");
        addTableHeader(table, "Risk Score");
        for (PdfSensorDto dto : dtoList) {
            table.addCell(dto.getTag_id());
            table.addCell(String.valueOf(dto.getDate()));
            table.addCell(String.valueOf(dto.getChewingActivity()));
            table.addCell(String.valueOf(dto.getTemperature()));
            table.addCell(String.valueOf(dto.getHeartBeat()));
            table.addCell(String.valueOf(dto.getRiskScore()));
        }

        document.add(table);
        document.close();
        return baos.toByteArray();
    }

    private void addTableHeader(PdfPTable table, String headerTitle) {
        PdfPCell header = new PdfPCell();
        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
        header.setBorderWidth(1);
        header.setPhrase(new Phrase(headerTitle));
        table.addCell(header);
    }
}

