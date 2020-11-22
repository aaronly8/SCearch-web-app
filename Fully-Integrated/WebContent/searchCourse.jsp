<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>Search</title>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
      	<link rel = "stylesheet" type = "text/css" href = "search.css" />
      	<link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
</head>

<% HttpSession sesh = request.getSession(false); String userName = ""; String name = "";
      	if (sesh == null) {
      	    // No session present, you can create yourself 
      	    //redirect to sign in 
      		response.sendRedirect("Login.html");
      	    
      	} else {
      	    // Already created. 
      		
      	      
      	    userName = (String) session.getAttribute("UserName");
      	    name = (String) session.getAttribute("Name");
      		
      	    if(userName == null || name == null){
      			response.sendRedirect("Login.html");
       	    }
      	}
      	
      	%>

<body>
	<div class="TopBar" >
    		<nav class="NavBar">
    			<div class="Align">
    				<div class="Title">SCearch</div>
    			</div>
    		</nav>
    			
    		<div class= "User"><a  class= "WriteRev" href="Profile.jsp">Welcome, <%=name%></a></div>
			<div class ="Divider"> | </div>
			<div class="Log"><a  class="Log" href="LogIn.html">LogOut</a></div>
    		
    		
    		
    	</div>
    	<div class="Content">
    	
    		
    		<div class="SearchSection">
    			<form class="Form" action="searchProf" method="GET">
            <label for="course"><strong>Enter a class to search for (Ex: CSCI-201):</strong></label>
            <div>
              <input type="text" name="course">
             <input type = "submit" value = "Search">  
            </div>
          </form>
    		</div>
    	</div>
</body>
</html>