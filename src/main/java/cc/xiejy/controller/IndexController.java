package cc.xiejy.controller;

import cc.xiejy.entity.Blog;
import cc.xiejy.entity.PageBean;
import cc.xiejy.service.BlogService;
import cc.xiejy.util.PageUtil;
import cc.xiejy.util.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 主页博客列表展示Controller
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Resource
    private BlogService blogService;

    /**
     * 显示博客列表
     *
     * @throws Exception
     */
    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "page", required = false) String page,
                              @RequestParam(value = "typeId", required = false) String typeId,
                              @RequestParam(value = "releaseDateStr", required = false) String releaseDateStr,
                              HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        //如果没有当前页参数则默认是第一页
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page), 5);
        //为查询博客设置参数
        Map<String, Object> map = new HashedMap();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("typeId", typeId);
        map.put("releaseDateStr", releaseDateStr);
        //查询博客列表
        List<Blog> blogList = blogService.getBlogList(map);
        //生成参数
        StringBuffer param = new StringBuffer();
        if (StringUtil.isNotEmpty(typeId)) {
            param.append("typeId=" + typeId + "&");
        }
        if (StringUtil.isNotEmpty(releaseDateStr)) {
            param.append("releaseDateStr=" + releaseDateStr + "&");
        }
        //生成分页导航栏的html代码
        mav.addObject("pageCode", PageUtil.genPagination(request.getContextPath() + "/index.html", blogService.getTotalCount(map), Integer.parseInt(page), 5, param.toString()));
        //为每一个blog设置缩略图
        for (Blog blog : blogList) {
            List<String> imageList = blog.getImageList();
            String blogContent = blog.getContent();
            //解析博客内容，找出其中所有的图像标签，取前三个作为缩略图
            Document document = Jsoup.parse(blogContent);
            Elements jpgs = document.select("img[src$=.jpg]");
            for (int i = 0; i < jpgs.size(); i++) {
                Element jpg = jpgs.get(i);
                imageList.add(jpg.toString());
                if (i == 2) {
                    break;
                }
            }
        }
        //为Model设置数据
        mav.addObject("blogList", blogList);
        mav.addObject("mainPage", "common/blog/list.jsp");
        mav.addObject("pageTitle", "博客系统-毕业设计");
        //设置用来响应的jsp
        mav.setViewName("index");
        return mav;
    }
}
