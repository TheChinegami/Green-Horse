<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	int success = Integer.parseInt((String)request.getParameter("success"));
	int product = Integer.parseInt((String)request.getParameter("product_id"));
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="sweetalert2.all.min.js"></script>
	<title>thank you</title>
	<style>
		body,* {
		    margin:0;
		    padding: 0;
		    font-family: "urbanist";
		}
	</style>
</head>
<body>
	<input id="success" type="hidden" value="<%out.print(success);%>"/>
	<input id="product" type="hidden" value="<%out.print(product);%>"/>
	<script type="text/javascript">
		var successVar = document.getElementById('success');
		var productVar = document.getElementById('product');
		
		var success = parseInt(successVar.value);
		var product = parseInt(productVar.value);
		
		setTimeout(callBack_func, 5000);
		
		if(success === 1) {
			Swal.fire({
       			icon: 'success',
       			title: 'Thank you for sharing your review',
       			showConfirmButton: false,
       			timer: 5500
       		})
		} else {
			Swal.fire({
       			icon: 'warning',
       			title: 'You have already shared your review',
       			showConfirmButton: false,
       			timer: 5500
       		})
		}
		
		function callBack_func() {
       		window.location.replace("http://localhost:8080/Green-Horse/ProductDetailsPage?id="+product);
		}
	</script>
</body>
</html>