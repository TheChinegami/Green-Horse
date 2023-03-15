package functional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyCon {

	private static Connection cnx;
	
	private MyCon() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/green_horse","root","");

	}
	
	public static Connection getCon() throws ClassNotFoundException, SQLException
	{
		if(cnx==null)
		{
			new MyCon();
		}
		return cnx;
	}
}
