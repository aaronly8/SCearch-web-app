

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String nextPage = "/searchCourse.jsp";

   public void init() throws ServletException {
      // Do required initialization
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	   
      // Set response content type
      response.setContentType("text/html");
      
      	HttpSession session = request.getSession(true);
      	
      	String db = "jdbc:mysql://scearch.cgmp7xzel2am.us-west-1.rds.amazonaws.com:3306/scearch";
		String user = "admin";
		String pwd = "admin123";
 
  		String sql1 = "INSERT INTO login (user, pass) VALUES  (?, ?)";
  		String sql2 = "INSERT INTO profile (user, fname, lname, major, email) VALUES  (?, ?, ?, ?, ?)";
  		String sql3 = "{CALL findProcedure(?, ?, ?)}";
  		String sql4 = "{CALL findProcedureProfile(?, ?, ?, ?, ?, ?, ?)}";
  		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
  		String usernameinput = request.getParameter("createusername");
  		String passwordinput = request.getParameter("createpassword");
  		String fnameinput = request.getParameter("createfirstname");
  		String lnameinput = request.getParameter("createlastname");
  		String majorinput = request.getParameter("createmajor");
  		String emailinput = request.getParameter("createemail");
  		String username = request.getParameter("createusername");
  		String password = request.getParameter("password");
  		
  		
		if (usernameinput != null && passwordinput != null) {
			try (Connection conn = DriverManager.getConnection(db, user, pwd);
					CallableStatement stmt = conn.prepareCall(sql3);) {
		  			stmt.setString(1, usernameinput);
		  			stmt.setString(2, passwordinput);
					stmt.registerOutParameter(3, Types.INTEGER);

					stmt.executeUpdate();
					int result = stmt.getInt(3);
					
					if (result == 1) {
						nextPage = "/LogIn.html";
					}
	  		    	
		  		} catch (SQLException ex) {
		  			System.out.println ("SQLException: " + ex.getMessage());
		  		}
			
			try (Connection conn = DriverManager.getConnection(db, user, pwd);
	  		    PreparedStatement ps = conn.prepareStatement(sql1);) {
	  				ps.setString(1, usernameinput);
	  		    	ps.setString(2, passwordinput);
	  		    	ps.executeUpdate();
	  		      
	  		} catch (SQLException ex) {
	  			System.out.println ("SQLException: " + ex.getMessage());
	  		}
	  		
	  		try (Connection conn = DriverManager.getConnection(db, user, pwd);
		  		    PreparedStatement ps = conn.prepareStatement(sql2);) {
		  				ps.setString(1, usernameinput);
		  		    	ps.setString(2, fnameinput);
		  		    	ps.setString(3, lnameinput);
		  		    	ps.setString(4, majorinput);
		  		    	ps.setString(5, emailinput);
		  		    	ps.executeUpdate();   
		  		      
		  		} catch (SQLException ex) {
		  			System.out.println ("SQLException: " + ex.getMessage());
		  		}
		}
		
		if (username != null && password != null) {
			try (Connection conn = DriverManager.getConnection(db, user, pwd);
				CallableStatement stmt = conn.prepareCall(sql3);) {
	  			stmt.setString(1, username);
	  			stmt.setString(2, password);
				stmt.registerOutParameter(3, Types.INTEGER);

				stmt.executeUpdate();
				int result = stmt.getInt(3);
				
				if (result != 1) {
					nextPage = "/LogIn.html";
				}
  		    	
	  		} catch (SQLException ex) {
	  			System.out.println ("SQLException: " + ex.getMessage());
	  		}
		}
		
		if (username == null) {
			session.setAttribute("isguest", true);
			session.setAttribute ("UserName", "Guest");
			session.setAttribute ("Name", "Guest");
			session.setAttribute ("LName", "None");
			session.setAttribute ("Major","None");
			session.setAttribute ("Email", "None");
		}
		else {
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
				CallableStatement stmt = conn.prepareCall(sql4);) {
					stmt.setString(1, username);
					stmt.registerOutParameter(2, Types.VARCHAR);
				stmt.registerOutParameter(3, Types.VARCHAR);
				stmt.registerOutParameter(4, Types.VARCHAR);
				stmt.registerOutParameter(5, Types.VARCHAR);
				stmt.registerOutParameter(6, Types.VARCHAR);
				stmt.registerOutParameter(7, Types.VARCHAR);
				stmt.executeUpdate();
				
				session.setAttribute("isguest", false);
				session.setAttribute ("UserName", username);
				session.setAttribute ("Name", stmt.getString(2));
				session.setAttribute ("LName", stmt.getString(3));
				session.setAttribute ("Major", stmt.getString(4));
				session.setAttribute ("Email", stmt.getString(5));
			    	
				} catch (SQLException ex) {
					System.out.println ("SQLException: " + ex.getMessage());
				}
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
		
		
   }
   

   public void destroy() {
      // do nothing.
   }
}



