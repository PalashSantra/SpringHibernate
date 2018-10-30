<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="./webjars/bootstrap/3.3.7/css/bootstrap.min.css">
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }
  </style>
</head>
<body>
	<nav class="navbar navbar-inverse">
	  <%@include file="/view/header.jsp" %>
	</nav>
	
	<div class="jumbotron">
	  <div class="container text-center">
	    <h3>My Portfolio</h3>      
	    <p>Some text that represents "Me"...</p>
	  </div>
	</div>
	<script type="text/javascript" src="./webjars/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="./webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
