<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/Main.css">
<script>

function validate(){
	var username=document.form.userName.value;
	var password=document.form.password.value;
	if(username=="" ){
	 alert("Enter Username!");
	  return false;
	}
	if(password==""){
	 alert("Enter Password!");
	  return false;
	}
	return true;
	}</script>
<style type="text/css">

</style>
</head>
<body> 
	<jsp:include page="header.jsp"></jsp:include>


	<div id="container">
		<div align="center" id="register">
			<form name = "form" action="HomeController" id="restaurant" method="post" >
				<fieldset id="fields">
				<input type="hidden" name="action" value="restaurant_login"> 
					<label>Name</label> <input type="text" name="userName" value="${userName}"> <label>Password</label>
					<input type="password" name="password"> <label></label>				
					<span type = "msg"></span>
					<input type="submit" onclick = "return validate()" value="submit">	
				</fieldset>

			</form>
		</div>

	</div>


	<jsp:include page="footer.html"></jsp:include>
</body>
</html>