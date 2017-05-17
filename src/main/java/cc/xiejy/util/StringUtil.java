package cc.xiejy.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 */
public class StringUtil {


    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return (str != null) && !"".equals(str.trim());
    }

    /**
     * 在mysql查询中用到，将字符串模糊化查询
     * @param str
     * @return
     */
    public static String formatLike(String str) {
        if (isNotEmpty(str)) {
            return "%" + str + "%";
        } else {
            return null;
        }
    }

    /**
     * 用来去除字符串链表中的空字符串
     * @param list
     * @return
     */
    public static List<String> filterWhite(List<String> list) {
        List<String> resultList = new ArrayList<String>();
        for (String l : list) {
            if (isNotEmpty(l)) {
                resultList.add(l);
            }
        }
        return resultList;
    }

}
