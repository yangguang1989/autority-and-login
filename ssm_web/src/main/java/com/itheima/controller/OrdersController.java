package com.itheima.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itheima.domain.Orders;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.OrdersService;
import com.itheima.service.UserService;
import com.itheima.utils.UserUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * @Version 1.0
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    private static Logger log = LogManager.getLogger(UserController.class);
    @Autowired
    OrdersService ordersService;
    @Autowired
    UserService userService;
    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        User user = UserUtils.getLoginUser();
        //获取用户名
        String username = user.getUsername();
        log.info("查询的该客户是:"+username);
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //查询数据
        List<Orders> ordersList =  ordersService.findAll();
        ArrayList<String> permissionList = new ArrayList<String>();
        //查询数据
        SysUser sysUser =  userService.findByUsername(username);
        for (Role role : sysUser.getRoleList()) {
            for (Permission permission : role.getPermissionList()) {
                permissionList.add(permission.getPermissionName());
                log.info("该用户拥有的权限:"+permission.getPermissionName());
            }
        }

        // 添加数据到模型中
        modelAndView.addObject("permissionList",permissionList);
        //添加数据到模型中
        modelAndView.addObject("ordersList",ordersList);
        //指定视图页面
        modelAndView.setViewName("order-list");
        //返回ModelAndView
        return modelAndView;
    }

    @RequestMapping("/findPermission")
    @ResponseBody
    public void findAll1(HttpServletResponse response) throws IOException {
        User user = UserUtils.getLoginUser();
        //获取用户名
        String username = user.getUsername();
        log.info("查询的该客户是:"+username);
        HashMap<String, Object> map = new HashMap<>();
        //查询数据
        SysUser sysUser =  userService.findByUsername(username);
        for (Role role : sysUser.getRoleList()) {
            for (Permission permission : role.getPermissionList()) {
                if (permission.getPermissionName().equals("订单查询")){
                    map.put("flag","0");
                }
            }
        }
        JSONObject jsonObject= (JSONObject) JSONObject.toJSON(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(jsonObject.toJSONString());
    }
    /**
     * 修改订单前回显该该订单信息
     * @param id
     * @return
     */
    @RequestMapping("/updateUI")
    public ModelAndView updateUI(Integer id){
        log.info("准备要修改的订单id是:"+id);
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //查询数据
        Orders orders =  ordersService.findById(id);
        // 添加数据到模型中
        modelAndView.addObject("orders",orders);
        //指定视图页面
        modelAndView.setViewName("order-update");
        //返回ModelAndView对象
        return modelAndView;
    }
    /**
     * 修改用户前回显该用户信息
     * @param
     * @return
     */
    @RequestMapping("/update")
    public String  update(Orders orders){
        log.info("准备要修改的订单是:"+orders.getOrderName());
        //修改数据
        ordersService.updateByOrdersId(orders);
        log.info("修改的用户:"+orders.getOrderName()+"成功");
        return "redirect:/orders/findAll";
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

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(Integer id){
        log.info("准备要删除的订单id是:"+id);
        ordersService.delete(id);
        log.info("删除该用户:"+id+"成功");
        return "redirect:/orders/findAll";
    }

}
