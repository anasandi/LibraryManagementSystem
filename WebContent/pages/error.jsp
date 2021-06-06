<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>
</head>
<body>
<%@ include file="nav_goback.jsp"%>
	<div class="container">
		<h4 class="page-header">Something Occured!</h4>
		<p>
			<%= exception  %>
		</p>
	</div>

	<%@ include file="footer.jsp"%>
	<script type = "text/javascript">
	function goBack(){
		window.history.back();
	}
	</script>
</body>
</html>