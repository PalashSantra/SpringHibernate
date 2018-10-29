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
		<div class="row">
			
		</div>
		<div class="row">
			<table class="table table-striped" style="text-align:center">
				<thead>
					<tr>
						<th class="text-center">Action</th>
						<th class="text-center">Sl. No.</th>
						<th class="text-center">Laptop ID</th>
						<th class="text-center">Laptop Name</th>
						<th class="text-center">Laptop Price</th>
					</tr>
				</thead>
				<tbody>
					<% int sl_no=0; %>
					<c:forEach items="${laps}" var="lap">
						<% sl_no+=1; %>
						<tr>
							<td><button class="btn btn-info edit">Edit</button>  <button class="btn btn-danger delete">Delete</button></td>
							<td><%=sl_no %></td>
							<td class="lap_id">${lap.getLId()}</td>
							<td class="lap_name">${lap.getName()}</td>
							<td class="lap_price">${lap.getPrice()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	  </div>
	</div>
	<script type="text/javascript" src="${base_url}/webjars/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="${base_url}/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".edit").click(function(){
				var dno = $(this).parent().parent().find(".dno").html();
				var dname = $(this).parent().parent().find(".dname").html();
				$("#dept_no").val(dno);
				$("#dept_name").val(dname);
				$("#mode").val("update");
				$(".btn_submit").text("Update");
			});
			$(".delete").click(function(){
				var dno = $(this).parent().parent().find(".dno").html();
				$.ajax({
				      type: 'POST',
				      url: "${base_url}/department/delete",
				      data: {
				    	  dept_no:dno
				      },
				      dataType: "text",
				      success: function(resultData) { 
				    	  window.location.replace("${base_url}/department"); 
				    	}
				});
			});
			$(".btn_reset").click(function(){
				$("#dept_no").val("0");
				$("#dept_name").val("");
				$("#mode").val("save");
				$(".btn_submit").text("Save");
			});
		});
	</script>
</body>
</html>
