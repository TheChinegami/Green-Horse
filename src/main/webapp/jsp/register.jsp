<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/register.css">
    <title>register</title>
</head>
<body>
    <div id="box">
        <div id="content">
            <div id="content-title">Register</div>
            <form id="form" action="GET"> 
                <label class="form-title" for="">First name :</label>
                <input class="form-input" type="text" placeholder="Enter your first name">
                <label class="form-title" for="">Last name :</label>
                <input class="form-input" type="text" placeholder="Enter your last name">
                <label class="form-title" for="">Display name :</label>
                <input class="form-input" type="text" placeholder="Enter your display name">
                <label class="form-title" for="">Email adress:</label>
                <input class="form-input" type="email" placeholder="you@example.com">
                <label class="form-title" for="">Password :</label>
                <input class="form-input" type="password" placeholder="Enter 6 caracters or more">
                <label class="form-title" for="">Confirm password :</label>
                <input class="form-input" type="password" placeholder="Enter 6 caracters or more">
                <input id="form-button" type="submit" value="Register">
                <label id="form-message" for="">If you have registered before please go to <a id="form-login-link" href="login.jsp">login</a> page.</label>
            </form>
        </div>
    </div>
</body>
</html>