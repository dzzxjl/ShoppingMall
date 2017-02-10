package com.dante.angular.util;

import lombok.*;

import java.util.List;


/**
 * Created by xsy83 on 2017/1/6.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    Integer total;
    Integer page;
    List<T> data;

    public Page(Integer total,List<T> data){
        this.total = total;
        this.data = data;
    }
}
