package cc.xiejy.util;

/**
 * 分页工具类，用来生成分页栏html代码
 * @author Xie
 */
public class PageUtil {

    /**
     * 根据总记录数计算出总共的页数
     */
    public static int getTotalPage(int totalNum, int currentPage, int pageSize) {
        int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        return totalPage;
    }

    /**
     * 生成分页代码
     * @param targetUrl   目标地址
     * @param totalNum    总记录数
     * @param currentPage 当前页
     * @param pageSize    每页大小
     * @param param       附加参数
     * @return
     */
    public static String genPagination(String targetUrl, long totalNum, int currentPage, int pageSize, String param) {
        //根据总记录数计算出总共的页数，如果刚好整除，则结果就是页数，否则页数要加1
        long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        if (totalPage == 0) {
            return "未查询到数据";
        } else {
            StringBuffer pageCode = new StringBuffer();
            pageCode.append("<li><a href='" + targetUrl + "?page=1&" + param + "'>首页</a></li>");
            //如果当前不是第一页的话，上一页按钮是可用的
            if (currentPage > 1) {
                pageCode.append("<li><a href='" + targetUrl + "?page=" + (currentPage - 1) + "&" + param + "'>上一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='" + targetUrl + "?page=" + (currentPage - 1) + "&" + param + "'>上一页</a></li>");
            }
            //考虑到页数很多的情况，只显示当前页的前三页和后三页
            for (int i = currentPage - 3; i <= currentPage + 3; i++) {
                if (i < 1 || i > totalPage) {
                    continue;
                }
                //如果是当前页的话，更改按钮的样式
                if (i == currentPage) {
                    pageCode.append("<li class='active'><a href='" + targetUrl + "?page=" + i + "&" + param + "'>" + i + "</a></li>");
                } else {
                    pageCode.append("<li><a href='" + targetUrl + "?page=" + i + "&" + param + "'>" + i + "</a></li>");
                }
            }
            //如果当前不是最后一页的话，下一页按钮是可用的
            if (currentPage < totalPage) {
                pageCode.append("<li><a href='" + targetUrl + "?page=" + (currentPage + 1) + "&" + param + "'>下一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='" + targetUrl + "?page=" + (currentPage + 1) + "&" + param + "'>下一页</a></li>");
            }
            pageCode.append("<li><a href='" + targetUrl + "?page=" + totalPage + "&" + param + "'>尾页</a></li>");
            return pageCode.toString();
        }
    }

    public static String genPagination(long totalNum, int currentPage, int pageSize) {
        String targetUrl = "";
        long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        if (totalPage == 0) {
            return "未查询到数据";
        } else {
            StringBuffer pageCode = new StringBuffer();
            pageCode.append("<li><a href='javascript:getBlogList(1)'>首页</a></li>");
            if (currentPage > 1) {
                pageCode.append("<li><a href='javascript:getBlogList(" + (currentPage - 1) + ")'>上一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='javascript:getBlogList(" + (currentPage - 1) + ")'>上一页</a></li>");
            }
            for (int i = currentPage - 3; i <= currentPage + 3; i++) {
                if (i < 1 || i > totalPage) {
                    continue;
                }
                if (i == currentPage) {
                    pageCode.append("<li class='active'><a href='javascript:getBlogList(" + i + ")'>" + i + "</a></li>");
                } else {
                    pageCode.append("<li><a href='javascript:getBlogList(" + i + ")'>" + i + "</a></li>");
                }
            }
            if (currentPage < totalPage) {
                pageCode.append("<li><a href='javascript:getBlogList(" + (currentPage + 1) + ")'>下一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='javascript:getBlogList(" + (currentPage + 1) + ")'>下一页</a></li>");
            }
            pageCode.append("<li><a href='javascript:getBlogList(" + totalPage + ")'>尾页</a></li>");
            return pageCode.toString();
        }
    }
}
