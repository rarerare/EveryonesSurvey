window.onload=function(){
	checkLogin();
	
	
}
function updateAnswerDetail(qNum){
	
	var cateRadios=document.getElementsByName("category"+qNum);
	var category;
	
	for(var i=0;i<cateRadios.length;i++){
		
		if(cateRadios[i].checked){
			category=cateRadios[i].value;
			
		}
	}
	
	var detail=document.getElementById("detail_answer"+qNum);
	
	switch(category){
	case "samc":
		detail.innerHTML="<span id='options"+qNum+"'><span>Option1: " +
				"<input type='text' name='q"+qNum+"option1' required><br></span></span>" +
				"<br><button id='addOptionButt"+qNum+"' type='button'" 
				+"onclick='addOption("+qNum+")'>Add option</button>";
		$('#optNum'+qNum).val(1);
		break;
		
	case "mamc":
		
		detail.innerHTML="<span id='options"+qNum+"'><span>Option1:" +
				" <input type='text' name='q"+qNum+"option1' required><br></span></span>" +
				"<br><button id='addOptionButt"+qNum+"' type='button'" +
				" onclick='addOption("+qNum+")'>Add option</button>";
		$('#optNum'+qNum).val(1);
        break;
        
	case "fr":
		detail.innerHTML="";
		break;
		
	}
	
}
function addOption(qNum){
	
	var optNum=$('#optNum'+qNum).val();
	optNum++;
	$('#optNum'+qNum).val(optNum);
	
	
	var newOption=document.createElement("span");
	newOption.innerHTML="Option"
		+optNum+": <input type='text' name='q"+qNum+"option"+optNum+"' required><br>";
	document.getElementById("options"+qNum).appendChild(newOption);
	
	
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
	
	var qNum=parseInt($('#qNumInput').val());
	qNum++;
	$("#qListDiv").append("<hr><br><h3>Question "+qNum+"</h3><br><b>Title:</b><br> " 
			+"<input type='hidden' name='optNum"+qNum+"' id='optNum"+qNum+"' value='0'>"
			+"<input type='text' name='qTitle"+qNum+"' required><br><br>"
			+"<b>Description:</b><br> <textarea name='qDescription"+qNum+"' required></textarea><br><br>"
			+"<b>Category:</b> <br> Single-Answer Multiple choice" 
			+"<input type='radio' name='category"+qNum+"' value='samc' " 
			+"onchange='updateAnswerDetail("+qNum+")' required><br>"
			+"Multiple-Answer Multiple choice" 
			+"<input type='radio' name='category"+qNum+"' value='mamc' "
			+"onchange='updateAnswerDetail("+qNum+")' required><br>"
			+"Free Response:" 
			+"<input type='radio' name='category"+qNum+"' value='fr'" 
			+" onchange='updateAnswerDetail("+qNum+")' required><br>"
			+"Number:"
			+"<input type='radio' name='category"+qNum+"' value='number'" 
			+" onchange='updateAnswerDetail("+qNum+")' required><br>"
			+"<div id='detail_answer"+qNum+"'></div><br><br>");
	$('#qNumInput').val(qNum);
	
}