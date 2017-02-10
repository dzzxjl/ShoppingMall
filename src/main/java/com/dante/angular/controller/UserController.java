package com.dante.angular.controller;

import com.dante.angular.entity.Orders;
import com.dante.angular.entity.User;
import com.dante.angular.service.OrdersService;
import com.dante.angular.service.UserService;
import com.dante.angular.util.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by xsy83 on 2017/1/8.
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrdersService ordersService;

    @RequestMapping(value = "/regist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response regist(@RequestBody User user,HttpSession session) {
        if (userService.checkUser(user)) {
            return new Response("error");
        }
        userService.createUser(user);
        log.info("注册用户成功");
        List<User> list = userService.checkUserAndPasswd(user);
        if (list.size() == 0){
            return new Response("error");
        }
        session.setAttribute("user",list.get(0));
        return new Response("success");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response login(@RequestBody User user, HttpSession session) {
        List<User> list = userService.checkUserAndPasswd(user);
        if (list.size() == 0){
            return new Response("error");
        }
        log.info("用户登录成功");
        User u = list.get(0);
        session.setAttribute("user",u);
        Orders orders = new Orders();
        orders.setUserId(u.getId());
        ordersService.createOrders(orders);
        return new Response("success",u.getIsAdmin());
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response update(@RequestBody User user,HttpSession session){
        User temp = (User) session.getAttribute("user");
        User u = setUser(temp,user);
        boolean bool = userService.updateUser(u);
        if(bool){
            return new Response("success");
        }
        return new Response("error");
    }

    @RequestMapping(value = "/viewUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User viewUser(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null){
            log.info("从session获取用户失败");
        }
        return user;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response logout(HttpSession session){
        session.invalidate();
        log.info("用户退出成功");
        return new Response("success");
    }

    private User setUser(User u1,User u2){
        if (u2.getAge() != null)
            u1.setAge(u2.getAge());
        if ((u2.getLocation() != null))
            u1.setLocation(u2.getLocation());
        if (u2.getMail() != null)
            u1.setMail(u2.getMail());
        if (u2.getPassword() != null)
            u1.setPassword(u2.getPassword());
        if (u2.getSex() != null)
            u1.setSex(u2.getSex());
        return u1;
    }
}
