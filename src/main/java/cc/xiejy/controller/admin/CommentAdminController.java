package cc.xiejy.controller.admin;

import cc.xiejy.entity.Comment;
import cc.xiejy.entity.PageBean;
import cc.xiejy.service.CommentService;
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
 * 评论管理Controller
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {

    @Resource
    CommentService commentService;

    /**
     * 转到评论审核页面
     *
     * @return
     */
    @RequestMapping("/checkComment")
    public ModelAndView checkComment(@RequestParam(value = "page", required = false) String page,
                                     HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();

        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
        String param = "";

        Map<String, Object> map = new HashedMap();

        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("state", 0);
        List<Comment> commentList = commentService.getCommentList(map);
        int totalNum = commentService.getTotalCount(map);

        String pageCode = PageUtil.genPagination(request.getContextPath() + "/admin/comment/checkComment.html",
                totalNum, Integer.parseInt(page), 10, param);

        mav.addObject("pageTitle", "后台管理-评论审核");
        mav.addObject("pageCode", pageCode);
        mav.addObject("commentList", commentList);
        mav.addObject("includePage", "checkComment.jsp");
        mav.setViewName("admin/index");
        return mav;
    }

    /**
     * 转到评论信息管理页面
     * @return
     */
    @RequestMapping("/commentManage")
    public ModelAndView commentManage(@RequestParam(value = "page", required = false) String page,
                                      HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();

        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
        String param = "";

        Map<String, Object> map = new HashedMap();

        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Comment> commentList = commentService.getCommentList(map);
        int totalNum = commentService.getTotalCount(map);

        String pageCode = PageUtil.genPagination(request.getContextPath() + "/admin/comment/commentManage.html",
                totalNum, Integer.parseInt(page), 10, param);

        mav.addObject("pageTitle", "后台管理-评论信息管理");
        mav.addObject("pageCode", pageCode);
        mav.addObject("commentList", commentList);
        mav.addObject("includePage", "commentManage.jsp");
        mav.setViewName("admin/index");
        return mav;
    }

    /**
     * 删除评论
     * @throws Exception
     */
    @RequestMapping("/delete")
    public void deleteComment(@RequestParam(value = "selectId", required = false) String selectId,
                              HttpServletResponse response, HttpServletRequest request) throws Exception {
        String[] selectIds = selectId.split(",");
        JSONObject result = new JSONObject();
        if (selectIds == null || StringUtil.isEmpty(selectId)) {
            result.put("success", false);
        } else {
            result.put("success", true);
            for (String id : selectIds) {
                commentService.delete(Integer.parseInt(id));
            }
        }
        ResponseUtil.write(response, result.toJSONString());
    }

    /**
     * 评论审核
     * @throws Exception
     */
    @RequestMapping("/pass")
    public void pass(@RequestParam(value = "selectId", required = false) String selectId,
                     @RequestParam(value = "pass") String pass,
                     HttpServletResponse response, HttpServletRequest request) throws Exception {
        String[] selectIds = selectId.split(",");
        JSONObject result = new JSONObject();
        if (selectIds == null || StringUtil.isEmpty(selectId)) {
            result.put("success", false);
        } else if (StringUtil.isEmpty(pass)) {
            result.put("success", false);
        } else {
            result.put("success", true);
            if (pass.equals("yes")) {
                for (String id : selectIds) {
                    Comment comment = new Comment();
                    comment.setId(Integer.parseInt(id));
                    comment.setState(1);
                    commentService.update(comment);
                }
            } else {
                for (String id : selectIds) {
                    Comment comment = new Comment();
                    comment.setId(Integer.parseInt(id));
                    comment.setState(2);
                    commentService.update(comment);
                }
            }
        }
        ResponseUtil.write(response, result.toJSONString());
    }
}
