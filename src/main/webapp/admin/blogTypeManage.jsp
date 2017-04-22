<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>

<script type="text/javascript">

    function resetVal() {
        $("#typeName").val('');
        $("#orderNum").val('');
    }

    function checkForm() {
        if ($("#typeName").val() == null || $("#typeName").val() == '') {
            alert("请输入类型名称");
            return false;
        } else if ($("#orderNum").val() == null || $("#orderNum").val() == '') {
            alert("请输入排序序号");
            return false;
        }
        return true;
    }

    function showAdd() {
        $('#add').show();
        $('#change').hide();
        $('#bt').click();
    }

    function showChange(typeId) {
        $('#id').val(typeId);
        $.post('${pageContext.request.contextPath}/admin/blogType/getInfo.do', {
            "typeId": typeId,
        }, function (result) {
            result = $.parseJSON(result);
            $("#typeName").val(result.typeName);
            $("#orderNum").val(result.orderNum);
        });
        $('#add').hide();
        $('#change').show();
        $('#bt').click();
    }

    function add() {
        if (checkForm()) {
            var typeName = $("#typeName").val();
            var orderNum = $("#orderNum").val();

            $.post('${pageContext.request.contextPath}/admin/blogType/save.do', {
                "typeName": typeName,
                "orderNum": orderNum
            }, function (result) {
                result = $.parseJSON(result);
                if (result.success) {
                    alert("添加成功");
                    resetVal();
                    location.reload([true]);
                } else {
                    alert("添加失败");
                }
            });
        }
    }

    function change() {
        if (checkForm()) {
            var typeId = $("#id").val();
            var typeName = $("#typeName").val();
            var orderNum = $("#orderNum").val();

            $.post('${pageContext.request.contextPath}/admin/blogType/save.do', {
                "typeName": typeName,
                "orderNum": orderNum,
                "id": typeId
            }, function (result) {
                result = $.parseJSON(result);
                if (result.success) {
                    alert("修改成功");
                    resetVal();
                    location.reload([true]);
                } else {
                    alert("修改失败");
                }
            });
        }
    }

    function deleteBlog(typeId) {
        $.post('${pageContext.request.contextPath}/admin/blogType/delete.do', {
            "typeId": typeId,
        }, function (result) {
            result = $.parseJSON(result);
            if (result.success) {
                alert("删除成功");
                location.reload([true]);
            } else {
                alert(result.errorInfo);
            }
        });
    }
</script>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">博客类别管理面板</h3>
            </div>
            <div class="panel-body">
                <%-- 下面是添加类别的模态对话框 --%>
                <div class="modal fade" id="myModal1" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">添加博客类别</h4>
                            </div>
                            <div class="modal-body">
                                <input class="hidden" type="text" name="id" id="id">
                                <div class="form-group">
                                    <label for="typeName">博客类别名称</label>
                                    <input type="text" class="form-control" name="typeName" id="typeName"
                                           placeholder="类别名称">
                                </div>
                                <div class="form-group">
                                    <label for="orderNum">排序序号</label>
                                    <input type="text" class="form-control" name="orderNum" id="orderNum"
                                           placeholder="排序序号">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" onclick="resetVal()">重置</button>
                                <button type="button" id="add" class="btn btn-primary" onclick="add()">提交</button>
                                <button type="button" id="change" class="btn btn-primary" style="display: none"
                                        onclick="change()">修改
                                </button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
                <%-- 上面是添加类别的模态对话框 --%>
                <button class="hidden" id="bt" data-toggle="modal" data-target="#myModal1">
                    添加类别
                </button>
                <button type="button" class="btn btn-primary" onclick="showAdd()">
                    添加类别
                </button>
                <div style="padding-top: 5px">
                    <table class="table table-bordered" style="text-align: center;">
                        <thead>
                        <tr>
                            <td style="width: 60px;display:table-cell; vertical-align:middle">编号</td>
                            <td style="display:table-cell; vertical-align:middle">博客类别名称</td>
                            <td style="width: 90px;display:table-cell; vertical-align:middle">排序序号</td>
                            <td style="width: 90px;display:table-cell; vertical-align:middle">博客数量</td>
                            <td style="width: 70px;display:table-cell; vertical-align:middle"></td>
                            <td style="width: 70px;display:table-cell; vertical-align:middle"></td>
                        </tr>
                        </thead>
                        <tbody id="tableBody">
                        <c:forEach items="${applicationScope.blogTypeList}" var="blogType">
                            <tr>
                                <td style="display:table-cell; vertical-align:middle">${blogType.id}</td>
                                <td style="display:table-cell; vertical-align:middle">${blogType.typeName}</td>
                                <td style="display:table-cell; vertical-align:middle">${blogType.orderNum}</td>
                                <td style="display:table-cell; vertical-align:middle">${blogType.blogCount}</td>
                                <td style="display:table-cell; vertical-align:middle">
                                    <button type="button" class="btn btn-info" onclick="showChange(${blogType.id})">修改
                                    </button>
                                </td>
                                <td style="display:table-cell; vertical-align:middle">
                                    <button type="button" class="btn btn-danger" onclick="deleteBlog(${blogType.id})">
                                        删除
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>


