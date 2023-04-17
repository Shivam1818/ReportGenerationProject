package com.shivam.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.shivam.entities.CitizenPlan;


@Component
public class ExcelGenerator {

	

	public void generate(HttpServletResponse response,List<CitizenPlan> records,File file) throws Exception {

		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("data_sheet");
		Row headrow = sheet.createRow(0);

		headrow.createCell(0).setCellValue("Id");
		headrow.createCell(1).setCellValue("Holder Name");
		headrow.createCell(2).setCellValue("Plan Name");
		headrow.createCell(3).setCellValue(" Plan Status");
		headrow.createCell(4).setCellValue(" Plan Start Date");
		headrow.createCell(5).setCellValue(" Plan End Date");
		headrow.createCell(6).setCellValue(" Benefit Amt");

	
		int dataRowIndex = 1;
		for (CitizenPlan plan : records) {
			Row row = sheet.createRow(dataRowIndex);
			row.createCell(0).setCellValue(plan.getCitizenId());
			row.createCell(1).setCellValue(plan.getCitizenName());
			row.createCell(2).setCellValue(plan.getPlanName());
			row.createCell(3).setCellValue(plan.getPlanStatus());
			if (null != plan.getPlanStartDate()) {
				
				row.createCell(4).setCellValue(plan.getPlanStartDate() + "");
			} else {
				row.createCell(4).setCellValue("N/A");
			}
			if (null != plan.getPlanEndDate()) {
				row.createCell(5).setCellValue(plan.getPlanEndDate() + "");
			} else {
				row.createCell(5).setCellValue("N/A");
			}

			if (null != plan.getBenefitAmt()) {
				row.createCell(6).setCellValue(plan.getBenefitAmt());
			} else {
				row.createCell(6).setCellValue("N/A");
			}
			dataRowIndex++;
		}

		
		  FileOutputStream fos= new FileOutputStream(file);
		  workbook.write(fos); workbook.close();
		  fos.close();
		 

		ServletOutputStream output = response.getOutputStream();
		workbook.write(output);
		workbook.close();

	}

}
