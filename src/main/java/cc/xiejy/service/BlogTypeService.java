package cc.xiejy.service;

import cc.xiejy.entity.BlogType;

import java.util.List;

/**
 * Created by xie on 2017/4/11 0011.
 */
public interface BlogTypeService {

    List<BlogType> getBlogTypeList();

    BlogType getBlogTypeById(int id);
}
