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
		// TODO Auto-generated method stub

		System.out.println("0");
		
		HttpSession session = request.getSession();
		
		String firstname,lastname,displayname,email,password,confirmpassword;
		firstname = request.getParameter("firstname");
		lastname = request.getParameter("lastname");
		displayname = request.getParameter("displayname");
		email = request.getParameter("email");
		password = request.getParameter("password");
		confirmpassword = request.getParameter("confirmpassword");
		
		if(!password.equals(confirmpassword))
		{
			session.setAttribute("error_message","your first and second password didn't match");
			this.getServletContext().getRequestDispatcher("/RegisterPage").forward(request, response);		
			return;
		}
		
		String query = "insert into user values(?,?,?,?,?)";
		

//		System.out.println("1");
		try {
			PreparedStatement st = MyCon.getCon().prepareStatement(query);
			st.setString(1, firstname);
			st.setString(2, lastname);
			st.setString(3, displayname);
			st.setString(4, email);
			st.setString(5, password);
			
			int rowcount = st.executeUpdate();
			
//			if(!(rowcount > 0))
//			{
//				session.setAttribute("error_message","you already registred with this email");
//				this.getServletContext().getRequestDispatcher("/RegisterPage").forward(request, response);		
//				return;
//			}
//
//			this.getServletContext().getRequestDispatcher("/LoginPage").forward(request, response);

//			System.out.println("2");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute("error_message","you already registred with this email");
			this.getServletContext().getRequestDispatcher("/RegisterPage").forward(request, response);		
			return;
		}
//		this.getServletContext().getRequestDispatcher("/LoginPage").forward(request, response);

		session.setAttribute("error_message","you have registered succefuly!");
		this.getServletContext().getRequestDispatcher("/RegisterPage").forward(request, response);
//		System.out.println("3");
//		doGet(request, response);
	}

}
