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
    <div class="row">
        <div id="cl3" class="col-md-3" style="padding-right: 0px;padding-left: 0px">
            <div id="overDiv" class="overlay" style="background-color: #87CEFA;opacity: 0.5;"></div>
            <div class="intrude-less" style="margin-bottom: 30px">
                <header id="header" class="inner">
                    <a href="/" class="profilepic">
                        <img src="${pageContext.request.contextPath}/static/userimages/cat.jpg" class="animated zoomIn">
                    </a>
                </header>
                <h1><a href="/" style="font-size: 20px;font-weight: bold">电压12伏特</a></h1>
                <p style="font-size: 18px">此处应有一句签名(＃－－)/ .</p>
            </div>
            <div style="width: 100%;">
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
        <div class="col-md-9" style="padding-right: 0px;padding-left: 0px;background-color: #E5E5E5">
            <div class="mydata_list">
                <div class="data_list_title">
                    <span class="title"><a href="${pageContext.request.contextPath}/blog/articles/52.html">使用CXF开发WebService客户端</a></span>
                    <span class="date"><a
                            href="${pageContext.request.contextPath}/blog/articles/52.html">2016年02月15日</a></span>
                </div>

                <div class="datas">
                    <ul>
                        <li style="margin-bottom: 30px">


                            <span class="summary">摘要: 前面一讲开发了webservice服务器端接口，今天的话，我们来开发webservice客户端，让大家来体验下过程；首先建一个Maven项目，项目名字，WS_Client；然后我们要用CXF给我们提供的工具wsdl2java 来根据请求的url生成客户端代码；wsdl2java工具在CXF开发包里；开发下载地...</span>
                            <span class="img">

						  		<a href="/blog/articles/52.html"><img
                                        src="${pageContext.request.contextPath}/static/userimages/a.jpg"
                                        alt="1455539511890048174.jpg"></a>
						  		&nbsp;&nbsp;

						  		<a href="/blog/articles/52.html"><img alt="QQ鎴浘20160215203213.jpg"
                                                                      src="${pageContext.request.contextPath}/static/userimages/b.jpg"
                                                                      title="1455539700734093102.jpg" width="667"
                                                                      height="264" style="width: 667px; height: 264px;"></a>
						  		&nbsp;&nbsp;

						  		<a href="/blog/articles/52.html"><img alt="QQ鎴浘20160215203317.jpg"
                                                                      src="${pageContext.request.contextPath}/static/userimages/c.jpg"
                                                                      title="1455539761187019902.jpg"></a>
						  		&nbsp;&nbsp;

					  	</span>
                            <span class="info">发表于 2016-02-15 21:06 阅读(71) 评论(5) </span>

                        </li>
                        <%--<hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:  10px;"/>--%>
                    </ul>
                </div>
            </div>
            <div class="mydata_list">
                <div class="data_list_title">
                    <span class="title"><a href="${pageContext.request.contextPath}/blog/articles/52.html">使用CXF开发WebService客户端</a></span>
                    <span class="date"><a
                            href="${pageContext.request.contextPath}/blog/articles/52.html">2016年02月15日</a></span>
                </div>

                <div class="datas">
                    <ul>
                        <li style="margin-bottom: 30px">


                            <span class="summary">摘要: 前面一讲开发了webservice服务器端接口，今天的话，我们来开发webservice客户端，让大家来体验下过程；首先建一个Maven项目，项目名字，WS_Client；然后我们要用CXF给我们提供的工具wsdl2java 来根据请求的url生成客户端代码；wsdl2java工具在CXF开发包里；开发下载地...</span>
                            <span class="img">

						  		<a href="/blog/articles/52.html"><img
                                        src="${pageContext.request.contextPath}/static/userimages/a.jpg"
                                        alt="1455539511890048174.jpg"></a>
						  		&nbsp;&nbsp;

						  		<a href="/blog/articles/52.html"><img alt="QQ鎴浘20160215203213.jpg"
                                                                      src="${pageContext.request.contextPath}/static/userimages/b.jpg"
                                                                      title="1455539700734093102.jpg" width="667"
                                                                      height="264" style="width: 667px; height: 264px;"></a>
						  		&nbsp;&nbsp;

						  		<a href="/blog/articles/52.html"><img alt="QQ鎴浘20160215203317.jpg"
                                                                      src="${pageContext.request.contextPath}/static/userimages/c.jpg"
                                                                      title="1455539761187019902.jpg"></a>
						  		&nbsp;&nbsp;

					  	</span>
                            <span class="info">发表于 2016-02-15 21:06 阅读(71) 评论(5) </span>

                        </li>
                        <%--<hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:  10px;"/>--%>
                    </ul>
                </div>
            </div>
            <div class="mydata_list">
                <div class="data_list_title">
                    <span class="title"><a href="${pageContext.request.contextPath}/blog/articles/52.html">使用CXF开发WebService客户端</a></span>
                    <span class="date"><a
                            href="${pageContext.request.contextPath}/blog/articles/52.html">2016年02月15日</a></span>
                </div>

                <div class="datas">
                    <ul>
                        <li style="margin-bottom: 30px">


                            <span class="summary">摘要: 前面一讲开发了webservice服务器端接口，今天的话，我们来开发webservice客户端，让大家来体验下过程；首先建一个Maven项目，项目名字，WS_Client；然后我们要用CXF给我们提供的工具wsdl2java 来根据请求的url生成客户端代码；wsdl2java工具在CXF开发包里；开发下载地...</span>
                            <span class="img">

						  		<a href="/blog/articles/52.html"><img
                                        src="${pageContext.request.contextPath}/static/userimages/a.jpg"
                                        alt="1455539511890048174.jpg"></a>
						  		&nbsp;&nbsp;

						  		<a href="/blog/articles/52.html"><img alt="QQ鎴浘20160215203213.jpg"
                                                                      src="${pageContext.request.contextPath}/static/userimages/b.jpg"
                                                                      title="1455539700734093102.jpg" width="667"
                                                                      height="264" style="width: 667px; height: 264px;"></a>
						  		&nbsp;&nbsp;

						  		<a href="/blog/articles/52.html"><img alt="QQ鎴浘20160215203317.jpg"
                                                                      src="${pageContext.request.contextPath}/static/userimages/c.jpg"
                                                                      title="1455539761187019902.jpg"></a>
						  		&nbsp;&nbsp;

					  	</span>
                            <span class="info">发表于 2016-02-15 21:06 阅读(71) 评论(5) </span>

                        </li>
                        <%--<hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:  10px;"/>--%>
                    </ul>
                </div>
            </div>
            <div class="mydata_list">
                <div class="data_list_title">
                    <span class="title"><a href="${pageContext.request.contextPath}/blog/articles/52.html">使用CXF开发WebService客户端</a></span>
                    <span class="date"><a
                            href="${pageContext.request.contextPath}/blog/articles/52.html">2016年02月15日</a></span>
                </div>

                <div class="datas">
                    <ul>
                        <li style="margin-bottom: 30px">


                            <span class="summary">摘要: 前面一讲开发了webservice服务器端接口，今天的话，我们来开发webservice客户端，让大家来体验下过程；首先建一个Maven项目，项目名字，WS_Client；然后我们要用CXF给我们提供的工具wsdl2java 来根据请求的url生成客户端代码；wsdl2java工具在CXF开发包里；开发下载地...</span>
                            <span class="img">

						  		<a href="/blog/articles/52.html"><img
                                        src="${pageContext.request.contextPath}/static/userimages/a.jpg"
                                        alt="1455539511890048174.jpg"></a>
						  		&nbsp;&nbsp;

						  		<a href="/blog/articles/52.html"><img alt="QQ鎴浘20160215203213.jpg"
                                                                      src="${pageContext.request.contextPath}/static/userimages/b.jpg"
                                                                      title="1455539700734093102.jpg" width="667"
                                                                      height="264" style="width: 667px; height: 264px;"></a>
						  		&nbsp;&nbsp;

						  		<a href="/blog/articles/52.html"><img alt="QQ鎴浘20160215203317.jpg"
                                                                      src="${pageContext.request.contextPath}/static/userimages/c.jpg"
                                                                      title="1455539761187019902.jpg"></a>
						  		&nbsp;&nbsp;

					  	</span>
                            <span class="info">发表于 2016-02-15 21:06 阅读(71) 评论(5) </span>

                        </li>
                        <%--<hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:  10px;"/>--%>
                    </ul>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div align="center" style="padding-top: 80px">
                        毕业设计-博客系统:<a href="https://github.com/xiejiny/MyBlog">源码在这</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
