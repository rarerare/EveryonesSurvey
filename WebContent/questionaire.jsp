<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="questionaire.js"></script>
<title>EveryoneQ</title>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="main.jsp">EveryoneQ</a>
    </div>
    
    
    <ul class="nav navbar-nav">
      
      <li><a href="question.jsp">Ask a Question</a></li>
      <li><a href="#">Make a Questionnaire</a></li>
      <li id="me"></li>
      
    </ul>
  </div>
</nav>
<div id="maindiv">
<form action="mainservlet" method="post" id="qform">
<input type="hidden" name="mact" value="sbmtQuestionaire">
<input name="qnum" id="qnumInput" type="hidden" value="0"/>
Title of questionnaire:<input type="text" name="qntitle" required><br>
<div id="qListDiv">
</div>
<button type="button" onclick="updateQList()">Add question</button>
<input type="submit" value="Submit questionaire"/>
</form>

</div>

</body>
</html>