<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	<link rel="stylesheet" type="text/css" href="css/Main.css">

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
</script>

<style type="text/css">
#container {
	height: 70%;
}

input[type="text"] {
	height: 50px;
	width: 500px;
	font-size: 20px;
}

img {
	display: block;
	width: 30%;
	height: 30%;
}
</style>
</head>
<body>
	<form action="HomeController" id="feedbackuser" method="post">
		<input type="hidden" name="action" value="feedbackuser">
		<div id="container">
			<div align="center" id="subcontainer">
				<fieldset id="fields">
					<div id="box">
						<h5>
							<c:out value="${recipe.name}"></c:out>
						</h5>
						<a href=""><img alt=""
							src="images/recipes/${recipe.imagePath}"></a>
						<p>${recipe.description}
						<p>
					</div>
					<label>Restaurant</label> <select name="restaurant" id="restaurant">
						<a href=""> <c:forEach items="${restaurantList}"
								var="restaurant">
								<option value="${restaurant.restaurantId}">${restaurant.registrationName}</option>

							</c:forEach>
					</select> <br> <label>Branch</label> <select name="branch" id="branch">
						<c:forEach items="${branchesList}" var="branch">
							<option value="${branch.branchId}">${branch.location}</option>
						</c:forEach>
					</select> <br> <label>Feedback</label> <input type="text"
						name="feedback" value=""><br> <label>Rating</label>
					<select name="rating">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select> <br> <input type="submit" name="submit" value="submit">

				</fieldset>
			</div>

		</div>
	</form>

</body>
</html>