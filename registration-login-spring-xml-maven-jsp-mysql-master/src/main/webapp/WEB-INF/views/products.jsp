<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
	
<title>Products</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Products</h1>
				<p>All Products List</p>
			</div>
		</div>
	</section>
	<button class="button"  onclick="window.location.href='${contextPath}/addProduct'">ADD PRODUCT</button>
     
	<section class="container">
		<div class="row">
			<c:forEach items="${products}" var="product">
			
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
					<div class="thumbnail">		
		
				    <img src="resources/img/${product.id}.png" alt="image" style = "width:251px ; height:152px"/>
						<div class="caption">
							<h3>${product.name}</h3>
							<p>${product.description}</p>
							<p>${product.price} TL</p>
                            <p>Available ${product.stock} units in stock</p>
                            <p><a href="<spring:url value= "/products/product/${product.id}" /> " class="btn btn-primary"><span class="glyphicon-info-sign glyphicon"/></span> Details </a></p>
							<p><a href="<spring:url value= "/updateProduct/${product.id}" /> " class="btn btn-primary"><span class="glyphicon glyphicon-circle-arrow-up"/></span> Update </a></p>
							<p><a href="<spring:url value= "/deleteProduct/${product.id}" /> " class="btn btn-primary"><span class="glyphicon glyphicon-trash"/></span> Delete </a></p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</body>
</html>
