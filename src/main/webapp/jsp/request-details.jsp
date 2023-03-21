<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/request-details.css">
<title>Insert title here</title>

</head>
<body>
<%@ include file="/jsp/header.jsp"  %>


    <div class="main">
        <form id="form" action="${pageContext.request.contextPath}/RequestDetailsServlet" method="POST"> 
            <label for ="filefield"><img class="product-img" src="${pageContext.request.contextPath}/products-requests-images/Product_Lg_Type.jpg" ></label>
            <label class="form-title" for="Product">Product Name :</label>
            <input class="form-input" type="text" required placeholder="Enter the product name" name="productname" value="PRODUCT X"/>
            <label class="form-title" for="price">Price in $ :</label>
            <label class="form-title" for="minprice">Min </label>
            <input class="form-input" type="text" required placeholder="Min Price in $" name="minprice" value="200 $"/>
            <label class="form-title" for="maxprice">Max </label>
            <input class="form-input" type="text" required placeholder="Max Price in $" name="maxprice" value="400 $"/>
            <label class="form-title" for="category">Category:</label>
            <select  class="form-input" name="category">
                <option value="Electronics" selected>Electronics</option>
                <option value="Beauty & Health">Beauty & Health</option>
                <option value="Men's fashion">Men's Fashion</option>
                <option value="Women's fashion">Women's Fashion</option>
            </select>
           <label class="form-title" for="Description">Description :</label>
            <textarea class="form-input" type="text" required placeholder="Enter the item Description here" name="Description">Unfeeling so rapturous discovery he exquisite. Reasonably so middletons or impression by terminated. Old pleasure required removing elegance him had. Down she bore sing saw calm high. Of an or game gate west face shed. ﻿no great but music too old found arose.
            </textarea>
            <label class="form-title" for="Comment" >Commentaire :</label>
            <textarea class="form-input" type="text" required placeholder="Enter your decision reason here !" name="Comment"> </textarea>
            <input id="form-button" type="submit" value="Accept request">
            <input id="form-button-cancel" type="reset" value="Reject Request">
    </div>
    


<%@ include file ="/jsp/footer.jsp" %>
</body>
</html>