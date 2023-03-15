<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,models.Product,functional.RateIconsGenerator,models.Review,models.User,models.Vote" %>
    
<%
	Product p = (Product)session.getAttribute("current_product");
	String userPhoto = ((User)session.getAttribute("current_user")).getPhoto();
	int userId = ((User)session.getAttribute("current_user")).getId();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>product details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-details.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="" type="image/x-icon">
    <script src="https://kit.fontawesome.com/aa5956fb58.js" crossorigin="anonymous"></script>
</head>
<body>
        <%@include file="/jsp/header.jsp" %>


    
    <div id="page-container">
        <div id="product" href="#">
            <div id="product-image-container">
                <img id="product-image" src="products-images/<%out.print(p.getPhoto()); %>" alt="">
            </div>
            <div id="product-information">
                <div id="product-information-title">
					<%out.print(p.getTitle()); %>
                </div>
                <div id="product-information-price">
                	<%out.print(p.getMinPrice()+"$ - "+p.getMaxPrice()+"$"); %>
                </div>
                <div id="product-information-stars">
                    <%out.print(RateIconsGenerator.getStars(p.getRate())); %>
                </div>
                <div id="product-information-rate">
                    <%out.print(p.getRate()); %>
				</div>
                <div id="product-information-category">
                    <%out.print(p.getCategory()); %>
				</div>
            </div>
        </div>

        <div class="section-divider"></div>

        <div id="description-title">Description</div>
        <div id="description-text">
			<%out.print(p.getDescription()); %>
        </div>
        
        <div class="section-divider"></div>

        <div id="reviews-title"> What people say about this product </div>
        <div id="reviews-container">

            <a id="custom-review" href="${pageContext.request.contextPath}/ReviewProductPage?id=<%out.print(p.getId());%>">
                <div id="custom-review-image-container">
                    <img id="custom-review-image" src="profiles-images/<%out.print(userPhoto); %>" alt="">
                </div>
                <div id="custom-review-text">write a review</div>
            </a>

	<%
		for(Review review:p.getReviews())
		{
			String voteColorUp = "";
			String voteColorDown = "";
			if(userId!=0){
				ArrayList<Vote> voteList = review.getVoteList();
				for(Vote vote:voteList){
					if(vote.getUser().getId() == userId)
					{
						if(vote.getDecision()==1){
							voteColorUp = "color: #1DB954;";
						}else {
							voteColorDown = "color: #1DB954;";
						}
					}
				}
			}
	%>
            <div class="review">
                <div class="review-votebox">
                    <a class="review-votebox-icon" href="${pageContext.request.contextPath}/VoteServlet?product_id=<%out.print(p.getId()); %>&review_id=<%out.print(review.getReviewId());%>&decision=1" style="<%out.print(voteColorUp); %>">
                        <i class="fa-solid fa-square-caret-up"></i>
                    </a>
                    <div class="review-votebox-number">
                        <% out.print(review.getVote()); %>
                    </div>
                    <a class="review-votebox-icon" href="${pageContext.request.contextPath}/VoteServlet?product_id=<%out.print(p.getId()); %>&review_id=<%out.print(review.getReviewId());%>&decision=-1" style="<%out.print(voteColorDown); %>">
                        <i class="fa-solid fa-square-caret-down"></i>
                    </a>
                </div>
                <div class="review-commentbox">
                    <div class="review-commentbox-left">
                        <div class="review-commentbox-left-img-container">
                            <img class="review-commentbox-left-img" src="${pageContext.request.contextPath}/profiles-images/<% out.print(review.getUser().getPhoto()); %>" alt="">
                        </div>
                    </div>
                    <div class="review-commentbox-center">
                        <div class="review-commentbox-center-name">
							<% out.print(review.getUser().getDisplayName()); %>
						</div>
                        <div class="review-commentbox-center-rate">
                            <div class="review-commentbox-center-stars">
                                <% out.print(RateIconsGenerator.getStars(review.getRate())); %>
                            </div>
                            <% out.print(review.getRate()); %>
                        </div> 
                    </div>
                    <div class="review-commentbox-divider"></div>
                    <div class="review-commentbox-right">
                        <div class="review-commentbox-right-title">
                        	<% out.print(review.getTitle()); %>
                        </div>
                        <div class="review-commentbox-right-comment">
                            <% out.print(review.getComment()); %>
                        </div>
                    </div>
                </div>
            </div>

	<%
		}
	%>   

        </div>
        
    </div>
    
<%@include file="/jsp/footer.jsp" %>

    
    <script>
        var items = document.querySelectorAll('.review-commentbox-right-comment');
        for (let i of items) {
            i.addEventListener("click", (e) => {
                i.style.lineClamp = "none";
                i.style.display = "inline-block";
            });
        }
    </script>
</body>
</html>