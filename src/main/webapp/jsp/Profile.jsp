
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile0.css">
<title>Profile</title>
<%
    String errorMessage = "";
    if(session.getAttribute("error_message")!=null)
    
    {
        errorMessage = (String)session.getAttribute("error_message");
        session.removeAttribute("error_message");
    }
%>
<%  String FN =  ((User)session.getAttribute("current_user")).getFirstName() ;
	String LN =  ((User)session.getAttribute("current_user")).getLastName() ;
	String DN =  ((User)session.getAttribute("current_user")).getDisplayName() ;
	int Id =  ((User)session.getAttribute("current_user")).getId() ;
	String Password =  ((User)session.getAttribute("current_user")).getPassword() ;
	String email =  ((User)session.getAttribute("current_user")).getEmail() ;%>

</head>
<body>
<%@ include file="/jsp/header.jsp"  %>
<div class="banner">
    <div class="leftside">
    	<div class="banner-profile-picture-container">
        <img  id="profile-image" src="${pageContext.request.contextPath}//profiles-images/${current_user.getPhoto()}?v=<%= System.currentTimeMillis() %>" alt="">
        </div>
        <p class="display_name"><% out.println(DN); %></p>
        <p class="full_name"><% out.println(FN +" " + LN); %></p>
    </div>
    <div class="rightside">
        <p class="reviews_count"><c:out value = "${ ReviewsCount }"></c:out></p>
        <p class="reviews_text">reviews</p>
        <p class="upvotes_count"><c:out value = "${ UpvotesCount }"></c:out></p>
        <p class="upvotes_text">upvotes</p>
    </div>

</div>
<div class="main">
    <form id="form" action="${pageContext.request.contextPath}//ProfileServlet" method="POST" enctype="multipart/form-data"> 
    <div class="form-profile-picture-container">
        <label for ="filefield"><img id="profile-image" src="${pageContext.request.contextPath}//profiles-images/${current_user.getPhoto()}?v=<%= System.currentTimeMillis() %>" alt=""></label>
    </div>   
        <input type="file" id="filefield" name="file" accept="image/.jpg,.jpeg,.png" style="display :none">
      	
        <label class="form-title-profile-picture" for="profilepicture"> Click the picture</label>
        <label class="form-title" for="firstname">First name :</label>
        <input class="form-input" type="text" value= <c:out value = " ${ current_user.getFirstName() }" /> name="firstname" required/>
        <label class="form-title" for="lastname">Last name :</label>
        <input class="form-input" type="text" value=<c:out value = " ${ current_user.getLastName() }" /> name="lastname" required/>
        <label class="form-title" for="displayname">Display name :</label>
        <input class="form-input" type="text" value=<c:out value = " ${ current_user.getDisplayName() }" />  name="displayname" required/>
        <label class="form-title" for="email">Email adress :</label>
        <input class="form-input" type="email" value=<c:out value = " ${ current_user.getEmail() }" />  name="email" disabled />
        <label class="form-title" for="">Current Password :</label>
        <input class="form-input" type="password" required placeholder="Enter your current password" name="password"/>
         <label class="form-title" for="">New Password :</label>
        <input class="form-input" type="password"  placeholder="Enter new password" name="newpassword"/>
        <label class="form-title" for="">Confirm password :</label>
        <input class="form-input" type="password"  placeholder="Confirm you password" name="confirmpassword"/>
        <input id="form-button" type="submit" value="Save">
        <input id="form-button-cancel" type="reset" value="Cancel">
     </form>
    <div id="form-error-message">
                    <%
                    out.print(errorMessage);
                    %>
	</div>
</div>





<%@ include file ="/jsp/footer.jsp" %>
</body>
</html>