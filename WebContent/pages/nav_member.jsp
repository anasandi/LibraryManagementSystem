<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarTogglerMenu" aria-controls="navbarTogglerMenu"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<a class="navbar-brand" href="#">Library</a>

	<div class="collapse navbar-collapse" id="navbarTogglerMenu">
		<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
			<li id="homeNav" class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Home
					<span class="sr-only">(current)</span>
			</a></li>
			<c:if test="${loginUser != null}">
				<li id="userborrowListNav" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/members/borrowList">BorrowList</a></li>
				<li id="bookListNav" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/members/userbooks" >Books</a></li>
			</c:if>
		</ul>
		<ul class="nav navbar-nav float-md-right">
		<c:if test="${loginUser != null}">
			<li id="logout" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">Log Out</a></li> 
		</c:if>
		</ul>
	</div>
</nav>