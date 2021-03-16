<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Setup</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<div id="navbar">
	<jsp:include page="../Parts/header.jsp" />
	</div>
	<div id="result">

		<table class="table">
			<thead>
				<tr>
					<th>Client ID</th>
					<th>Client First Name</th>
					<th>Client Last Name</th>
					<th>Client Gender</th>
					<th>Client Martial Status</th>
					<th>Client Date Of Birth</th>
					<th>Client Address</th>
					<th>Client Country</th>
				</tr>
			</thead>

			
		</table>
		</div>
	<div id="footer">
	<jsp:include page="../Parts/footer.jsp" />
	</div>
</body>
</html>