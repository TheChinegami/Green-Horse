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
import java.util.ArrayList;

import dao.ProductDao;

/**
 * Servlet implementation class SearchByTitleServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		HttpSession session = request.getSession();
		
		ProductDao productDao = new ProductDao();
		ArrayList<Product> products = new ArrayList<Product>();
		
		int method = Integer.parseInt((String)request.getParameter("search_method"));
		String value = (String)request.getParameter("search_value");
		
//		System.out.println("method : "+method);
		

		session.removeAttribute("search_method");
		session.removeAttribute("search_value");
		
		int productsCount = 0;
		if(method==1) {
			String title = value;
			try {
				productsCount = productDao.getCountProductBySearch(title);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			session.setAttribute("search_method", 1);
			session.setAttribute("search_value", title);
//			System.out.println(title);
		}else {
			int category = Integer.parseInt(value);
			try {
				productsCount = productDao.getCountProductByCategory(category);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			session.setAttribute("search_method", 2);
			session.setAttribute("search_value", category);
//			System.out.println(category);
		}

		session.setAttribute("products_count", productsCount);
		
		
		this.getServletContext().getRequestDispatcher("/jsp/search.jsp").forward(request, response);	
	}

}
