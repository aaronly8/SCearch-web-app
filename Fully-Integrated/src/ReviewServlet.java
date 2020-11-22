

import java.io.*;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//processCall(request,response);
		
		HttpSession session = request.getSession(false); String userName = ""; String name = "";
      	if (session == null) {
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
      	
      	String destination = "Review.jsp";
      	RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
      	
      	//NOTE THIS IS NOT THE FINAL ENDPOINT
      	requestDispatcher.forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//PARAMETERS
		String review = request.getParameter("review");
		System.out.println(review);
		//USER IS NOT THE EMAIL THE SESSION USERNAME
		String user = request.getParameter("user");
		System.out.println(user);
		
		String classname = request.getParameter("classname");
		System.out.println("classname");
		System.out.println(classname);
		
		
		String prof = request.getParameter("prof");
		System.out.println(prof);
		
		String section = request.getParameter("section");
		System.out.println(section);
		
		//GET USERNAME(EMAIL),MAJOR from session 
		HttpSession session = request.getSession(false);
		String userName = (String) session.getAttribute("UserName");
  	   	String  major = (String) session.getAttribute("Major");
		
		//PrintWriter out = response.getWriter();
		//response.setContentType("text/html");
		
		
		Boolean isSuccess = false;
		if(review != null && user != null) {
			if(user.equals("anon")) {	
				isSuccess = ReviewFactory.uploadReview("Anon", section, classname, prof, review, Calendar.getInstance().get(Calendar.YEAR),"Anon");
				//isSuccess = ReviewClasses.uploadReview("Anon", classs, prof,"Anon",Calendar.getInstance().get(Calendar.YEAR), review);
			}else {
				isSuccess = ReviewFactory.uploadReview(userName, section, classname, prof, review, Calendar.getInstance().get(Calendar.YEAR), major);
				//isSuccess = ReviewClasses.uploadReview(userName, classs, prof, major,Calendar.getInstance().get(Calendar.YEAR), review);
			}		
		}
		
		//REDIRECT out.println is for developer purposes
		if(isSuccess) {
			//response.sendRedirect(HOMESCREEN);
			response.sendRedirect("searchCourse.jsp");
		}else {
			//response.sendRedirect(ERROR MESSAGE);
			response.sendRedirect("searchCourse.jsp");
		}
		     
	     
	}

}
