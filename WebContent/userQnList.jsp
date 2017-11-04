<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="everyonesSurvey.User"%>
<%@page import="java.util.ArrayList" %>
<%@page import="everyonesSurvey.Questionnaire"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="js/init.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>EveryoneQ</title>
<script type="text/javascript">
window.onload=function(){
	checkLogin();
}
</script>
</head>
<body>
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
      
      
      <li><a href="questionaire.jsp">Make a Questionnaire</a></li>
      
      
    </ul>
  </div>
</nav>
<ul class="nav nav-pills">
    <li><a href="userProfile.jsp">My profile</a></li>
    <li class="active"><a href="#">My surveys</a></li>
    
</ul>
<% ArrayList<Questionnaire> qns=(ArrayList<Questionnaire>)request.getAttribute("qns"); %>
<%for(Questionnaire qn:qns){%>
 <a href='displaysurveyresult?mact=getQnResult&qnId=<%=qn.getId()%>'><%=qn.getTitle() %></a><br>
<% }%>


</body>
</html>