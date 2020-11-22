<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html lang="en">
    <head>
      	 <title>Review</title>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
      	<link rel = "stylesheet" type = "text/css" href = "Review.css" />
      	<script src= "Review.js"></script>
      	<link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
      	
      	<% HttpSession sesh = request.getSession(false); String userName = "anon"; String name = "anon";
      	if (sesh == null) {
      	    // No session present, you can create yourself 
      	    //redirect to sign in 
      		response.sendRedirect("Login.html");
      	} else {
      	    // Already created. 
     
      	    userName = (String) session.getAttribute("UserName");
      	    name = (String) session.getAttribute("Name");
      		
      	    //redirect to sign in 
      	    if(userName == null || name == null){
      	    	response.sendRedirect("Login.html");
      	    }
      	   
      		
      	}
      	
      	String prof = request.getParameter("prof");
      	String section = request.getParameter("section");
      	String className = request.getParameter("classname");
      	
      	
      
      	%>
    </head>
    <body>
    	<div class="TopBar" >
    		<nav class="NavBar">
    			<div class="Align">
    				<div class="Title">SCearch</div>
    			</div>
    		</nav>
    		<div class= "User"><a class= "WriteRev" href="searchCourse.jsp">Search</a></div>
    		<div class= "User"><a  class= "WriteRev" href="Profile.jsp">Welcome, <%=name%></a></div>
			<div class ="Divider"> | </div>
			<div class="Log"><a  class="Log" href="LogIn.html">LogOut</a></div>
    	</div>
    	<div class="Content">
    		
    		<div class="ReviewSect">
    			<div class="ClassTitle Text"><%=section%> </div>
    			<div class="Prof Text">Professor <%=prof%></div>
    			<div class="Header Text"> Write a Review as:</div>
    			
    			<form class="Form" action="ReviewServlet" method="POST">
    			<div class="Who Text">
    				<input type="radio" id="user" name="user" value="user" required>
  					<label for="user"> <%=userName%> </label><br>
  					<div class ="Divider"> | </div>
  					<input type="radio" id="anon" name="user" value="anon">
 					<label for="anon">Anon</label><br>
    			</div>
    			<input type="hidden" id="section" name="section" value="<%=section%>" >
    			<input type="hidden" id="prof" name="prof" value="<%=prof%>">
    			<input type="hidden" id="classname" name="classname" value="<%=className%>">
    	 		<textarea class="ReviewInput"id="review" name="review" required ></textarea>
        		<button type="submit" > Submit Review</button>
        		</form>
        		
        	</div>
        	
        	
    	
    	</div>
    	
    </body>
</html>