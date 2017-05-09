package cc.xiejy.service;

import cc.xiejy.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * Created by xie on 2017/4/12 0012.
 */
public interface CommentService {

    List<Comment> getCommentList(Map<String, Object> map);

    int add(Comment comment);

    Integer getTotalCount(Map<String, Object> map);

    Integer update(Comment comment);

    Integer delete(Integer id);
}
