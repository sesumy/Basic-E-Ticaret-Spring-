<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport"content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    
<style>

</style>    
<title>Product Operations Page</title>
    
</head>

<body>

<section>
	<div class="jumbotron">
		<div class="container">
			<h1>Update Product</h1>
			<p>One Update Product</p>
		</div>
	</div>
	</section>


	<div class="container">
		<section class="container">
			<form:form modelAttribute="product" action="${contextPath}/updateProduct?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal" enctype="multipart/form-data" method="POST">

				<spring:bind path="name">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="name" class="form-control"
							placeholder="Name" autofocus="true"></form:input>
						<form:errors path="name"></form:errors>
					</div>
				</spring:bind>

				<spring:bind path="description">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="description" class="form-control"
							placeholder="Description"></form:input>
						<form:errors path="description"></form:errors>
					</div>
				</spring:bind>

				<spring:bind path="price">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="number" path="price" class="form-control"
							placeholder="Price"></form:input>
						<form:errors path="price"></form:errors>
					</div>
				</spring:bind>

				<spring:bind path="stock">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="stock" class="form-control"
							placeholder="Stock"></form:input>
						<form:errors path="stock"></form:errors>
					</div>
				</spring:bind>
				
				<spring:bind path="image">
					<div class="form-group">			
						<input name="image" type="file" class="form-control" />
						<form:errors path="image"></form:errors>
						
					</div>
				</spring:bind>
				
			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
			</form:form>
		</section>

	</div>




<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>