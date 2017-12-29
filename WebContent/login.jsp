<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="js/login.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>EveryoneQ</title>
<script type="text/javascript">

</script>
</head>
<body class="loginbody">
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="displayquestion">EveryoneQ</a>
    </div>
    
    <form class="navbar-form navbar-left" action="displayquestion">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search" name="searchkey">
        <input type="hidden" name="mact" value="searchQ">
        <div class="input-group-btn">
          <button class="btn btn-default" type="submit">
            <i class="glyphicon glyphicon-search"></i>
          </button>
        </div>
      </div>
    </form>
   
  </div>
</nav>
<h1 class="loginh1"></h1><br>
<div class="margin_wrapper">
	<div class="loginformdiv">
	<% String nextPage=(String)request.getAttribute("nextPage"); 
	   if(nextPage==null){
	     nextPage="displayquestion";
	    }
	%>
	<form method="post"  id="loginform">
	<input type="hidden" name="mact" value="login">
	<input type="hidden" name="nextPage" value='<%=nextPage%>'>
	Username: <span id="usernamewrong" class="wrong"></span>
	</span> <br><input type="text" class="logininput" name="username" id="username"><br>
	Password: <span id="passwordwrong" class="wrong"></span>
	</span> <br><input type="password" class="logininput" name="password" id="password"><br>
	<button  class="loginsubmit" id="loginsubmit" type="submit">Sign in</button><br>
	</form>
	
	<a href="forgetpass.jsp" class="signupbutt">Forgot password</a><br>
	
	Don't have an account?
	<a href="signup.jsp" class="signupbutt">Sign up</a>
	
	</div>
</div>
</body>
</html>