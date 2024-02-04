<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/HomePage.css">
<style>
* {
	font-family: sans-serif;
}

.logout-button {
	float: right;
	position: relative;
	top: 8px;
	right: 3%;
}

button {
	padding: 8px 16px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

#addCust {
	width: 142px;
	height: 30px;
	background: #5baff0;
	border: 1px solid #212121;
	border-radius: 5px;
}

#addCust>a {
	text-decoration: none;
	color: red;
	font-weight: bolder;
}

#searchBy {
	width: 142px;
	height: 30px;
	font-weight: bolder;
	border: 1px solid #212121;
	border-radius: 5px;
	background: #93bddb;
}

#searchBy:hover {
	cursor: pointer;
}

#searchBy option {
	text-align: center;
	background: #0d072e;
	color: #fff;
}

#searchBar {
	width: 122px;
	height: 26px;
	font-weight: bolder;
	border: 1px solid #212121;
}

th {
	height: 25px;
	width: 155px;
}

th:nth-child(6) {
	width: 180px;
}

tr:first-child {
	background-color: #a7b1fc;
	animation: animate 10s linear infinite;
}

header {
	margin: 0;
	padding: 0;
	border: none;
	overflow: hidden;
	background: rgba(150, 150, 150, 0.3);
	min-height: 50px;
}

footer {
	margin-top: auto;
	background: rgba(150, 150, 150, 0.3);
	min-height: 50px;
}

#section {
	margin-top: 0;
	padding-top: 0;
}

#section>#addCust {
	position: relative;
	left: 100px;
	top: 10px;
}

#section #searchBy {
	position: relative;
	left: 260px;
	bottom: 22px;
}

#section #searchBar {
	position: relative;
	bottom: 52px;
	left: 425px;
	padding-right: 32px;
}

#section input[type="submit"] {
	position: relative;
	bottom: 51px;
	left: 390px;
	width: 28px;
	height: 25px;
	background: #fff;
	border: none;
	cursor: pointer;
}

#section {
	height: 30px;
}

h2 {
	font-size: 30px;
	color: #fff;
	text-align: center;
}

h2>p {
	position: relative;
	top: 5px;
	padding-bottom: 10px;
}
</style>
<script>
	function go() {
		window.location
				.replace(
						"LogoutPage.jsp",
						'window',
						'toolbar=1,location=1,directories=1,status=1,menubar=1,scrollbars=1,resizable=1');
		self.close()
	}
</script>

</head>
<body>
	<header id="header">
		<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
		response.setHeader("Pragma", "no-cache"); //HTTP 1.0
		response.setHeader("Expires", "0"); //Proxies
		%>
		<a href="logout"><button type="button" class="logout-button"
				onclick="go()">Logout</button></a>
	</header>

	<div id="section">
		<button id="addCust">
			<a href="addCustomer">Add Customer</a>
		</button>

		<form id="filterForm" action="searchBy" method="post">
			<label for="filterCriteria"></label> <select id="searchBy"
				name="searchBy" onchange="this.form.submit()">
				<option value="" selected disabled>Select</option>
				<option value="First Name">First Name</option>
				<option value="City">City</option>
				<option value="Email">Email</option>
				<option value="Phone">Phone</option>
				<option value="Normal">Default</option>
			</select>
		</form>

		<form action="searching">
			<input id="searchBar" type="text" name="searching"> <input
				type="submit" value="&#x1F50E;">
		</form>

	</div>
	<div>
		<div>
			<h2>
				<p>${updateMessage}</p>
			</h2>
			<table id="table2" border="2px">
				<tr id="tr21" style="width: 100%;">
					<th>First Name</th>
					<th>Last Name</th>
					<th>Address</th>
					<th>City</th>
					<th>State</th>
					<th>Email</th>
					<th>Phone</th>
					<th colspan="2">Action</th>
				</tr>
				<c:forEach var="customer" items="${viewCustomers}">
					<tr id="tr22">
						<td>${customer.firstName}</td>
						<td>${customer.lastName}</td>
						<td>${customer.address}</td>
						<td>${customer.city}</td>
						<td>${customer.state}</td>
						<td>${customer.email}</td>
						<td>${customer.mobileNumber}</td>
						<td><a class="cb"
							href="editCustomer/${customer.customerId}">Edit</a></td>
						<td><a class="del-button"
							href="deleteCustomer/${customer.customerId}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<footer id="footer"></footer>
</body>
</html>