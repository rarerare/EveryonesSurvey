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
    
    
    <ul class="nav navbar-nav sub_navbar" >
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
<% Questionnaire qn=(Questionnaire)request.getAttribute("questionnaire");%>
<% ArrayList<Question> questions=(ArrayList<Question>)request.getAttribute("questions");%>
<h1><%=qn.getTitle() %></h1>
<div class='mainq'>
<form action="recordanswer" method="post">
<input type="hidden" name="mact" value="recordQnAnswer">
<input type="hidden" name='qnId' value='<%=qn.getId()%>'>
<input type="hidden" name='qNum' value='<%=qn.getQNum() %>'>
<%for(Question q:questions){
	
	%><h4><%=q.getTitle() %></h4>
	
	<% QCategory category=q.getCategory();
	
	if(category.isFinAns()){
		ArrayList<FinOption> options=((FinAnsQuestion)q).getOptions();
		
		
		String inputName=category+"__"+q.getId();
		for(FinOption op:options){
			%><input type='<%=q.getCategory().getInputType()%>' name='<%=inputName%>' value='<%=op.getId()%>' ><%=op.getDescription() %><br> <% 
		}
	}else {
		String inputName=category+"__"+q.getId();
		if(category==QCategory.fr){
			%><input type='<%=q.getCategory().getInputType()%>' name='<%=inputName%>' step='any' required><%
		}else if(category==QCategory.number){
			%><input type='<%=q.getCategory().getInputType()%>' name='<%=inputName%>' step='any' required><%
		}
		
		 
	} %>
	<br><br><% 
}%>
<button type="submit">Submit</button>
</form>
</div>
</body>
</html>