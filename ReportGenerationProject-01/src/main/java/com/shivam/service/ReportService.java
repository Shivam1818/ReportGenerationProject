package com.shivam.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.shivam.entities.CitizenPlan;
import com.shivam.requst.SearchRequest;

public interface ReportService {
	
	public List<String> getPlanNames();
	
	public List<String> getPlanStatuses();
	
	public List<CitizenPlan> search(SearchRequest requst);
	
	public boolean exportExcel(HttpServletResponse response) throws Exception;
	
	public boolean exportPdf(HttpServletResponse response) throws Exception;

}
