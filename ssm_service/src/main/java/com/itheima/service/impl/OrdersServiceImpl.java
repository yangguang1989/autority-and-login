package com.itheima.service.impl;

import com.itheima.dao.OrdersDao;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * @Version 1.0
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersDao ordersDao;

    @Override
    public List<Orders> findAll() {
        return ordersDao.findAll();
    }

    @Override
    public void save(Orders orders) {
        ordersDao.save(orders);
    }

    @Override
    public Orders findById(Integer id) {
        return ordersDao.findById(id);
    }

    @Override
    public void delete(Integer id) {
        ordersDao.delete(id);
    }

    @Override
    public void updateByOrdersId(Orders orders) {
        ordersDao.updateByOrdersId(orders);
    }

}
