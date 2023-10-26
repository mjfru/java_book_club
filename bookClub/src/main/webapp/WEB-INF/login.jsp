<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/loginStyle.css"/>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Architects+Daughter&family=Homemade+Apple&display=swap" rel="stylesheet">
<title>Register | Login</title>
</head>
<body>
	<div class="page-container">
		<div class="header">
			<h1>Book Club</h1>
			<h3>A Place for Friends to Share Thoughts on Books</h3>
		</div>
		<div class="form-container">
			
			<div class="register">
				<h3>New Here? Register:</h3>
				<form:form action="/register" method="POST" modelAttribute="newUser">
					<div>
						<form:label path="name">Name: </form:label>			
						<form:input type="text" path="name"/><br/>
						<form:errors path="name" class="errors"/>
					</div>
					<div>
						<form:label path="email">Email: </form:label>			
						<form:input type="text" path="email"/><br/>
						<form:errors path="email" class="errors"/>
					</div>
					<div>
						<form:label path="password">Password: </form:label>					
						<form:input type="password" path="password"/><br/>
						<form:errors path="password" class="errors"/>
					</div>
					<div>
						<form:label path="confirmPass">Confirm Password: </form:label>			
						<form:input type="password" path="confirmPass"/><br/>
						<form:errors path="confirmPass" class="errors"/>
					</div>
					<div class="buttons">
						<input type="submit" value="Register" class="btn btn-primary" id="l-r-button"/>
					</div>
				</form:form>
			</div>
			
			<div class="login">
			<h3>Already a Member? Log In:</h3>
				<form:form action="/login" method="POST" modelAttribute="newLogin">
					<div>
						<form:label path="email">Email: </form:label>		
						<form:input type="string" path="email"/><br/>
						<form:errors path="email" class="errors"/>
					</div>
					<div>
						<form:label path="password">Password: </form:label>	
						<form:input type="password" path="password"/><br/>
						<form:errors path="password" class="errors"/>
					</div>
					<div class="buttons">
						<input type="submit" value="Log In" class="btn btn-primary" id="l-r-button"/>
					</div>	
				</form:form>
			</div>
			
		</div>
	</div>
</body>
</html>