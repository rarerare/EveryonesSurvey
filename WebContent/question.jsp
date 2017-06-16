<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="question.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="qform" action="mainservlet?mact=question">

Title:<input type="text" name="title"><br>
<br>
Description:<input type="text" name="description"><br>
<br>
Category: <br> Single-Answer Multiple choice<input type="radio" name="category" value="samc"><br>
Multiple-Answer Multiple choice<input type="radio" name="category" value="mamc"><br>
Free Response:<input type="radio" name="category" value="fr"><br>
<br>
<br>
<div id="detail_answer">
</div>

</form>
<br>
<button id="submit">Submit Question</button>
</body>
</html>