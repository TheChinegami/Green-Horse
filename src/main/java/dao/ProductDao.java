package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import functional.MyCon;
import models.Product;

public class ProductDao {
	
	private PreparedStatement st;
	private ResultSet rs;
	
	public ArrayList<Product> getProducts() throws ClassNotFoundException, SQLException 
	{
		ArrayList<Product> list = new ArrayList();
		st = MyCon.getCon().prepareStatement("select * from product");
		rs = st.executeQuery();
		Product p;
		while(rs.next())
		{
			p = new Product();
			p.setId(rs.getInt("id"));
			p.setTitle(rs.getString("title"));
			p.setMinPrice(rs.getDouble("minprice"));
			p.setMaxPrice(rs.getDouble("maxprice"));
			p.setPhoto(rs.getString("photo"));
			p.setRate(rs.getFloat("rate"));
			list.add(p);
		}
		close();
		return list;
	}
	
	private void close() throws SQLException 
	{
		st.close();
		rs.close();
	}
}
