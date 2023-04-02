package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Category;
import models.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CategoryDao;

/**
 * Servlet implementation class RequestSendPage
 */
public class RequestSendPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestSendPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();


		int userId = ((User)session.getAttribute("current_user")).getId();
		if(userId == 0) {
			this.getServletContext().getRequestDispatcher("/LoginPage").forward(request, response);
			return;
		}
		CategoryDao cate = new CategoryDao();
		ArrayList<Category> Clist = new ArrayList();
		try {
			Clist=cate.getCategories();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("Clist", Clist);
		
		this.getServletContext().getRequestDispatcher("/jsp/request-send.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
