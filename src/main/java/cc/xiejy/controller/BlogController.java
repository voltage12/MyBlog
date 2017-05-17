package cc.xiejy.controller;

import cc.xiejy.entity.Blog;
import cc.xiejy.lucene.BlogIndex;
import cc.xiejy.service.BlogService;
import cc.xiejy.service.CommentService;
import cc.xiejy.util.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 博客内容展示Controller
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    @Resource
    private CommentService commentService;

    private BlogIndex blogIndex = new BlogIndex();

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * 获取博客内容
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/articles/{id}")
    public ModelAndView details(@PathVariable("id") Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();

        Blog blog = blogService.getById(id);
        blog.setClick(blog.getClick() + 1);
        blogService.update(blog);
        //搜索并添加评论信息
        Map<String, Object> map = new HashedMap();
        map.put("blogId", blog.getId());
        map.put("state", 1);
        mav.addObject("commentList", commentService.getCommentList(map));

        mav.addObject("lastBlog", blogService.getLastBlog(blog.getId()));
        mav.addObject("nextBlog", blogService.getNextBlog(blog.getId()));
        mav.addObject("blog", blog);
        mav.addObject("mainPage", "common/blog/view.jsp");
        mav.addObject("pageTitle", blog.getTitle());
        mav.setViewName("index");
        return mav;
    }

    /**
     * 根据关键字查询相关博客信息
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/search")
    public ModelAndView search(@RequestParam(value = "q", required = false) String q,
                               @RequestParam(value = "page", required = false) String page, HttpServletRequest request) throws Exception {
        int pageSize = 10;
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("pageTitle", "搜索关键字'" + q + "'结果页面");
        mav.addObject("mainPage", "common/blog/result.jsp");
        blogIndex.setPath((String) request.getSession().getServletContext().getAttribute("indexPath"));
        List<Blog> blogList = blogIndex.searchBlog(q);
        Integer toIndex = blogList.size() >= Integer.parseInt(page) * pageSize ? Integer.parseInt(page) * pageSize : blogList.size();
        mav.addObject("blogList", blogList.subList((Integer.parseInt(page) - 1) * pageSize, toIndex));
        mav.addObject("pageCode", this.genUpAndDownPageCode(Integer.parseInt(page), blogList.size(), q, pageSize, request.getServletContext().getContextPath()));
        mav.addObject("q", q);
        mav.addObject("resultTotal", blogList.size());
        mav.setViewName("index");
        return mav;
    }

    /**
     * 获取搜索结果页面中上一页，下一页代码
     * @param page
     * @param totalNum
     * @param q
     * @param pageSize
     * @param projectContext
     * @return
     */
    private String genUpAndDownPageCode(Integer page, Integer totalNum, String q, Integer pageSize, String projectContext) {
        long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        StringBuffer pageCode = new StringBuffer();
        if (totalPage == 0) {
            return "";
        } else {
            pageCode.append("<nav>");
            pageCode.append("<ul class='pager'>");
            if (page > 1) {
                pageCode.append("<li><a href='" + projectContext + "/blog/q.html?page=" + (page - 1) + "&q=" + q + "'>上一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
            }
            if (page < totalPage) {
                pageCode.append("<li><a href='" + projectContext + "/blog/q.html?page=" + (page + 1) + "&q=" + q + "'>下一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
            }
            pageCode.append("</ul>");
            pageCode.append("</nav>");
        }
        return pageCode.toString();
    }
}
