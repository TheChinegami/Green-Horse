<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="functional.RateIconsGenerator" %>

<% 
	int product_id = Integer.parseInt(request.getParameter("product_id"));
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/review-product.css">
    <script src="https://kit.fontawesome.com/aa5956fb58.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="sweetalert2.all.min.js"></script>
    <title>Review product</title>
</head>
<body>
    <div id="box">
        <div id="content">
            <div id="content-title">Submit a review </div>
            <form id="form" action="${pageContext.request.contextPath}/ReviewProductServlet" method="POST">
                <label class="form-title" for="">Title :</label>
                <input name="title" id="form-input-title" type="text" placeholder="ex : not the best but still worth it" required>
                <label class="form-title" for="">Comment :</label>
                <textarea name="comment" id="form-input-comment" placeholder="ex : I like this product because ..." required></textarea>
                <label class="form-title" for="">Rate :</label>
                <input name="rate" id="form-input-rate" type="range" placeholder="4" min="0" max="5" value="4"/>
                <div id="form-stars">
                    <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-regular fa-star"></i>
                </div>
                <div id="form-checkbox-container">
                    <input type="checkbox" id="form-checkbox-input"/>
                    <label for="form-checkbox">I confirm this review is about my own genuine experience. and have not been offered any incentive or payment to leave a review for this product.</label>
                </div>
                <input name="product_id" type="hidden" value="<%out.print(product_id); %>"/>
                <input id="form-button" type="submit" value="Submit">
            </form>
        </div>
    </div>
    <script>
        let input_title = document.querySelector('#form-input-title');
        let input_comment = document.querySelector('#form-input-comment');
        let input_rate = document.querySelector('#form-input-rate');
        let stars = document.querySelector('#form-stars');
        let form = document.querySelector('#form');
        let checkbox = document.querySelector('#form-checkbox-input');

        input_rate.addEventListener('input', function(e){
            var num = parseInt(input_rate.value);
            if(num < 1){
                stars.innerHTML = "<i class=\"fa-regular fa-star\"></i><i class=\"fa-regular fa-star\"></i><i class=\"fa-regular fa-star\"></i><i class=\"fa-regular fa-star\"></i><i class=\"fa-regular fa-star\"></i>";
            }else if(num < 2) {
                stars.innerHTML = "<i class=\"fa-solid fa-star\"></i><i class=\"fa-regular fa-star\"></i><i class=\"fa-regular fa-star\"></i><i class=\"fa-regular fa-star\"></i><i class=\"fa-regular fa-star\"></i>";
            }else if(num < 3) {
                stars.innerHTML = "<i class=\"fa-solid fa-star\"></i><i class=\"fa-solid fa-star\"></i><i class=\"fa-regular fa-star\"></i><i class=\"fa-regular fa-star\"></i><i class=\"fa-regular fa-star\"></i>";
            }else if(num < 4) {
                stars.innerHTML = "<i class=\"fa-solid fa-star\"></i><i class=\"fa-solid fa-star\"></i><i class=\"fa-solid fa-star\"></i><i class=\"fa-regular fa-star\"></i><i class=\"fa-regular fa-star\"></i>";
            }else if(num < 5) {
                stars.innerHTML = "<i class=\"fa-solid fa-star\"></i><i class=\"fa-solid fa-star\"></i><i class=\"fa-solid fa-star\"></i><i class=\"fa-solid fa-star\"></i><i class=\"fa-regular fa-star\"></i>";
            }else {
                stars.innerHTML = "<i class=\"fa-solid fa-star\"></i><i class=\"fa-solid fa-star\"></i><i class=\"fa-solid fa-star\"></i><i class=\"fa-solid fa-star\"></i><i class=\"fa-solid fa-star\"></i>";
            }
        });

        input_title.addEventListener('keypress',function(e) {
            console.log('action of input comment is working ...');
            console.log(input_title.value.length);
            if(input_title.value.length>=50){
                Swal.fire({
                    title:'Title should\'nt be too long',
                    icon:'info',
                    confirmButtonColor: '#87adbd',
                });
                e.preventDefault();
            }
        });

        input_comment.addEventListener('keypress',function(e) {
            console.log('action of input comment is working ...');
            console.log(input_comment.value.length);
            if(input_comment.value.length>=1000){
                Swal.fire({
                    title:'you have reached the input limit of the comments',
                    icon:'info',
                    confirmButtonColor: '#87adbd',
                });
                e.preventDefault();
            }
        });

        form.addEventListener('submit', function(e){
            if(checkbox.checked == false){
                Swal.fire({
                    icon: 'info',
                    title: 'the last condition should be confirmed',
                    confirmButtonColor: '#87adbd',
                })
                e.preventDefault();
            }
        });
        
    </script>
</body>
</html>