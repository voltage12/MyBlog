package cc.xiejy.controller.admin;

import cc.xiejy.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xie on 2017/4/16 0016.
 */
@Controller
@RequestMapping("/admin")
public class IndexAdminController {

    @RequestMapping("/index")
    public String index(@RequestParam(value = "targetPage", required = false) String targetPage, Model model) {
        if (StringUtil.isEmpty(targetPage))
            targetPage = "default";
        model.addAttribute("targetPage", targetPage + ".jsp");
        return "admin/index";
    }
}
