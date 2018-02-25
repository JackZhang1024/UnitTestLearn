package com.lucky.unittestlearn.mockuser;

/**
 * Created by zfz on 2017/3/13.
 */

public class UserServiceImpl implements UserService{

    private UserDao userDao;

    @Override
    public void setUserDao(UserDao userDao){
        this.userDao=userDao;
    }

    @Override
    public User findUserByLoginName(String loginName) {
        //return new User("liming");
        return userDao.findUserByUserName(loginName);
    }

    @Override
    public String saveUser(User user) {
        userDao.saveUser(user);
        return  user.getUserName();
    }
}
