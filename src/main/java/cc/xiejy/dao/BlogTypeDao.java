package cc.xiejy.dao;

import cc.xiejy.entity.BlogType;

import java.util.List;
import java.util.Map;

/**
 * Created by xie on 2017/4/11 0011.
 */
public interface BlogTypeDao {
    List<BlogType> getBlogTypeList();

    BlogType getBlogTypeById(int id);

    List<BlogType> getList(Map<String, Object> map);

    Integer getTotalCount(Map<String, Object> map);

    Integer add(BlogType blogType);

    Integer update(BlogType blogType);

    Integer delete(Integer id);
}
