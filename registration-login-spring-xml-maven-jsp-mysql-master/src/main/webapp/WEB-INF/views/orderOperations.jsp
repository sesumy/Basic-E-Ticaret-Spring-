<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<link href="${contextPath}/resources/css/style.css" rel="stylesheet">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<title>Insert title here</title>
</head>
<body>

<section>
		<div class="jumbotron">
			<div class="container">
				<h1>ORDERS</h1>
				<p>All ORDERS</p>
			</div>
		</div>
</section>

<form:form  action="${contextPath}/addOrder" commandName="order" class="form-horizontal"  method="POST" >
	<form:select class="form-control" path="user" items="${users}"  itemValue="id"/>	
	<form:select class="form-control" path="product" items="${products}" itemValue="id" />
	<button class="button"  type="submit" style="    position: relative; left: 36%;  top: 8px;"> ADD ORDER </button>
</form:form>

	<div style="padding-right: 40px">
			<div class="table-title">
				<h3>ORDER OPERATIONS</h3>
			</div>
			<table class="table-fill">
				<thead>
					<tr>
						<th class="text-left">ID</th>
						<th class="text-left">DATE</th>
						<th class="text-left">STATE</th>
						<th class="text-left">USER</th>
						<th class="text-left">PRODUCT</th>
						<th class="text-left">DELETE</th>
						<th class="text-left">STATUS UPDATE</th>
					</tr>
				</thead>
				<tbody class="table-hover">
					<c:forEach items="${orders}" var="order">
						<tr>
							<td class="text-left">${order.id}</td>
							<td class="text-left">${order.date}</td>
							<td class="text-left">${order.state}</td>
							<td class="text-left">${order.users}</td>				
							<td class="text-left">${order.products}</td>
							<td class="text-left">
								<form method="GET" action="${contextPath}/deleteOrder/${order.id}">
									<input type="submit" value="DELETE" />
								</form>
							</td>
							<td class="text-left">
								<form method="GET" action="${contextPath}/updateOrder/${order.id}">
									<input type="submit" value="STATUS UPDATE" />
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
<%-- 	</c:if> --%>

</body>
</html>