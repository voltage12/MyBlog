package cc.xiejy.service;

import cc.xiejy.entity.User;

/**
 * 用户Service接口
 */
public interface UserService {
    User getByUserName(String userName);
}
