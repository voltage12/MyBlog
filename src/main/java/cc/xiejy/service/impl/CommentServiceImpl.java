package cc.xiejy.service.impl;

import cc.xiejy.dao.CommentDao;
import cc.xiejy.entity.Comment;
import cc.xiejy.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by xie on 2017/4/12 0012.
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public List<Comment> getCommentList(Map<String, Object> map) {
        return commentDao.getCommentList(map);
    }

    @Override
    public int add(Comment comment) {
        return commentDao.add(comment);
    }

    @Override
    public Integer getTotalCount(Map<String, Object> map) {
        return commentDao.getTotalCount(map);
    }

    @Override
    public Integer update(Comment comment) {
        return commentDao.update(comment);
    }

    @Override
    public Integer delete(Integer id) {
        return commentDao.delete(id);
    }

    //public static void main(String[] args){
    //    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    //    CommentService commentServeice = (CommentService) applicationContext.getBean("commentService");
    //    Map<String, Object> map = new HashedMap();
    //    map.put("blogId",22);
    //    List<Comment> list = commentServeice.getCommentList(map);
    //    if(list == null){
    //        System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");
    //    }else {
    //        if(list.size() == 0)
    //            System.out.println("YUYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
    //        for (Comment comment:list){
    //            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
    //            System.out.println(comment);
    //        }
    //    }
    //}
}
