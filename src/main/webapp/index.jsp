<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <title>Dashbord</title>
</head>
<body>

   <%@ include file="/jsp/header.jsp" %>
   
    
    <section id="content" style="background-image: url('images/space.png');">
        <div id="content-txt">Welcome to Green Horse <br>
            your best place to get customer reviews</div>
        <div id="content-button">
            <a id="content-button-link" href="${pageContext.request.contextPath}/MainPage">try now</a>
        </div>
    </section>

    <%@ include file="/jsp/footer.jsp" %>

</body>
</html>