package cc.xiejy.service.impl;

import cc.xiejy.dao.UserDao;
import cc.xiejy.entity.User;
import cc.xiejy.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户Service接口实现
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getByUserName(String userName) {
        return userDao.getByUserName(userName);
    }
}
