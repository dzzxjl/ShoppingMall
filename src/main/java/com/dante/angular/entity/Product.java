package com.dante.angular.entity;

import lombok.Data;

/**
 * Created by xsy83 on 2017/1/6.
 */
@Data
public class Product {
    Integer id;         // 逻辑id
    String name;        // 商品名称
    String category;    // 种类
    Double price;       // 价格
    String src;         // 图片地址
    String intro;       // 商品详情
}
