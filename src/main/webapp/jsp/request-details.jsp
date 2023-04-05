<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/requestdetails.css">
<title>Insert title here</title>

</head>
<body>
<%@ include file="/jsp/header.jsp"  %>


    <div class="main">
        <form id="form" action="${pageContext.request.contextPath}/RequestDetailsServlet" method="POST"> 
            <label for ="filefield"><img class="product-img" src="${pageContext.request.contextPath}/products-images/${Requested_Product.getPhoto()}" ></label>
            <label class="form-title" for="Product">Product Name :</label>
            <input class="form-input" type="text" required placeholder="Enter the product name" name="productname" disabled value="${ Requested_Product.getTitle() }" />
            <input style="display :none" name="productid" value="${ Requested_Product.getId() }">
            <label class="form-title" for="price">Price in $ :</label>
            <label class="form-title" for="minprice">Min </label>
            <input class="form-input" type="text" required placeholder="Min Price in $" name="minprice" disabled value="${ Requested_Product.getMinPrice() } "/>
            <label class="form-title" for="maxprice">Max </label>
            <input class="form-input" type="text" required placeholder="Max Price in $" name="maxprice" disabled value="${ Requested_Product.getMaxPrice() } "/>
            <label class="form-title" for="category">Category:</label>
            <input  class="form-input" name="category" type="text" disabled value ="${ Requested_Product.getCategory() }">
           	<label class="form-title" for="Description">Description :</label>
            <textarea class="form-input" type="text" required placeholder="Enter the item Description here" disabled name="Description">${ Requested_Product.getDescription() } </textarea>
            <label class ="form-title">Decision :</label>
            <fieldset>
		    <label><input type="radio" name="decision" value="1"> Accept</label>
		    <label><input type="radio" name="decision" value="0"> Reject</label>
			</fieldset>
            <label class="form-title" for="Comment" >Reason :</label>
            <textarea class="form-input" type="text" required placeholder="Enter your decision reason here !" name="reason"> </textarea>
            <input id="form-button" type="submit" value="Submit Decision">
            <input id="form-button-cancel" type="reset" value="Cancel">
    </div>
    


<%@ include file ="/jsp/footer.jsp" %>
</body>
</html>