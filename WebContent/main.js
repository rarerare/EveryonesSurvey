window.onload=function(){
	loadData();
}
function loadData(){
	if(window.XMLHttpRequest){
		var xhttpq=new XMLHttpRequest();
	}else{
		var xhttpq = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xhttpq.onreadystatechange=function(){
		document.getElementById("popqdiv").innerHTML=this.responseText;
	}
	
	xhttpq.open("POST", "mainservlet?mact=getsvypops",true);
	xhttpq.send();
	
	if(window.XMLHttpRequest){
		var xhttpname=new XMLHttpRequest();
	}else{
		var xhttpname = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xhttpname.onreadystatechange=function(){
		if(xhttpname.readyState==4){
			var firstname=this.responseText;
			alert(firstname);
			document.getElementById("me").innerHTML=this.responseText;
		}
	}
	xhttpname.open("POST", "mainservlet?mact=getfirstname", true);
	xhttpname.send();

}
