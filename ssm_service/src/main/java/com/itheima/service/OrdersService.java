package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * @Version 1.0
 */
public interface OrdersService {
    /**
     * 查询所有
     * @return
     */
    List<Orders> findAll();

    /**
     * 保存订单
     * @param orders
     */
    void save(Orders orders);
}
