window.onload=function(){
	checkLogin();
	var cate=document.getElementsByName("category");
	for(var i=0;i<cate.length;i++){
		cate[i].addEventListener("change",updateAnswerDetail);
	}
	document.getElementById("submit").addEventListener("click", function(e){
		e.preventDefault();
		var categoryRs=document.getElementsByName("category");
		var cateSelected=false;
		for(var i=0;i<categoryRs.length;i++){
			if(categoryRs[i].checked){
				cateSelected=true;
			}
		}
		
		if(cateSelected==false){
			
		}else{
			document.getElementById("qform").submit();
		}
		
	})
}
function updateAnswerDetail(e){
	var cateRadios=document.getElementsByName("category");
	var category;
	for(var i=0;i<cateRadios.length;i++){
		if(cateRadios[i].checked){
			category=cateRadios[i].value;
			if(updateAnswerDetail.category==category){
				return;
			}else{
				updateAnswerDetail.category=category;
			}
		}
	}
	var detail=document.getElementById("detail_answer");
	switch(category){
	case "samc":
		detail.innerHTML="<span id='choices'><span>Choice1: <input type='text' name='choice1' required><br></span></span>" +
				"<br><button id='addchoicebutt'>Add choice</button>";
		
		document.getElementById("opnum").value=1;
		addChoice.num=1;
		document.getElementById("addchoicebutt").addEventListener("click", addChoice);
		break;
		
	case "mamc":
		detail.innerHTML="<span id='choices'><span>Choice1: <input type='text' name='choice1' required><br></span></span>" +
				"<br><button id='addchoicebutt'>Add choice</button>";
        addChoice.num=1;
        document.getElementById("addchoicebutt").addEventListener("click", addChoice);
        break;
        
	case "fr":
		detail.innerHTML="";
		break;
		
	}
	
}
function addChoice(e){
	e.preventDefault();
	addChoice.num++;
	document.getElementById("opnum").value=addChoice.num;
	var newChoice=document.createElement("span");
	newChoice.innerHTML="Choice"
		+addChoice.num+": <input type='text' name='choice"+addChoice.num+"' required><br>";
	document.getElementById("choices").appendChild(newChoice);
	
	
}
function checkLogin(){
	
	if(window.XMLHttpRequest){
		var xhttpchecklogin=new XMLHttpRequest();
	}else{
		var xhttpchecklogin = new ActiveXObject("Microsoft.XMLHTTP");
	}
	

	xhttpchecklogin.onreadystatechange=function(){
		if(xhttpchecklogin.readyState==4){
			if(this.responseText=="no"){
				window.location="login.jsp";
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
			
			document.getElementById("me").innerHTML="<a href=''>"+this.responseText
			+"<i class='glyphicon glyphicon-user'></i></a>";
		}
	}
	xhttpname.open("POST", "mainservlet?mact=getfirstname", true);
	xhttpname.send();
}