package cc.xiejy.controller.admin;

import cc.xiejy.entity.BlogType;
import cc.xiejy.service.BlogService;
import cc.xiejy.service.BlogTypeService;
import cc.xiejy.util.ResponseUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 博客类别管理Controller
 */
@Controller
@RequestMapping("admin/blogType")
public class BlogTypeAdminController {

    @Resource
    private BlogTypeService blogTypeService;

    @Resource
    private BlogService blogService;

    /**
     * 转到博客类别管理页面
     *
     * @return
     */
    @RequestMapping("/blogTypeManage")
    public ModelAndView blogTypeManage() {
        ModelAndView mav = new ModelAndView();

        mav.addObject("pageTitle", "后台管理-博客类别管理");
        mav.addObject("includePage", "blogTypeManage.jsp");
        mav.setViewName("admin/index");
        return mav;
    }

    /**
     * 修改或添加博客类别
     * @throws Exception
     */
    @RequestMapping("/save")
    public void save(BlogType blogType, HttpServletResponse response,
                     HttpServletRequest request) throws Exception {
        int resultNum;
        JSONObject result = new JSONObject();
        //判断是修改还是添加博客类别
        if (blogType.getId() != null) {
            resultNum = blogTypeService.update(blogType);
        } else {
            resultNum = blogTypeService.add(blogType);
        }
        if (resultNum > 0) {
            result.put("success", true);
            //更新系统中的博客类别列表缓存
            List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();
            request.getServletContext().setAttribute("blogTypeList", blogTypeList);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result.toString());
    }

    /**
     * 获取博客类别信息
     * @throws Exception
     */
    @RequestMapping("/getInfo")
    public void getInfo(@RequestParam(value = "typeId") String typeId, HttpServletResponse response) throws Exception {
        BlogType blogType = blogTypeService.getBlogTypeById(Integer.parseInt(typeId));
        String result = JSON.toJSONString(blogType);
        ResponseUtil.write(response, result.toString());
    }

    /**
     * 删除博客类别
     * @throws Exception
     */
    @RequestMapping("/delete")
    public void delete(@RequestParam(value = "typeId") String typeId, HttpServletResponse response,
                       HttpServletRequest request) throws Exception {
        JSONObject result = new JSONObject();
        //如果此类别下还有博客则删除失败
        if (blogService.getCountByTypeId(Integer.parseInt(typeId)) == 0) {
            int resultNum = blogTypeService.delete(Integer.parseInt(typeId));
            if (resultNum > 0) {
                List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();
                request.getServletContext().setAttribute("blogTypeList", blogTypeList);
                result.put("success", true);
            } else {
                result.put("success", false);
                result.put("errorInfo", "删除失败");
            }
        } else {
            result.put("success", false);
            result.put("errorInfo", "此类别下还有博客文章，无法删除");
        }
        ResponseUtil.write(response, result.toString());
    }
}
