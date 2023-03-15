package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Product;
import models.Review;
import models.Vote;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ProductDao;
import dao.ReviewDao;
import dao.VoteDao;

/**
 * Servlet implementation class ProductDetailsPage
 */
public class ProductDetailsPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailsPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		
		int id = Integer.parseInt(request.getParameter("id"));
		Product p = loadProduct(id);
		ArrayList<Review> reviews = loadReview(p.getId());
		loadVotes(reviews);
		p.setReviews(reviews);
		session.setAttribute("current_product", p);
		
		///////////////////////////////////////
//		for(Review r:reviews)
//		{
//			System.out.println("khdam");
//			if(r.getReviewId() == 1) {
//				System.out.println(r.getVoteList());
//			}
//		}
		///////////////////////////////////////
		
		this.getServletContext().getRequestDispatcher("/jsp/product-details.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public Product loadProduct(int id) {
		ProductDao dao = new ProductDao();
		Product p = new Product();
		try {
			p = dao.getProductById(id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public ArrayList<Review> loadReview(int id) {
		ReviewDao dao = new ReviewDao();
		ArrayList<Review> reviews = new ArrayList<Review>();
		try {
			reviews = dao.getReviewsByProductId(id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return reviews;
	}
	
	public void loadVotes(ArrayList<Review> reviews)
	{
		VoteDao voteDao = new VoteDao();
		try {
			for(Review review:reviews) {
				review.setVoteList(voteDao.getVotesOfReview(review.getReviewId()));
			}			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
