<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="${base_url}/webjars/bootstrap/3.3.7/css/bootstrap.min.css">
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
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
	      </button>
	      <a class="navbar-brand" href="#">Portfolio</a>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav">
	        <li class="active"><a href="#">Home</a></li>
	        <li><a href="#">Student</a></li>
	        <li><a href="#">Department</a></li>
	        <li><a href="#">Laptop</a></li>
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
	        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
	      </ul>
	    </div>
	  </div>
	</nav>
	
	<div class="jumbotron">
	  <div class="container text-center">
		<div class="panel panel-primary col-md-6 col-md-offset-3">
			<div class="panel-heading">User Registration</div>
  			<div class="panel-body">
  				<form:form id="userRegistration" method="POST" action="${base_url}/user/saveUser" modelAttribute="user">
	  				<div class="row">
	  					<div class="form-group col-md-12" id="">
							<form:label path="FullName">Full Name <font color='red'><form:errors path='FullName' /></font></form:label>
							<form:input path="FullName" class="form-control" placeholder="Enter ..."/>
						</div>
						<div class="form-group col-md-12" id="">
							<form:label path="Email">Email Address <font color='red'><form:errors path='Email' /></font></form:label>
							<form:input path="Email" class="form-control" placeholder="Enter ..."/>
						</div>
						<div class="form-group col-md-12" id="">
							<form:label path="UserName">User Name (For Login) <font color='red'><form:errors path='UserName' /></font></form:label>
							<form:input path="UserName" class="form-control" placeholder="Enter ..."/>
						</div>
						<div class="form-group col-md-12" id="">
							<form:label path="Password">Password (For Login) <font color='red'><form:errors path='Password' /></font></form:label>
							<form:input path="Password" class="form-control" placeholder="Enter ..."/>
						</div>
	  				</div>
	  				<div class="row">
	  					<div class="form-group col-md-6 col-md-offset-3">
							<br>
							<form:button id="save" name="save" class="btn btn-primary btn_submit col-md-12 text-center">Save</form:button>
						</div>
	  				</div>
				</form:form>
  			</div>
  			<div class="panel-footer">
  				Already registered? Login
  			</div>
		</div>
	  </div>
	</div>
	<script type="text/javascript" src="${base_url}/webjars/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="${base_url}/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>
