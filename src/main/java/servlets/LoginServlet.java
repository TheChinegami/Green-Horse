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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CategoryDao;
import dao.ProductDao;
import functional.MyCon;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement st;
	private ResultSet rs;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		try {
			st = MyCon.getCon().prepareStatement("select * from user where email = ?;");
			st.setString(1, email);
			rs = st.executeQuery();
			// if the inserted email is not exist
			if(!rs.next()) {
				session.setAttribute("error_message","this account is not exist");
				this.getServletContext().getRequestDispatcher("/LoginPage").forward(request, response);
				return;
			} 
			
			st = MyCon.getCon().prepareStatement("select * from user where email = ? and password = ?;");
			st.setString(1, email);
			st.setString(2, password);
			rs = st.executeQuery();
			// if the inserted password is not correct		
			if(!rs.next()) {
				session.setAttribute("error_message","password uncorrect");
				this.getServletContext().getRequestDispatcher("/LoginPage").forward(request, response);
				return;
			} 

			User user = new User() ;
			// if the email and password both correct
				user.setId(rs.getLong(1));
				user.setEmail(rs.getNString(2));
				user.setPassword(rs.getNString(3));
				user.setFirstName(rs.getNString(4));
				user.setLastName(rs.getNString(5));
				user.setDisplayName(rs.getNString(6));
				user.setPhoto(rs.getNString(7));
			
			session.setAttribute("current_user",user);
			this.getServletContext().getRequestDispatcher("/MainPage").forward(request, response);
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
	}
	

}
