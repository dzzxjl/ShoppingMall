package com.dante.angular.dao.order;

import com.dante.angular.dao.CommenDao;
import com.dante.angular.entity.Orders;
import com.dante.angular.util.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by xsy83 on 2017/1/8.
 */
@Repository
public class OrdersDao extends CommenDao<Orders>{

    public boolean createOrders(Orders orders) {
        return getSqlSession().insert("Orders.create", orders) == 1 ? true : false;
    }

    public boolean updateOrders(Orders orders) {
        return getSqlSession().update("Orders.update", orders) == 1 ? true : false;
    }

    public boolean deleteOrders(Integer id) {
        return getSqlSession().delete("Orders.delete", id) == 1 ? true : false;
    }

    public Orders getOrdersById(Integer id){
        return getSqlSession().selectOne("Orders.getById",id);
    }

    public List<Orders> getOrders(Map map){
        return getSqlSession().selectList("Orders.get",map);
    }

    public Page<Orders> pagingOrders(Map map){
        return paging(map);
    }
}
