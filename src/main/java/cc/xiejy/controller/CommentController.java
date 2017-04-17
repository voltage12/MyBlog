package cc.xiejy.controller;

import cc.xiejy.entity.Blog;
import cc.xiejy.entity.Comment;
import cc.xiejy.service.BlogService;
import cc.xiejy.service.CommentService;
import cc.xiejy.util.ResponseUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xie on 2017/4/13 0013.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private BlogService blogService;

    @RequestMapping("/add")
    public void add(@RequestParam("content") String content, @RequestParam("blogId") Integer blogId,
                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        Comment newComment = new Comment();
        Blog blog = blogService.getById(blogId);
        blog.setReply(blog.getReply() + 1);
        newComment.setContent(content);
        blogService.update(blog);
        newComment.setBlog(blog);
        newComment.setUserIp(request.getRemoteAddr());

        JSONObject result = new JSONObject();
        int resultNum = commentService.add(newComment);
        if (resultNum == 0) {
            result.put("success", false);
            result.put("errorInfo", "写入数据库时失败");
        } else {
            result.put("success", true);
        }
        ResponseUtil.write(response, result.toJSONString());
        return;
    }
}
