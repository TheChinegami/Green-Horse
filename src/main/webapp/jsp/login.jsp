<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String errorMessage = "";
    if(session.getAttribute("error_message")!=null)
    {
        errorMessage = (String)session.getAttribute("error_message");
        session.removeAttribute("error_message");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <title>Log in</title>
    <style>
        
    </style>
</head>
<body>
    <div id="box">
        <div id="content">
            <div id="content-title">Log in</div>
            <form id="form" action="${pageContext.request.contextPath}/LoginServlet" method="POST">
                <label class="form-title" for="email">Email adress:</label>
                <input class="form-input" type="email" placeholder="you@example.com" required name="email"/>
                <label class="form-title" for="password">Password :</label>
                <input class="form-input" type="password" placeholder="Enter 6 caracters or more" required name="password"/>
                <input id="form-button" type="submit" value="Log in" name="login"/>
                <label id="form-message" for="">If you don't have an account please <a id="form-login-link" href="/Green-Horse/RegisterPage">register</a> now.</label>
                <div id="form-error-message">
                    <%
                    out.print(errorMessage);
                    %>
                </div>
            </form>
        </div>
    </div>
</body>
</html>