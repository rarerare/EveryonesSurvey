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
		
		if($('form#mainform > :input[required]:visible').val() != ""){
			  form.submit();
		}else{
			alert("please fill in all required fields");
		}
	}else{
		alert("password doesn't match");
	}
});
}