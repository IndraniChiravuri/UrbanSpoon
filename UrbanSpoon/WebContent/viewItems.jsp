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
	height: 115%;;
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
</style>
</head>
<body>
	<div>
		<jsp:include page="header.jsp"></jsp:include>
	</div>
	<div id="container">
	<form action="RestaurantController" id="view_recipes" method="post" enctype="multipart/form-data">
	<input type = "hidden" name="action" value="view_recipes">
	<h4>RECIPE LIST</h4>	
		<c:forEach items="${recipesList}" var="recipe">
			<div id="box">
				<h5>
					<c:out value="${recipe.name}">${recipe.name}</c:out>
				</h5>
				<a href=""><img alt="" src="images/recipes/${recipe.imagePath}"></a>
				<p>
					<c:out value="${recipe.description}">${recipe.description}</c:out>
				<p>
				<div>${recipes.ratings}</div>
			</div>
		</c:forEach>
	</form>
	</div>
	<div>
		<jsp:include page="footer.html"></jsp:include>

	</div>
</body>
</html>