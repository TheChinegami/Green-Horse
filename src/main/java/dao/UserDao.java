package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import functional.MyCon;
import models.Category;
import models.User;

public class UserDao {

	private PreparedStatement st;
	private ResultSet rs;
	
	public void insertUser(User user) throws ClassNotFoundException, SQLException 
	{
		String query = "insert into user(user_firstname,user_lastname,user_displayname,user_email,user_password) values(?,?,?,?,?)";
		st = MyCon.getCon().prepareStatement(query);
		st.setString(1, user.getFirstName());
		st.setString(2, user.getLastName());
		st.setString(3, user.getDisplayName());
		st.setString(4, user.getEmail());
		st.setString(5, user.getPassword());
		st.executeUpdate();
		close();
	}

	public boolean isUserExists(String email) throws ClassNotFoundException, SQLException 
	{
		boolean temp = false;
		st = MyCon.getCon().prepareStatement("select * from user where user_email = ?");
		st.setString(1, email);
		rs = st.executeQuery();
		if(rs.next())
		{
			temp = true;
		}
		close();
		return temp;
	}
	
	public boolean getUserByIdAndEmail(int id, String email) throws ClassNotFoundException, SQLException 
	{
		boolean temp = false;
		st = MyCon.getCon().prepareStatement("select * from user where user_id = ? and user_email = ?");
		st.setInt(1, id);
		st.setString(2, email);
		rs = st.executeQuery();
		if(rs.next())
		{
			temp = true;
		}
		close();
		return temp;
	}
	
	public User getUserByEmailAndPassword(String email, String password) throws ClassNotFoundException, SQLException 
	{
		User user = new User();
		st = MyCon.getCon().prepareStatement("select * from user where user_email = ? and user_password = ?");
		st.setString(1, email);
		st.setString(2, password);
		rs = st.executeQuery();
		while(rs.next())
		{
			user.setId(rs.getInt("user_id"));
			user.setEmail(email);
			user.setPassword(password);
			user.setFirstName(rs.getString("user_firstname"));
			user.setLastName(rs.getString("user_lastname"));
			user.setDisplayName(rs.getString("user_displayname"));
			user.setPhoto(rs.getString("user_photo"));
			user.setAdmin(rs.getInt("user_admin"));
			user.setToken(rs.getInt("user_token"));
		}
		close();
		return user;
	}
	
	public void updatetUserToken(String email) throws ClassNotFoundException, SQLException 
	{
		String query = "UPDATE user SET user_token = 1 WHERE user_email = ?;";
		st = MyCon.getCon().prepareStatement(query);
		st.setString(1, email);
		st.executeUpdate();
		close();
	}
	
	public int getReviewsCount(int id) throws ClassNotFoundException, SQLException {
		int count;
		String query = "Select count(review_userId) from review where review_userId = ?;";
		st = MyCon.getCon().prepareStatement(query);	
		st.setInt(1,id);
		rs = st.executeQuery();
		rs.next();
		count=rs.getInt(1);
		close();
		
	return count ; }
	
	public int getUpvotesCount(int Userid) throws ClassNotFoundException, SQLException{
		int count ;
		String query = "Select count(review_userId) from review,vote where review.review_userId = ? and vote_reviewId=review.review_id and vote_decision=1;";
		st = MyCon.getCon().prepareStatement(query);	
		st.setInt(1,Userid);
		rs = st.executeQuery();
		rs.next();
		count=rs.getInt(1);
		close();
		
		
		
	return count ;} 
	
	private void close() throws SQLException 
	{
		st.close();
		rs.close();
	}
	
}
