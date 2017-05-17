<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    function submitData() {
        var formData = new FormData(document.getElementById("form"));
        var filename = $("#image").val().toLowerCase();
        //上传图片之前先判断后缀名是否符合要求
        if (filename && (!(filename.endsWith('.jpeg') || filename.endsWith('.jpg') || filename.endsWith('.png') || filename.endsWith('.gif')))) {
            alert('请选择正确的图片格式');
        } else {
            $.ajax({
                'url': '${pageContext.request.contextPath}/admin/user/changeInfo.do',
                'type': 'POST',
                'data': formData,
                // 告诉jQuery不要去处理发送的数据
                'processData': false,
                // 告诉jQuery不要去设置Content-Type请求头
                'contentType': false,
                'success': function (responseStr) {
                    var result = $.parseJSON(responseStr);
                    if (result.success == true) {
                        alert("修改成功");
                    } else {
                        alert("修改失败");
                    }
                }
            });
        }
    }
</script>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">个人信息管理</h3>
            </div>
            <div class="panel-body">
                <form method="post" id="form" action="${pageContext.request.contextPath}/admin/user/changeInfo.do"
                      enctype="multipart/form-data">
                    <div class="form-group" style="width: 400px">
                        <label>用户名</label>
                        <input id="userName" name="userName" type="text" class="form-control" placeholder="请输入用户名"
                               value="${user.userName}">
                    </div>
                    <div class="form-group" style="width: 400px">
                        <label>昵称</label>
                        <input id="nickName" name="nickName" type="text" class="form-control" placeholder="请输入昵称"
                               value="${user.nickName}">
                    </div>
                    <div class="form-group">
                        <label for="image">头像</label>
                        <input type="file" id="image" name="image">
                    </div>
                    <div class="form-group" style="width: 700px">
                        <label>个人签名</label>
                        <input id="sign" name="sign" type="text" class="form-control" placeholder="请输入个人签名"
                               value="${user.sign}">
                    </div>
                    <div class="form-group" style="width: 700px">
                        <label>自我介绍</label>
                        <textarea id="introduce" name="introduce" class="form-control"
                                  placeholder="请输入自我介绍" rows="3">${user.introduce}</textarea>
                    </div>
                    <div class="form-group">
                        <button type="button" onclick="submitData()" class="btn btn-primary">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


