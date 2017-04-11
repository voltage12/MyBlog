package cc.xiejy.service.impl;

import cc.xiejy.dao.BlogDao;
import cc.xiejy.entity.Blog;
import cc.xiejy.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by xie on 2017/4/11 0011.
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Resource
    BlogDao blogDao;

    public void setBlogDao(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    @Override
    public List<Blog> getBlogListGroupByDate() {
        return blogDao.getBlogListGroupByDate();
    }

    @Override
    public List<Blog> getBlogList(Map<String, Object> map) {
        return blogDao.getBlogList(map);
    }

    @Override
    public int getTotalCount(Map<String, Object> map) {
        return blogDao.getTotalCount(map);
    }
}
