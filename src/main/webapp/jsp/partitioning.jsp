
<%@page import="servlets.ReviewProductServlet"%>
<%@ page import="functional.RateIconsGenerator,models.Product" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <title>main page</title>
    <style type="text/css">
/*	<%@ include file="../css/main.css"%> */
    </style>
    <script src="https://kit.fontawesome.com/aa5956fb58.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

<title>Insert title here</title>
</head>
<body>


	<%
				ArrayList<Product> plist = (ArrayList<Product>)session.getAttribute("products");
        		for(Product item:plist){
        	%>
				<section class="section">
			        <div class="section-image-container">
			            <img class="section-item-img" src="${pageContext.request.contextPath}/products-images/<%out.print(item.getPhoto());%>" alt="">
			        </div>
			        <div class="section-content">
			            <div class="section-content-title">
			            	<%out.print(item.getTitle());%>
			            </div>
			            <div class="section-content-price">
			            	<%out.print(item.getMinPrice()+"$ - "+item.getMaxPrice()+"$");%>
			            </div>
			            <div class="section-content-stars">
			           		<%
			           			out.println(RateIconsGenerator.getStars(item.getRate()));
			           		%>		
			            </div>
			            <div class="section-content-rating">
			            	<%out.print(item.getRate());%>
						</div>
			            <div class="section-content-link-container">
			                <a class="section-content-link" href="${pageContext.request.contextPath}/ProductDetailsPage?id=<%out.print(item.getId());%>">see more
			                    <i class="fa-solid fa-circle-right section-content-link-icon"></i>
			                </a>
			            </div>
			        </div>
			    </section>
	            <div class="section-divider"></div> 
	            
            <%
            	} 
            %>
	
	
	
</body>
</html>