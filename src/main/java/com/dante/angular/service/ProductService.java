package com.dante.angular.service;

import com.dante.angular.dao.order.ProductDao;
import com.dante.angular.entity.Product;
import com.dante.angular.util.Base64ToImg;
import com.dante.angular.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xsy83 on 2017/1/8.
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public boolean createProduct(Product product){
        return productDao.createProduct(product);
    }

    public boolean updateProduct(Product product){
        return productDao.updateProduct(product);
    }

    public boolean deleteProduct(Integer id){
        return productDao.deleteProduct(id);
    }

    public Product getProduct(Integer id){
        return productDao.getProduct(id);
    }

    public Page<Product> pagingProduct(String name,String category,Integer page){
        Map map = new HashMap();
        map.put("name",name);
        map.put("category",category);
        map.put("offset",page-1);
        map.put("limit",8);
        Page<Product> paging = productDao.pagingProduct(map);
        if (paging.getData() == null)
            return null;
        List<Product> list = paging.getData();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSrc(Base64ToImg.getRealPath(list.get(i).getSrc()));
        }
        paging.setData(list);
        return paging;
    }

    public List<String> getCategory(){
        return productDao.getCategory();
    }
}
