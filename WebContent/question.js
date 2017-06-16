window.onload=function(){
	var cate=document.getElementsByName("category");
	for(var i=0;i<cate.length;i++){
		cate[i].addEventListener("change",updateAnswerDetail);
	}
	document.getElementById("submit").addEventListener("click", function(e){
		e.preventDefault();
		document.getElementById("qform").submit();
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
		detail.innerHTML="<span id='choices'>Choice1: <input type='text' name='choice1'><br></span>" +
				"<br><button id='addchoicebutt'>Add choice</button>";
		addChoice.num=1;
		document.getElementById("addchoicebutt").addEventListener("click", addChoice);
		break;
		
	case "mamc":
		detail.innerHTML="<span id='choices'>Choice1: <input type='text' name='choice1'><br></span>" +
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
	document.getElementById("choices").innerHTML+="Choice"
		+addChoice.num+": <input type='text' name='choice"+addChoice.num+"'><br>";
	
	
}