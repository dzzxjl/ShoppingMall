package com.dante.angular.dao.order;

import com.dante.angular.dao.CommenDao;
import com.dante.angular.entity.User;
import com.dante.angular.util.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by xsy83 on 2017/1/7.
 */
@Repository
public class UserDao extends CommenDao<User> {

    public boolean createUser(User user) {
        return getSqlSession().insert("User.create", user) == 1 ? true : false;
    }

    public boolean updateUser(User user) {
        return getSqlSession().insert("User.update", user) == 1 ? true : false;
    }

    public boolean deleteUser(Integer id) {
        return getSqlSession().insert("User.delete", id) == 1 ? true : false;
    }

    public List<User> getUserInfo(User user){
        return getSqlSession().selectList("User.check", user);
    }

    public User getUserById(Integer id) {
        List<User> list = getSqlSession().selectList("User.get", id);
        return list != null ? list.get(0) : null;
    }

    public Page<User> getAllUsers(Map map){
        return paging(map);
    }
}
