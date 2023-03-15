package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import functional.MyCon;
import models.Category;

public class CategoryDao {
	
	private PreparedStatement st;
	private ResultSet rs;
	
	public ArrayList<Category> getCategories() throws ClassNotFoundException, SQLException 
	{
		ArrayList<Category> list = new ArrayList<Category>();
		st = MyCon.getCon().prepareStatement("select * from category");
		rs = st.executeQuery();
		Category c;
		while(rs.next())
		{
			c = new Category();
			c.setId(rs.getInt("category_id"));
			c.setName(rs.getString("category_name"));
			c.setIcon(rs.getString("category_icon"));
			list.add(c);
		}
		return list;
	}
	

	private void close() throws SQLException 
	{
		st.close();
		rs.close();
	}
	
}
