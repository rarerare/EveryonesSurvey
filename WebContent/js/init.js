function initDOM(){
	
}

function loadPopQs(){
	$.post("displayquestion?mact=getpopqs", function(data){
		$('#popqdiv').html(data);
	})
}
function loadPopQns(){
	$.post("displayquestion?mact=getpopqns", function(data){
		$('#popqndiv').html(data);
	})
}
/*function searchQ(event){
	if(window.XMLHttpRequest){
		var xhttpq=new XMLHttpRequest();
	}else{
		var xhttpq = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xhttpq.open("POST", "displayquestion?mact=searchQ&searchkey="+$("#searchKeyText").val(),true);
	xhttpq.send();	
	event.preventDefault();
}*/

function checkLogin(){
	
	if(window.XMLHttpRequest){
		var xhttpchecklogin=new XMLHttpRequest();
	}else{
		var xhttpchecklogin = new ActiveXObject("Microsoft.XMLHTTP");
	}
	

	xhttpchecklogin.onreadystatechange=function(){
		if(xhttpchecklogin.readyState==4){
			if(this.responseText=="no"){
				
				document.getElementById("navRightUl").innerHTML+=
					"<form method='post'  id='loginform' class='navbar-form navbar-right'>"+
"<input type='hidden' name='mact' value='login'> <span id='usernamewrong' class='wrong'></span>"+
"</span> <input type='text' class='form-control' placeholder='username' name='username' id='username'>"+
" <span id='passwordwrong' class='wrong'></span>"+
"</span> <input type='password' class='form-control' placeholder='password' class='logininput' name='password' id='password'>"+
"<button type='submit' id='loginsubmit'>Sign in</button>"+
"<span class='navbartext'>or</span> <a href='signup.jsp'>Sign up</a></form>";
				var loginSubmitButt=document.getElementById("loginsubmit");
				loginSubmitButt.onclick=function(e){
					e.preventDefault();
					login();
					
				}
			}else{
				
				setupUser();
			}
		}
		
		

		
	}
	xhttpchecklogin.open("POST", "mainservlet?mact=checklogin",true);

	xhttpchecklogin.send();
}



function setupUser(){
	if(window.XMLHttpRequest){
		var xhttpname=new XMLHttpRequest();
	}else{
		var xhttpname = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xhttpname.onreadystatechange=function(){
		if(xhttpname.readyState==4){
			var firstname=this.responseText;
			document.getElementById("navRightUl").innerHTML+="<li class='dropdown userProfileDropdown'>"
				
				+"<a class='btn dropdown-toggle' role='button' data-toggle='dropdown' href='#'>"+this.responseText
				+"<i class='glyphicon glyphicon-user'></i>"
				+"<span class='caret'></span></a>"
				+"<ul class='dropdown-menu userProfileDropdownMenu'>"
				+"<li><a href='displaysurveyresult?mact=getQnList'>My Surveys</a></li>"
				+"<li><a href='userProfile.jsp'>My Profile</a></li>"
				+"</ul></li>";
		}
	}
	xhttpname.open("POST", "mainservlet?mact=getfirstname", true);
	xhttpname.send();
}
function login(){
	var loginform=document.getElementById("loginform");
	
	if(window.XMLHttpRequest){
		var xhttp=new XMLHttpRequest();
	}else{
		var xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xhttp.open("POST", "mainservlet?mact=login", true);
	xhttp.send(new FormData(loginform));
	
	xhttp.onreadystatechange=function(){
		responseText=this.responseText
		if(responseText=="Incorrect password"){
			document.getElementById("passwordwrong").innerHTML=this.responseText;
			document.getElementById("usernamewrong").innerHTML="";
		}else if(responseText=="Incorrect Username"){
			document.getElementById("usernamewrong").innerHTML=this.responseText;
			document.getElementById("passwordwrong").innerHTML="";
		}else if(responseText=="main"){
			window.location="displayquestion";
		}
		
		
	}
	
}
function submitSingleAns(event){
	
	event.preventDefault();
	var thisForm=$(this);
	
	$.post("recordanswer",$(this).serialize(),function(data){
		thisForm.html(data);
	});
}
