<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="Label"/>
<!DOCTYPE ">
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
					<th>Client Column</th>
					<th>Xml Tag Name</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="Map" items="${listMapping}">
					<tr>
						<td><p>
								<c:out value="${Map.clientColumn}" />
							</p></td>
						<td><p>
								<c:out value="${Map.xmlTagName}" />
							</p></td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
			
		</div>
		<button class="btn btn-primary" onclick="location.href='searchClient'"style="width:200px;text-align:center" >OK</button>
	<div id="footer">
	<jsp:include page="../Parts/footer.jsp" />
	</div>
</body>
</html>