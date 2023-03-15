package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import models.Vote;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.http.HttpSession;

import dao.VoteDao;

/**
 * Servlet implementation class VoteServlet
 */
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		VoteDao voteDao = new VoteDao();
		
		int reviewId = Integer.parseInt(request.getParameter("review_id"));
		int decision = Integer.parseInt(request.getParameter("decision"));
		int productId = Integer.parseInt(request.getParameter("product_id"));
		int userId = ((User)session.getAttribute("current_user")).getId();
		
		String decisionString = (decision==1) ? "up" : "down";
		boolean voteBefore = false;
		
		if(userId == 0) {
			this.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			return;
		}
		
		ArrayList<Vote> votes = new ArrayList();
		try {
			votes = voteDao.getVotesOfReview(reviewId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Vote vote:votes) {
			if(vote.getUser().getId() == userId) {
				voteBefore = true;
				if(vote.getDecision() == decision) {
					this.getServletContext().getRequestDispatcher("/ProductDetailsPage?id="+productId).forward(request, response);
					System.out.println("you already voted "+decisionString+" to this review");
					return;
				}				
			}
		}
		
		try {
			if(voteBefore) {
				voteDao.updateVote(userId,reviewId,decision);
				System.out.println("you updated your vote successfully");
			}else {
				voteDao.insertVote(userId,reviewId,decision);
				System.out.println("you add new vote successfully");
			}
			this.getServletContext().getRequestDispatcher("/ProductDetailsPage?id="+productId).forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
