package com.dante.angular.util;

import com.dante.angular.util.Page;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by Dante on 2016/12/23.
 */
public abstract class BaseDao<T> extends SqlSessionDaoSupport {

  public final String nameSpace;
  private final String PAGING = "paging";
  private final String TOTAL = "count";

  public BaseDao() {
    if (this.getClass().getGenericSuperclass() instanceof ParameterizedType)
      this.nameSpace = ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
    else
      this.nameSpace = ((Class) ((ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
  }

  public void init(SqlSessionFactory factory) {
    super.setSqlSessionFactory(factory);
  }

  public abstract void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory);

  public Page<T> paging(Map map){
    int total = getSqlSession().selectOne(nameSpace+"."+TOTAL,map);
    List<T> page = getSqlSession().selectList(nameSpace+"."+PAGING,map);
    return new Page<T>(total,page);
  }
}
