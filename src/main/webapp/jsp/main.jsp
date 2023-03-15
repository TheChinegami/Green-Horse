<%@ page import ="java.util.ArrayList" %>

<%
	ArrayList<Product> plist = (ArrayList<Product>)session.getAttribute("products");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <title>main page</title>
    <style type="text/css">
	<%@ include file="../css/main.css"%> */
    </style>
    <%@include file="/jsp/header.jsp" %>
</head>
<body>
    <div id="page-container">
        <aside id="aside">
            <div id="aside-title">
                Categories
            </div>
            <c:forEach items="${categories}" var="item">
				<a class="aside-item-link" href="#">
	                <div class="aside-item-icon">
	                    <i class="fa-solid ${item.getIcon()}"></i>
	                </div>
	                <c:out value="${item.getName()}"/>
	            </a>
			</c:forEach>
            
        </aside>
        
        <div id="content">
        	<c:forEach items="${products}" var="item">
				<section class="section">
			        <div class="section-image-container">
			            <img class="section-item-img" src="${pageContext.request.contextPath}/product-img/${item.getPhoto()}" alt="">
			        </div>
			        <div class="section-content">
			            <div class="section-content-title">
			            	<c:out value="${item.getTitle()}"/>
			            </div>
			            <div class="section-content-price">
			            	
			            	<c:out value="${item.getMinPrice()}$ - ${item.getMaxPrice()}$"/>
			            </div>
			            <div class="section-content-stars">
			            
			            <c:choose>
			            	<c:when test="${item.getRate() < 1 }">
			            		<i class="fa-regular fa-star"></i>
				                <i class="fa-regular fa-star"></i>
				                <i class="fa-regular fa-star"></i>
				                <i class="fa-regular fa-star"></i>
				                <i class="fa-regular fa-star"></i>
			            	</c:when>
			            	<c:when test="${item.getRate() < 2 }">
				                <i class="fa-solid fa-star"></i>
				                <i class="fa-regular fa-star"></i>
				                <i class="fa-regular fa-star"></i>
				                <i class="fa-regular fa-star"></i>
				                <i class="fa-regular fa-star"></i>
			            	</c:when>
			            	<c:when test="${item.getRate() < 3 }">
				                <i class="fa-solid fa-star"></i>
				                <i class="fa-solid fa-star"></i>
				                <i class="fa-regular fa-star"></i>
				                <i class="fa-regular fa-star"></i>
				                <i class="fa-regular fa-star"></i>
			            	</c:when>
			            	<c:when test="${item.getRate() < 4 }">
				                <i class="fa-solid fa-star"></i>
				                <i class="fa-solid fa-star"></i>
				                <i class="fa-solid fa-star"></i>
				                <i class="fa-regular fa-star"></i>
				                <i class="fa-regular fa-star"></i>
			            	</c:when>
			            	<c:when test="${item.getRate() < 5 }">
				                <i class="fa-solid fa-star"></i>
				                <i class="fa-solid fa-star"></i>
				                <i class="fa-solid fa-star"></i>
				                <i class="fa-solid fa-star"></i>
				                <i class="fa-regular fa-star"></i>
			            	</c:when>
			            	<c:otherwise>
			            		<i class="fa-solid fa-star"></i>
				                <i class="fa-solid fa-star"></i>
				                <i class="fa-solid fa-star"></i>
				                <i class="fa-solid fa-star"></i>
				                <i class="fa-solid fa-star"></i>
			            	</c:otherwise>
			            </c:choose>
		
			            </div>
			            <div class="section-content-rating">
			            	<c:out value="${item.getRate()}"/>
						</div>
			            <div class="section-content-link-container">
			                <a class="section-content-link" href="#">see more
			                    <i class="fa-solid fa-circle-right section-content-link-icon"></i>
			                </a>
			            </div>
			        </div>
			    </section>
	            <div class="section-divider"></div>
			</c:forEach>
			<div id="content-footer">
				<a class="content-footer-pages" href="#">1</a>
				<a class="content-footer-pages" href="#">2</a>
				<a class="content-footer-pages" href="#">3</a>
				<a class="content-footer-pages" href="#">4</a>
				<a class="content-footer-pages" href="#">5</a>
				<a class="content-footer-pages" href="#">6</a>
				<a class="content-footer-pages" href="#">7</a>
				<a class="content-footer-pages" href="#">8</a>
				<a class="content-footer-pages" href="#">9</a>
				<a class="content-footer-pages" href="#">10</a>
				<a class="content-footer-pages" href="#">11</a>
				<a class="content-footer-pages" href="#">12</a>
				<a class="content-footer-pages" href="#">13</a>
				<a class="content-footer-pages" href="#">14</a>
				<a class="content-footer-pages" href="#">15</a>
				<a class="content-footer-pages" href="#">16</a>
				<a class="content-footer-pages" href="#">17</a>
				<a class="content-footer-pages" href="#">18</a>
				<a class="content-footer-pages" href="#">19</a>
				<a class="content-footer-pages" href="#">20</a>
				<a class="content-footer-pages" href="#">21</a>
				<a class="content-footer-pages" href="#">22</a>
				<a class="content-footer-pages" href="#">23</a>
				<a class="content-footer-pages" href="#">24</a>
				<a class="content-footer-pages" href="#">25</a>
				<a class="content-footer-pages" href="#">26</a>
				<a class="content-footer-pages" href="#">27</a>
				<a class="content-footer-pages" href="#">28</a>
				<a class="content-footer-pages" href="#">29</a>
			</div>
        </div>
        
    </div>
    
<%@include file="/jsp/footer.jsp" %>

</body>
</html>
        
