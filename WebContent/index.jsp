<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="pages/header.jsp" %>  
</head>
<body>
	<%@ include file="pages/nav_admin.jsp" %>
	<div class="bg-image"></div>
	<div class="container">

		<div class="row" align="center">
			<div class="col-md-6 login-box bg-text">
				<h3>Login Form</h3>
				<p style="color: red;"><c:out value="${errorMessage}" /></p>
				
				<form action="login" method="post" style="width: 300px; margin: auto">
					<div class="form-group">
						<input type="text"
							class="form-control" name="username" id="username" placeholder="Username" required="required"/>
					</div>
					<div class="form-group">
						<input type="password"
							class="form-control" name="password" id="password"
							placeholder="Password" required="required"/>
					</div>
					<button type="submit" class="btn btn-primary">Login</button>
				    
					
				</form>

			</div>
		</div>
	</div>

	<%@ include file="pages/footer.jsp" %> 
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$("li.nav-item > a").addClass('disabled');
							$(".nav-register").removeClass('disabled');
							$('.disabled').click(function () {return false;});
						});
	</script>
</body>
</html>