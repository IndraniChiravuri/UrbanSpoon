<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/Main.css">

<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div id="container">
		<div align="center" id="subcontainer">
			<form action="RestaurantController" id="Recipe" method="post" enctype="multipart/form-data">
				<h3>Add Item</h3>
				<fieldset id="restaurant">
					<input type="hidden" name="action" value="add_recipe"> 
					<label for="name of item">Name of Item:</label><input type="text" name="name"> 
					<label for="price">Price:</label><input type="text" name="price">
					<label for="cuisine">Cuisine</label>
						<select name="cuisine">
						<c:forEach items="${cuisinesList}" var="cuisine">
							<option value="${cuisine.cuisineId}">${cuisine.name}</option>
						</c:forEach></select>
					<label for="branch">Branch</label>
						<select multiple ="multiple" name="branch">
						<c:forEach items="${branchesList}" var="branch">
							<option value="${branch.branchId}">${branch.location}</option>
						</c:forEach></select>
					<label for="Description">Description:</label><input type="text" name="description"><br>
					Vegetarian<input type="radio" name="veg" value="Veg"></input>
					Non-vegetarian<input type="radio" name="veg" value="Non-veg"></input>
					<label for="image">Image:</label><input type="file" name="image" size="20"> 
					<input type="submit" value="Submit">
				</fieldset>
			</form>

		</div>
	</div>

	<jsp:include page="footer.html"></jsp:include>
</body>
</html>