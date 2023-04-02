package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import models.Product;
import models.User;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.io.IOException;
import java.io.InputStream;

import dao.ProductDao;
import dao.UserDao;

/**
 * Servlet implementation class RequestSendServlet
 */


@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
	    maxFileSize = 1024 * 1024 * 10, // 10 MB
	    maxRequestSize = 1024 * 1024 * 50 // 50 MB
	)

public class RequestSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestSendServlet() {
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
		
		HttpSession session = request.getSession();	
		
		int category;
		float minprice = 0,maxprice = 0;
		int Hid=0,X = 0;
		
		Product product = new Product();
		ProductDao P = new ProductDao();
		UserDao userDao = new UserDao();
		ProductDao productDao = new ProductDao();		
		
		Part filePart = request.getPart("file");
		
		product.setTitle(request.getParameter("productname"));
		System.out.println(request.getParameter("productname"));	
		
		category = Integer.parseInt(request.getParameter("category"));
		System.out.println(category);

		product.setCategory(Integer.toString(category));
		product.setDescription(request.getParameter("description")) ;
		product.setMinPrice(Float.parseFloat(request.getParameter("minprice")));
		product.setMaxPrice(Float.parseFloat(request.getParameter("maxprice")));
		product.setRate(0);
		product.setUser((User) session.getAttribute("current_user"));
		
		
		
	
		if(filePart != null && filePart.getSize() > 0){
					
		// get the values of the input fields
		
		
		try {
			Hid=productDao.getHighestProductId();
			
			X=Hid+1;
			System.out.println(Hid);
			
			productDao.resetHighestId(Hid);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String newFileName = X + ".jpg";
		
		System.out.println(newFileName);
		
		product.setPhoto(newFileName);
		
		InputStream fileContent = filePart.getInputStream();
		
		Path filePath = Paths.get(getServletContext().getRealPath("/")+"products-images/",newFileName );
		
		System.out.println(filePath);
		
		Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);

		}
		else {
			session.setAttribute("error_message","You can't send a request without a picture");
		this.getServletContext().getRequestDispatcher("/RequestSendPage").forward(request, response);		
		return; }
		
		try {
			P.insertProduct(product);
			
			session.setAttribute("error_message_successfully","Your Request has been sent succefully!");
			this.getServletContext().getRequestDispatcher("/MainPage").forward(request, response);
			return;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
