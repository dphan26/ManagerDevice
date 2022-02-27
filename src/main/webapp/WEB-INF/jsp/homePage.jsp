<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HomePage</title>
</head>
<body>
Get user
<form action="getUser" method="get">
	enter your id: <input type="text" name="id"><br>
	enter your name: <input type="text" name="name"><br>
	<input type="submit">
<!-- 		When you click on Submit, it is calling ad request is again going to dispatch a servlet word disbursal -->
</form>
Add user
<form action="addUser" method="post">
	enter your id: <input type="text" name="id"><br>
	enter your name: <input type="text" name="name"><br>
	<input type="submit">
<!-- 		When you click on Submit, it is calling ad request is again going to dispatch a servlet word disbursal -->
</form>

Add user B
<form action="addUserB" method="get">
	enter your id: <input type="text" name="id"><br>
	enter your name: <input type="text" name="name"><br>
	enter your mail: <input type="text" name="mail"><br>
	<input type="submit">
<!-- 		When you click on Submit, it is calling ad request is again going to dispatch a servlet word disbursal -->
</form>

Get user by name
<hr>
<form action="getUserByName" method="post">
	
	enter your name: <input type="text" name="name"><br>
	<input type="submit">
<!-- 		When you click on Submit, it is calling ad request is again going to dispatch a servlet word disbursal -->
</form>

List All users
${users}
</body>
</html>