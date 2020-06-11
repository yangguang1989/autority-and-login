package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author yangjianguang
 * @Version 1.0
 */
@Controller
@RequestMapping("/permission")
@Secured("ROLE_ADMIN")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //查询数据-- 查询所有的产品
        List<Permission> permissionList = permissionService.findAll();
        //添加数据到模型中
        modelAndView.addObject("permissionList",permissionList);
        //指定视图页面
        modelAndView.setViewName("permission-list");
        //返回ModelAndView
        return modelAndView;
    }

    /**
     * 保存页面数据回显
     * @return
     */
    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //查询数据-- 查询所有的父权限
        List<Permission> permissionList = permissionService.findParentPermission();
        //添加数据到模型中
        modelAndView.addObject("permissionList",permissionList);
        //指定视图页面
        modelAndView.setViewName("permission-add");
        //返回ModelAndView
        return modelAndView;
    }

    /**
     * 保存
     * @param permission
     * @return
     */
    @RequestMapping("/save")
    public String save(Permission permission){
        //保存操作
        permissionService.save(permission);
        //查询所有
        return "redirect:/permission/findAll";
    }
}
