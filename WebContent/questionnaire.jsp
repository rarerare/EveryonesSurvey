<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/questionnaire.js"></script>
<script src="js/init.js"></script>
<title>EveryoneQ</title>
<script type="text/javascript">
window.onload=function(){
	notLoggedIn();
	checkLogin();
}
</script>
</head>
<body>
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
<div id="maindiv">
<form action="recordquestion" method="post" id="qform">
<input type="hidden" name="mact" value="sbmtQnr">
<input name="qNum" id="qNumInput" type="hidden" value="0"/>
Title of Survey:<input type="text" name="qnTitle" required><br>
<div id="qListDiv">
</div>
<button type="button" onclick="updateQList()">Add question</button>
<input type="submit" value="Submit questionnaire"/>
</form>

</div>

</body>
</html>