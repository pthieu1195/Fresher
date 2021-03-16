<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Import Client</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	 <style><%@ include file="../css/upload.css" %></style>
</head>

<body>
	<div id="navbar">
		<jsp:include page="../Parts/header.jsp" />
	</div>
	
	<div id="uploadFile">
	<p> ${message}</p>
	<form method="post" action="importClient" enctype="multipart/form-data">
    <input type="file" name="file" size="60" /><br /><br /> 
    <input type="submit" value="Upload" class="btn btn-primary" />
  </form>
	</div>
	<div id="footer">
		<jsp:include page="../Parts/footer.jsp" />
	</div>
</body>
</html>