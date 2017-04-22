package cc.xiejy.dao;

import cc.xiejy.entity.Blog;

import java.util.List;
import java.util.Map;

/**
 * Created by xie on 2017/4/11 0011.
 */
public interface BlogDao {

    List<Blog> getBlogListGroupByDate();

    List<Blog> getBlogList(Map<String, Object> map);

    int getTotalCount(Map<String, Object> map);

    Blog getById(Integer id);

    int update(Blog blog);

    Blog getLastBlog(Integer id);

    Blog getNextBlog(Integer id);

    Integer add(Blog blog);

    Integer getCountByTypeId(Integer typeId);

    Integer deleteBlogById(Integer id);
}
