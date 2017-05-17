package cc.xiejy.service.impl;

import cc.xiejy.entity.Blog;
import cc.xiejy.entity.BlogType;
import cc.xiejy.entity.User;
import cc.xiejy.service.BlogService;
import cc.xiejy.service.BlogTypeService;
import cc.xiejy.service.UserService;
import cc.xiejy.util.PropertiesUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by xie on 2017/4/11 0011.
 */
@Component
public class InitListener implements ServletContextListener, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();
        UserService userService = (UserService) applicationContext.getBean("userService");
        BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
        BlogService blogService = (BlogService) applicationContext.getBean("blogService");

        //分别往缓存中加入用户信息，按类别分类的博客信息和按日期分类的博客信息
        User user = userService.getByUserName("xiejy");
        user.setPassword(null);
        application.setAttribute("user", user);

        List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();
        application.setAttribute("blogTypeList", blogTypeList);

        List<Blog> blogListGroupByDate = blogService.getBlogListGroupByDate();
        application.setAttribute("blogListGroupByDate", blogListGroupByDate);

        //读取lucene设置文件，如果没有那么默认输出索引至默认路径
        try {
            PropertiesUtil propertiesUtil = new PropertiesUtil("my.properties");
            application.setAttribute("indexPath", propertiesUtil.readValue("path"));
        } catch (Exception e) {
            application.setAttribute("indexPath", "D:/lucene");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        InitListener.applicationContext = applicationContext;
    }
}
