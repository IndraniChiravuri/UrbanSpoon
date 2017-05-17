<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/Main.css">
<style type="text/css">
#container {
	height: 115%;
	width:115%;
}

#box {
	width: 18%;
	background-color: #fcabba;
	border-radius: 5px;
	padding: 5px;
	margin: 3px 0px 0px 15px;
	float: left;
}

img {
	border-radius: 2px;
	width: 70%;
}
.imgButton {
	height:5%;
	border-radius: 7px;
	border-color:#ea800e;
	
}
</style>
</head>
<body>
	<div>
		<jsp:include page="header.jsp"></jsp:include>
	</div>
	<div id="container">



		<c:forEach items="${recipesList}" var="recipe">
			<div id="box">
				<h5>
					<c:out value="${recipe.name}"></c:out>
				</h5>
				<a href=""><img alt="" src="images/recipes/${recipe.imagePath}"></a>
				<p>${recipe.description}
				<p>
				<div class="imgButton">
					<a
						href="HomeController?action=user_login_form&recipe_id=${recipe.recipeId}"
						class="button">feedback</a>
				</div>
			</div>
		</c:forEach>

	</div>

	<div>
		<jsp:include page="footer.html"></jsp:include>
	</div>
</body>
</html>