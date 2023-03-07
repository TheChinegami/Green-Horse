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
		ArrayList<Category> list = new ArrayList();
		st = MyCon.getCon().prepareStatement("select * from category");
		rs = st.executeQuery();
		Category c;
		while(rs.next())
		{
			c = new Category();
			c.setId(rs.getInt("id"));
			c.setName(rs.getString("name"));
			c.setIcon(rs.getString("icon"));
			list.add(c);
		}
		return list;
	}
	
}
