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
 * Servlet implementation class PartitioningServlet
 */
public class PartitioningServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartitioningServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		HttpSession session = request.getSession();
		int page = Integer.parseInt((String)request.getParameter("search_page"));
		int method = (int)session.getAttribute("search_method");
//		System.out.println("page" +page);
		ProductDao productDao = new ProductDao();
		ArrayList<Product> products = new ArrayList<Product>();

//		System.out.println("method is "+method);
		try {
			if(method == 1) {
				String title = (String)session.getAttribute("search_value");
				products = productDao.getProductBySearch(title,2,2*(page-1));
				
				
			}else {
//				if(session.getAttribute("search_value")==null) {
//					System.out.println("value is null");
//				}else {
//
//					System.out.println("value is "+(int)session.getAttribute("search_value"));
//				}
				int category = (int)session.getAttribute("search_value");
				products = productDao.getProductByCategory(category,2,2*(page-1));
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		session.removeAttribute("products");
		session.setAttribute("products", products);
		
		this.getServletContext().getRequestDispatcher("/jsp/partitioning.jsp").forward(request, response);	
	}

}
