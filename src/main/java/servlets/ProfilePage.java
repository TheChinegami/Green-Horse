package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

import java.io.IOException;
import java.sql.SQLException;

import dao.UserDao;

/**
 * Servlet implementation class ProfilePage
 */
public class ProfilePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		session.getAttribute("current_user");
		UserDao U = new UserDao();
		int Rcount = 0, Ucount = 0;
		try {
			Rcount=U.getReviewsCount(((User) session.getAttribute("current_user")).getId());
			Ucount=U.getUpvotesCount(((User) session.getAttribute("current_user")).getId());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Rcount+"rrrr" + Ucount+"UUU " );
		
		session.setAttribute("ReviewsCount", Integer.toString(Rcount));
		session.setAttribute("UpvotesCount", Integer.toString(Ucount));
		
		
		this.getServletContext().getRequestDispatcher("/jsp/Profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
