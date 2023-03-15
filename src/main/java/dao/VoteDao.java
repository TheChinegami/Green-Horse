package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import functional.MyCon;
import models.User;
import models.Vote;

public class VoteDao {

	private PreparedStatement st;
	private ResultSet rs;
	
	public ArrayList<Vote> getVotesOfReview(int reviewId) throws ClassNotFoundException, SQLException {
		ArrayList<Vote> voteList = new ArrayList<Vote>();
		st = MyCon.getCon().prepareStatement("select * from vote where vote_reviewId = ?");
		st.setInt(1, reviewId);
		rs = st.executeQuery();
		Vote vote;
		User user;
//		Review review;
		while(rs.next())
		{
			vote = new Vote();
			user = new User();
//			review = new Review();
			
			user.setId(rs.getInt("vote_userId"));
			
//			review.setReviewId(rs.getInt("vote_reviewId"));

			vote.setId(rs.getInt("vote_id"));
			vote.setDecision(rs.getInt("vote_decision"));
			vote.setUser(user);
//			vote.setReview(review);
			voteList.add(vote);
		}
		close();
		return voteList;
	}
	
	public void insertVote(int userId, int reviewId, int decision) throws ClassNotFoundException, SQLException 
	{
		String query = "INSERT INTO `vote` (`vote_userId`, `vote_reviewId`, `vote_decision`) VALUES (?,?,?);";
		st = MyCon.getCon().prepareStatement(query);
		st.setInt(1, userId);
		st.setInt(2, reviewId);
		st.setInt(3, decision);
		st.executeUpdate();
		close();
	}
	
	public void updateVote(int userId, int reviewId, int decision) throws ClassNotFoundException, SQLException 
	{
		String query = " UPDATE `vote` SET `vote_decision` = ? WHERE `vote`.`vote_userId` = ? and `vote`.`vote_reviewId` = ? ;";
		st = MyCon.getCon().prepareStatement(query);
		st.setInt(1, decision);
		st.setInt(2, userId);
		st.setInt(3, reviewId);
		st.executeUpdate();
		close();
	}
	// UPDATE `vote` SET `vote_decision` = '-1' WHERE `vote`.`vote_id` = 4;
	private void close() throws SQLException 
	{
		st.close();
		rs.close();
	}
	
}
