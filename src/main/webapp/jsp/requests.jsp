<%@ page import="java.util.ArrayList,models.Product" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/requests.css">	
    <title>main page</title>
    <style type="text/css">

    </style>
    <script src="https://kit.fontawesome.com/aa5956fb58.js" crossorigin="anonymous"></script>
<body>
    
	<%@ include file="/jsp/header.jsp"  %>
    
    <div id="page-container">
        <div id="content">
        
        <c:if test="${empty requests}">
            <img id="no-request-yet-image" src="${pageContext.request.contextPath}/images/no-request-yet.png"/>
            <div id="content-footer">
                no request yet
            </div>
        </c:if>
        
        <c:forEach items="${requests}" var="item">
            <section class="section">
                <div class="secion-id">
                    ${item.getId() }
                </div>
                <div class="section-content-vertical-divider"></div>
                <div class="section-image-container">
                    <img class="section-item-img" src="${pageContext.request.contextPath}/products-images/${item.getPhoto()}" alt="">
                </div>
                <div class="section-content">
                    <div class="section-content-title">
                        ${item.getTitle()}
                    </div>
                    
                    <div class="section-content-link-container">
                        <a class="section-content-link" href="${pageContext.request.contextPath}/RequestDetailsPage?Pid=${item.getId()}">
                        	watch details
                        </a>
                    </div>
                </div>
            </section>

            <div class="section-divider"></div>
            
        </c:forEach>
            
        </div>
        
    </div>
    
    <%@ include file="/jsp/footer.jsp"  %>
    
</body>
</html>