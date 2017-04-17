<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Title</title>
    <%-- 加入bootstrap和jquery库 --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mycss.css">
    <script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="page-header">
                <h1>博客系统后台管理页面</h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">导航栏</a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                   aria-haspopup="true" aria-expanded="false">博客管理<span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">写博客</a></li>
                                    <li><a href="#">博客信息管理</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                   aria-haspopup="true" aria-expanded="false">评论管理<span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">评论审核</a></li>
                                    <li><a href="#">评论信息管理</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                   aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Action</a></li>
                                    <li><a href="#">Another action</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#">Separated link</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#">One more separated link</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                   aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Action</a></li>
                                    <li><a href="#">Another action</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#">Separated link</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#">One more separated link</a></li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#">退出系统</a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">Panel title</h3>
                </div>
                <div class="panel-body">
                    <button type="button" class="btn btn-primary">修改</button>
                    <button type="button" class="btn btn-danger">删除</button>
                    <div style="padding-top: 5px">
                        <table class="table table-bordered">
                            <thead>
                            <%--<td style="width: 30px"></td>--%>
                            <td style="text-align: center;width: 40px"><input type="checkbox" value=""></td>
                            <td style="text-align: center;width: 60px">编号</td>
                            <td style="text-align: center">博客标题</td>
                            <td style="text-align: center;width: 160px">发布日期</td>
                            <td style="text-align: center;width: 160px">博客类别</td>
                            </thead>
                            <tr>
                                <td style="text-align: center;width: 40px"><input type="checkbox" value=""></td>
                                <td style="text-align: center;width: 60px">1</td>
                                <td style="text-align: center">博客标题</td>
                                <td style="text-align: center;width: 160px">2017-04-17</td>
                                <td style="text-align: center;width: 160px">javaweb</td>
                            </tr>
                            <tr>
                                <td style="text-align: center;width: 40px"><input type="checkbox" value=""></td>
                                <td style="text-align: center;width: 60px">1</td>
                                <td style="text-align: center">博客标题</td>
                                <td style="text-align: center;width: 160px">2017-04-17</td>
                                <td style="text-align: center;width: 160px">javaweb</td>
                            </tr>
                            <tr>
                                <td style="text-align: center;width: 40px"><input type="checkbox" value=""></td>
                                <td style="text-align: center;width: 60px">1</td>
                                <td style="text-align: center">博客标题</td>
                                <td style="text-align: center;width: 160px">2017-04-17</td>
                                <td style="text-align: center;width: 160px">javaweb</td>
                            </tr>
                            <tr>
                                <td style="text-align: center;width: 40px"><input type="checkbox" value=""></td>
                                <td style="text-align: center;width: 60px">1</td>
                                <td style="text-align: center">博客标题</td>
                                <td style="text-align: center;width: 160px">2017-04-17</td>
                                <td style="text-align: center;width: 160px">javaweb</td>
                            </tr>
                            <tr>
                                <td style="text-align: center;width: 40px"><input type="checkbox" value=""></td>
                                <td style="text-align: center;width: 60px">1</td>
                                <td style="text-align: center">博客标题</td>
                                <td style="text-align: center;width: 160px">2017-04-17</td>
                                <td style="text-align: center;width: 160px">javaweb</td>
                            </tr>
                            <tr>
                                <td style="text-align: center;width: 40px"><input type="checkbox" value=""></td>
                                <td style="text-align: center;width: 60px">1</td>
                                <td style="text-align: center">博客标题</td>
                                <td style="text-align: center;width: 160px">2017-04-17</td>
                                <td style="text-align: center;width: 160px">javaweb</td>
                            </tr>
                            <tr>
                                <td style="text-align: center;width: 40px"><input type="checkbox" value=""></td>
                                <td style="text-align: center;width: 60px">1</td>
                                <td style="text-align: center">博客标题</td>
                                <td style="text-align: center;width: 160px">2017-04-17</td>
                                <td style="text-align: center;width: 160px">javaweb</td>
                            </tr>
                            <tr>
                                <td style="text-align: center;width: 40px"><input type="checkbox" value=""></td>
                                <td style="text-align: center;width: 60px">1</td>
                                <td style="text-align: center">博客标题</td>
                                <td style="text-align: center;width: 160px">2017-04-17</td>
                                <td style="text-align: center;width: 160px">javaweb</td>
                            </tr>
                            <tr>
                                <td style="text-align: center;width: 40px"><input type="checkbox" value=""></td>
                                <td style="text-align: center;width: 60px">1</td>
                                <td style="text-align: center">博客标题</td>
                                <td style="text-align: center;width: 160px">2017-04-17</td>
                                <td style="text-align: center;width: 160px">javaweb</td>
                            </tr>
                            <tr>
                                <td style="text-align: center;width: 40px"><input type="checkbox" value=""></td>
                                <td style="text-align: center;width: 60px">1</td>
                                <td style="text-align: center">博客标题</td>
                                <td style="text-align: center;width: 160px">2017-04-17</td>
                                <td style="text-align: center;width: 160px">javaweb</td>
                            </tr>
                            <tr>
                                <td style="text-align: center;width: 40px"><input type="checkbox" value=""></td>
                                <td style="text-align: center;width: 60px">1</td>
                                <td style="text-align: center">博客标题</td>
                                <td style="text-align: center;width: 160px">2017-04-17</td>
                                <td style="text-align: center;width: 160px">javaweb</td>
                            </tr>
                        </table>
                    </div>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li>
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li>
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
