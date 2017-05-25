<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<link href="${contextPath}/resources/css/style.css" rel="stylesheet">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Products</title>
</head>
<body>

<section>
		<div class="jumbotron">
			<div class="container">
				<h1>SEARCH</h1>
				<p>All MODEL SEARCH</p>
			</div>
		</div>
</section>

	<form:form method="GET" commandName="search"
		action="${contextPath}/searchOp">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<table style="position: relative; left: 39%; top: 174px;">
			<tr>
				<td>Search Item :</td>
				<td><form:input path="username" type="text" /></td>
				<td><form:errors path="username" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit"  style="width: 194px; height: 47px; position: relative; left: 100px;"/> SEARCH</td>
			</tr>
		</table>
	</form:form>

	<a>CUSTOMER SEARCH</a>

	<c:if test="${not empty customerSearch}">
		<table class="table-fill" style="position: relative; top: 195px;">
			<thead>
				<tr>
					<th class="text-left">CUSTOMER TABLE</th>

					<th class="text-left">ID</th>
					<th class="text-left">USERNAME</th>
					<th class="text-left">ADDRESS</th>
					<th class="text-left">DELETE</th>
					<th class="text-left">UPDATE</th>

				</tr>
			</thead>
			<tbody class="table-hover">
				<tr>

					<td class="text-left">CUSTOMER TABLE INFO</td>
					<td class="text-left">${customerSearch.id}</td>
					<td class="text-left">${customerSearch.username}</td>
					<td class="text-left">${customerSearch.address.toString()}</td>
					<td class="text-left"><form method="GET"
							action="${contextPath}/account/deleteCustomers/${customerSearch.id}">
							<input type="submit" value="DELETE" />
						</form></td>
					<td class="text-left">
						<form method="GET"
							action="${contextPath}/account/updateCustomers/${customerSearch.id}">
							<input type="submit" value="UPDATE" />
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</c:if>
	<a>PRODUCT SEARCH</a>

	<c:if test="${not empty productSearch}">
		<table class="table-fill" style="position: relative; top: 195px;">
			<thead>
				<tr>
					<th class="text-left">PRODUCT TABLE</th>
					<th class="text-left">ID</th>
					<th class="text-left">NAME</th>
					<th class="text-left">DESCRIPTION</th>
					<th class="text-left">PRICE</th>
					<th class="text-left">STOCK</th>
					<th class="text-left">DETAIL</th>
					<th class="text-left">UPDATE</th>
					<th class="text-left">DELETE</th>


				</tr>
			</thead>
			<tbody class="table-hover">
				<tr>

					<td class="text-left">PRODUCT TABLE INFO</th>
					<td class="text-left">${productSearch.id}</td>
					<td class="text-left">${productSearch.name}</td>
					<td class="text-left">${productSearch.description}</td>
					<td class="text-left">${productSearch.price}</td>
					<td class="text-left">${productSearch.stock}</td>


					<td class="text-left">
						<form method="GET" action="/products/product/${productSearch.id}">
							<input type="submit" value="DETAIL" />
						</form>
					</td>

					<td class="text-left">
						<form method="GET" action="/updateProduct/${productSearch.id}">
							<input type="submit" value="UPDATE" />
						</form>
					</td>

					<td class="text-left">
						<form method="GET" action="/deleteProduct/${productSearch.id}">
							<input type="submit" value="DELETE" />
						</form>
					</td>


				</tr>
			</tbody>
		</table>
	</c:if>

	<a>ORDER SEARCH</a>
	<c:if test="${not empty orderSearch}">
		<table class="table-fill" style="position: relative; top: 195px;">
			<thead>
				<tr>
					<th class="text-left">ORDER TABLE</th>
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
				<tr>
					<td class="text-left">ORDER TABLE INFO</td>
					<td class="text-left">${orderSearch.id}</td>
					<td class="text-left">${orderSearch.date}</td>
					<td class="text-left">${orderSearch.state}</td>
					<td class="text-left">${orderSearch.users}</td>
					<td class="text-left">${orderSearch.products}</td>
					<td class="text-left">
						<form method="GET"
							action="${contextPath}/deleteOrder/${orderSearch.id}">
							<input type="submit" value="DELETE" />
						</form>
					</td>
					<td class="text-left">
						<form method="GET"
							action="${contextPath}/updateOrder/${orderSearch.id}">
							<input type="submit" value="STATUS UPDATE" />
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</c:if>






</body>
</html>