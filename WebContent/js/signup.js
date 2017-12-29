function passMatch(){
	var pass=document.getElementById("password").value;
	var confirmPass=document.getElementById("confirmpassword").value;
	if(pass==confirmPass){
		return true;
	}else{
		return false;
	}
}
function requiredFields(){
	
}
window.onload=function(){
document.getElementById("submitButt").addEventListener("click",
		function(event){
	event.preventDefault();
	if(passMatch()){
		var unFilled=false;
		$('form#mainform > :input[required]:visible').each(
				function(){
					if($(this).val()==""){
						unFilled=true;
					}
				}
		);
		if(!unFilled){
			
			//$('form#mainform').submit(
					
					    $.ajax({
					           type: "POST",
					           url: "usertracker",
					           data: $("#mainform").serialize(), 
					           success: function(message)
					           {
					        	   if(message=="success"){
					        		   window.location="login.jsp";
					        	   }else{
					        		   alert(message); 
					        	   }
					               
					           }
					         });

					    
			
		}else{
			alert("please fill in all required fields");
		}
	}else{
		
		alert("Password confirmation doesn't match Password");
	}
});
}