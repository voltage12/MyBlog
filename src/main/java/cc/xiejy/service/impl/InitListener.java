package cc.xiejy.service.impl;

import cc.xiejy.entity.Blog;
import cc.xiejy.entity.BlogType;
import cc.xiejy.entity.User;
import cc.xiejy.service.BlogService;
import cc.xiejy.service.BlogTypeService;
import cc.xiejy.service.UserService;
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
        User user = userService.getByUserName("xiejy");
        if (user != null) {
        }
        user.setPassword(null);
        application.setAttribute("user", user);

        BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
        List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();
        application.setAttribute("blogTypeList", blogTypeList);

        BlogService blogService = (BlogService) applicationContext.getBean("blogService");
        List<Blog> blogListGroupByDate = blogService.getBlogListGroupByDate();
        application.setAttribute("blogListGroupByDate", blogListGroupByDate);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        InitListener.applicationContext = applicationContext;
    }
}
