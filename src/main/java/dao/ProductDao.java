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
		st = MyCon.getCon().prepareStatement("select * from product where product_accept = 1;");
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
	
	public int getHighestProductId()  throws ClassNotFoundException, SQLException {
	int id;
	st = MyCon.getCon().prepareStatement("SELECT MAX( product_id ) FROM product");
	rs = st.executeQuery();
	rs.next();
	id=rs.getInt(1);
	
			
	return(id);}
	
	public void setDecisionAccepted( int id ) throws ClassNotFoundException, SQLException {
		
		
		st = MyCon.getCon().prepareStatement("Update product set product_accept = ? where product_id = ?");
		st.setInt(1, 1);
		st.setInt(2, id);
		
		st.execute();
			
	}
	
	public void DeleteProduct (int id) throws ClassNotFoundException, SQLException{
		
		
		st=MyCon.getCon().prepareStatement("Delete from product where product_id= ?");
		st.setInt(1, id);
		st.execute();		
	}
	
	public  void resetHighestId( int id ) throws ClassNotFoundException, SQLException { 
		
		st=MyCon.getCon().prepareStatement("ALTER TABLE product AUTO_INCREMENT = ?");
		st.setInt(1,id);
		st.execute();
		
	}
	
	public ArrayList<Product> getProductByCategory(int category, int size, int offset) throws ClassNotFoundException, SQLException {
		ArrayList<Product> list = new ArrayList<Product>();
		st = MyCon.getCon().prepareStatement("select * from product where product_category = ? and product_accept = 1 limit ? offset ?;");
		st.setInt(1, category);
		st.setInt(2, size);
		st.setInt(3, offset);
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
	
	public ArrayList<Product> getProductBySearch(String title, int size, int offset) throws ClassNotFoundException, SQLException {
		ArrayList<Product> list = new ArrayList<Product>();
		st = MyCon.getCon().prepareStatement("select * from product where product_title like CONCAT( '%',?,'%') and product_accept = 1 limit ? offset ?;");
		st.setString(1, title);
		st.setInt(2, size);
		st.setInt(3, offset);
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
	
	public int getCountProductByCategory(int category) throws ClassNotFoundException, SQLException {
		int num = 0;
		st = MyCon.getCon().prepareStatement("select count(*) from product where product_category = ? and product_accept = 1;");
		st.setInt(1, category);
		rs = st.executeQuery();
		rs.next();
		num = rs.getInt(1);		
		close();
		return num;
	}

	public int getCountProductBySearch(String title) throws ClassNotFoundException, SQLException {
		int num = 0;
		st = MyCon.getCon().prepareStatement("select count(*) from product where product_title like CONCAT( '%',?,'%') and product_accept = 1;");
		st.setString(1, title);
		rs = st.executeQuery();
		rs.next();
		num = rs.getInt(1);
		close();
		return num;
	}
	
	
	public ArrayList<Product> getRequests() throws ClassNotFoundException, SQLException {
		ArrayList<Product> list = new ArrayList<Product>();
		st = MyCon.getCon().prepareStatement("select * from product where product_accept = 0;");
		rs = st.executeQuery();
		Product p;
		while(rs.next())
		{
			p = new Product();
			p.setId(rs.getInt("product_id"));
			p.setTitle(rs.getString("product_title"));
			p.setPhoto(rs.getString("product_photo"));
			list.add(p);
		}
		close();
		return list;
	}
	
	public void insertProduct(Product P) throws ClassNotFoundException, SQLException {
		
		st = MyCon.getCon().prepareStatement("INSERT into product (product_title,product_description,product_minprice,product_maxprice,product_photo,product_rate,product_category,product_user,product_accept) values (?,?,?,?,?,?,?,?,?)");
		st.setString(1,P.getTitle() );
		st.setString(2,P.getDescription() );
		st.setFloat(3,(float) P.getMinPrice() );
		st.setFloat(4,(float) P.getMaxPrice() );
		st.setString(5,P.getPhoto() );
		st.setFloat(6,P.getRate() );
		st.setString(7,P.getCategory() );
		st.setInt(8,P.getUser().getId() );
		st.setInt(9,0 );
		st.executeUpdate();
	}
	
	public int getCategoryId(String cat) {
		
			int C=0 ;
			switch (cat) {
			
			case "Electronics" :
				C = 4 ;
				break;
			case "Beauty & Health" :
				C = 3 ;
				break;
			case "Men's Fashion" :
				C = 1;
				break;
			
			case "Kids's Fashion" :
				C=2 ;
				break;
			}
		
		
		return C;
		
	}
	
	private void close() throws SQLException 
	{
		st.close();
		rs.close();
	}
}
