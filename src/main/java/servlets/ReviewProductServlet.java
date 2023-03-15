package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Person;
import models.Product;
import models.Review;
import models.User;

import java.io.IOException;
import java.sql.SQLException;

import dao.ReviewDao;

/**
 * Servlet implementation class ReviewProductServlet
 */
public class ReviewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ReviewDao reviewDao = new ReviewDao();
		
		Product product = new Product();
		User user = new User();
		Review review = new Review();
		
		String title = (String)request.getParameter("title");
		String comment = (String)request.getParameter("comment");
		int product_id = Integer.parseInt((String)request.getParameter("product_id"));
		float rate = Float.parseFloat((String)request.getParameter("rate"));
		int user_id = ((User)session.getAttribute("current_user")).getId();

		try {
			if(reviewDao.isHeHaveAReview(user_id,product_id)==true) {
				this.getServletContext().getRequestDispatcher("/jsp/product-review-success.jsp?success=0&product_id="+product_id).forward(request, response);
				return;
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		user.setId(user_id);
		product.setId(product_id);
		
		review.setUser(user);
		review.setProduct(product);
		review.setTitle(title);
		review.setComment(comment);
		review.setRate(rate);
		
		try {
			reviewDao.insertReview(review);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		this.getServletContext().getRequestDispatcher("/jsp/product-review-success.jsp?success=1&product_id"+product_id).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
