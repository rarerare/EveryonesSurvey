<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="main.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Everyone's Survey</title>
</head>
<body>
<h1>Everyone's Survey</h1>
<div class="loginformdiv">

<form method="post" action="mainservlet">
<input type="hidden" name="mact" value="login">
Username: <br><input type="text" class="logininput" name="username"><br>
Password: <br><input type="password" class="logininput" name="password"><br>
<button type="submit"  class="loginsubmit">Sign in</button><br>
Don't have an account?
<a href="signup.jsp" class="signupbutt">Sign up</a>
</form>
</div>
</body>
</html>