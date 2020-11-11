

import java.io.*;
import java.util.Calendar;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//processCall(request,response);
		HttpSession session = request.getSession(false);
		
      	if (session == null) {
      	    // No session present, go to login 
      		//NOTE THIS IS NOT THE RIGHT ENDPOINT JUST USED FOR DEVELOPING PURPOSES
      		response.sendRedirect("/Review.jsp");
      		//response.sendRedirect(LOGINSCREEN);
      	} else {
      	    // Already created. 
      		//SETTING ATTRUBUTES BELOW WAS FOR DEVELOPING PURPOSES
      		 session.setAttribute("UserName", "tylerf@usc.edu");
      		 session.setAttribute("Name", "Tyler");
      		 session.setAttribute("Major", "CSCI");
      		
      	}
      	//NOTE THIS IS NOT THE FINAL ENDPOINT
		response.sendRedirect("/201ProjFrontEnd/Review.jsp");
		
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
		
		String classs = request.getParameter("class");
		System.out.println(classs);
		
		String prof = request.getParameter("prof");
		System.out.println(prof);
		
		//GET USERNAME(EMAIL),MAJOR from session 
		HttpSession session = request.getSession(false);
		String userName = (String) session.getAttribute("UserName");
  	   	String  major = (String) session.getAttribute("Major");
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		Boolean isSuccess = false;
		if(review != null && user != null) {
			if(user == "anon") {
				isSuccess = ReviewClasses.uploadReview("Anon", classs, prof,"Anon",Calendar.getInstance().get(Calendar.YEAR), review);
			}else {
				isSuccess = ReviewClasses.uploadReview(userName, classs, prof, major,Calendar.getInstance().get(Calendar.YEAR), review);
			}		
		}
		
		//REDIRECT out.println is for developer purposes
		if(isSuccess) {
			//response.sendRedirect(HOMESCREEN);
			out.println("success");
		}else {
			//response.sendRedirect(ERROR MESSAGE);
			out.println("fail");
		}
		     
	     
	}

}
