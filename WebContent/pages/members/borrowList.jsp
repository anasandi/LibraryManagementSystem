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
	<h1 class="page-header" align="center">Borrow Book List</h1>
	<div class="container">
		<div align="center" style="margin-bottom: 2em;">
			<table id="borrowListTable" id="tableDiv" class="table table-striped table-bordered EventDetail"  style="width:100%">
				<thead class="thead-dark">
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>User Name</th>
						<th>Borrow Date</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="borrow" items="${borrowList}">
						<tr>
							<td><c:out value="${borrow.id}" /></td>
							<td><c:out value="${borrow.title}" /></td>
							<td><c:out value="${borrow.username}" /></td>
							<td><c:out value="${borrow.borrowedDate}" /></td>
							<td>
							<c:if test="${borrow.status == 1}">
								<a href="${pageContext.request.contextPath}/members/returnBook?id=<c:out value='${borrow.id}'/>" id="btnDisabled" class="btn btn-primary" role="button" style="padding: .1rem 1.3em; margin-right: 1.5em;" disabled=true>Return</a>
							</c:if>
							<c:if test="${borrow.status == 0}">
								<a href="${pageContext.request.contextPath}/members/returnBook?id=<c:out value='${borrow.id}'/>" class="btn btn-primary" role="button" style="padding: .1rem 1.3em;" >Return</a>
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
		$(document).ready(function() {

			$("li#bookListNav").removeClass('active');
			$("li#homeNav").removeClass('active');
			$("li#homeNav").addClass('disabled');
			$('#homeNav').find("a").click(function() {
				return false;
			});

			$("li#register").css('display', 'none');
			//set active
			$("li#userborrowListNav").addClass('active');
			
			//Disable search box
			$('#borrowListTable').DataTable({
				searching : false
			});

			$('#borrowListTable_length').attr('align', 'left');
			$('#borrowListTable_info').attr('align', 'left');

		});

		
	</script>
</body>
</html>