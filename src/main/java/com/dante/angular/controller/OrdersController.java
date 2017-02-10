package com.dante.angular.controller;

import com.dante.angular.entity.OrderProductRef;
import com.dante.angular.entity.Orders;
import com.dante.angular.entity.Product;
import com.dante.angular.entity.User;
import com.dante.angular.service.OrderProductRefService;
import com.dante.angular.service.OrdersService;
import com.dante.angular.service.ProductService;
import com.dante.angular.util.Page;
import com.dante.angular.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by xsy83 on 2017/1/8.
 */
@RestController
@Slf4j
public class OrdersController {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrderProductRefService orderProductRefService;
    @Autowired
    private ProductService productService;

    /**
     * 将商品添加到购物车
     * @param productId
     * @param session
     * @return
     */
    @RequestMapping(value = "/addCard", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response addCard(@RequestParam Integer productId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Orders orders = ordersService.getCard(user.getId());

        OrderProductRef opf = new OrderProductRef();
        opf.setOrderId(orders.getId());
        opf.setProductId(productId);
//        opf.setNum(num);
        orderProductRefService.createOrderProductRef(opf);
        log.info("添加到购物车成功");
        return new Response("success");
    }

    /**
     * 查看当前用户的购物车
     * @param session
     * @return
     */
    @RequestMapping(value = "/viewCard", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Orders viewCard(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Orders orders = ordersService.getCard(user.getId());
        List<OrderProductRef> ref = orderProductRefService.getOrderProductRefByOrderId(orders.getId());

        for (int i = 0; i < ref.size(); i++) {
            Product product = productService.getProduct(ref.get(i).getProductId());
            ref.get(i).setProduct(product);
        }
        orders.setProducts(ref);
        return orders;
    }

    /**
     * 购物车结算，这里是将购物车修改为订单
     * @param price
     * @return
     */
    @RequestMapping(value = "/settle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response settle(@RequestParam Double price,HttpSession session){
        User user = (User) session.getAttribute("user");
        Orders orders = ordersService.getCard(user.getId());
        orders.setPrice(price);
        orders.setCategory(1);
        ordersService.updateOrders(orders);
        log.info("购物成功");
        return new Response("success");
    }

    /**
     * 商家修改订单状态为已完成
     * @param userId
     * @return
     */
    @RequestMapping(value = "/changeStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response changeStatus(@RequestParam Integer userId){
        Orders orders = new Orders();
        orders.setStatus(1);
        orders.setUserId(userId);
        ordersService.updateOrders(orders);
        log.info("订单已完成");
        return new Response("success");
    }

    /**
     * 查看当前用户的订单，分页查询，管理员（在本系统系统中视为商家）可以查看所有的用户订单
     * @param page
     * @param session
     * @return
     */
    @RequestMapping(value = "/orders/{page}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Orders> viewOrders(@PathVariable Integer page, HttpSession session){
        if (page == null || page < 1) {
            page = 1;
        }
        User user = (User) session.getAttribute("user");

        Page<Orders> paging = ordersService.getOrders(user, page - 1);
        if (paging.getData() == null){
            log.info("获取订单失败");
            return null;
        }

        // 联表查询，返回订单相关信息
        for (int i = 0; i <paging.getData().size() ; i++) {
            List<OrderProductRef> ref = orderProductRefService.getOrderProductRefByOrderId(paging.getData().get(i).getId());
            for (int j = 0; j < ref.size(); j++) {
                Product product = productService.getProduct(ref.get(j).getProductId());
                ref.get(j).setProduct(product);
            }
            paging.getData().get(i).setProducts(ref);

        }
        log.info("获取订单成功");
        return paging;
    }
}
