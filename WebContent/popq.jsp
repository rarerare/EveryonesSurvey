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
loadPopQs();
checkLogin();
initDOM();
</script>
</head>
<body id="mainbody">
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="main.jsp">EveryoneQ</a>
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
	  <li ><a href="main.jsp">Top Surveys</a></li>
	  <li class="active"><a href="#">Top questions</a></li>
	  
	  
	</ul> 
</div>
<div id="popqdiv"></div>
</body>
</html>