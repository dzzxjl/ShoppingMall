package com.dante.angular.service;

import com.dante.angular.dao.order.OrderProductRefDao;
import com.dante.angular.dao.order.OrdersDao;
import com.dante.angular.entity.Orders;
import com.dante.angular.entity.User;
import com.dante.angular.util.Page;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xsy83 on 2017/1/8.
 */
@Service
public class OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    public boolean createOrders(Orders orders){
        return ordersDao.createOrders(orders);
    }

    public boolean updateOrders(Orders orders){
        return ordersDao.updateOrders(orders);
    }

    public boolean deleteOrders(Integer id){
        return ordersDao.deleteOrders(id);
    }

    public Page<Orders> pagingOrders(Map map){
        return ordersDao.pagingOrders(map);
    }

    public Orders getOrdersById(Integer id){
        return ordersDao.getOrdersById(id);
    }

    /**
     * 获取购物车
     * @param userId
     * @return
     */
    public Orders getCard(Integer userId){
        List<Orders> list = ordersDao.getOrders(ImmutableMap.of("userId", userId, "status", 0));
        if(list == null && list.size() == 0){
            Orders orders = new Orders();
            orders.setUserId(userId);
            createOrders(orders);
            return getCard(userId);
        }
        return list.get(0);
    }

    /**
     * 获取订单,分页,这里和前端商量好每页8条信息
     * @param user
     * @return
     */
    public Page<Orders> getOrders(User user,Integer page){
        Map map = new HashMap();
        map.put("category",1);
        map.put("offset",page);
        map.put("limit",8);
        if (user.getIsAdmin() == 0){
            map.put("userId",user.getId());
        }

        // 前端说不保留页数的状态，所以这里还得将page返回给他
        Page<Orders> paging = ordersDao.pagingOrders(map);
        paging.setPage(page);
        return paging;
    }
}
