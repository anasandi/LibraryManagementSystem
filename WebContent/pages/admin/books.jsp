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
	<h1 class="page-header" align="center">Book Management</h1>
	<div class="container">
		<div align="center" style="margin-bottom: 2em;">
			<table id="bookListTable" class="table table-striped table-bordered" style="width:100%">
				<thead class="thead-dark">
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Author</th>
						<th>ISBN</th>
						<th>Published Date</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${bookList}">
						<tr>
							<td><c:out value="${book.id}" /></td>
							<td><c:out value="${book.title}" /></td>
							<td><c:out value="${book.author}" /></td>
							<td><c:out value="${book.ISBN}" /></td>
							<td><c:out value="${book.publishedDate}" /></td>
							<td>
								<a href="${pageContext.request.contextPath}/admin/editBook?id=<c:out value='${book.id}' />" class="btn btn-primary" role="button" style="padding: .1rem 1.3em; margin-right: 1.5em;">Edit</a>
								<a href="${pageContext.request.contextPath}/admin/deleteBook?id=<c:out value='${book.id}' />" onclick="return confirm('Are you to delete this book?')" class="btn btn-primary" role="button" style="padding: .1rem .75rem;">Delete</a>
							</td>
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
		function goToAddBook(){
			window.location.href = "${pageContext.request.contextPath}/pages/admin/bookForm.jsp";
		}

		$(document)
				.ready(
						function() {

							$("li#userListNav").removeClass('active');
							$("li#homeNav").removeClass('active');
							$("li#homeNav").addClass('disabled');
							$('#homeNav').find("a").click(function () {return false;});
							
							$("li#register").css('display', 'none');
							//set active
							$("li#bookListNav").addClass('active');
							
							//Disable search box
							$('#bookListTable').DataTable();

							$('#bookListTable_length').attr('align', 'left');
							$('#bookListTable_info').attr('align', 'left');
							var addButton = $('<input type="button" onclick="goToAddBook()" style="border-radius: 4px; color: white; margin: 0 2em 0 0; background-color: #212529; border: 0; padding: 0.5em;" value="Add Book"/>');
							$("#bookListTable_length").prepend(addButton);
							
						});
	</script>
</body>
</html>