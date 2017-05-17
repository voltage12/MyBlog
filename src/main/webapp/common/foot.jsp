<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <div class="col-md-4" style="padding-top: 40px;padding-left: 5%">
        <scan>
            毕业设计-博客系统:<a href="https://github.com/xiejiny/MyBlog">源码在这</a>
        </scan>
    </div>
    <div class="col-md-4" style="padding-top: 40px;text-align: center">
        <scan>
            <a href="${pageContext.request.contextPath}">回到首页</a>/<a href="#top" target="_self">返回顶部</a>
        </scan>
    </div>
    <div class="col-md-4" style="padding-top: 40px;text-align: right;padding-right: 5%">
        <scan>
            <a href="https://github.com/xiejiny/MyBlog" data-toggle="modal" data-target="#myModa2">关于本站</a>
            <%-- 下面是模态对话框 --%>
            <div class="modal fade bs-example-modal-lg" id="myModa2" tabindex="-1" role="dialog"
                 aria-labelledby="myModalLabel2">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header" style="margin-bottom: 25px">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel2" style="text-align: center;">本站介绍</h4>
                        </div>
                        <div class="modal-body" style="font-size: 17px;text-align: left">
                            &nbsp;&nbsp;因为本来就打算建一个自己的博客网站，并且不打算在博客园之类的网站申请账号，也不打算用wordpress来快速搭建博客，
                            准备使用Spring+SpringMvc+Mybatis从零开始写一个博客网站，刚好在选毕业设计课题的时候发现有一个实现博客系统的课题，也没人和我抢
                            （确实在现在这个年头，也没人对写一个博客网站有兴趣）。总得来说，实现起来非常简单，其实这么小的网站，直接用servlet和jsp写都非常
                            方便，不过刚学了SSM，可以拿来练练手。
                        </div>
                    </div>
                </div>
            </div>
            <%-- 上面是模态对话框 --%>
        </scan>
    </div>
</div>