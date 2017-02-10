package com.dante.angular.entity;

import lombok.Data;

/**
 * Created by xsy83 on 2017/1/6.
 */
@Data
public class OrderProductRef {
    Integer id;
    Integer orderId;
    Integer productId;
    Integer num = 1;                // 商品数量
    Product product;                // 保存商品信息
}
