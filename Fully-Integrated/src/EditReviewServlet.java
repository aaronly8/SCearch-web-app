

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditReviewServlet
 */
@WebServlet("/EditReviewServlet")
public class EditReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Integer id = Integer.parseInt(request.getParameter("revid"));
		System.out.println(id);
		String editedReview = request.getParameter("editedRev");
		System.out.println(editedReview);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		/*
		Call server ReviewFactory Change to Review Factory
		*/
		Boolean serverSuccess = ReviewFactory.editReview(editedReview, id);
		//Boolean serverSuccess = ReviewClasses.editReview(editedReview, Calendar.getInstance().get(Calendar.YEAR), id);
		
		
		if(serverSuccess) {
			out.println("success");
		}else {
			out.println("fail");
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
