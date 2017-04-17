package cc.xiejy.controller.admin;

import cc.xiejy.entity.Blog;
import cc.xiejy.entity.PageBean;
import cc.xiejy.service.BlogService;
import cc.xiejy.util.PageUtil;
import cc.xiejy.util.ResponseUtil;
import cc.xiejy.util.StringUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by xie on 2017/4/16 0016.
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

    @Resource
    private BlogService blogService;

    @RequestMapping("/add")
    public void addBlog(Blog blog, HttpServletResponse response) throws Exception {
        int resultNum;
        JSONObject result = new JSONObject();
        if (blog.getId() != null) {
            resultNum = blogService.update(blog);
        } else {
            resultNum = blogService.add(blog);

        }
        if (resultNum == 0) {
            result.put("success", false);
        } else {
            result.put("success", true);
        }
        ResponseUtil.write(response, result.toJSONString());
    }

    @RequestMapping("/delete")
    public void deleteBlog(@RequestParam(value = "selectId", required = false) String selectId,
                           HttpServletResponse response) throws Exception {
        String[] selectIds = selectId.split(",");
        JSONObject result = new JSONObject();
        if (selectIds == null) {
            result.put("success", false);
        } else {
            int resultNum = 0;
            result.put("success", true);
            for (String id : selectIds) {
                resultNum = resultNum + blogService.deleteBlogById(Integer.parseInt(id));
            }
        }
        ResponseUtil.write(response, result.toJSONString());
    }

    @RequestMapping("/getContent")
    public void getContentById(@RequestParam(value = "id") String id, HttpServletResponse response) throws Exception {
        Blog bLog = blogService.getById(Integer.parseInt(id));
        JSONObject result = new JSONObject();
        if (bLog != null) {
            result.put("content", bLog.getContent());
            ResponseUtil.write(response, result.toJSONString());
        }
    }

    @RequestMapping("/getList")
    public void getBlogList(@RequestParam(value = "page", required = false) String page, HttpServletRequest request
            , HttpServletResponse response)
            throws Exception {
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
        Map<String, Object> map = new HashedMap();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<Blog> blogList = blogService.getBlogList(map);

        JSONArray jsonArray = new JSONArray();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        int totalNum = blogService.getTotalCount(map);
        String pageCode = PageUtil.genPagination(totalNum, Integer.parseInt(page), 10);

        JSONObject jPageCode = new JSONObject();
        jPageCode.put("pageCode", pageCode);
        jsonArray.add(jPageCode);
        for (Blog blog : blogList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", blog.getId());
            jsonObject.put("title", blog.getTitle());
            jsonObject.put("releaseDate", simpleDateFormat.format(blog.getReleaseDate()));
            jsonObject.put("blogTypeName", blog.getBlogType().getTypeName());
            jsonArray.add(jsonObject);
        }
        ResponseUtil.write(response, jsonArray.toJSONString());
    }

}
