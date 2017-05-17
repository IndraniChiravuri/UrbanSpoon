<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/Main.css">

<script>
	function validate() {
		var username = document.form.userName.value;
		var password = document.form.password.value;
		if (username == "") {
			alert("Enter Username!");
			return false;
		}
		if (password == "") {
			alert("Enter Password!");
			return false;
		}
		return true;
	}
	function signup(pageURL) {
		window.location.href("pageURL");
		
		
		
	}
	
</script>
<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>


	<div id="container">
		<div align="center" id="register">
			<form  action="HomeController" id="feedback" method="post">
				<fieldset id="fields">

					<input type="hidden" name="action" value="feedback_login"> <label>Name</label>
					<input type="text" name="userName" value="${userName}"> <label>Password</label>
					<input type="password" name="password"> <label></label>
					

					<span type="msg"></span> <input type="submit"
						onclick="return validate()" value="submit"><br>
					<li><a href="HomeController?action=register">Sign up</a></li>
				</fieldset>

			</form>
		</div>

	</div>


	<jsp:include page="footer.html"></jsp:include>
</body>
</html>