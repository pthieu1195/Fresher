<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="Label"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<div id="navbar">
	<jsp:include page="../Parts/header.jsp" />
	</div>
	
	<div class="container">

		<h3>User Info</h3>
		<img
			src="https://st2.depositphotos.com/1104517/11967/v/950/depositphotos_119675554-stock-illustration-male-avatar-profile-picture-vector.jpg"
			alt="avatar" width="100" height="100">
		<p>Name: Phan Trung Hieu</p>
		<p>Email : pthieu@gmail.com</p>
		<p>Address : Dong Nai</p>
		<p>Phone Number: 0346091668</p>
	</div>
	<div id="footer">
	<jsp:include page="../Parts/footer.jsp" />
	</div>
</body>
</html>