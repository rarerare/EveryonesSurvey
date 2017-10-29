<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="everyonesSurvey.Questionnaire"%>
<%@page import="everyonesSurvey.Question"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="main.css">
<title>EveryoneQ</title>
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
      
      <li><a href="question.jsp">Ask a Question</a></li>
      <li><a href="questionaire.jsp">Make a Questionnaire</a></li>
      
      
    </ul>
  </div>
</nav>
<% Questionnaire qn=(Questionnaire)request.getAttribute("questionnaire");%>
<% ArrayList<Question> questions=(ArrayList<Question>)request.getAttribute("questions");%>
<h1><%=qn.getTitle() %></h1>
<div class='mainq'>
<form action="recordanswer" method="post">
<input type="hidden" name="mact" value="recordQnAnswer">
<input type="hidden" name='qnId' value='<%=qn.getId()%>'>
<input type="hidden" name='qNum' value='<%=qn.getQNum() %>'>
<%for(Question q:questions){
	%><h4><%=q.getTitle() %></h4><hr><%q.getDescription(); %><br>
	<%=q.addQ("") %><hr class='betweenQsHr'><br><% 
}%>
<button type="submit">Submit</button>
</form>
</div>
</body>
</html>