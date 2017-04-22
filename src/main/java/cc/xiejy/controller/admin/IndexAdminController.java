package cc.xiejy.controller.admin;

import cc.xiejy.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String index(Model model) {

        model.addAttribute("includePage", "default.jsp");
        return "admin/index";
    }
}
