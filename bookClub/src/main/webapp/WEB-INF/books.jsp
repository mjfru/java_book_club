<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/dashStyle.css"/>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Architects+Daughter&family=Homemade+Apple&display=swap" rel="stylesheet">
<title>Book Club | Home</title>
</head>
<body>
	<div class="page-container">
		<div class="header">
			<div class="header-line">
				<h1>Welcome, <span><c:out value="${user.name}"/></span> !</h1>
				<a href="/logout" class="btn btn-info">Log Out</a>
			</div>
			<div class="header-line">
				<h3>Books from Our Community's Shelves:</h3>
				<a href="/books/new" class="btn btn-success">+ Add a Book</a>
			</div>
		</div>
		<div class="table-container">
			<table class="table table-primary table-striped table-hover">
				<thead>
					<tr id="table-headings">
						<th>ID#</th>
						<th>Book Title</th>
						<th>Author</th>
						<th>Posted By</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${allBooks}">
						<tr>
							<td><c:out value="${book.id}"/></td>
							<td><a href="/book/${book.id}"><c:out value="${book.title}"/></a></td>
							<td> <c:out value="${book.author}"/> </td>
							<td> <c:out  value="${book.user.name}" /> </td>
						</tr>
					</c:forEach>
				
				</tbody>
			</table>
		</div>
	
	
	</div>
</body>
</html>