
<%@page import="servlets.ReviewProductServlet"%>
<%@ page import="functional.RateIconsGenerator,models.Product" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">    
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

				<div id="content"></div>
			
				<% 
					int productListLength = (int)session.getAttribute("products_count");
					if(productListLength == 0 ){
				%>	
						<div id="null-image-container">
							<img class="null-image" src="images/no-result-found.png" alt="">
						</div>
						<div id="null-message-container">
			                <div class="null-message">
			                	if you have a product which isn't exist in our database, you can added it from here <br> 
				                <a class="null-message-link" href="${pageContext.request.contextPath}/RequestSendPage">
				                add product
				                </a> 
			                </div>
			            </div>
				<%
					}
				%>
				
            	<div id="content-footer">
            	
	            	<%
	            		
	            		int productPages;
	            		if(productListLength % 2 != 0)
	            			productPages = (productListLength/2)+1;
	            		else
	            			productPages = (productListLength/2);
	            		for(int i=1;i<=productPages; i++){
	            	%>
						<div class="content-footer-pages"><%out.print(i); %></div>
					<%
	            		}
					%>
				</div>
	<script type="text/javascript">
	
	$.ajax({
		type: "POST",
		url: 'PartitioningServlet',
		data: {search_page:'1'},
		success: function(response)
		{
			$('#content').html(response);
		}
	});
	
	$(document).ready(function() {
    	$('.content-footer-pages').click(function(e) {
    		$('.content-footer-pages').css('color','#5C5C5C');
    		$('.content-footer-pages').css('background-color','transparent');

    		$(this).css('color','#e1ffc1');
    		$(this).css('background-color','#1DB954');  
	        $.ajax({
	            type: "POST",
	            url: 'PartitioningServlet',
	            data: {search_page: $(this).html()},
	            success: function(response)
	            {
	                $('#content').html(response);
	           }
	       });
	     });
    });
	
	var page = document.querySelector('.content-footer-pages');
	page.style.color = '#e1ffc1';
	page.style.backgroundColor = '#1DB954';
	
	</script>
</body>
</html>