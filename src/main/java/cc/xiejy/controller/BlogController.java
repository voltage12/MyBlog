package cc.xiejy.controller;

import cc.xiejy.entity.Blog;
import cc.xiejy.service.BlogService;
import cc.xiejy.service.CommentService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by xie on 2017/4/12 0012.
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    @Resource
    private CommentService commentService;

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping("/articles/{id}")
    public ModelAndView details(@PathVariable("id") Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        Blog blog = blogService.getById(id);
        blog.setClick(blog.getClick() + 1);
        blogService.update(blog);
        mav.addObject("lastBlog", blogService.getLastBlog(blog.getId()));
        mav.addObject("nextBlog", blogService.getNextBlog(blog.getId()));
        Map<String, Object> map = new HashedMap();
        map.put("blogId", blog.getId());
        map.put("state", 1);
        mav.addObject("commentList", commentService.getCommentList(map));
        mav.addObject("blog", blog);
        mav.addObject("mainPage", "common/blog/view.jsp");
        mav.addObject("pageTitle", blog.getTitle());
        mav.setViewName("index");
        return mav;
    }
}
