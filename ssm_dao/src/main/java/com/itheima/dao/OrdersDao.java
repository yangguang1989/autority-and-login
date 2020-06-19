package com.itheima.dao;

import com.itheima.domain.Orders;

import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * @Version 1.0
 */
public interface OrdersDao {
    /**
     * 查询所有
     * @return
     */
    List<Orders> findAll();

    /**
     * 保存
     * @param orders
     */
    void save(Orders orders);

    Orders findById(Integer id);

    void delete(Integer id);

    void updateByOrdersId(Orders orders);
}
