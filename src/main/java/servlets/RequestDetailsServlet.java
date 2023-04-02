package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import dao.ProductDao;

/**
 * Servlet implementation class RequestDetailsServlet
 */
public class RequestDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestDetailsServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		HttpSession session = request.getSession();	
		ProductDao P = new ProductDao();

		int decision = Integer.parseInt(request.getParameter("decision")) , Pid = Integer.parseInt(request.getParameter("productid"));
		
		if (decision == 1) {
			
			try {
				P.setDecisionAccepted(Pid);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("error_message_successfully","The Product has been accepted");
			this.getServletContext().getRequestDispatcher("/RequestsPage").forward(request, response);
			return;
		}
		else {
					
			try {
				P.DeleteProduct(Pid);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Path filePath = Paths.get(getServletContext().getRealPath("/")+"products-images/",Pid+".jpg" );
			
			// create a File object for the file to be deleted
			File fileToDelete = new File(filePath.toString());

			// use the delete() method to delete the file
			if (fileToDelete.delete())
			
			{
			    System.out.println("File deleted successfully");
			} else {
			    System.out.println("Failed to delete the file");
			}
			
			session.setAttribute("error_message_successfully","The product has been refused and deleted ");
			this.getServletContext().getRequestDispatcher("/RequestsPage").forward(request, response);
			return;
			
			
		}
		
		
	
	
	
	}

}
