package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Category;
import models.Product;
import models.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CategoryDao;
import dao.ProductDao;

/**
 * Servlet implementation class HomePage
 */
@WebServlet(name = "MainPage", urlPatterns = {"/MainPage"})
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		try
		{
			HttpSession session = request.getSession();
			if(session.getAttribute("current_user")==null)
			{
				User user = new User();
				user.setId(0);
				user.setPhoto("0.jpg");
				session.setAttribute("current_user",user);
			}
			categoriesLoad(session);
			productsLoad(session);
			this.getServletContext().getRequestDispatcher("/jsp/main.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request,response);

	}

	private void categoriesLoad(HttpSession session) throws ClassNotFoundException, SQLException
	{
		CategoryDao categoryDao = new CategoryDao();
		ArrayList<Category> categories = categoryDao.getCategories();
		session.setAttribute("categories",categories);
	}
	
	private void productsLoad(HttpSession session) throws ClassNotFoundException, SQLException
	{
		ProductDao productDao = new ProductDao();
		ArrayList<Product> products = productDao.getProducts();
		productRate(products);
		session.setAttribute("products",products);
	}
	
	private void productRate(ArrayList<Product> products) {
//		for(Product p:products)
//		{
//			float rate = p.getRate();
//			int i = 0;
//			while(i<5 && rate>0) {
//				
//			}
//		}
	}
	
}
