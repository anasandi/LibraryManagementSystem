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
	<h1 class="page-header" align="center">Return Books History</h1>
	<div class="container">
		<div align="center" style="margin-bottom: 2em;">
			<table id="returnListTable" class="table table-striped table-bordered" style="width:100%">
				<thead class="thead-dark">
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Returned User</th>
						<th>Returned Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${returnList}">
						<tr>
							<td><c:out value="${book.id}" /></td>
							<td><c:out value="${book.title}" /></td>
							<td><c:out value="${book.username}" /></td>
							<td><c:out value="${book.returnedDate}" /></td>
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

		$(document)
				.ready(
						function() {

							$("li#userListNav").removeClass('active');
							$("li#bookListNav").removeClass('active');
							$("li#borrowListNav").removeClass('active');
							$("li#homeNav").removeClass('active');
							$("li#homeNav").addClass('disabled');
							$('#homeNav').find("a").click(function () {return false;});
							
							$("li#register").css('display', 'none');
							//set active
							$("li#returnListNav").addClass('active');
							
							//Disable search box
							$('#returnListTable').DataTable();

							$('#returnListTable_length').attr('align', 'left');
							$('#returnListTable_info').attr('align', 'left');
							
						});
	</script>
</body>
</html>