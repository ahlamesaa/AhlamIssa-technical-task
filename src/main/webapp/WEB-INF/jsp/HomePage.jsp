<%@ page import="java.io.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Home Page</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<style>
.flex-container {
	display: flex;
	flex-direction: row;
	flex-wrap: wrap;
	text-align: center;
	justify-content: center;
}

.button {
	background-color: #4CAF50;
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}

@media ( max-width : 500px) {
	.flex-container {
		flex-direction: column;
	}
}

.flex-container>div {
	background-color: #f1f1f1;
	margin: 16px;
	padding: 16px;
	font-size: 25px;
}
</style>
</head>
<body>
<div class="flex-container">

<a class="button" href= "/reload">reload all data from API</a>

</div>
	<div class="flex-container">


		<c:forEach items="${allDigimons}" var="element">
			<div align="center" valign="center">
				<a href="/digimon/update/${element.name}"><img alt="${element.name}"
					src="${element.img}"></a>
				<div>${element.name}</div>
				<div>${element.level}</div>
			
				<a class="button" href= "/favorite/${element.name}">Add to favorite</a>
				<a class="button" href= "/digimon/delete/${element.id}">Delete</a>
			</div>
		</c:forEach>


	</div>



</body>
</html>