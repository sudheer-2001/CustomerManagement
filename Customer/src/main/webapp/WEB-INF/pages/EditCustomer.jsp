<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
	body {
		display: flex;
		flex-direction: column;
		background: #000;
		margin: 0;
		min-height: 100vh;
		background-size: cover;
	}
	* {
		margin: 0;
		padding: 0;
		box-sizing: border-box;
		font-family: "Poppins", sans-serif;
	}

#header {
	background: rgba(0, 0, 0, 0.1);
	height: 50px;
	display: flex;
	justify-content: flex-end;
	align-items: center;
	padding-right: 20px;
	border: 2px solid rgba(255, 255, 255, .2);
}
#header a{
	text-decoration: none;
	background: cyan;
	color: black;
	border: 2px groove violet;
	border-radius: 2px;
}
#home{
	border: none;
}
#div {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 84vh;
}

.wrapper {
	width: 750px;
	height: 500px;
	background-color: rgba(100, 100, 190, 0.1);
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
	backdrop-filter: blur(20px);
	border-radius: 20px;
	color: #fff;
	padding: 40px 35px 55px;
	margin: 30px 10px;
	position: relative;
	overflow: hidden;
}
.wrapper h1 {
	color: #fa0a8e;
	font-size: 36px;
	position: relative;
	bottom: 25px;
}

.wrapper .input-box {
	display: flex;
	justify-content: space-between;
	flex-wrap: wrap;
}

.input-box .input-field {
	position: relative;
	width: 48%;
	height: 45px;
	margin: 18px 0;
}
.input-box .input-field input[value] {
	width: 100%;
	height: 100%;
	background: transparent;
	background: #fff;
	border: 2px solid rgba(30, 30, 30, .9);
	outline: none;
	font-size: 16px;
	color: navy;
	border-radius: 18px;
	padding: 15px 15px 15px 40px;
}

input:focus {
	font-weight: 600;
}

.input-field input:hover {
	border: 2px groove rgba(255, 80, 114, 0.9);
}
input {
	font-weight: 30px;
	font-size: 30px;
}

.input-box .input-field input::value {
	color: #001f3f;
	font-size: 15px;
}
.wrapper .btn {
	width: 20%;
	height: 45px;
	background: #fff;
	border: none;
	outline: none;
	border-radius: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border: 1px groove #0050c8;
	cursor: pointer;
	font-size: 16px;
	color: #333;
	font-weight: 900;
	margin-top: 15px;
	margin-left: 78%;
}

.wrapper .btn:hover {
	color: #fff;
	background: #001919;
}
#footer {
	margin-top: auto;
	background: rgba(0, 0, 0, 0.1);
	height: 50px;
	border: 2px solid rgba(255, 255, 255, .2);
}

@media ( max-width : 576px) {
	.input-box .input-field {
		width: 100%;
		margin: 10px 0;
	}
}
</style>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	%>
	<header id="header">
	</header>
	<div id="div">
		<div class="wrapper">
			<form onsubmit="return validate()" action="${pageContext.request.contextPath}/updateDetails/${customerId}" method="post">
				<h1>
					Edit Customer Details
				</h1>
				<div class="input-box">
					<div class="input-field">
						<input id="fname" type="text" name="firstName"
							value="${firstName}">
					</div>
					<div class="input-field">
						<input id="lname" type="text" name="lastName" value="${lastName}">
					</div>
				</div>
				<div class="input-box">
					<div class="input-field">
						<input id="street" type="text" name="street"
							value="${street}">

					</div>
					<div class="input-field">
						<input id="address" type="text" name="address"
							value="${address}">
					</div>
				</div>
				<div class="input-box">
					<div class="input-field">
						<input id="city" type="text" name="city"
							value="${city}"> 
					</div>
					<div class="input-field">
						<input type="text" name="state" value="${state}">
					</div>
				</div>
				<div class="input-box">
					<div class="input-field">
						<input id="email" type="email" name="email"
							value="${email}"> 
					</div>
					<div class="input-field">
						<input id="phone" type="tel" name="mobileNumber"
							value="${mobileNumber}">
					</div>
				</div>
				<button type="submit" class="btn">Submit</button>
			</form>
		</div>
	</div>
	<div id="result">
		<h1>${result}</h1>
	</div>
	<footer id="footer"></footer>
</body>
</html>