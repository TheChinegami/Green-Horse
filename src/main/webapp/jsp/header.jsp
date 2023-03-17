
<c:choose>
	<c:when test="${not empty search_box}">
		<c:set var="local_search_var" value="${search_box}"/>
	</c:when>
	<c:otherwise>
		<c:set var="local_search_var" value="${false}"/>
	</c:otherwise>
</c:choose>

<%
	session.removeAttribute("search_box");
%>

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

	<nav>
	
        <a href="/Green-Horse"><img id="logo" src="images\logo.png"/></a>
        
        <c:if test="${local_search_var eq true}">
        
	        <div id="search-box">
	            <input id="search-input" type="text" placeholder="search"/>
	            <span id="search-icon-link">
	                <i id="search-icon" class="fa-solid fa-magnifying-glass"></i>
	            </span>
	        </div>
        
        </c:if>

		<c:choose>
			<c:when test="${ current_user.getId() == 0 || empty current_user }">        
		        <ul id="list-menu">
		            <li class="list-menu-item"><a class="list-menu-item-link" href="/Green-Horse/LoginPage">Log in</a></li>
		            <li class="list-menu-item"><a class="list-menu-item-link" href="/Green-Horse/RegisterPage">Register</a></li>
		        </ul>
		    </c:when>
		    <c:otherwise>
		        <div id="menu-container">
		           	<div id="profile-image-container">
		                <img id="profile-image" src="${pageContext.request.contextPath}/profiles-images/${current_user.getPhoto()}" alt="">
		            </div>
			        <ul id="list-menu">
			            <li class="list-menu-item"><a class="list-menu-item-link" href="/Green-Horse/ProfilePage">
			            	Profile
			            </a></li>
			            <li class="list-menu-item"><a class="list-menu-item-link" href="# ">Notification</a></li>
			            <li class="list-menu-item"><a class="list-menu-item-link" href="/Green-Horse/LogoutServlet">Log Out</a></li>
			        </ul>
		        </div>
		    </c:otherwise>
		</c:choose>

    </nav>
    
</body>
</html>