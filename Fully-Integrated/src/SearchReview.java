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
		String sql = "SELECT display, section, classname, body, id\n"+ 
		"FROM reviews\n" + 
		"WHERE prof= ? ";
		
		
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
		

		
		// get professor to search
		String prof = request.getParameter("prof");
		String [] realProf = prof.split(",");
		
		System.out.println();
		response.setContentType("text/html");
		String section = realProf[1];
		String classname = realProf[2];
		
		
		
		

		// heading
		PrintWriter out = response.getWriter();
		out.println("<html>");
		
		
		
		
		
		out.println("<head><title>Search</title>"
				+ "<link rel = \"stylesheet\" type = \"text/css\" href = \"search.css\" />\n"
				+ "<link href=\"https://fonts.googleapis.com/css2?family=Lobster&display=swap\" rel=\"stylesheet\">\n" 
				+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n"
				+ "<script src=\"EditDelete.js\"></script>\n"
				+ "</head>");
		out.println("<body>");
		out.println("<div class=\"TopBar\" >\n" + 
				"<nav class=\"NavBar\">\n" + 
				"<div class=\"Align\">\n" + 
				"<div class=\"Title\">SCearch</div>\n" + 
				"</div>\n" + 
				"</nav>\n" +
				"<div class= \"User\"><a class= \"WriteRev\" href=\"ReviewServlet?section="+realProf[1]+"&prof="+realProf[0]+"&classname="+realProf[2]+"\" >Write a Review</a></div>" +
				"<div class= \"User\"><a a class= \"WriteRev\" href=\"Profile.jsp\">Welcome, "+name+"</a></div>\n" + 
				"<div class =\"Divider\"> | </div>\n" + 
				"<div class=\"Log\"><a class=\"Log\" href=\"LogIn.html\">LogOut</a></div>\n"+
				"</div>");
		
		// reviews for professor
		out.println("<h2>Reviews for "+realProf[0]+"</h2>");
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
			ps.setString(1, realProf[0]);
			ResultSet rs = ps.executeQuery();

			// prints out reviews for a professor
			while (rs.next())
			{
				out.println("<tr  id='"+rs.getString("id")+"'>"+"<td>" + rs.getString("display") + "</td> \n");
				out.println(
						"<td>" + rs.getString("section") + " " + rs.getString("classname") + "</td>\n");
				out.println(
						"<td  class='body' id='rev"+rs.getString("id")+"' >" + rs.getString("body") + "</td>\n");
				out.println(
						"<td>" + "<button revID='"+rs.getString("id")+"' class='editbtn' >edit</button>" + "<button  style='display: none;' revID='"+rs.getString("id")+"' class='confirm' >save</button>"+ "</td> \n");
				out.println(
						"<td>" + "<button  revID='"+rs.getString("id")+"' class='deletebtn' >delete</button>" + "</td> \n");
				out.println("</tr>\n");
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
