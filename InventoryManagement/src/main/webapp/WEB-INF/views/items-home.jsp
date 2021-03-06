<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Items</title>
		
		<link href="<c:url value="/resources/css/bootstrap.min.css" />"rel="stylesheet">
		<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	</head>
	<body>
		<div class="container">
			<div class="col-md-offset-1 col-md-10">
				<input type="button" value="Back To Home" onclick="window.location.href='home'; return false;" class="btn btn-primary" />
			</div>
		</div>
		<div class="container">
			<div class="col-md-offset-1 col-md-10">
				<hr />
				<br/><br/>
				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="panel-title">Item List</div>
					</div>
					<div class="panel-body">
						<table class="table table-striped table-bordered">
							<tr>
								<th>SL</th>
								<th>Item</th>
								<th>Model Name</th>
								<th>Brand Name</th>
								<th>Entry Date</th>
								<th>Action</th>
							</tr>
							
							<% int i = 1; %>
							
							<!-- loop over and print our itemList -->
							<c:forEach var="tempItem" items="${itemList}">
	
								<!-- construct an "update" link with customer id -->
								<c:url var="updateLink" value="/updateItem">
									<c:param name="id" value="${tempItem.id}" />
								</c:url>
	
								<!-- construct an "delete" link with customer id -->
								<c:url var="deleteLink" value="/deleteItem">
									<c:param name="id" value="${tempItem.id}" />
								</c:url>
	
								<tr>
									<td><%= i %> <% i++; %></td>
									<td>${tempItem.itemName}</td>
									<td>${tempItem.modelId}</td>
									<td>${tempItem.brandId}</td>
									<td>${tempItem.entryDate}</td>
									<td>
										<!-- display the update link --> 
										<a href="${updateLink}">Update</a>
										| <a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this Item?'))) return false">Delete</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<input type="button" value="Add Item" onclick="window.location.href='showItemsForm'; return false;" class="btn btn-primary" />
				<br/><br/>
			</div>
		</div>
	</body>
</html>