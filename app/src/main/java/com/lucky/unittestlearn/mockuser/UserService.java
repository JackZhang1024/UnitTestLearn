package com.lucky.unittestlearn.mockuser;

/**
 * Created by zfz on 2017/3/13.
 */

public interface UserService {
    void setUserDao(UserDao userDao);
    User findUserByLoginName(String loginName);
    String saveUser(User user);
}
