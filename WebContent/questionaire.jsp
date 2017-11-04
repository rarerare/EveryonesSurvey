<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/questionaire.js"></script>
<title>EveryoneQ</title>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="displayquestion">EveryoneQ</a>
    </div>
    
    
    <ul class="nav navbar-nav" id="navRightUl">
      
      
      <li class="active"><a href="#">Create a Survey</a></li>
      
      
    </ul>
  </div>
</nav>
<div id="maindiv">
<form action="recordquestion" method="post" id="qform">
<input type="hidden" name="mact" value="sbmtQnr">
<input name="qNum" id="qNumInput" type="hidden" value="0"/>
Title of Survey:<input type="text" name="qnTitle" required><br>
<div id="qListDiv">
</div>
<button type="button" onclick="updateQList()">Add question</button>
<input type="submit" value="Submit questionaire"/>
</form>

</div>

</body>
</html>