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
		ArrayList<Product> list = new ArrayList<Product>();
		st = MyCon.getCon().prepareStatement("select * from product;");
		rs = st.executeQuery();
		Product p;
		while(rs.next())
		{
			p = new Product();
			p.setId(rs.getInt("product_id"));
			p.setTitle(rs.getString("product_title"));
			p.setMinPrice(rs.getDouble("product_minprice"));
			p.setMaxPrice(rs.getDouble("product_maxprice"));
			p.setPhoto(rs.getString("product_photo"));
			p.setRate(rs.getFloat("product_rate"));
			list.add(p);
		}
		close();
		return list;
	}
	
	public Product getProductById(int id) throws ClassNotFoundException, SQLException {
		Product p = new Product();
		st = MyCon.getCon().prepareStatement("select * from product, category where product_category = category_id and product_id = ?");
		st.setInt(1, id);
		rs = st.executeQuery();
		rs.next();
		p.setId(rs.getInt("product_id"));
		p.setTitle(rs.getString("product_title"));
		p.setMinPrice(rs.getDouble("product_minprice"));
		p.setMaxPrice(rs.getDouble("product_maxprice"));
		p.setCategory(rs.getString("category_name"));
		p.setDescription(rs.getString("product_description"));
		p.setPhoto(rs.getString("product_photo"));
		p.setRate(rs.getFloat("product_rate"));
		close();
		return p;
	}
	
	
	private void close() throws SQLException 
	{
		st.close();
		rs.close();
	}
}
