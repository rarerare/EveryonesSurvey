<%@page import="everyonesSurvey.Questionnaire"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html  >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="main.css">
<script src="main.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>EveryoneQ</title>
<script type="text/javascript">
window.onload=function(){
	checkLogin();
	initDOM();
}
</script>
</head>
<body id="mainbody">
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
    <ul class="nav navbar-nav">
      
      <li><a href="question.jsp">Ask a Question</a></li>
      <li><a href="questionaire.jsp">Make a Questionnaire</a></li>
      <li id="me"></li>
      
    </ul>
  </div>
</nav>
<div class="container">
  
  <ul class="nav nav-pills">
    <li class="active"><a href="#">Top Surveys</a></li>
    <li><a href="displayquestion?mact=getpopqs">Top questions</a></li>
    
    
  </ul>
  
</div>
<div id="popqdiv">

<%

ArrayList<Questionnaire> popQns=(ArrayList<Questionnaire>)request.getAttribute("popQns");

for(Questionnaire qn:popQns){
	%> <div class='mainq'><h2><a href='displayquestion?mact=displayQn&qnid=<%=qn.getId()%>'><%= qn.getTitle()%></a></h2></div> <% 
}

%>
</div>
</body>
</html>