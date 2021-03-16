<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <base href="http://localhost:8080/ImportClient/">
<title>Search Client</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <style><%@ include file="../css/search.css" %></style>
</head>
<body>
	<div id="navbar">
		<jsp:include page="../Parts/header.jsp" />
	</div>
	<div id=buttonSearch>
	
	<button class="btn btn-primary" onclick="ToggleForm('form')">Search By ID</button>
	<button class="btn btn-primary" onclick="ToggleForm('form1')">Search By Name</button>
	<button class="btn btn-primary" onclick="ToggleForm('form2')">Search By Date Of Birth</button>
	<button class="btn btn-primary" onclick="ToggleForm('form3')">Search By ID, Name, DOB</button>
	
    	<button class="btn btn-primary" onclick="location.href='setup'" >Setup</button>

</div>

<div id="formSearch">
		<form action="searchClient" method="GET" style="display: none;" id="form">
			<div class="form-group">
			<label for="ClientID">Input client ID</label>
				<input type="text"class="form-control" name="ClientID" id="ClientID" required>
			</div>
			<button type="submit" class="btn btn-default">Search</button>
		</form>
		<form action="searchClient" method="GET" style="display: none;" id="form1" >
			<div class="form-group">
			<label for="FirstName">Input first name</label>
				<input type="text"class="form-control" name="FirstName"id="FirstName" required>
			</div>
			<div class="form-group">
			<label for="LastName">Input last name</label>
				<input type="text"class="form-control" name="LastName" id="LastName" required>
			</div>
			<button type="submit" class="btn btn-default">Search</button>
		</form>
		<form action="searchClient" method="GET" style="display: none;" id="form2">
			<div class="form-group">
			<label for="ClientDOB">Input date of birth</label>
				 <input type="date"class="form-control" name="ClientDOB"id="ClientDOB" required>
			</div>
			<button type="submit" class="btn btn-default">Search</button>
		</form>
		<form action="searchClient" method="GET" style="display: none;" id="form3">
			<div class="form-group">
			<label for="ClientID">Input client ID</label>
				<input type="text"class="form-control" name="ClientID" id="ClientID" required>
			</div>
			<div class="form-group">
			<label for="FirstName">Input first name</label>
				<input type="text"class="form-control" id="FirstName" name="FirstName"required>
			</div>
			<div class="form-group">
			<label for="LastName">Input last name</label>
				<input type="text"class="form-control" id="LastName" name="LastName" required>
			</div>
			<div class="form-group">
			<label for="ClientDOB">Input date of birth</label>
				 <input type="date"class="form-control" id="ClientDOB" name="ClientDOB" required>
			</div>
			<button type="submit" class="btn btn-default">Search</button>
		</form>
		</div>
		
	<div id="result">
<p> ${message}</p>
<br>
		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Gender</th>
					<th>Martial Status</th>
					<th>Date Of Birth</th>
					<th>Address</th>
					<th>Country</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="Client" items="${listClient}">
					<tr>
						<td><p>
								<c:out value="${Client.clientId}" />
							</p></td>
						<td><p>
								<c:out value="${Client.firstName}" />
							</p></td>
						<td><p>
								<c:out value="${Client.lastName}" />
							</p></td>
						<td><p>
								<c:out value="${Client.gender}" />
							</p></td>
						<td><c:out value="${Client.martialStatus}" /></td>
						<td><p>
								<c:out value="${Client.dob}" />
							</p></td>
						<td><p>
								<c:out value="${Client.address}" />
							</p></td>
						<td><p>
								<c:out value="${Client.country}" />
							</p></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div id= "pageNumber" align="center">
			<table border="1" >
				<tr>
					<c:forEach begin="1" end="${noOfPages}" var="i">
						<c:choose>
							<c:when test="${currentPage eq i}">
								<td>${i}</td>
							</c:when>
							<c:otherwise>
								<td><a href="searchClient?page=${i}">${i}</a></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
			</table>
		</div>
	</div>
	<div id="footer">
		<jsp:include page="../Parts/footer.jsp" />
	</div>
	<script type="text/javascript">
    function ToggleForm(inputID) {
    		if(inputID=='form' && document.getElementById(inputID).style.display == 'none')
    			{
    			document.getElementById('form1').style.display = 'none';
    			document.getElementById('form2').style.display = 'none';
    			document.getElementById('form3').style.display = 'none'
    			}
    		if(inputID=='form1'&& document.getElementById(inputID).style.display == 'none')
			{
			document.getElementById('form').style.display = 'none';
			document.getElementById('form2').style.display = 'none';
			document.getElementById('form3').style.display = 'none'
			}
    		if(inputID=='form2'&& document.getElementById(inputID).style.display == 'none')
			{
			document.getElementById('form1').style.display = 'none';
			document.getElementById('form').style.display = 'none';
			document.getElementById('form3').style.display = 'none'
			}
    		if(inputID=='form3'&& document.getElementById(inputID).style.display == 'none')
			{
			document.getElementById('form1').style.display = 'none';
			document.getElementById('form2').style.display = 'none';
			document.getElementById('form').style.display = 'none'
			}
    	   if(document.getElementById(inputID).style.display == 'none')
    	   {
    		   
    		   document.getElementById(inputID).style.display = 'block'; 
    	      
    	   }
    	   else
    	   {
    		   document.getElementById(inputID).style.display = 'none';
    	      
    	   }
    	   
       
    }
   
</script>
</body>
</html>