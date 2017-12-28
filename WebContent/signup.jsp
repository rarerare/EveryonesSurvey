<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/signup.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/signup.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EveryoneQ</title>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="displayquestion">EveryoneQ</a>
    </div>
    
    <form class="navbar-form navbar-left">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search">
        <div class="input-group-btn">
          <button class="btn btn-default" type="submit">
            <i class="glyphicon glyphicon-search"></i>
          </button>
        </div>
      </div>
    </form>
   
  </div>
</nav>
<h2>EveryoneQ</h2><br>

<div class="signupformdiv">
<form action="usertracker" method="post" id="mainform">
	<input type="hidden" value="signup" name="mact">
	First Name:<input type="text" name="firstname" required ><br><br>
	Last Name:<input type="text" name="lastname" required ><br><br>
	Username: <input type="text" name="username" required><br><br>
	Password: <input type="password" name="password" id="password" required><br><br>
	Confirm Password: <input type="password" id="confirmpassword" required><br><br>
	
	Email: <input type="text" name="email" required><br><br>
	<button type="submit" id="submitButt">Sign up</button>
</form>
</div>
</body>
</html>