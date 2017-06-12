<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="signup.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="jquery.min.js"></script>
<script src="signup.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Everyone's Survey</title>
</head>
<body>
<h2>Everyone's Survey</h2><br>

<div class="signupformdiv">
<form action="mainservlet" method="post" id="mainform">
<input type="hidden" value="signup" name="mact">
Username: <input type="text" name="username"><br><br>
Password: <input type="password" name="password" id="password"><br><br>
Confirm Password: <input type="password" id="confirmpassword"><br><br>
Email (optional): <input type="text" name="email"><br><br>
<button type="submit" id="submitButt">Sign up</button>
</form>
</div>
</body>
</html>