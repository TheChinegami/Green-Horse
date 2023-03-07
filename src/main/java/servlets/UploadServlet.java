package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import functional.MyCon;

/**
 * Servlet implementation class UploadServlet
 */
@MultipartConfig
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Part part = request.getPart("upload");
		InputStream is = part.getInputStream();
		try {
			PreparedStatement ps = MyCon.getCon().prepareStatement("\r\n"
					+ "insert into product(id,title,minprice,maxprice,photo,rate,category,user) values(7,'Global Version Xiaomi 12T Smartphone 128GB/256GB MediaTek Dimensity 8100-Ultra 108MP Camera 120Hz Display 120W' ,4114.32 ,6000.00, ?,1.5,4,1);");
			ps.setBlob(1, is);
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
