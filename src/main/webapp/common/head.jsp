<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="top" class="col-md-3" style="padding-right: 0px;padding-left: 0px">
    <div id="overDiv" class="overlay" style="background-color: #87CEFA;opacity: 0.5;"></div>
    <div class="intrude-less" style="margin-bottom: 30px">
        <header id="header" class="inner">
            <a href="${pageContext.request.contextPath}/login.jsp" class="profilepic">
                <img src="${pageContext.request.contextPath}/static/userimages/${user.imageLocal}"
                     class="animated zoomIn">
            </a>
        </header>
        <h1>
            <a href="/" style="font-size: 20px;font-weight: bold" data-toggle="modal" data-target="#myModa2">
                ${user.nickName}
            </a>
            <%-- 下面是模态对话框 --%>
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header" style="margin-bottom: 25px">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel" style="text-align: center;">个人介绍</h4>
                        </div>
                        <div class="modal-body" style="font-size: 17px">
                            ${user.introduce}
                        </div>
                    </div>
                </div>
            </div>
            <%-- 上面是模态对话框 --%>
        </h1>
        <p style="font-size: 18px">${user.sign}</p>
    </div>
    <div style="margin-top: 80px">
        <div class="data_list">
            <div class="data_list_title">
                <img src="${pageContext.request.contextPath}/static/images/byType_icon.png"/>
                按文章类别
            </div>
            <div class="datas">
                <ul>
                    <c:forEach items="${blogTypeList}" var="blogType">
                        <li><span><a
                                href="${pageContext.request.contextPath}/index.html?typeId=${blogType.id}">${blogType.typeName}(${blogType.blogCount})</a></span>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="data_list">
            <div class="data_list_title">
                <img src="${pageContext.request.contextPath}/static/images/byDate_icon.png"/>
                按发布日期
            </div>
            <div class="datas">
                <ul>
                    <c:forEach items="${blogListGroupByDate}" var="blogGBD">
                        <li><span><a
                                href="${pageContext.request.contextPath}/index.html?releaseDateStr=${blogGBD.releaseDateStr}">${blogGBD.releaseDateStr}(${blogGBD.blogCountForSameDate})</a></span>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>