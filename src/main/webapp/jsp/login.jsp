<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

    String message = "";
    if(session.getAttribute("message")!=null)
    {
        message = (String)session.getAttribute("message");
        session.removeAttribute("message");
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
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
                <input id="form-error-message" type="hidden" value="<% out.print(message);%>"/>
            </form>
        </div>
    </div>
    <script>
    	var message = document.getElementById('form-error-message').value;
		 
    	if(message === 'email_error'){
    		Swal.fire({
	   			icon: 'error',
	   			title: 'this account is not exist',
                confirmButtonColor: '#87adbd',
	   		})
    		
    	}else if(message === 'password_error') {
    		Swal.fire({
	   			icon: 'error',
	   			title: 'password uncorrect',
                confirmButtonColor: '#87adbd',
	   		})
    	}else if(message === 'register_success') {
    		Swal.fire({
	   			icon: 'success',
	   			title: 'you have registered successfully',
                confirmButtonColor: '#87adbd',
	   		})
    	}
    </script>
</body>
</html>