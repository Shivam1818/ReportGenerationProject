package com.shivam.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.shivam.entities.CitizenPlan;


@Component
public class PdfGenerator {

	

	public void generate(HttpServletResponse response,List<CitizenPlan> records,File f) throws Exception {
		// create document
		// create document
		Document doc = new Document(PageSize.A4);
		// add document to response output file stream
		PdfWriter.getInstance(doc, response.getOutputStream());
		PdfWriter.getInstance(doc,new FileOutputStream(f));
		doc.open();

		// create font to add style to paragraph
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(20);
		font.setColor(Color.BLUE);
		// create paragraph
		Paragraph p = new Paragraph("Citizen Pdf", font);

		p.setAlignment(p.ALIGN_CENTER);
		doc.add(p);

		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.0f, 2.5f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f });
		table.setSpacingBefore(20);
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.CYAN);
		cell.setPadding(2);

		Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
		font1.setColor(Color.RED);

		cell.setPhrase(new Phrase("ID", font1));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Name", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Name", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Status", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Start Date", font1));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Plan End Date", font1));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Plan Benefit Amount", font1));
		table.addCell(cell);

		

		for (CitizenPlan plan : records) {
			table.addCell(plan.getCitizenId() + "");
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			if (null != plan.getPlanStartDate()) {
				table.addCell(plan.getPlanStartDate() + "");
			} else {
				table.addCell("N/A");
			}
			if (null != plan.getPlanEndDate()) {
				table.addCell(plan.getPlanEndDate() + "");
			} else {
				table.addCell("N/A");
			}
			if (null != plan.getBenefitAmt()) {
				table.addCell(plan.getBenefitAmt() + "");
			} else {
				table.addCell("N/A");
			}

		}

		doc.add(table);
		doc.close();

		
	}

}
