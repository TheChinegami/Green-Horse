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
import java.sql.ResultSet;
import java.sql.SQLException;

import functional.MyCon;



/**
 * Servlet implementation class ProfileServlet
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String firstname,lastname,displayname,email,password,newpassword,confirmpassword;
		
		HttpSession session = request.getSession(); 
		
		firstname = request.getParameter("firstname");
		lastname = request.getParameter("lastname");
		displayname = request.getParameter("displayname");
		email = request.getParameter("email");
		password = request.getParameter("password");
		newpassword = request.getParameter("newpassword");
		confirmpassword = request.getParameter("confirmpassword");
		
		
		

		if(!password.equals(((User) session.getAttribute("current_user")).getPassword()))
		{
			session.setAttribute("error_message","You entered a wrong password");
			this.getServletContext().getRequestDispatcher("/ProfilePage").forward(request, response);		
			return;
		}
		
		if(newpassword.isBlank()) {
			
			
			String query = "update user set firstname = ? ,lastname = ? ,displayname = ? where email= ?";

				
				
				
			try {
				
				
				System.out.println(((User)session.getAttribute("current_user")).getEmail());

			
				
				PreparedStatement st = MyCon.getCon().prepareStatement(query);
				st.setString(1, firstname);
				st.setString(2, lastname);
				st.setString(3, displayname);
				st.setString(4, ((User)session.getAttribute("current_user")).getEmail());
				
				 st.execute();
				User user = new User() ;
				  
					user.setFirstName(firstname);
					user.setLastName(lastname);
					user.setDisplayName(displayname);
					user.setId(((User)session.getAttribute("current_user")).getId());
					user.setEmail(((User)session.getAttribute("current_user")).getEmail());
					user.setPassword(((User)session.getAttribute("current_user")).getPassword());
					user.setPhoto(((User)session.getAttribute("current_user")).getPassword());

				
				session.setAttribute("current_user",user);

				// if the query executed
				session.setAttribute("error_message_successfully","Your Informations have registered succefully!");
				this.getServletContext().getRequestDispatcher("/ProfilePage").forward(request, response);
				return;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
		}
		else {
			
			if(!newpassword.equals(confirmpassword))
		{
			session.setAttribute("error_message","your first and second password didn't match");
			this.getServletContext().getRequestDispatcher("/ProfilePage").forward(request, response);		
			return;
		}
			String query = "update user set firstname = ? ,lastname = ? ,displayname = ? ,password = ? where email = ?";


			try {
				PreparedStatement st = MyCon.getCon().prepareStatement(query);
				st.setString(1, firstname);
				st.setString(2, lastname);
				st.setString(3, displayname);
				st.setString(4,newpassword);
				st.setString(5, ((User)session.getAttribute("current_user")).getEmail());
				
				User user = new User() ;
				  
				user.setFirstName(firstname);
				user.setLastName(lastname);
				user.setDisplayName(displayname);
				user.setPassword(newpassword);
				user.setId(((User)session.getAttribute("current_user")).getId());
				user.setEmail(((User)session.getAttribute("current_user")).getEmail());
				user.setPhoto(((User)session.getAttribute("current_user")).getPhoto());

			
			session.setAttribute("current_user",user);
				
				st.executeUpdate();

				// if the query executed
				session.setAttribute("error_message_successfully","Your Informations have registered succefully!");
				this.getServletContext().getRequestDispatcher("/ProfilePage").forward(request, response);
				return;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}

}
