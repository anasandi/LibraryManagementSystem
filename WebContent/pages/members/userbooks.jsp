<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<%@ include file="../header.jsp" %>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">
<style>
#btnDisabled[disabled]
{
   cursor:not-allowed;
   background-color:#ddd;
   background:#ddd;
   pointer-events:none;
   border:2px outset ButtonFace;
   color: #212529;
}

</style>
</head>
<body>
	<%@ include file="../nav_member.jsp" %>
	<h1 class="page-header" align="center">Book List</h1>
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
						<tr id="table1">
							<td><c:out value="${book.id}" /></td>
							<td><c:out value="${book.title}" /></td>
							<td><c:out value="${book.author}" /></td>
							<td><c:out value="${book.ISBN}" /></td>
							<td><c:out value="${book.publishedDate}" /></td>
							<td>
							<c:if test="${book.status == 'ACTIVE'}">
								<a href="${pageContext.request.contextPath}/members/borrow?id=<c:out value='${book.id}' />" class="btn btn-primary" id="btnClickMe" role="button" >Borrow</a>	
							</c:if>
							<c:if test="${book.status == 'INACTIVE'}">
								<a href="${pageContext.request.contextPath}/members/borrow />" class="btn btn-primary" id="btnDisabled" role="button" disabled=true>Borrow</a>	
							</c:if>
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
    
     	$(document)
		.ready(
				function() {

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
					
				});
       
     </script>
	
	
</body>
</html>