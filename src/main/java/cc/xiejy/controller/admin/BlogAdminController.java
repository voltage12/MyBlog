package cc.xiejy.controller.admin;

import cc.xiejy.entity.Blog;
import cc.xiejy.entity.BlogType;
import cc.xiejy.entity.PageBean;
import cc.xiejy.lucene.BlogIndex;
import cc.xiejy.service.BlogService;
import cc.xiejy.service.BlogTypeService;
import cc.xiejy.util.PageUtil;
import cc.xiejy.util.ResponseUtil;
import cc.xiejy.util.StringUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @Resource
    private BlogTypeService blogTypeService;

    private BlogIndex blogIndex = new BlogIndex();

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

    @RequestMapping("/blogManage")
    public ModelAndView blogManage(@RequestParam(value = "page", required = false) String page,
                                   @RequestParam(value = "blogTitle", required = false) String blogTitle,
                                   HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
        String param = "";

        Map<String, Object> map = new HashedMap();

        if (StringUtil.isNotEmpty(blogTitle)) {
            map.put("title", StringUtil.formatLike(blogTitle));
            param = "blogTitle=" + blogTitle;
        }
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Blog> blogList = blogService.getBlogList(map);
        int totalNum = blogService.getTotalCount(map);

        if (!page.equals("1") && Integer.parseInt(page) > PageUtil.getTotalPage(totalNum, Integer.parseInt(page), 10)) {
            page = String.valueOf(PageUtil.getTotalPage(totalNum, Integer.parseInt(page), 10));
        }

        String pageCode = PageUtil.genPagination(request.getContextPath() + "/admin/blog/blogManage.html",
                totalNum, Integer.parseInt(page), 10, param);

        mav.addObject("pageCode", pageCode);
        mav.addObject("blogList", blogList);
        mav.addObject("includePage", "blogManage.jsp");
        mav.setViewName("admin/index");
        return mav;
    }

    @RequestMapping("/writeBlog")
    public ModelAndView writeBlog(@RequestParam(value = "blogId", required = false) String blogId) {
        ModelAndView mav = new ModelAndView();

        if (StringUtil.isNotEmpty(blogId)) {
            Blog blog = blogService.getById(Integer.parseInt(blogId));
            mav.addObject("blog", blog);
        }

        mav.addObject("includePage", "writeBlog.jsp");
        mav.setViewName("admin/index");
        return mav;
    }

    @RequestMapping("/save")
    public void addBlog(Blog blog, HttpServletResponse response, HttpServletRequest request) throws Exception {
        int resultNum;
        JSONObject result = new JSONObject();
        if (blog.getId() != null) {
            resultNum = blogService.update(blog);
            blogIndex.updateIndex(blog);
        } else {
            resultNum = blogService.add(blog);
            blogIndex.setPath((String) request.getSession().getServletContext().getAttribute("indexPath"));
            blogIndex.addIndex(blog);
            List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();
            request.getServletContext().setAttribute("blogTypeList", blogTypeList);
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
                           HttpServletResponse response, HttpServletRequest request) throws Exception {
        String[] selectIds = selectId.split(",");
        JSONObject result = new JSONObject();
        if (selectIds == null || StringUtil.isEmpty(selectId)) {
            result.put("success", false);
        } else {
            result.put("success", true);
            for (String id : selectIds) {
                blogService.deleteBlogById(Integer.parseInt(id));
                blogIndex.deleteIndex(id);
            }
            List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();
            request.getServletContext().setAttribute("blogTypeList", blogTypeList);
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

}
