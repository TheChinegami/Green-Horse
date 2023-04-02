package servlets;


import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import models.User;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserDao;
import functional.MyCon;



@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
	    maxFileSize = 1024 * 1024 * 10, // 10 MB
	    maxRequestSize = 1024 * 1024 * 50 // 50 MB
	)
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
		
		HttpSession session = request.getSession(); 
		
		String firstname,lastname,displayname,email,password,newpassword,confirmpassword,newFileName =  Integer.toString(((User) session.getAttribute("current_user")).getId())+".jpg";
		
		UserDao userDao = new UserDao();
		User currentUser;
		
		
		
		
		firstname = request.getParameter("firstname");
		lastname = request.getParameter("lastname");
		displayname = request.getParameter("displayname");
		email =((User) session.getAttribute("current_user")).getEmail();
		password = request.getParameter("password");
		newpassword = request.getParameter("newpassword");
		confirmpassword = request.getParameter("confirmpassword");
		
		Part filePart = request.getPart("file");
		System.out.println(filePart.getSize());
		
		
	/*	if(filePart != null && filePart.getSize() > 0){
			
		//	String originalFileName= filePart.getSubmittedFileName();
			
			// Extract the file extension from the original file name
		//	String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
			
		
		//extracting the previous photo name so we can rewrite it to the new photo
			
			
		//	String query = "update user set user_photo = ? where user_email= ?";
		//	try {
				
				
				System.out.println(((User)session.getAttribute("current_user")).getEmail());

				PreparedStatement st = MyCon.getCon().prepareStatement(query);
				st.setString(1, newFileName);
				st.setString(2, ((User)session.getAttribute("current_user")).getEmail());
				
				 st.execute();

				 
					System.out.println("Uploaded file saved as: " + newFileName + " in " + filePath);
			System.out.println("current user photo :" + ((User) session.getAttribute("current_user")).getPhoto() );
			 /*response.setContentType("image/jpg");
			 currentUser = userDao.getUserByEmailAndPassword(((User)session.getAttribute("current_user")).getEmail(), ((User)session.getAttribute("current_user")).getPassword());
				session.setAttribute("current_user", currentUser); 
			 
			 session.setAttribute("error_message_successfully","Your Informations have registered succefully!");
			 this.getServletContext().getRequestDispatcher("/ProfilePage").forward(request, response);
					return;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}catch (SQLException e) {
					
					e.printStackTrace();
				}
	
		}else { */
		
		if(!password.equals(((User) session.getAttribute("current_user")).getPassword()))
		{
			System.out.println("Test if 1 ");
			session.setAttribute("error_message","You entered a wrong password");
			this.getServletContext().getRequestDispatcher("/ProfilePage").forward(request, response);		
			return;
		}
		else { System.out.println("Test else 1 ");} 
		
			
		if(filePart != null && filePart.getSize() > 0){
			
				System.out.println("Test if 2 ");
				
				InputStream fileContent = filePart.getInputStream();
				
				Path filePath = Paths.get(getServletContext().getRealPath("/")+"profiles-images/", newFileName);
				
				Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
				
			}
		else { 				System.out.println("test else 2 "); }
			
				if(newpassword.isBlank()) { 
					
					System.out.println("Test if 3 ");
				newpassword= ((User) session.getAttribute("current_user")).getPassword();
				;
				
			} else {
				
				System.out.println("Test else 3 ");

				
				
				if(!newpassword.equals(confirmpassword))
				{							System.out.println("Test if 4 ");

					session.setAttribute("error_message","your first and second password didn't match");
					this.getServletContext().getRequestDispatcher("/ProfilePage").forward(request, response);		
					return;
				}else {					System.out.println("Test else 4 "); }}
				
				System.out.println("Test final stuff  ");

				
				String query = "update user set user_firstname = ? ,user_lastname = ? ,user_displayname = ?,user_password = ?, user_photo = ? where user_email= ?";
				try {
					
					
					System.out.println(((User)session.getAttribute("current_user")).getEmail());

					PreparedStatement st = MyCon.getCon().prepareStatement(query);
					st.setString(1, firstname);
					st.setString(2, lastname);
					st.setString(3, displayname);
					st.setString(4, newpassword);
					st.setString(5,newFileName);
					st.setString(6, email);
					
					 st.execute();

					 currentUser = userDao.getUserByEmailAndPassword(email, newpassword);
					 session.setAttribute("current_user", currentUser);

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

