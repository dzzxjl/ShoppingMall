package com.dante.angular.dao;

import com.dante.angular.util.BaseDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Dante on 2016/12/23.
 */
public abstract class CommenDao<T> extends BaseDao<T> {
  @Autowired
  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
    super.init(sqlSessionFactory);
  }
}
