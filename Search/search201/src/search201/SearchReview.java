package search201;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class SearchReview
 */
@WebServlet("/SearchReview")
public class SearchReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchReview() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// sql initializations
		String db = "jdbc:mysql://scearch.cgmp7xzel2am.us-west-1.rds.amazonaws.com:3306/scearch?serverTimezone=PST";
		String user = "admin";
		String pwd = "admin123";
		String sql = "SELECT display, section, classname, body\n"+ 
		"FROM reviews\n" + 
		"WHERE prof= ? ";
		
		// get professor to search
		String prof = request.getParameter("prof");
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
				"<div class= \"User\">Welcome, user</div>\n" + 
				"<div class =\"Divider\"> | </div>\n" + 
				"<div class=\"Log\">LogOut</div>\n" + 
				"</div>");
		
		// reviews for professor
		out.println("<h2>Reviews for "+prof+"</h2>");
		out.println("<div class=\"center\">");
		out.println(
				"<div class=\"center\"> <table border=\"1\" cellpadding=\"5\">\n" + 
				"<tr>\n" + 
				"<th>Student Name</th>\n" + 
				"<th>Class</th>\n" +
				"<th>Review</th>\n" +
				"</tr>\n");
		
		// retrieves reviews from the database
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
			  PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, prof);
			ResultSet rs = ps.executeQuery();

			// prints out reviews for a professor
			while (rs.next())
			{
				out.println("<tr><td>" + rs.getString("display") + "</td> \n");
				out.println(
						"<td>" + rs.getString("section") + " " + rs.getString("classname") + "</td>\n");
				out.println(
						"<td>" + rs.getString("body") + "</td>\n </tr>\n");
			}
			out.println("</table></div>");
			out.println("</div>");
			out.println("</body></html>");
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
