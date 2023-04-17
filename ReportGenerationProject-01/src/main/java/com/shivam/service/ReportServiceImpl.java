package com.shivam.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.shivam.entities.CitizenPlan;
import com.shivam.repo.CitizenRepo;
import com.shivam.requst.SearchRequest;
import com.shivam.utils.EmailUtils;
import com.shivam.utils.ExcelGenerator;
import com.shivam.utils.PdfGenerator;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private CitizenRepo repo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public List<String> getPlanNames() {
		
		return  repo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatuses() {
		
		return repo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest requst) {
		
		CitizenPlan entity=new CitizenPlan();
		if(null!=requst.getPlanName()&&!"".equals(requst.getPlanName())) {
			entity.setPlanName(requst.getPlanName());
		}
		if(null!=requst.getPlanStatus()&&!"".equals(requst.getPlanStatus())) {
			entity.setPlanStatus(requst.getPlanStatus());
		}
		if(null!=requst.getGender()&&!"".equals(requst.getGender())) {
			entity.setGender(requst.getGender());
		}
		if(null!=requst.getStartDate()&&!"".equals(requst.getStartDate())) {
			String endDate=requst.getStartDate();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localdate=LocalDate.parse(endDate, formatter);
			entity.setPlanStartDate(localdate);
		}
		if(null!=requst.getEndDate()&&!"".equals(requst.getEndDate())) {
			String endDate=requst.getEndDate();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localdate=LocalDate.parse(endDate, formatter);
			entity.setPlanEndDate(localdate);
		}
		
		return repo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception{
		
		File f=new File("data.xls");
		List<CitizenPlan> records = repo.findAll();
		excelGenerator.generate(response, records,f);
		
		String subject="Test Mail subject";
		String body="<h1>test mail body</h1>";
		String to="shivamc8262@gmail.com";
		
		
		emailUtils.sendEmail(subject,body,to,f);
		f.delete();
	
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception{
		File f=new File("data.pdf");
		List<CitizenPlan> records = repo.findAll();
		pdfGenerator.generate(response, records,f);
		
		String subject="Test Mail subject";
		String body="<h1>test mail body</h1>";
		String to="shivamc8262@gmail.com";
		
		emailUtils.sendEmail(subject, body, to, f);
		
		f.delete();
		return true;
	}

}
