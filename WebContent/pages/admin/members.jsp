<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<%@ include file="../header.jsp" %>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">
</head>
<body>
	<%@ include file="../nav_admin.jsp" %>
	<h1 class="page-header" align="center">Member Management</h1>
	<div class="container">
		<div align="center" style="margin-bottom: 2em;">
			<table id="userListTable" class="table table-striped table-bordered" style="width:100%">
				<thead class="thead-dark">
					<tr>
						<th>ID</th>
						<th>User Name</th>
						<th>Gender</th>
						<th>Birthday</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${userList}">
						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.username}" /></td>
							<td><c:out value="${user.gender}" /></td>
							<td><c:out value="${user.birthDate}" /></td>
							<td><a href="${pageContext.request.contextPath}/admin/deleteMember?id=<c:out value='${user.id}' />" onclick="return confirm('Are you to delete this member?')" class="btn btn-primary" role="button" style="padding: .1rem .75rem;">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<%@ include file="../footer.jsp" %>  
	<script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js"></script>
	
	<script type="text/javascript">
		function goToAddUser(){
			window.location.href = "${pageContext.request.contextPath}/pages/admin/memberForm.jsp";
		}

		$(document)
				.ready(
						function() {

							$("li#homeNav").removeClass('active');
							$("li#homeNav").addClass('disabled');
							$('#homeNav').find("a").click(function () {return false;});
							
							$("li#register").css('display', 'none');
							
							//set active
							$("li#userListNav").addClass('active');
							
							//Disable search box
							$('#userListTable').DataTable({
								searching : false
							});

							$('#userListTable_length').attr('align', 'left');
							$('#userListTable_info').attr('align', 'left');
							var addButton = $('<input type="button" onclick="goToAddUser()" style="border-radius: 4px; color: white; margin: 0 2em 0 0; background-color: #212529; border: 0; padding: 0.5em;" value="Add user"/>');
							$("#userListTable_length").prepend(addButton);
							
						});
	</script>
</body>
</html>