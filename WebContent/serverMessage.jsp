<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="everyonesSurvey.Questionnaire"%>
<%@page import="everyonesSurvey.Question"%>
<%@page import="java.util.ArrayList" %>
<%@page import="everyonesSurvey.QCategory"%>
<%@page import="everyonesSurvey.FinAnsQuestion"%>
<%@page import="everyonesSurvey.FinOption"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/init.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>EveryoneQ</title>
<script type="text/javascript">
window.onload=function(){
	checkLogin();
	initDOM();
}
</script>
</head>
<body class='mainbody'>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="displayquestion">EveryoneQ</a>
    </div>
    
    <form class="navbar-form navbar-left" id="searchForm">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search" id="searchKeyText">
        <div class="input-group-btn">
          <button class="btn btn-default" type="submit" >
            <i class="glyphicon glyphicon-search"></i>
          </button>
        </div>
      </div>
    </form>
    <ul class="nav navbar-nav" id="navRightUl">
      
      
      <li><a href="questionnaire.jsp">Make a Questionnaire</a></li>
      
      
    </ul>
  </div>
</nav>

<h1><%=request.getAttribute("qnTitle") %></h1>
<div class='mainq'>
<%=request.getAttribute("serverMessage") %>
</div>
</body>
</html>