window.onload=function(){
	checkLogin();
	var cate=document.getElementsByName("category");
	for(var i=0;i<cate.length;i++){
		cate[i].addEventListener("change",updateAnswerDetail);
	}
	document.getElementById("submitButt").addEventListener("click", function(){
		
		var categoryRs=document.getElementsByName("category");
		var cateSelected=false;
		for(var i=0;i<categoryRs.length;i++){
			if(categoryRs[i].checked){
				cateSelected=true;
			}
		}
		
		
			document.getElementById("qform").submit();
		
		
	})
}
function updateAnswerDetail(qnum){
	
	var cateRadios=document.getElementsByName("category"+qnum);
	var category;
	
	for(var i=0;i<cateRadios.length;i++){
		
		if(cateRadios[i].checked){
			category=cateRadios[i].value;
			
		}
	}
	
	var detail=document.getElementById("detail_answer"+qnum);
	
	switch(category){
	case "samc":
		detail.innerHTML="<span id='choices"+qnum+"'><span>Choice1: " +
				"<input type='text' name='choice1' required><br></span></span>" +
				"<br><button id='addchoicebutt"+qnum+"' type='button'" 
				+"onclick='addChoice("+qnum+")'>Add choice</button>";
		
		
		addChoice.num=1;
		
		break;
		
	case "mamc":
		
		detail.innerHTML="<span id='choices"+qnum+"'><span>Choice1:" +
				" <input type='text' name='choice1' required><br></span></span>" +
				"<br><button id='addchoicebutt"+qnum+"' type='button'" +
				" onclick='addChoice("+qnum+")'>Add choice</button>";
        addChoice.num=1;
        
        
        break;
        
	case "fr":
		detail.innerHTML="";
		break;
		
	}
	
}
function addChoice(qnum){
	
	addChoice.num++;
	
	document.getElementById("opnum"+qnum).value=addChoice.num;
	var newChoice=document.createElement("span");
	newChoice.innerHTML="Choice"
		+addChoice.num+": <input type='text' name='q"+qnum+"choice"+addChoice.num+"' required><br>";
	document.getElementById("choices"+qnum).appendChild(newChoice);
	
	
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
function updateQList(e){
	
	var qnum=parseInt($('#qnumInput').val());
	qnum++;
	$("#qListDiv").append("<hr><br><h3>Question "+qnum+"</h3><br><b>Title:</b><br> " 
			+"<input type='hidden' name='opnum' id='opnum"+qnum+"'>"
			+"<input type='text' name='title"+qnum+"' required><br><br>"
			+"<b>Description:</b><br> <textarea name='description"+qnum+"' required></textarea><br><br>"
			+"<b>Category:</b> <br> Single-Answer Multiple choice" 
			+"<input type='radio' name='category"+qnum+"' value='samc' " 
			+"onchange='updateAnswerDetail("+qnum+")' required><br>"
			+"Multiple-Answer Multiple choice" 
			+"<input type='radio' name='category"+qnum+"' value='mamc' "
			+"onchange='updateAnswerDetail("+qnum+")' required><br>"
			+"Free Response:" 
			+"<input type='radio' name='category"+qnum+"' value='fr'" 
			+" onchange='updateAnswerDetail("+qnum+")' required><br>"
			+"<div id='detail_answer"+qnum+"'></div><br><br>");
	$('#qnumInput').val(qnum);
	
}