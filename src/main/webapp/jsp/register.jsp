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
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">	
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <title>register</title>
    <style>
    	
    </style>
</head>
<body>
    <div id="box">
        <div id="content">
            <div id="content-title">Register</div>
            <form id="form" action="${pageContext.request.contextPath}/RegisterServlet" method="POST"> 
                <label class="form-title" for="firstname">First name* :</label>
                <input class="form-input" type="text" required placeholder="Enter your first name" name="firstname"/>
                <label class="form-title" for="lastname">Last name* :</label>
                <input class="form-input" type="text" required placeholder="Enter your last name" name="lastname"/>
                <label class="form-title" for="displayname">Display name* :</label>
                <input class="form-input" type="text" required placeholder="Enter your display name" name="displayname"/>
                <label class="form-title" for="email">Email adress* :</label>
                <input class="form-input" type="email" required placeholder="you@example.com" name="email"/>
                <label class="form-title" for="">Password* :</label>
                <input class="form-input" type="password" required placeholder="Enter 6 caracters or more" name="password"/>
                <label class="form-title" for="">Confirm password* :</label>
                <input class="form-input" type="password" required placeholder="Enter 6 caracters or more" name="confirmpassword"/>
                <input id="form-button" type="submit" value="Register">
                <div id="form-message">If you have registered before please go to <a id="form-login-link" href="${pageContext.request.contextPath}/LoginPage">login</a> page.</div>
                <input id="form-error-message" type="hidden" value="<% out.print(message);%>"/>
            </form>
        </div>
    </div>
    <script>
    	var message = document.getElementById('form-error-message').value;
		 
    	if(message === 'email_error'){
    		Swal.fire({
	   			icon: 'error',
	   			title: 'you already registered with this email',
                confirmButtonColor: '#87adbd',
	   		})
    		
    	}else if(message === 'password_error') {
    		Swal.fire({
	   			icon: 'error',
	   			title: 'your first and second password didn\'t match',
                confirmButtonColor: '#87adbd',
	   		})
    	}
    </script>
</body>
</html>