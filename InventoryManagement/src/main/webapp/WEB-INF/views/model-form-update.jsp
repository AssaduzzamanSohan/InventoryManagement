<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Model</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

<script>
	function validateForm() {
		if(!document.getElementById("modelName").value)
		{
			alert("Model name can not be empty");
			return false;
		}
		if(document.getElementById("modelName").value.length > 50){
			alert("Brand name can not be more than 100 character");
			return false;
		}
	}
</script>

</head>
<body>
	<div class="container">
		<div class="col-md-offset-2 col-md-7">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Edit Model</div>
				</div>
				<div class="panel-body">
					<h5 class="text-left">Fields marked with * are mandatory</h5>
					<form:form action="saveModel" cssClass="form-horizontal" method="post" modelAttribute="models"
					onsubmit="return validateForm()">

						<!-- need to associate this data with Model id -->
						<form:hidden path="id" />
						<form:hidden path="entryDate" />
				        
				        <div class="form-group">
							<label for="brandId" class="col-md-3 control-label">Brand *</label>
							<div class="col-md-9">
								<select name="modelBrandId">
						            <c:forEach items="${models.brandList}" var="brand">
						                <option value="${brand.id}"
						                    <c:if test="${brand.id == brandId}">selected="selected"</c:if>>
						                    ${brand.brandName}
						                </option>
						            </c:forEach>
						        </select>
							</div>
						</div>
						<div class="form-group">
							<label for="modelName" class="col-md-3 control-label">Model Name*</label>
							<div class="col-md-9">
								<form:input path="modelName" cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<!-- Button -->
							<div class="col-md-offset-3 col-md-9">
								<form:button cssClass="btn btn-primary">Update</form:button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>