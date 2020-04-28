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
	  <%@include file="header.jsp" %>
	</nav>
	
	<div class="jumbotron">
	  <div class="container text-center">
		<div class="row">
			
			<form:form cssClass="form-horizontal" method="post" action="${base_url}/department/service" modelAttribute="Department">
				<input type="hidden" name="encrypted_dept_no" id="encrypted_dept_no" value="0">
				<input type="hidden" name="dept_no" id="dept_no" value="0">
				<input type="hidden" name="mode" id="mode" value="save">
			  <div class="form-group">
			    <label class="control-label col-sm-2" for="dept_name">Department Name:</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="dept_name" id="dept_name" placeholder="Enter Department Name">
			    </div>
			  </div>
			  <div class="form-group"> 
			    <div class="col-sm-offset-2 col-sm-10 col-md-8">
			      <button type="button" class="btn btn-primary btn_reset">Reset</button>
			      <button type="submit" class="btn btn-primary btn_submit">Save</button>
			    </div>
			  </div>
			</form:form>
		</div>
		<div class="row">
			<table class="table table-striped" style="text-align:center">
				<thead>
					<tr>
						<th class="text-center">Action</th>
						<th class="text-center">Sl. No.</th>
						<th class="text-center">Department ID</th>
						<th class="text-center">Department Name</th>
					</tr>
				</thead>
				<tbody>
					<% int sl_no=0; %>
					<c:forEach items="${depts}" var="dept">
						<% sl_no+=1; %>
						<tr>
							<td><button class="btn btn-info edit">Edit</button>  <button class="btn btn-danger delete">Delete</button></td>
							<td class="hidden encrypted_dno">${dept.getEncryptedDno()}</td>
							<td><%=sl_no %></td>
							<td class="dno">${dept.getDNo()}</td>
							<td class="dname">${dept.getDName()}</td>
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
			    var encrypted_dept_no = $(this).parent().parent().find(".encrypted_dno").html();
				var dno = $(this).parent().parent().find(".dno").html();
				var dname = $(this).parent().parent().find(".dname").html();
				$("#encrypted_dept_no").val(encrypted_dept_no);
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
