<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
function addbranch() {
	document.getElementById("addbranch").style.display = "block";
	document.getElementById("display").style.display = "none";

}
</script>
<style type="text/css">
#container {
	height: 75%;
}

#subcontainer {
	width: 30%;
	height: 75%;
	background-color: orange;
	border-width: 2px;
	margin: 0px auto;
	position: absolute;
	margin-left: 500px;
}

label {
	display: inline-block;
	width: 90px;
	margin-right: 30px;
	text-align: right;
}

#fields input {
	width: 50%;
	height: 35px;
	border-radius: 6px;
	padding: 0px 13px 4px 23px;
	margin-bottom: 27px;
}
</style>

</head>

<body>
	<jsp:include page="header.jsp" />
	<div align="center" id="subcontainer">
			<form action="RestaurantController" id="Branch" method="post" enctype = "multipart/form-data">
				<h2>Add Branch</h2>
				<fieldset id="fields">
					<input type = "hidden" name="action" value="add_branch">
					<label>Location</label> <input type="text" name="location">
					<label>City</label> <input type="text" name="city"> 
					<label>State</label> <input type="text" name="state">
					<label>Country</label> <input type="text" name="country">
					<label>Postal Code</label> <input type="text" name="postal_code">  
					<label>Phone Number</label> <input type="text" name="phone_number"> 
					<label>Email Id</label> <input type="email" name="email_id">
					<label>Image </label> <input type="file" name="image"> 
					<input type="submit" value="ADD">
				</fieldset>
			</form>

		</div>
	</div>


	<div>
		<jsp:include page="footer.html"></jsp:include>
	</div>
</body></html>