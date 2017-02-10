package com.dante.angular.service;

import com.dante.angular.dao.order.UserDao;
import com.dante.angular.entity.User;
import com.dante.angular.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by xsy83 on 2017/1/8.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public boolean createUser(User user) {
        return userDao.createUser(user);
    }

    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    public boolean deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }

    public boolean checkUser(User user) {
        List<User> list = userDao.getUserInfo(user);
        return list.size() > 0 ? true : false;
    }

    public List<User> checkUserAndPasswd(User user){
        user.setIsAdmin(null);
        return userDao.getUserInfo(user);
    }

    public User getUser(Integer id) {
        return userDao.getUserById(id);
    }

    public Page<User> getAllUsers(Map map) {
        return userDao.getAllUsers(map);
    }
}
