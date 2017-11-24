<%@page import="everyonesSurvey.Questionnaire"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html  >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="js/init.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>EveryoneQ</title>
<script type="text/javascript">
window.onload=function(){
	checkLogin();
	
}
</script>
</head>
<body id="mainbody">
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="displayquestion">EveryoneQ</a>
    </div>
    
    <form class="navbar-form navbar-left" id="searchForm" action="displayquestion">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search" id="searchKeyText" name="searchkey">
        <input type="hidden" name="mact" value="searchQ">
        <div class="input-group-btn">
          <button class="btn btn-default" type="submit" >
            <i class="glyphicon glyphicon-search"></i>
          </button>
        </div>
      </div>
    </form>
    <ul class="nav navbar-nav" >
      <li><a href="usertracker?mact=hrefCheckLogin&nextPage=questionnaire.jsp">Create a Survey</a></li>
    </ul>
    <ul class="nav navbar-nav" id="navRightUl">
    </ul>
  </div>
</nav>
<div class="container">
  
  <ul class="nav nav-pills">
    <li class="active"><a href="#">Top Surveys</a></li>
    
    
    
  </ul>
  
</div>
<div id="popqdiv">

<%

ArrayList<Questionnaire> popQns=(ArrayList<Questionnaire>)request.getAttribute("popQns");

for(Questionnaire qn:popQns){
	%> <div class='mainq'><h2><a href='displayquestion?mact=displayQn&qnid=<%=qn.getId()%>'><%= qn.getTitle()%></a></h2>
	<span class="date_span">created on <%=qn.getCreateTimeStr() %> by <%=qn.getUserName() %></span></div> <% 
}

%>
</div>
</body>
</html>