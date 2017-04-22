<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
    function getBlogList(var1) {
        $.post("${pageContext.request.contextPath}/admin/blog/getList.do", {"page": var1}, function (result) {
            $("#tableBody").empty();
            result = $.parseJSON(result);
            for (var i = 1, l = result.length; i < l; i++) {
                var tr = document.createElement("tr");
                tr.id = "tr" + i;
                var td1 = document.createElement("td");
                var input1 = document.createElement("input");
//                input1.id = "selectId";
                input1.setAttribute("type", "checkbox");
                input1.setAttribute("value", result[i].id);
                input1.setAttribute("name", "selectId");
                td1.appendChild(input1);
                var td2 = document.createElement("td");
                td2.innerText = result[i].id;
                var td3 = document.createElement("td");
                var target = document.createElement("a");
                target.innerText = result[i].title;
                target.setAttribute("href", "${pageContext.request.contextPath}/admin/index.html?targetPage=writeBlog&blogId=" + result[i].id);
                td3.appendChild(target);
                var td4 = document.createElement("td");
                td4.innerText = result[i].releaseDate;
                var td5 = document.createElement("td");
                td5.innerText = result[i].blogTypeName;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tr.appendChild(td5);
                $("#tableBody").append(tr);
            }
            var pageCode = $(result[0].pageCode);
            $("#nav").empty();
            $("#nav").append(pageCode);
        });
    }

    function deleteBlog() {
        var id_array = [];
        $('input[name="selectId"]:checked').each(function () {
            id_array.push($(this).val());//向数组中添加元素
        });
//        alert(id_array);
        var idstr = id_array.join(',');
        if (idstr == null || idstr == '') {
            alert("请选择需要删除的博客");
        } else {
            $.post("${pageContext.request.contextPath}/admin/blog/delete.do", {"selectId": idstr}, function (result) {
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
</script>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">博客管理面板</h3>
            </div>
            <div class="panel-body">
                <form class="form-inline" action="${pageContext.request.contextPath}/admin/blog/blogManage.do"
                      method="get">
                    <button type="button" class="btn btn-danger" onclick="deleteBlog()">删除</button>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/blog/blogManage.html"
                       role="button">全部博客</a>
                    <div class="form-group" style="float: right">
                        <input type="text" class="form-control" name="blogTitle" placeholder="请输入要搜索的标题">
                        <button type="submit" class="btn btn-default">搜索</button>
                    </div>
                </form>
                <div style="padding-top: 5px">
                    <table class="table table-bordered" style="text-align: center;">
                        <thead>
                            <tr>
                                <td style="width: 40px;display:table-cell; vertical-align:middle"><input type="checkbox"
                                                                                                         value=""></td>
                                <td style="width: 60px;display:table-cell; vertical-align:middle">编号</td>
                                <td style="display:table-cell; vertical-align:middle">博客标题</td>
                                <td style="width: 160px;display:table-cell; vertical-align:middle">发布日期</td>
                                <td style="width: 160px;display:table-cell; vertical-align:middle">博客类别</td>
                                <td style="width: 40px;display:table-cell; vertical-align:middle"></td>
                            </tr>
                        </thead>
                        <tbody id="tableBody">
                        <c:forEach items="${blogList}" var="blog">
                            <tr>
                                <td style="display:table-cell; vertical-align:middle"><input name="selectId"
                                                                                             type="checkbox"
                                                                                             value="${blog.id}"></td>
                                <td style="display:table-cell; vertical-align:middle">${blog.id}</td>
                                <td style="display:table-cell; vertical-align:middle"><a
                                        href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html"
                                        role="button">${blog.title}</a></td>
                                <td style="display:table-cell; vertical-align:middle"><fmt:formatDate
                                        value="${blog.releaseDate}" type="date" pattern="yyyy年MM月dd日"/></td>
                                <td style="display:table-cell; vertical-align:middle">${blog.blogType.typeName}</td>
                                <td style="display:table-cell; vertical-align:middle"><a class="btn btn-info"
                                                                                         href="${pageContext.request.contextPath}/admin/blog/writeBlog.do?blogId=${blog.id}"
                                                                                         role="button">修改</a></td>
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
