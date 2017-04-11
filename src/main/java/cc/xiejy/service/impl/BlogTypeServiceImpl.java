package cc.xiejy.service.impl;

import cc.xiejy.dao.BlogTypeDao;
import cc.xiejy.entity.BlogType;
import cc.xiejy.service.BlogTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xie on 2017/4/11 0011.
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {

    @Resource
    private BlogTypeDao blogTypeDao;

    public BlogTypeDao getBlogDao() {
        return blogTypeDao;
    }

    public void setBlogDao(BlogTypeDao blogDao) {
        this.blogTypeDao = blogDao;
    }

    @Override
    public List<BlogType> getBlogTypeList() {
        return blogTypeDao.getBlogTypeList();
    }

    @Override
    public BlogType getBlogTypeById(int id) {
        return blogTypeDao.getBlogTypeById(id);
    }
}
