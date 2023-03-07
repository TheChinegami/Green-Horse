package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import functional.MyCon;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		String firstname,lastname,displayname,email,password,confirmpassword;
		
		HttpSession session = request.getSession();
		
		// get the values of the input fields
		firstname = request.getParameter("firstname");
		lastname = request.getParameter("lastname");
		displayname = request.getParameter("displayname");
		email = request.getParameter("email");
		password = request.getParameter("password");
		confirmpassword = request.getParameter("confirmpassword");
		
		// if the password 1 and 2 don't match
		if(!password.equals(confirmpassword))
		{
			session.setAttribute("error_message","your first and second password didn't match");
			this.getServletContext().getRequestDispatcher("/RegisterPage").forward(request, response);		
			return;
		}
		
		String query = "insert into user (firstname,lastname,displayname,email,password) values(?,?,?,?,?)";

		try {
			PreparedStatement st = MyCon.getCon().prepareStatement(query);
			st.setString(1, firstname);
			st.setString(2, lastname);
			st.setString(3, displayname);
			st.setString(4, email);
			st.setString(5, password);
			
			st.executeUpdate();

			// if the query executed
			session.setAttribute("error_message_successfully","you have registered succefully!");
			this.getServletContext().getRequestDispatcher("/RegisterPage").forward(request, response);
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// if the query failed to execute it mean that we tried to insert an existed email
			session.setAttribute("error_message","you already registred with this email");
			this.getServletContext().getRequestDispatcher("/RegisterPage").forward(request, response);
			return;
//			e.printStackTrace();
		}

	}

}
