<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    function submitData() {
        var newPw = $("#newPw").val();
        var newPw1 = $("#newPw1").val();
        var oldPw = $("#oldPw").val();
        if (newPw && newPw1 && oldPw) {
            if (newPw === newPw1) {
                $.post("${pageContext.request.contextPath}/admin/user/changePw.do", {
                    'oldPw': oldPw, 'newPw': newPw
                }, function (result) {
                    if (result.success) {
                        alert("修改成功！");
                        $("#newPw").val('');
                        $("#newPw1").val('');
                        $("#oldPw").val('');
                    } else {
                        alert("修改失败，原因：" + result.errorInfo);
                    }
                }, "json");
            } else {
                alert("输入的密码不一致！");
            }
        } else {
            alert("请填写完整内容");
        }
    }
</script>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">密码管理</h3>
            </div>
            <div class="panel-body">
                <form method="post" action="${pageContext.request.contextPath}/admin/user/changeInfo.do">
                    <div class="form-group" style="width: 400px">
                        <label>旧密码</label>
                        <input id="oldPw" name="oldPw" type="password" class="form-control">
                    </div>
                    <div class="form-group" style="width: 400px">
                        <label>新密码</label>
                        <input id="newPw" name="newPw" type="password" class="form-control">
                    </div>
                    <div class="form-group" style="width: 400px">
                        <label>确认密码</label>
                        <input id="newPw1" name="newPw1" type="password" class="form-control">
                    </div>
                    <div class="form-group">
                        <button type="button" onclick="submitData()" class="btn btn-primary">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


