package cc.xiejy.dao;


import cc.xiejy.entity.User;

/**
 * 用户Dao接口
 */
public interface UserDao {
    User getByUserName(String userName);
}
