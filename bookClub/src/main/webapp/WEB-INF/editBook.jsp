<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" href="/css/addEditStyle.css"/>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Architects+Daughter&family=Homemade+Apple&display=swap" rel="stylesheet">
<title>Books | Edit <c:out value="${book.title}" /></title>
</head>
<body>
	<div class="page-container">
		<div class="header">
			<div class="header-line">
				<h1>Edit <span><c:out value="${book.title}" /></span></h1>
				<a href="/books" class="btn btn-info">Back to the Shelves</a>
			</div>

		</div>
		<form:form action="/book/edit/${book.id}" method="PUT" modelAttribute="book">
			<form:input type="hidden" path="id" value="${book.id}"/>
			<form:input type="hidden" path="user.id" value="${book.user.id}"/>
			<div class="form">
				<div>
					<form:label path="title">Book Title:</form:label>
					<form:input type="text" path="title"/><br/>
					<form:errors path="title" class="errors"/>
				</div>
				<div>
					<form:label path="author">Author:</form:label>
					<form:input type="text" path="author"/><br/>
					<form:errors path="author" class="errors"/>
				</div>
				<div>
					<form:label path="thoughts">Your Thoughts:</form:label>
					<div>
						<form:textarea path="thoughts" rows="5" cols="45"></form:textarea>
					</div><br/>
					<form:errors path="thoughts" class="errors"/>
				</div>
				<input type="submit" value="Edit Book" class="btn btn-success" id="submit-btn"/>
			</div>
		</form:form>

	</div>
</body>
</html>