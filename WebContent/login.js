window.onload=function(){
	var loginSubmitButt=document.getElementById("loginsubmit");
	loginSubmitButt.onclick=function(e){
		e.preventDefault();
		login();
		
	}
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
		}else if(responseText="main"){
			window.location="main.jsp";
		}
		
		
	}
	
}