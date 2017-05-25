<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet"
            href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
        <script src="/resource/js/controllers.js"></script>
        <title>Products</title>
    </head>
    <body>
        <section>
            <div class= "jumbotron">
                <div class = "container">
                    <h1>Products</h1>
                </div>
            </div>
        </section>
        <section class = "container" >
            <div class = "row">
                <div class = "col-md-5">
				    <img src="resources/img/${product.id}.png" alt="image" style = "width:251px ; height:152px"/>
                    <h3>${product.name}</h3>
                    <p>${product.description}</p>
                    <p><strong>Item Code: </strong><span class="label label-warning">${product.id}</span></p>
                    <p><strong>Price</strong> : ${product.price} TL </p>
                    <p><strong>Stock</strong> : ${product.stock}</p>
                </div>
            </div>
         </section>
    </body>
</html>