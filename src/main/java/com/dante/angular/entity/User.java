package com.dante.angular.entity;

import lombok.Data;

/**
 * Created by xsy83 on 2017/1/6.
 */
@Data
public class User {
    Integer id;             // 逻辑id
    String name;            // 名字
    String password;        // 密码
    Integer age;            // 年龄
    String sex;             // 性别
    String mail;            // 邮箱
    String phone;           // 手机
    String location;        // 地址
    Integer isAdmin = 0;    // 是否为管理员，0:不是 1:是
}
