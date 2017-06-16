<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="login.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="login.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>EveryoneQ</title>
</head>
<body id="loginbody">
<h1 class="loginh1">EveryoneQ</h1>
<div class="loginformdiv">

<form method="post"  id="loginform">
<input type="hidden" name="mact" value="login">
Username: <span id="usernamewrong" class="wrong"></span>
</span> <br><input type="text" class="logininput" name="username" id="username"><br>
Password: <span id="passwordwrong" class="wrong"></span>
</span> <br><input type="password" class="logininput" name="password" id="password"><br>
</form>
<button  class="loginsubmit" id="loginsubmit">Sign in</button><br>
<a href="forgetpass.jsp" class="signupbutt">Forgot password</a><br>

Don't have an account?
<a href="signup.jsp" class="signupbutt">Sign up</a>

</div>
</body>
</html>