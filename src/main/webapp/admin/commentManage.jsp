<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
    function deleteComment() {
        var id_array = [];
        $('input[name="selectId"]:checked').each(function () {
            id_array.push($(this).val());
        });
        var idstr = id_array.join(',');
        if (idstr == null || idstr == '') {
            alert("请选择需要删除的评论");
        } else {
            $.post("${pageContext.request.contextPath}/admin/comment/delete.do", {"selectId": idstr}, function (result) {
                result = $.parseJSON(result);
                if (result.success) {
                    alert("删除成功");
                    location.reload([true]);
                } else {
                    alert("删除失败");
                }
            });
        }
    }

    function selectAll() {
        if ($("#bt").is(":checked")) {
            $('input[name="selectId"]').prop("checked", true);
        } else {
            $('input[name="selectId"]').prop("checked", false);
        }
    }
</script>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">评论管理面板</h3>
            </div>
            <div class="panel-body">
                <form class="form-inline">
                    <button type="button" class="btn btn-danger" onclick="deleteComment()">删除</button>
                </form>
                <div style="padding-top: 5px">
                    <table class="table table-bordered" style="text-align: center;">
                        <thead>
                        <tr>
                            <td style="width: 40px;display:table-cell; vertical-align:middle">
                                <input type="checkbox" id="bt" onclick="selectAll()"/>
                            </td>
                            <td style="width: 60px;display:table-cell; vertical-align:middle">编号</td>
                            <td style="width: 100px;display:table-cell; vertical-align:middle">用户IP</td>
                            <td style="display:table-cell; vertical-align:middle">评论内容</td>
                            <td style="width: 250px;display:table-cell; vertical-align:middle">评论的博客</td>
                            <td style="width: 90px;display:table-cell; vertical-align:middle">状态</td>
                        </tr>
                        </thead>
                        <tbody id="tableBody">
                        <c:forEach items="${commentList}" var="comment">
                            <tr>
                                <td style="display:table-cell; vertical-align:middle"><input name="selectId"
                                                                                             type="checkbox"
                                                                                             value="${comment.id}"></td>
                                <td style="display:table-cell; vertical-align:middle">${comment.id}</td>
                                <td style="display:table-cell; vertical-align:middle">${comment.userIp}</td>
                                <td style="display:table-cell; vertical-align:middle">${comment.content}</td>
                                <td style="display:table-cell; vertical-align:middle">
                                    <a href="${pageContext.request.contextPath}/blog/articles/${comment.blog.id}.html">
                                            ${comment.blog.title}
                                    </a>
                                </td>
                                <td style="display:table-cell; vertical-align:middle">
                                    <c:choose>
                                        <c:when test="${comment.state == 0}">
                                            未审核
                                        </c:when>
                                        <c:when test="${comment.state == 1}">
                                            审核通过
                                        </c:when>
                                        <c:when test="${comment.state == 2}">
                                            审核未通过
                                        </c:when>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <nav aria-label="Page navigation">
                    <ul class="pagination" id="nav">
                        ${pageCode}
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
