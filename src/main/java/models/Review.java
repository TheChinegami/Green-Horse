package models;

import java.util.ArrayList;

public class Review {
	
	private int reviewId;
	private User user;
	private Product product;
	private String title;
	private String comment;
	private float rate;
	private int vote;
	private ArrayList<Vote> voteList;
	
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	public ArrayList<Vote> getVoteList() {
		return voteList;
	}
	public void setVoteList(ArrayList<Vote> voteList) {
		this.voteList = voteList;
	}
	
}
