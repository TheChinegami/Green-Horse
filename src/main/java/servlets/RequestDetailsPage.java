package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Product;

import java.io.IOException;
import java.sql.SQLException;

import dao.ProductDao;

/**
 * Servlet implementation class RequestDetailsPage
 */
public class RequestDetailsPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestDetailsPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(); 
		ProductDao P = new ProductDao();
		Product pro = new Product();
		
		System.out.println(request.getParameter("Pid"));
		System.out.println(Integer.parseInt(request.getParameter("Pid")));
		
		try {
			pro = P.getProductById(Integer.parseInt(request.getParameter("Pid"))) ;
		} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(pro.getTitle());
		System.out.println(pro.getPhoto());
		
		 session.setAttribute("Requested_Product", pro);
		 
		this.getServletContext().getRequestDispatcher("/jsp/request-details.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
