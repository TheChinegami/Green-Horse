package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.UserDao;
import functional.MyCon;
import functional.SendMail;

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
		String firstname,lastname,displayname,confirmpassword,email,password;
		User user = new User();
		UserDao userDao = new UserDao();
		
		HttpSession session = request.getSession();
		
		// get the values of the input fields
		firstname = request.getParameter("firstname");
		lastname = request.getParameter("lastname");
		confirmpassword=request.getParameter("confirmpassword");
		email = request.getParameter("email");
		password = request.getParameter("password");
		displayname=request.getParameter("displayname");
		
		// if the password 1 and 2 didn't match
		if(!password.equals(confirmpassword))
		{
			session.setAttribute("message","password_error");
			this.getServletContext().getRequestDispatcher("/RegisterPage").forward(request, response);		
			return;
		}
		

		try {
			// if the user already have an account
			if(userDao.isUserExists(email)) {
				session.setAttribute("message","email_error");
				this.getServletContext().getRequestDispatcher("/RegisterPage").forward(request, response);
			}else {
				user.setFirstName(firstname);
				user.setLastName(lastname);
				user.setDisplayName(displayname);
				user.setEmail(email);
				user.setPassword(confirmpassword);
				userDao.insertUser(user);
				
				User u = userDao.getUserByEmailAndPassword(email, confirmpassword);
				String url = "http://localhost:8080/Green-Horse/VerificationServlet?id="+u.getId()+"&email="+u.getEmail();
				SendMail.send(u.getEmail(),"Green Horse email verification",url);
				
				session.setAttribute("message","register_success");
				this.getServletContext().getRequestDispatcher("/LoginPage").forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
