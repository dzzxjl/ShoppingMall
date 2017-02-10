package com.dante.angular.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xsy83 on 2017/1/10.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response{
    String status;
    Integer isAdmin;

    public Response(String status){
        this.status = status;
    }
}
