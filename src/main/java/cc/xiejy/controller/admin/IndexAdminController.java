package cc.xiejy.controller.admin;

import cc.xiejy.entity.Blog;
import cc.xiejy.service.BlogService;
import cc.xiejy.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created by xie on 2017/4/16 0016.
 */
@Controller
@RequestMapping("/admin")
public class IndexAdminController {

    @Resource
    BlogService blogService;

    @RequestMapping("/index")
    public String index(@RequestParam(value = "targetPage", required = false) String targetPage,
                        @RequestParam(value = "blogId", required = false) String blogId, Model model) {
        if (StringUtil.isEmpty(targetPage))
            targetPage = "default";
        model.addAttribute("targetPage", targetPage + ".jsp");
        if (StringUtil.isNotEmpty(blogId)) {
            Blog blog = blogService.getById(Integer.parseInt(blogId));
            model.addAttribute("blog", blog);
        }
        return "admin/index";
    }
}
