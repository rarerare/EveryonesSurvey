<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="question.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EveryoneQ</title>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="main.jsp">EveryoneQ</a>
    </div>
    
    
    <ul class="nav navbar-nav">
      
      <li><a href="#">Ask a Question</a></li>
      <li><a href="questionaire.jsp">Make a Questionnaire</a></li>
      <li id="me"></li>
      
    </ul>
  </div>
</nav>
<form id="qform" action="mainservlet">
<input type="hidden" name="mact" value="question">
<input type="hidden" name="opnum" id="opnum">
Title:<input type="text" name="title" required><br>
<br>
Description:<textarea name="description" required></textarea><br>
<br>
Category: <br> Single-Answer Multiple choice<input type="radio" name="category" value="samc" required><br>
Multiple-Answer Multiple choice<input type="radio" name="category" value="mamc" required><br>
Free Response:<input type="radio" name="category" value="fr" required><br>
<br>
<br>
<div id="detail_answer">
</div>

</form>
<br>
<button id="submit">Submit Question</button>
</body>
</html>