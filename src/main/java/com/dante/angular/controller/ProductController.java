package com.dante.angular.controller;

import com.dante.angular.entity.Product;
import com.dante.angular.service.ProductService;
import com.dante.angular.util.Base64ToImg;
import com.dante.angular.util.Page;
import com.dante.angular.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xsy83 on 2017/1/8.
 */
@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/admin/products/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response create(@RequestBody Product product) {
        if (product.getSrc() != null){
            product.setSrc(Base64ToImg.createImage(product.getSrc()));
        }
        boolean bool = productService.createProduct(product);
        if (!bool) {
            return new Response("error");
        }
        log.info("创建商品成功");
        return new Response("success");
    }

    @RequestMapping(value = "/admin/products/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response update(@RequestBody Product product,@PathVariable String type) {
        if (product.getSrc() != null){
            product.setSrc(Base64ToImg.createImage(product.getSrc()));
        }
        boolean bool = productService.updateProduct(product);
        if (!bool) {
            return new Response("error");
        }
        log.info("修改商品成功");
        return new Response("success");
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getCategory() {
        List<String> list = productService.getCategory();
        log.info("获取类别");
        return list;
    }

    @RequestMapping(value = "/getProduct/{page}", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Product> getProduct(@RequestBody Product product,@PathVariable Integer page){
        Page<Product> products = productService.pagingProduct(product.getName(), product.getCategory(), page);
        log.info("获取商品成功");
        return products;
    }
}
