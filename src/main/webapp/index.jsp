<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <title>Dashbord</title>
</head>
<body>
    <nav>
        <a href="/Green-Horse"><img id="logo" src="images\logo.png"/></a>
        <ul id="list-menu">
            <li class="list-menu-item"><a class="list-menu-item-link" href="/Green-Horse/LoginPage">log in</a></li>
            <li class="list-menu-item"><a class="list-menu-item-link" href="/Green-Horse/RegisterPage">register</a></li>
        </ul>
    </nav>
    <section id="content" style="background-image: url('images/space.png');">
        <div id="content-txt">Welcome to Green Horse <br>
            your best place to get customer reviews</div>
        <div id="content-button">
            <a id="content-button-link" href="${pageContext.request.contextPath}/MainPage">try now</a>
        </div>
    </section>
    <form action="UploadServlet" method="post" enctype='multipart/form-data'>
		<input name="upload" type="file" value="chose" required/>
		<input type="submit" value="upload"/>
	</form>
    <footer id="footer">
        <div id="footer-img-container"><a href="#"><img id="footer-img" src="images\logo.png"/></a></div>
        <div id="footer-text">2022-2023 Green House | All Rights Reserved.</div>
    </footer>
</body>
</html>