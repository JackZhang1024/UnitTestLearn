package com.lucky.unittestlearn.mockuser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by zfz on 2017/3/13.
 */

public class UserDao {

    public static HashMap<String,User> userHashMap=new HashMap<>();

    public User findUserByUserName(String userName){
        return userHashMap.get(userName);
    }

    public void saveUser(User user){
        if(userHashMap.containsKey(user.getUserName()))
            throw  new IllegalStateException("用户名不能重复!");
        userHashMap.put(user.getUserName(),user);
    }

    public HashMap<String,User> getAllUsers(){
        return userHashMap;
    }

}
