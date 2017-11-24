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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
  <div class="container-fluid" id="nav_container">
    <div class="navbar-header">
    
	    <button type="button" class="pull-left navbar-toggle collapsed btn btn-primary" data-toggle="collapse" data-target="#searchCollapse">
	    
	      <i class="glyphicon glyphicon-search"></i>Search
	   	</button>
	   	
      <a class="navbar-brand" href="displayquestion">EveryoneQ</a>
      
      <button type="button" id="navRightButt" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navRightCollapse">
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
   		</button>
   		
    </div>
    
    
    <ul class="nav navbar-nav " >
	   	<li>
	      	<a href="usertracker?mact=hrefCheckLogin&nextPage=questionnaire.jsp">Create a Survey</a>
	    </li>
    </ul>
   
   	
    <div class="nav navbar-nav collapse navbar-collapse" id="searchCollapse">
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
    </div>  
    <div class="collapse navbar-collapse" id="navRightCollapse">
	    <ul class="nav navbar-nav navbar-right" id="navRightUl">
	    </ul>
    </div>
    <div class="collapse navbar-collapse" id="right_button_div">
    	
    </div>
  </div>
</nav>

<h1><%=request.getAttribute("qnTitle") %></h1>
<div class='mainq'>
<%=request.getAttribute("serverMessage") %>
</div>
</body>
</html>