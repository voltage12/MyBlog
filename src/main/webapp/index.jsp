<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>博客系统后台登录页面</title>
    <%-- 加入bootstrap和jquery库 --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mycss.css">
    <script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row" style="background-color: #e6e6e6">
        <div class="col-md-3" style="background-color: #FFFFFF">
            <div class="overlay" style="background-color: 	#87CEFA;opacity: 0.5;"></div>
            <div class="intrude-less" style="margin-bottom: 30px">
                <header id="header" class="inner">
                    <a href="/" class="profilepic">
                        <img src="${pageContext.request.contextPath}/static/userimages/cat.jpg" class="animated zoomIn">
                    </a>
                </header>
                <h1><a href="/" style="font-size: 20px;font-weight: bold">电压12伏特</a></h1>
                <p style="font-size: 18px">此处应有一句签名(＃－－)/ .</p>
            </div>
            <div style="width: 85%">
                <div class="data_list">
                    <div class="data_list_title">
                        <img src="${pageContext.request.contextPath}/static/images/byType_icon.png"/>
                        按日志类别
                    </div>
                    <div class="datas">
                        <ul>
                            <li><span><a href="/index.html?typeId=1">Java核心基础(1)</a></span></li>
                            <li><span><a href="/index.html?typeId=2">Mysql(1)</a></span></li>
                            <li><span><a href="/index.html?typeId=3">Tomcat(1)</a></span></li>
                            <li><span><a href="/index.html?typeId=10">jsoup(1)</a></span></li>
                            <li><span><a href="/index.html?typeId=7">shiro(1)</a></span></li>
                            <li><span><a href="/index.html?typeId=9">webservice(2)</a></span></li>
                            <li><span><a href="/index.html?typeId=4">IT之路(3)</a></span></li>
                            <li><span><a href="/index.html?typeId=5">随心生活(2)</a></span></li>
                        </ul>
                    </div>
                </div>
                <div class="data_list">
                    <div class="data_list_title">
                        <img src="${pageContext.request.contextPath}/static/images/byDate_icon.png"/>
                        按日志日期
                    </div>
                    <div class="datas">
                        <ul>
                            <li><span><a href="/index.html?releaseDateStr=2016年02月">2016年02月(11)</a></span></li>
                            <li><span><a href="/index.html?releaseDateStr=2016年01月">2016年01月(1)</a></span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            col-md-6
        </div>
    </div>
</div>
</body>
</html>
