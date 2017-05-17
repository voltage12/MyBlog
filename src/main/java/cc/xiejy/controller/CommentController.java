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
import javax.servlet.http.HttpSession;

/**
 * 评论Controller
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private BlogService blogService;

    /**
     * 添加评论
     *
     * @param content
     * @param blogId
     * @throws Exception
     */
    @RequestMapping("/add")
    public void add(@RequestParam("content") String content, @RequestParam("blogId") Integer blogId, @RequestParam("imageCode") String imageCode,
                    HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONObject result = new JSONObject();

        String sRand = (String) session.getAttribute("sRand");
        if (!imageCode.equals(sRand)){
            result.put("success", false);
            result.put("errorInfo", "验证码填写错误!");
        } else {
            Comment newComment = new Comment();
            Blog blog = blogService.getById(blogId);
            blog.setReply(blog.getReply() + 1);
            newComment.setContent(content);
            blogService.update(blog);
            newComment.setBlog(blog);
            newComment.setUserIp(request.getRemoteAddr());
            int resultNum = commentService.add(newComment);
            if (resultNum == 0) {
                result.put("success", false);
                result.put("errorInfo", "写入数据库时失败");
            } else {
                result.put("success", true);
            }
        }
        ResponseUtil.write(response, result.toJSONString());
        return;
    }
}
