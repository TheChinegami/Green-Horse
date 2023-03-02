<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/login.css">
    <title>Log in</title>
</head>
<body>
    <div id="box">
        <div id="content">
            <div id="content-title">Log in</div>
            <form id="form" action="GET"> 
                <label class="form-title" for="">Email adress:</label>
                <input class="form-input" type="email" placeholder="you@example.com">
                <label class="form-title" for="">Password :</label>
                <input class="form-input" type="password" placeholder="Enter 6 caracters or more">
                <input id="form-button" type="submit" value="Log in">
                <label id="form-message" for="">If you don't have an account please <a id="form-login-link" href="register.jsp">register</a> first.</label>
            </form>
        </div>
    </div>
</body>
</html>