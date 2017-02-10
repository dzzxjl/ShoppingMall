package com.dante.angular.dao.order;

import com.dante.angular.dao.CommenDao;
import com.dante.angular.entity.OrderProductRef;
import com.dante.angular.entity.Orders;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xsy83 on 2017/1/8.
 */
@Repository
public class OrderProductRefDao extends CommenDao<OrderProductRef> {

    public boolean createOrderProductRef(OrderProductRef orderProductRef) {
        return getSqlSession().insert("OrderProductRef.create", orderProductRef) == 1 ? true : false;
    }

    public boolean updateOrderProductRef(OrderProductRef orderProductRef) {
        return getSqlSession().update("OrderProductRef.update", orderProductRef) == 1 ? true : false;
    }

    public boolean deleteOrderProductRef(Integer id) {
        return getSqlSession().delete("OrderProductRef.deleteById", id) == 1 ? true : false;
    }

    public List<OrderProductRef> getOrderProductRefByOrderId(Integer orderId){
        return getSqlSession().selectList("OrderProductRef.getByOrderId",orderId);
    }
}
