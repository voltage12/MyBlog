<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach items="${blogList}" var="blog">
    <div class="mydata_list">
        <div class="data_list_title">
            <span class="title"><a
                    href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">${blog.title}</a></span>
            <span class="date">
                <a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">
                    <fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy年MM月dd日"/>
                </a>
            </span>
        </div>

        <div class="datas">
            <ul>
                <li style="margin-bottom: 30px">
                    <span class="summary">摘要: ${blog.summary}。。。</span>
                    <span class="img">
                        <c:forEach items="${blog.imageList}" var="image">
                            <a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">${image}</a>
                            &nbsp;&nbsp;
                        </c:forEach>
                    </span>
                    <span class="info">发表于 <fmt:formatDate value="${blog.releaseDate}" type="date"
                                                           pattern="yyyy-MM-dd HH:mm"/> 阅读(${blog.click}) 评论(${blog.reply}) </span>
                </li>
                    <%--<hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:  10px;"/>--%>
            </ul>
        </div>
    </div>
</c:forEach>
<nav style="text-align: center;padding-top: 30px" aria-label="Page navigation">
    <ul class="pagination">
        <%--<li><a href="${pageContext.request.contextPath}/index.html">主页</a></li>--%>
        ${pageCode}
    </ul>
</nav>

