
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">

</head>
<body>


<c:choose>

	<c:when test="${ empty current_user }">
	
	 <nav>
	
        <a href="/Green-Horse"><img id="logo" src="images\logo.png"/></a>
        <ul id="list-menu">
            <li class="list-menu-item"><a class="list-menu-item-link" href="/Green-Horse/LoginPage">log in</a></li>
            <li class="list-menu-item"><a class="list-menu-item-link" href="/Green-Horse/RegisterPage">register</a></li>
        </ul>
    </nav>
    
    </c:when>
    
    <c:otherwise>
    
     <nav>
        <a href="index.jsp"><img id="logo" src="images\logo.png"/></a>
        <ul id="list-menu">
            <li class="list-menu-item"><a class="list-menu-item-link" href="/Green-Horse/ProfilePage">Profile</a></li>
            <li class="list-menu-item"><a class="list-menu-item-link" href="# ">Notifications</a></li>
            <li class="list-menu-item"><a class="list-menu-item-link" href="/Green-Horse/LogoutServlet">Log Out</a></li>
        </ul>
    </nav>
    
    </c:otherwise>
    
</c:choose>

</body>
</html>