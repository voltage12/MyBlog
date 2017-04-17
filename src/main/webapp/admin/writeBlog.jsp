<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" charset="utf-8"
        src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" charset="utf-8"
        src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.min.js"></script>

<script type="text/javascript">
    function submitData() {
        var title = $("#title").val();
        var blogTypeId = $("#blogTypeId").val();
        var content = UE.getEditor('editor').getContent();

        if (title == null || title == '') {
            alert("请输入标题！");
        } else if (blogTypeId == null || blogTypeId == '') {
            alert("请选择博客类别！");
        } else if (content == null || content == '') {
            alert("请填写内容！");
        } else {
            $.post("${pageContext.request.contextPath}/admin/blog/add.do", {
                'title': title, 'blogType.id': blogTypeId,
                'content': content, 'summary': UE.getEditor('editor').getContentTxt().substr(0, 150)
            }, function (result) {
                if (result.success) {
                    alert("博客发布成功！");
                    resetValue();
                } else {
                    alert("博客发布失败！");
                }
            }, "json");
        }
    }

    function resetValue() {
        $("#title").val("");
        $("#blogTypeId").val("");
        UE.getEditor('editor').setContent('');
    }
</script>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">Panel title</h3>
            </div>
            <div class="panel-body">
                <form>
                    <div class="form-group" style="width: 400px">
                        <label>博客标题</label>
                        <input id="title" name="title" type="text" class="form-control" placeholder="请输入博客标题">
                    </div>
                    <div class="form-group" style="width: 400px">
                        <label>博客类别</label>
                        <select class="form-control" name="blogTypeId" id="blogTypeId">
                            <option value="">请选择博客类别。。。</option>
                            <c:forEach var="blogType" items="${blogTypeList}">
                                <option value="${blogType.id}">${blogType.typeName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>博客内容</label>
                        <script id="editor" name="content" type="text/plain">
                        </script>
                        <script type="text/javascript">
                            var ue = UE.getEditor('editor', {initialFrameHeight: 500});
                        </script>
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-primary" onclick="submitData()">提交</button>
                        <button type="button" class="btn btn-danger" onclick="resetValue()">重置</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
