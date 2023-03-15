
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
<title>Insert title here</title>
<%@ include file="/jsp/header.jsp"  %>
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
<div class="banner">
    <div class="leftside">
        <img class="profile_picture" src="images/profile.png" >
        <p class="display_name"><% out.println(DN); %></p>
        <p class="full_name"><% out.println(FN +" " + LN); %></p>
    </div>
    <div class="rightside">
        <p class="reviews_count">69</p>
        <p class="reviews_text">reviews</p>
        <p class="upvotes_count">69</p>
        <p class="upvotes_text">upvotes</p>
    </div>

</div>
<div class="main">
    <form id="form" action="${pageContext.request.contextPath}/ProfileServlet" method="POST"> 
        <img class="profile_picture_form" src="images/profile.png" >
        <label class="form-title-profile-picture" for="profilepicture">Change Picture</label>
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