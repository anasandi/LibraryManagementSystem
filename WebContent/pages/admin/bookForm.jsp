<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<%@ include file="../header.jsp"%>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.standalone.min.css"
	rel="stylesheet" />
</head>
<body>
	<%@ include file="../nav_admin.jsp"%>
	<h1 class="page-header" align="center">Add Book</h1>
	<div class="container">
		<div class="registration-form">
			<c:if test="${book != null}">
				<form id="book-form" action="${pageContext.request.contextPath}/admin/editBook?id=${book.id}" method="POST"
				novalidate>
			</c:if>
			
			<c:if test="${book == null}">
				<form id="book-form" action="${pageContext.request.contextPath}/admin/addBook" method="POST"
				novalidate>
			</c:if>
			
				<div class="row form-group">
					<div class="col-md-3">
						<label for="title">Book Title</label>
					</div>
					<div class="col-md-9">
						<input type="text" class="form-control" id="title"
							name="title" placeholder="Enter Title" value="<c:out value='${book.title}' />" required>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-md-3">
						<label for="author">Book Author</label>
					</div>
					<div class="col-md-9">
						<input type="text" class="form-control" id="author"
							name="author" placeholder="Enter Author" value="<c:out value='${book.author}' />">
					</div>
				</div>

				<div class="row form-group">
					<div class="col-md-3">
						<label for="isbn">ISBN</label>
					</div>
					<div class="col-md-9">
						<input type="text" class="form-control" id="isbn"
							name="isbn" placeholder="Enter ISBN" value="<c:out value='${book.ISBN}' />">
					</div>
				</div>

				<div class="row form-group">
					<div class="col-md-3">
						<label for="publishedDate">Published Date</label>
					</div>
					<div class="col-md-9">
						<div class='date' id='datetimepicker'>
							<span class="input-group-addon calendar"> 
							<i class="fa fa-calendar" aria-hidden="true"></i>
							</span><input type='text' name="publishedDate" class="form-control birthdate" value="<c:out value='${book.publishedDate}' />"/> 
						</div>
					</div>
				</div>

				<div align="center">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</form>
		</div>

	</div>
	<%@ include file="../footer.jsp"%>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
	<script type="text/javascript">

		$(document).ready(function() {
			$('#datetimepicker').datepicker({
				format : "dd-mm-yyyy",
				autoclose : true,
				endDate: new Date()
			});

			$("li#userListNav").removeClass('active');
			$("li#homeNav").removeClass('active');
			$("li#homeNav").addClass('disabled');
			$('#homeNav').find("a").click(function() {
				return false;
			});
			$("li#register").css('display', 'none');

			//set active
			$("li#bookListNav").addClass('active');
			
			//Form Validation
			$('#book-form').validate({ // initialize the plugin
		        rules: {
		            title: {
		                required: true
		            },
		            author: {
		            	required: true
		            },
		            isbn:{
		            	required: true
		            },
		            publishedDate: {
		            	required: true
		            }
		        },
		        messages: {
		        	title: "Please enter book's title",
		        	author: "Please enter book's author",
		        	isbn: "Please enter book's ISBN",
		        	publishedDate: "Please enter book's publishedDate"
		        },
		        submitHandler: function(form) {
		            form.submit();
		         }
		    });

		});
	</script>
</body>
</html>