package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import functional.MyCon;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		// TODO Auto-generated method stub
		PreparedStatement st;
		ResultSet rs;
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		try {
			st = MyCon.getCon().prepareStatement("select * from user where email = ?;");
			st.setString(1, email);
			rs = st.executeQuery();
			if(!rs.next()) {
				session.setAttribute("error_message","this account is not exist");
				this.getServletContext().getRequestDispatcher("/LoginPage").forward(request, response);
				return;
			} 
//			else {
//				session.setAttribute("error_message","wtf bro you have an error");
//				session.removeAttribute(ERROR_MESSAGE);
//				this.getServletContext().getRequestDispatcher("/LoginPage").forward(request, response);
//				response.sendRedirect("/Green-Horse/LoginPage");
//			}
			st = MyCon.getCon().prepareStatement("select * from user where email = ? and password = ?;");
			st.setString(1, email);
			st.setString(2, password);
			rs = st.executeQuery();
			if(!rs.next()) {
				session.setAttribute("error_message","password uncorrect");
				this.getServletContext().getRequestDispatcher("/LoginPage").forward(request, response);
				return;
			} 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/MainPage").forward(request, response);
		
		
		
	}

}
