package cc.xiejy.util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 返回json字符串的工具类
 */
public class ResponseUtil {

    public static void write(HttpServletResponse response, Object o) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }
}
