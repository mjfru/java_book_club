<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/oneBookStyle.css"/>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Architects+Daughter&family=Homemade+Apple&display=swap" rel="stylesheet">
<title>Viewing <c:out value="${book.title}" /> </title>
</head>
<body>
	<div class="page-container">
		<div class="header">
			<div class="header-line">
				<h1><c:out value="${book.title}"/></h1>
				<a href="/books" class="btn btn-info">Back to The Shelves</a>
			</div>
		</div>
		<div class="content-container">
			<c:choose>
				<c:when test="${userId == book.user.id}">
					<h3><span class="name">You</span> read <span class="book"><c:out value="${book.title}"/></span> by <span class="author"><c:out value="${book.author}"/></span>.</h3>
					<h4>Here are your thoughts about it:</h4>
					<hr>
				</c:when>
				<c:otherwise>
					<h3><span class="name"><c:out value="${book.user.name}"/></span> read <span class="book"><c:out value="${book.title}"/></span> by <span class="author"><c:out value="${book.author}"/></span>.</h3>
					<h4>Here are <c:out value="${book.user.name}"/>'s thoughts about it: </h4>
					<hr>
				</c:otherwise>
			</c:choose>
				<p><c:out value="${book.thoughts}"/></p>
				<hr>
		</div>
		
		<c:choose>
			<c:when test="${userId == book.user.id}">
				<div class="buttons">
					<div>
						<a href="/book/edit/${book.id}" class="btn btn-secondary" id="edit-btn">Edit</a>
					</div>
					<form action="/book/delete/${book.id}" method="POST">
						<input type="hidden" name="_method" value="delete"/>
						<input type="submit" value="Delete" class="btn btn-danger" id="delete-btn"/> 
					</form>
				</div>
			</c:when>
			<c:otherwise>
			</c:otherwise>
			
		</c:choose>
	</div>
</body>
</html>