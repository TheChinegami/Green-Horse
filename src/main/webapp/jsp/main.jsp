
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.ArrayList,models.Category,models.Product,functional.RateIconsGenerator" %>

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
/*	<%@ include file="../css/main.css"%> */
    </style>
    <script src="https://kit.fontawesome.com/aa5956fb58.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="/jsp/header.jsp" %>

    <div id="page-container">
        <aside id="aside">
            <div id="aside-title">
                Categories
            </div>
            <c:forEach items="${categories}" var="item">
				<div class="aside-item-link">
	                <div class="aside-item-icon">
	                    <i class="fa-solid ${item.getIcon()}"></i>
	                </div>
	                <c:out value="${item.getName()}"/>
	                <input class="inputtt" type="hidden" value="${item.getId()}"/>
	            </div>
			</c:forEach>
            <div id="aside-footer-distance"></div>
        </aside>
        
        <div id="content-container">
        	
        </div>
        
    </div>
    
<%@include file="/jsp/footer.jsp" %>

<script type="text/javascript">


	$.ajax({
		type: "POST",
		url: 'SearchServlet',
		data: {search_method:'1',search_value:''},
		success: function(response)
		{
			$('#content-container').html(response);
		}
	});

	$(document).ready(function() {
	    $('#search-icon-link').click(function(e) {
			$('.aside-item-link').css('color','#5C5C5C');
	        $.ajax({
	            type: "POST",
	            url: 'SearchServlet',
	            data: {search_method:'1', search_value:$('#search-input').val()},
	            success: function(response)
	            {
	                $('#content-container').html(response);
	            }
	       });
	     });
	});

	$(document).ready(function() {
    	$('.aside-item-link').click(function(e) {
    		$('.aside-item-link').css('color','#5C5C5C');
    		$(this).css('color','#1DB954');
	        $.ajax({
	            type: "POST",
	            url: 'SearchServlet',
	            data: {search_method:'2', search_value: $(this).find('.inputtt').val()},
	            success: function(response)
	            {
	                $('#content-container').html(response);
	           }
	       });
	     });
    });
        
</script>
</body>
</html>
        
