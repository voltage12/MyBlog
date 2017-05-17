<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">

    var showState = 0;

    function showOtherComment() {
        if (showState == 0) {
            $(".otherComment").show();
            $("#showBt").html("折叠");
            showState = 1;
        } else {
            $(".otherComment").hide();
            $("#showBt").html("展开");
            showState = 0;
        }
    }

    function submitData() {
        var content = $("#content").val();
        var imageCode = $("#imageCode").val();
        if (content == null || content == "") {
            alert("请输入评论内容！");
        } else if (imageCode == null || imageCode == "") {
            alert("请填写验证码！");
        } else {
            $.post("${pageContext.request.contextPath}/comment/add.do", {
                "content": content,
                'imageCode': imageCode,
                'blogId': '${blog.id}'
            }, function (result) {
                if (result.success) {
                    alert("评论已提交成功，审核通过后显示！");
                    location.reload([true]);
                } else {
                    alert(result.errorInfo);
                }
            }, "json");
        }
    }

    function loadimage() {
        document.getElementById("randImage").src = "${pageContext.request.contextPath}/image.jsp?" + Math.random();
    }
</script>

<div class="mydata_list">
    <div class="data_list_title">
        <div class="blog_title" style="width: 100%;"><h3><strong>${blog.title}</strong></h3></div>
    </div>

    <div>
        <div class="blog_info" style="margin-top: 5px;margin-bottom: 10px;">
            发布时间：『 <fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy-MM-dd HH:mm"/> 』&nbsp;&nbsp;博客类别：${blog.blogType.typeName}&nbsp;&nbsp;阅读(${blog.click})
            评论(${blog.reply})
        </div>
        <div class="blog_content">
            ${blog.content}
        </div>
    </div>
</div>

<div class="mydata_list">
    <div class="data_list_title">
        评论信息
    </div>

    <div class="commentDatas">
        <c:choose>
            <c:when test="${commentList.size() eq 0}">
                暂无评论
            </c:when>
            <c:otherwise>
                <c:forEach var="comment" items="${commentList}" varStatus="status">
                    <c:choose>
                        <c:when test="${status.index<10 }">
                            <div class="comment">
                                <span>${status.index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.userIp }：${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;<fmt:formatDate
                                        value="${comment.commentDate }" type="date"
                                        pattern="yyyy-MM-dd HH:mm"/>&nbsp;]</span>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="otherComment">
                                <span>${status.index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.userIp }：${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;<fmt:formatDate
                                        value="${comment.commentDate }" type="date"
                                        pattern="yyyy-MM-dd HH:mm"/>&nbsp;]</span>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <c:if test="${commentList.size() > 10}">
            <div class="comment">
                <button id="showBt" type="button" class="btn btn-primary" onclick="showOtherComment()">展开</button>
            </div>
        </c:if>
    </div>
</div>

<div class="mydata_list">
    <div class="data_list_title">
        发表评论
    </div>
    <div class="publish_comment">
        <div>
            <textarea style="width: 100%" rows="3" id="content" name="content" placeholder="写下你的评论..."></textarea>
        </div>
        <div class="publishButton">
            <button class="btn btn-primary" type="button" onclick="submitData()">发表评论</button>
            验证码：<input type="text" value="" name="imageCode" id="imageCode" size="10"/>&nbsp;<img onclick="loadimage();"
                                                                                                  title="换一张试试"
                                                                                                  name="randImage"
                                                                                                  id="randImage"
                                                                                                  src="${pageContext.request.contextPath}/image.jsp"
                                                                                                  width="60" height="20"
                                                                                                  border="1"
                                                                                                  align="absmiddle">
        </div>
    </div>
</div>

<div style="margin-left: 5%;margin-right: 5%;">
    <nav aria-label="...">
        <ul class="pager">
            <li class="previous <c:if test="${empty lastBlog}">disabled</c:if>">
                <a href="${pageContext.request.contextPath}/blog/articles/${lastBlog.id}.html">
                    <span aria-hidden="true">
                        &larr;
                    </span>
                    <c:choose>
                        <c:when test="${empty lastBlog}">
                            上一篇:没有了
                        </c:when>
                        <c:otherwise>
                            上一篇:${lastBlog.title}
                        </c:otherwise>
                    </c:choose>
                </a>
            </li>
            <li class="next  <c:if test="${empty nextBlog}">disabled</c:if>">
                <a href="${pageContext.request.contextPath}/blog/articles/${nextBlog.id}.html">
                    <c:choose>
                        <c:when test="${empty nextBlog}">
                            下一篇:没有了
                        </c:when>
                        <c:otherwise>
                            下一篇:${nextBlog.title}
                        </c:otherwise>
                    </c:choose>
                    <span aria-hidden="true">&rarr;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>
