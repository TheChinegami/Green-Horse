package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import functional.MyCon;
import models.Product;
import models.Review;
import models.User;

public class ReviewDao {

	private PreparedStatement st;
	private ResultSet rs;
	
	public ArrayList<Review> getReviewsByProductId(int productId) throws ClassNotFoundException, SQLException 
	{
		ArrayList<Review> list = new ArrayList<Review>();
		st = MyCon.getCon().prepareStatement("select * from review,user where review_userId = user_id and review_productId = ?;");
		st.setInt(1, productId);
		rs = st.executeQuery();
		Review review;
		User user;
		Product product;
		while(rs.next())
		{
			user = new User();
			product = new Product();
			review = new Review();
			review.setReviewId(rs.getInt("review_id"));
			review.setUser(user);
			review.setProduct(product);
			user.setEmail(rs.getString("user_email"));
			user.setDisplayName(rs.getString("user_displayname"));
			user.setPhoto(rs.getString("user_photo"));
			product.setId(rs.getInt("review_productId"));
			review.setRate(rs.getFloat("review_rate"));
			review.setTitle(rs.getString("review_title"));
			review.setComment(rs.getString("review_comment"));
			review.setVote(rs.getInt("review_vote"));
			list.add(review);
		}
		close();
		return list;
	}
	
	public boolean isHeHaveAReview(int userId, int productId) throws ClassNotFoundException, SQLException {
		boolean temp = false;
		st = MyCon.getCon().prepareStatement("select * from review where review_userId = ? and review_productId = ?;");
		st.setInt(1, userId);
		st.setInt(2, productId);
		rs = st.executeQuery();
		if(rs.next()) {
			temp = true;
		}
		close();
		return temp;
	}
	
	public void insertReview(Review review) throws ClassNotFoundException, SQLException 
	{
		st = MyCon.getCon().prepareStatement("insert into review(review_userId,review_productId,review_rate,review_title,review_comment) values (?,?,?,?,?);");
		st.setInt(1, review.getUser().getId());
		st.setInt(2, review.getProduct().getId());
		st.setFloat(3, review.getRate());
		st.setString(4, review.getTitle());
		st.setString(5, review.getComment());
		st.executeUpdate();
	}
	
	private void close() throws SQLException 
	{
		st.close();
		rs.close();
	}
	
}
