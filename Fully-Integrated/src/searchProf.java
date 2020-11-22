import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/searchProf")
public class searchProf extends HttpServlet
{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// sql initializations
		String db = "jdbc:mysql://scearch.cgmp7xzel2am.us-west-1.rds.amazonaws.com:3306/scearch?serverTimezone=PST";
		String user = "admin";
		String pwd = "admin123";
		String sql = "SELECT Instructor, Overall_Rating, Days, Time, Course_title \n" + 
				"FROM ClassInfo\n" + 
				"WHERE Course_number LIKE ?";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String userName = "";
		String name = "";
		HttpSession session = request.getSession(false);
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
		
		
		// gets course to search
		String course= request.getParameter("course");
		System.out.println(course);
		response.setContentType("text/html");
		
		// heading
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Search</title>"
				+ "<link rel = \"stylesheet\" type = \"text/css\" href = \"search.css\" />\n"
				+ "<link href=\"https://fonts.googleapis.com/css2?family=Lobster&display=swap\" rel=\"stylesheet\">\n" 
				+ "</head>");
		out.println("<body>");
		out.println("<div class=\"TopBar\" >\n" + 
				"<nav class=\"NavBar\">\n" + 
				"<div class=\"Align\">\n" + 
				"<div class=\"Title\">SCearch</div>\n" + 
				"</div>\n" + 
				"</nav>\n" +
				"<div class= \"User\"><a a class= \"WriteRev\" href=\"Profile.jsp\">Welcome, "+name+"</a></div>\n" + 
				"<div class =\"Divider\"> | </div>\n" + 
				"<div class=\"Log\"><a class=\"Log\" href=\"LogIn.html\">LogOut</a></div>\n"+
				"</div>");
		
		// professor for course
		out.println("<h2>Professors for "+course+"</h2>");
		out.println("<div class=\"center\">");
		
		
		out.println("<form id=\"profList\" action=\"SearchReview\" method=\"GET\">");
		out.println(
				"<div class=\"center\"> <table border=\"1\" cellpadding=\"5\">\n" + 
				"<tr>\n" + 
				"<th>Professor</th>\n" + 
				"<th>Overall Rating</th>\n" + 
				"<th>Days</th>\n" + 
				"<th>Time</th>\n" + 
				 "</tr>\n");
		
		// retrieves course from the database
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
			  PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, "%"+course+"%");
			ResultSet rs = ps.executeQuery();
			
			// prints out professors for a course
			while (rs.next())
			{
				String myProf = rs.getString("Instructor");
				out.println("<tr><td><input type=\"radio\" name=\"prof\" value = \"" + myProf+","+course+","+rs.getString("Course_title")+ "\"" + ">\n"+
						"<label>" +rs.getString("Instructor")+"</label></td>");
				out.println(
				"<td>" + rs.getString("Overall_Rating") + "</td>\n");
				out.println(
						"<td>" + rs.getString("Days") + "</td>\n");
				out.println(
				"<td>" + rs.getString("Time") + "</td>\n </tr>\n");
					
				
			}
			out.println("</table></div> \n");
			out.println("<input type=\"submit\" value=\"Search\"></form>");
			out.println("</div>");
			out.println("</body></html>");
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
	}
}