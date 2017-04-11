<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${pageTitle}</title>
    <%-- 加入bootstrap和jquery库 --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mycss.css">
    <script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="common/head.jsp"></jsp:include>
        <div class="col-md-9" style="padding-right: 0px;padding-left: 0px;background-color: #E5E5E5">
            <%--<jsp:include page="blog/${mainPage}"></jsp:include>--%>
            <jsp:include page="${mainPage}"></jsp:include>
            <nav style="text-align: center;padding-top: 30px" aria-label="Page navigation">
                <ul class="pagination">
                    <li><a href="${pageContext.request.contextPath}/index.html">主页</a></li>
                    ${pageCode}
                    </ul>
            </nav>
            <jsp:include page="common/foot.jsp"></jsp:include>
        </div>
    </div>
</div>
</body>
</html>
