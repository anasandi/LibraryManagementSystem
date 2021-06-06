<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../header.jsp"%>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.standalone.min.css"
	rel="stylesheet" />
</head>
<body>
   <%@ include file="../nav_member.jsp"%>
	<h1 class="page-header" align="center">Register Member</h1>
	<div class="container">
		<div class="registration-form">
			<form id="reg-form" action="${pageContext.request.contextPath}/RegisterMemberServlet" method="POST"
				novalidate>
				<div class="row form-group">
					<div class="col-md-3">
						<label for="username">User Name</label>
					</div>
					<div class="col-md-9">
						<input type="text" class="form-control" id="username"
							name="username" placeholder="Enter username" required>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-md-3">
						<label for="password">Password</label>
					</div>
					<div class="col-md-9">
						<input type="password" class="form-control" id="password"
							name="password" placeholder="Password">
					</div>
				</div>

				<div class="row form-group">
					<div class="col-md-3">
						<label for="confirm-password">Confirm Password</label>
					</div>
					<div class="col-md-9">
						<input type="password" class="form-control" id="confirm_assword"
							name="confirm_password" placeholder="Confirm Password">
					</div>
				</div>

				<div class="row form-group">
					<div class="col-md-3">
						<label for="gender">Gender</label>
					</div>
					<div class="col-md-9">
						<select id="gender" name="gender" class="form-control">
							<option value="">Choose...</option>
							<option value="F">Female</option>
							<option value="M">Male</option>
						</select>
					</div>
				</div>

				<div class="row form-group">
					<div class="col-md-3">
						<label for="birthday">Birthday</label>
					</div>
					<div class="col-md-9">
						<div class='date' id='datetimepicker2'>
							<span class="input-group-addon calendar"> 
							<i class="fa fa-calendar" aria-hidden="true"></i>
							</span><input type='text' name="birthdate" class="form-control birthdate" /> 
						</div>
					</div>
				</div>

				<div align="center">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</form>
		</div>

	</div>
	<%@ include file="../footer.jsp" %>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
			$('#datetimepicker2').datepicker({
				format : "dd-mm-yyyy",
				autoclose : true,
				endDate: new Date()
			});

			$("li#homeNav").removeClass('active');
			
			//Form Validation
			$('#reg-form').validate({ // initialize the plugin
		        rules: {
		            username: {
		                required: true
		            },
		            password: {
		            	required: true,
		            	minlength: 5
		            },
		            confirm_password:{
		            	required: true,
		            	minlength: 5,
		            	equalTo: "#password"
		            },
		            gender: {
		            	required: true
		            },
		            birthdate: {
		            	required: true
		            }
		        },
		        messages: {
		        	username: "Please enter username",
		        	password: {
		        		required: "Please provide password",
		        		minlength: "Password must be at least 5 characters long"
		        	},
		        	confirm_password: {
		        		required: "Please provide password again",
		        		equalTo: "Password not match"
		        	},
		        	gender: {
		        		required: "Please select gender"
		        	},
		        	birthdate: {
		        		required: "Please provide birthdate"
		        	}	        	
		        },
		        submitHandler: function(form) {
		            form.submit();
		         }
		    });

		});
	</script>
</body>
</html>