
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <script src="https://kit.fontawesome.com/aa5956fb58.js" crossorigin="anonymous"></script>

</head>
<body>


<c:choose>

	<c:when test="${ current_user.getId() == 0 || empty current_user.getId() }">
	
	 <nav>
	
        <a href="/Green-Horse"><img id="logo" src="images\logo.png"/></a>
        <div id="search-box">
            <input id="search-input" type="text" placeholder="search"/>
            <a id="search-icon-link" href="#">
                <i id="search-icon" class="fa-solid fa-magnifying-glass"></i>
            </a>
        </div>
        <ul id="list-menu">
            <li class="list-menu-item"><a class="list-menu-item-link" href="/Green-Horse/LoginPage">Log in</a></li>
            <li class="list-menu-item"><a class="list-menu-item-link" href="/Green-Horse/RegisterPage">Register</a></li>
        </ul>
    </nav>
    
    </c:when>
    
    <c:otherwise>
    
     <nav>
        <a href="${pageContext.request.contextPath}"><img id="logo" src="images\logo.png"/></a>
        <div id="search-box">
            <input id="search-input" type="text" placeholder="search"/>
            <a id="search-icon-link" href="#">
                <i id="search-icon" class="fa-solid fa-magnifying-glass"></i>
            </a>
        </div>
        <div id="menu-container">
           	<div id="profile-image-container">
                <img id="profile-image" src="${pageContext.request.contextPath}/profiles-images/${current_user.getPhoto()}" alt="">
            </div>
	        <ul id="list-menu">
	        	
	            <li class="list-menu-item"><a class="list-menu-item-link" href="/Green-Horse/ProfilePage">
	            	Profile
	            </a></li>
	            <li class="list-menu-item"><a class="list-menu-item-link" href="# ">Notifications</a></li>
	            <li class="list-menu-item"><a class="list-menu-item-link" href="/Green-Horse/LogoutServlet">Log Out</a></li>
	        </ul>
        </div>
    </nav>
    
    </c:otherwise>
    
</c:choose>

</body>
</html>