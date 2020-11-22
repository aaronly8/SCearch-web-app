<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="javax.servlet.http.HttpSession"%>
    
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover">
    <link rel="shortcut icon" type="image/png" href="favicon.png">
    
	<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css?7276">
	<link rel="stylesheet" type="text/css" href="style.css?7433">
	<link rel="stylesheet" type="text/css" href="./css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="./css/feather.min.css">
	
    <title>SCearch</title>
    
</head>

<%
String username = (String) session.getAttribute("UserName");
String fname = (String) session.getAttribute("Name");
String lname = (String) session.getAttribute("LName");
String major = (String) session.getAttribute("Major");
String email = (String) session.getAttribute("Email");
Boolean isguest = (Boolean) session.getAttribute("isguest");
%>
<body>
<!-- Main container -->
<div class="page-container">
    
<!-- nav -->
<div class="bloc l-bloc" id="nav">
	<div class="container bloc-sm">
		<div class="row">
			<div class="col">
				<nav class="navbar navbar-light row navbar-expand-md" role="navigation">
					<a class="navbar-brand" href="./">Profile</a>
				</nav>
			</div>
		</div>
	</div>
</div>
<!-- nav END -->

<!-- demobody -->
<div class="bloc l-bloc" id="resultsBody">
	<div class="container bloc-md">
		<div class="row">
			<div class="col-12">
				<ul class="list-unstyled">
					
					<li>
						<h3 class="mg-md">
							First Name
						</h3>
						<p id="nametext">
							<%= fname %>
						</p>
					</li>
					
					<li>
						<h3 class="mg-md">
							Last Name
						</h3>
						<p id="nametext">
							<%= lname %>
						</p>
					</li>
					
					<li>
						<h3 class="mg-md">
							Username
						</h3>
						<p id="nametext">
							<%= username %>
						</p>
					</li>
					
					<li>
						<h3 class="mg-md">
							Major
						</h3>
						<p id="nametext">
							<%= major %>
						</p>
					</li>
					
					<li>
						<h3 class="mg-md">
							Email
						</h3>
						<p id="emailtext">
							<%= email %>
						</p>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Main container END -->
    

<script src="./js/jquery-3.3.1.min.js?5156"></script>
<script src="./js/bootstrap.bundle.min.js?4376"></script>
<script src="./js/blocs.min.js?6449"></script>
<script src="./js/jqBootstrapValidation.js"></script>
<script src="./js/formHandler.js?8991"></script>
<script src="./js/lazysizes.min.js" defer></script>
<!-- Additional JS END -->

</body>
</html>
