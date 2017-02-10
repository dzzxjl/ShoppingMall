package com.dante.angular.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by xsy83 on 2017/1/6.
 */
@Data
public class Orders {
    Integer id;             // 逻辑id
    Integer userId;         // 用户id
    Date date;              // 订单生成时间
    Double price;           // 订单总价格
    Integer status = 0;     // 订单状态,0:未完成，1：已完成，2:取消
    Integer category = 0;   // 0:购物车  1：订单
    List<OrderProductRef> products; // 商品列表
}
