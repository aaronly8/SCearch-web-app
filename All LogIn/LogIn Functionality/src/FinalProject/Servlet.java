package FinalProject;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   private String message;

   public void init() throws ServletException {
      // Do required initialization
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	   
	   String nextPage = "/Profile.jsp";
      // Set response content type
      response.setContentType("text/html");

  		String db = "jdbc:mysql://localhost/SCearch?autoRecconect=true&useSSL=false";
  		String user = "root";
  		String pwd = "C0ppell2";
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
  		
  		String profilefname = "none";
  		String profilelname = "none";
  		String profilemajor = "none";
  		String profileemail = "none";
  		String profileclasses = "none";
  		String profilefriends = "none";
  		
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
		  		    	
		  		      PrintWriter out = response.getWriter();
		  		      out.println("<h1>" + "Created new account!" + "</h1>");
		  		      
		  		} catch (SQLException ex) {
		  			System.out.println ("SQLException: " + ex.getMessage());
		  			PrintWriter out = response.getWriter();
		  		      out.println("<h1>" + "Username taken!" + "</h1>");
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
				
				if (result == 1) {
	  		    	PrintWriter out = response.getWriter();
	  		    	out.println("<h1>" + "Logged in!" + "</h1>");
				}
				else {
					PrintWriter out = response.getWriter();
	  		    	out.println("<h1>" + "Failed to log in" + "</h1>");
				}
  		    	
	  		} catch (SQLException ex) {
	  			System.out.println ("SQLException: " + ex.getMessage());
	  		}
		}
		
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
				
				if (username == "admin") {
					
				}
				else {
					profilefname = stmt.getString(2);
					profilelname = stmt.getString(3);
					profilemajor = stmt.getString(4);
					profileemail = stmt.getString(5);
					profileclasses = stmt.getString(6);
					profilefriends = stmt.getString(7);
				}
			    	
				} catch (SQLException ex) {
					System.out.println ("SQLException: " + ex.getMessage());
				}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
   }
   

   public void destroy() {
      // do nothing.
   }
}



