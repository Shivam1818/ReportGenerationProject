<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <!DOCTYPE html>
 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link 
    	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" 
    	rel="stylesheet" 
    	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" 
    	crossorigin="anonymous">
  </head>
  <body>
  <div class="container">
  	<h3 class="pt-3 pb-3">Form Application</h3>
  
    <form:form action="search" modelAttribute="search" Method="POST">
    <table>
    
    	<tr >
    		<td>Plan Name:</td> 
    		<td><form:select path="planName">
    				<form:option value="">-select-</form:option>
    				<form:options items="${names}"/>
    				</form:select>    		
    		</td>  	
    	 	
    		<td>Plan Status:</td> 
    		<td><form:select path="planStatus">
    				<form:option value="">-select-</form:option>
    				<form:options items="${status}"/>
    				</form:select>    		
    		</td> 
    		
    		<td> Gender:</td>
    		<td><form:select path="gender">
    				<form:option value="">-select-</form:option>
    				<form:option value="Male">Male</form:option>
    				<form:option value="Fe-Male">Fe-Male</form:option>
    			</form:select>    		
    		</td> 
    	</tr>  
    	<tr>
    		<td>Start Date:</td>
    		<td><form:input type="date" path="startDate"/></td>
    	
    		<td>End Date:</td>
    		<td><form:input type="date" path="endDate"/></td>
    	</tr>
    	
    </table>
    <a href="/" class="btn btn-info">Reset</a>
   	<input type="submit" value="search" class="  mt-6 btn btn-primary ">
    
    
    </form:form>
    <hr/>
    <table class="table table-striped">
    <thead>
    	<tr>
    		<th>S.No.</th>
    		<th>Holder Name</th>
    		<th>Plan Name</th>
    		<th>Gender</th>
    		<th>Plan Status</th>
    		<th>Start Date</th>
    		<th>End Date</th>
    		<th>Benefit Amt</th>
    	</tr> 
    </thead> 
    <tbody>
    	<c:forEach items="${plans}" var="plan" varStatus="index">
    	<tr>
    		<td>${index.count}</td>
    		<td>${plan.citizenName}</td>
    		<td>${plan.planName}</td>
    		<td>${plan.gender}</td>
    		<td>${plan.planStatus}</td>
    		<td>${plan.planStartDate}</td>
    		<td>${plan.planEndDate}</td>
    		<td>${plan.benefitAmt}</td>
    	 </tr>
    	</c:forEach>
    	<tr>
    	<td colspan="8" style="text-align:center">
	    	<c:if test="${empty plans}">
	    	no record found
    	</c:if>
    	</td>
    	</tr>
    </tbody>  	
    </table>
    <hr/>
    Export <a href="excel" class="btn btn-info">EXCEL</a> 
    	   <a href="pdf" class="btn btn-primary">PDF</a>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
  </body>
</html>