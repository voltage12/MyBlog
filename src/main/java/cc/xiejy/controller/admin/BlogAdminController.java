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
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 博客管理Controller
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

    @Resource
    private BlogService blogService;

    @Resource
    private BlogTypeService blogTypeService;

    /**
     * 跳转到博客管理页面
     *
     * @return
     */
    @RequestMapping("/blogManage")
    public ModelAndView blogManage(@RequestParam(value = "page", required = false) String page,
                                   @RequestParam(value = "blogTitle", required = false) String blogTitle,
                                   HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        //如果没有分页参数，默认显示第一页
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        //下面两个是生成分页代码所需的参数
        PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
        String param = "";
        //用来存放数据库查询用到的参数
        Map<String, Object> map = new HashedMap();
        if (StringUtil.isNotEmpty(blogTitle)) {
            map.put("title", StringUtil.formatLike(blogTitle));
            param = "blogTitle=" + blogTitle;
        }
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Blog> blogList = blogService.getBlogList(map);
        int totalNum = blogService.getTotalCount(map);
        //生成分页导航栏的html代码
        String pageCode = PageUtil.genPagination(request.getContextPath() + "/admin/blog/blogManage.html",
                totalNum, Integer.parseInt(page), 10, param);

        mav.addObject("pageTitle", "后台管理-博客管理");
        mav.addObject("pageCode", pageCode);
        mav.addObject("blogList", blogList);
        mav.addObject("includePage", "blogManage.jsp");
        mav.setViewName("admin/index");
        return mav;
    }

    /**
     * 跳转到写博客页面
     * @param blogId
     * @return
     */
    @RequestMapping("/writeBlog")
    public ModelAndView writeBlog(@RequestParam(value = "blogId", required = false) String blogId) {
        ModelAndView mav = new ModelAndView();
        //如果blogId参数不为空的话，那么是在修改博客内容
        if (StringUtil.isNotEmpty(blogId)) {
            Blog blog = blogService.getById(Integer.parseInt(blogId));
            mav.addObject("blog", blog);
        }
        mav.addObject("pageTitle", "后台管理-写博客");
        mav.addObject("includePage", "writeBlog.jsp");
        mav.setViewName("admin/index");
        return mav;
    }

    /**
     * 添加或修改博客
     * @throws Exception
     */
    @RequestMapping("/save")
    public void addBlog(Blog blog, HttpServletResponse response, HttpServletRequest request) throws Exception {
        //操作lucene索引所需要的对象
        BlogIndex blogIndex = new BlogIndex();
        int resultNum;
        JSONObject result = new JSONObject();
        //如果blog的id字段不为null说明是修改博客
        if (blog.getId() != null) {
            resultNum = blogService.update(blog);
            //修改lucene的索引
            blogIndex.updateIndex(blog);
        } else {
            resultNum = blogService.add(blog);
            blogIndex.setPath((String) request.getSession().getServletContext().getAttribute("indexPath"));
            blogIndex.addIndex(blog);
            //更新系统缓存中的博客分类列表
            List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();
            request.getServletContext().setAttribute("blogTypeList", blogTypeList);
            List<Blog> blogListGroupByDate = blogService.getBlogListGroupByDate();
            request.getServletContext().setAttribute("blogListGroupByDate", blogListGroupByDate);
        }
        if (resultNum == 0) {
            result.put("success", false);
        } else {
            result.put("success", true);
        }
        ResponseUtil.write(response, result.toJSONString());
    }

    /**
     * 删除博客
     * @throws Exception
     */
    @RequestMapping("/delete")
    public void deleteBlog(@RequestParam(value = "selectId", required = false) String selectId,
                           HttpServletResponse response, HttpServletRequest request) throws Exception {
        JSONObject result = new JSONObject();
        //操作lucene索引所需要的对象
        BlogIndex blogIndex = new BlogIndex();
        //获取所选的博客id
        String[] selectIds = selectId.split(",");
        if (selectIds == null || StringUtil.isEmpty(selectId)) {
            result.put("success", false);
        } else {
            result.put("success", true);
            for (String id : selectIds) {
                blogService.deleteBlogById(Integer.parseInt(id));
                blogIndex.deleteIndex(id);
            }
            //更新系统缓存中的博客分类列表
            List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();
            request.getServletContext().setAttribute("blogTypeList", blogTypeList);
            List<Blog> blogListGroupByDate = blogService.getBlogListGroupByDate();
            request.getServletContext().setAttribute("blogListGroupByDate", blogListGroupByDate);
        }
        ResponseUtil.write(response, result.toJSONString());
    }

    /**
     * 获取博客的内容
     * @throws Exception
     */
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
