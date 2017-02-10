package com.dante.angular.service;

import com.dante.angular.dao.order.OrderProductRefDao;
import com.dante.angular.entity.OrderProductRef;
import com.dante.angular.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xsy83 on 2017/1/8.
 */
@Service
public class OrderProductRefService {

    @Autowired
    private OrderProductRefDao orderProductRefDao;

    public boolean createOrderProductRef(OrderProductRef orderProductRef){
        return orderProductRefDao.createOrderProductRef(orderProductRef);
    }

    public boolean updateOrderProductRef(OrderProductRef orderProductRef) {
        return orderProductRefDao.updateOrderProductRef(orderProductRef);
    }

    public boolean deleteOrderProductRef(Integer id) {
        return orderProductRefDao.deleteOrderProductRef(id);
    }

    public List<OrderProductRef> getOrderProductRefByOrderId(Integer orderId){
        return orderProductRefDao.getOrderProductRefByOrderId(orderId);
    }
}
