package com.dante.angular.dao.order;

import com.dante.angular.dao.CommenDao;
import com.dante.angular.entity.Product;
import com.dante.angular.util.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by xsy83 on 2017/1/8.
 */
@Repository
public class ProductDao extends CommenDao<Product> {

    public boolean createProduct(Product product) {
        return getSqlSession().insert("Product.create", product) == 1 ? true : false;
    }

    public boolean updateProduct(Product product) {
        return getSqlSession().update("Product.update", product) == 1 ? true : false;
    }

    public boolean deleteProduct(Integer id) {
        return getSqlSession().delete("Product.delete", id) == 1 ? true : false;
    }

    public Product getProduct(Integer id){
        return getSqlSession().selectOne("Product.get",id);
    }

    public Page<Product> pagingProduct(Map map){
        return paging(map);
    }

    public List<String> getCategory(){
        return getSqlSession().selectList("Product.getCategory");
    }
}
