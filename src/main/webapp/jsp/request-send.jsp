<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/requestsend.css">
<title>Product Request</title>

<%
	ArrayList<Category> Clist = (ArrayList<Category>)session.getAttribute("Clist");
%>
</head>
<body>
<%@ include file="/jsp/header.jsp"  %>


    <div class="main">
        <form id="form" action="${pageContext.request.contextPath}/RequestSendServlet" method="POST" enctype="multipart/form-data">
            <label for ="filefield"><img class="product-img" src="${pageContext.request.contextPath}/products-requests-images/Product_Lg_Type.jpg" ></label>
            <input type="file" id="filefield" name="file" required accept="image/.jpg,.jpeg,.png" style="display :none">
            <label class="form-title" for ="imgclick">Click to Upload</label>
            <label class="form-title" for="Product">Product Name :</label>
            <input class="form-input" type="text" required placeholder="Enter the product name" name="productname"/>
            <label class="form-title" for="price">Price in $ :</label>
            <label class="form-title" for="minprice">Min </label>
            <input class="form-input" type="number" step="0.01" required placeholder="Min Price" name="minprice"/>
            <label class="form-title" for="maxprice">Max </label>
            <input class="form-input" type="number" step="0.01" required placeholder="Max Price" name="maxprice"/>
            <label class="form-title" for="category">Category:</label>
            <select  class="form-input" name="category">
              <c:forEach var="cate" items="${Clist}">
    	  	<option value="${cate.id}">${cate.name}</option>
   			</c:forEach>
            </select>
            <label class="form-title" for="Description">Description :</label>
            <textarea class="form-input" type="text" required placeholder="Enter the item Description here" name="description" style="resize :none"></textarea>
            <input id="form-button" type="submit" value="Send request">
            <input id="form-button-cancel" type="reset" value="Cancel">
            
    </div>
    


<%@ include file ="/jsp/footer.jsp" %>

    </body>
</html>