<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="everyonesSurvey.Question"%>
<%@page import="java.util.ArrayList" %>
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
    <ul class="nav navbar-nav" id="navRightUl">
      
      <li><a href="question.jsp">Ask a Question</a></li>
      <li><a href="questionaire.jsp">Make a Questionnaire</a></li>
      
      
    </ul>
  </div>
</nav>
<div class="container">
	<ul class="nav nav-pills">
	  <li ><a href="displayquestion">Top Surveys</a></li>
	  <li class="active"><a href="#">Top questions</a></li>
	  
	  
	</ul> 
</div>
<% ArrayList<Question> popQs=(ArrayList<Question>)request.getAttribute("popQs"); %>
<div id="popqdiv">
<% for(Question q: popQs){
	%><div class='mainq'>
	<h4><%=q.getTitle() %></h4><hr><%q.getDescription(); %><br>
	<form class="singleQForm" id="formSingle_<%=q.getCategory()%>_<%=q.getId()%>">
	<input type='hidden' value='recordSingleQAnswer' name='mact'>
	<input type='hidden' value='<%=q.getId() %>' name='qId'>
	<input type='hidden' value='<%=q.getCategory() %>' name='qCategory'>
	
	<%=q.addQ("") %><button type="submit">submit</button></form></div><%
	
}%>

</div>
</body>
</html>