package com.itheima.controller;

import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * @Version 1.0
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //查询数据
        List<Orders> ordersList =  ordersService.findAll();
        //添加数据到模型中
        modelAndView.addObject("ordersList",ordersList);
        //指定视图页面
        modelAndView.setViewName("order-list");
        //返回ModelAndView
        return modelAndView;
    }
    /**
     * 保存操作
     * @param orders
     * @return
     */
    @RequestMapping("/save")
    public String save(Orders orders){
        //保存操作
        ordersService.save(orders);
        //重定向：查询全部
        return "redirect:/orders/findAll";
    }
}
