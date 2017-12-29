<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Retrieve password</title>
</head>
<body>
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
<br>
<div class="margin_wrapper">
	<div class="loginformdiv">
		<form method="post" action="usertracker" >
			<input type="hidden" value="retrievePass" name="mact">
			Your Email: <input type="text" class="logininput" name="email">
			<button  class="loginsubmit" type="submit">Retrieve password</button>
		</form>
	</div>
</div>
</body>
</html>