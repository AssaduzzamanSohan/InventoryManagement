<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Home</title>
		
		<link href="<c:url value="/resources/css/bootstrap.min.css" />"rel="stylesheet">
		<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	</head>
	<body>
		<div class="container">
			<div class="col-md-offset-1 col-md-10">
				<a href="<c:url value="/brandHome"/>">Brand</a>
			</div>
			<div class="col-md-offset-1 col-md-10">
				<a href="<c:url value="/modelHome"/>">Model</a>
			</div>
			<div class="col-md-offset-1 col-md-10">
				<a href="<c:url value="/itemsHome"/>">Item</a>
			</div>
		</div>
	</body>
</html>