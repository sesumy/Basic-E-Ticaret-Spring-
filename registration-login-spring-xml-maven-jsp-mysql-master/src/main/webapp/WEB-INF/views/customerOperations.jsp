<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<link href="${contextPath}/resources/css/style.css" rel="stylesheet">

<title>User Operations Page</title>
</head>


<body>
	<section>
	<div class="jumbotron">
		<div class="container">
			<h1>Customer</h1>
			<p>All Custumers</p>
		</div>
	</div>
	</section>
	<c:if test="${not empty userList}">
		<div style="padding-right: 40px">
			<div class="table-title">
				<h3>USER OPERATIONS</h3>
			</div>
			<table class="table-fill">
				<thead>
					<tr>
						<th class="text-left">ID</th>
						<th class="text-left">USERNAME</th>
						<th class="text-left">ADDRESS</th>
						<th class="text-left">DELETE</th>
						<th class="text-left">UPDATE</th>

					</tr>
				</thead>
				<tbody class="table-hover">
					<c:forEach items="${userList}" var="listValue">
						<tr>
							<td class="text-left">${listValue.id}</td>
							<td class="text-left">${listValue.username}</td>
							<td class="text-left">${listValue.address.toString()}</td>

							<td class="text-left">
								<form method="GET"
									action="${contextPath}/deleteCustomers/${listValue.id}">
									<input type="submit" value="DELETE" />
								</form>
							</td>
							<td class="text-left">
								<form method="GET"
									action="${contextPath}/updateCustomers/${listValue.id}">
									<input type="submit" value="UPDATE" />
								</form>
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</c:if>

</body>
</html>