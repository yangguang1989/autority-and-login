package com.itheima.controller;

import com.alibaba.fastjson.JSONObject;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import com.itheima.domain.SysUser;
import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import com.itheima.service.impl.PermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangjianguang
 * @Version 1.0
 */
@Controller
@RequestMapping("/role")
@Secured("ROLE_ADMIN")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        //创建ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        //查询数据
        List<Role> roleList = roleService.findAll();
        //添加数据
        modelAndView.addObject("roleList",roleList);
        //指定页面
        modelAndView.setViewName("role-list");
        //返回ModelAndView对象
        return  modelAndView;
    }
    @ResponseBody
    @RequestMapping("/checkRoleName")
    public void checkUsername(String roleName, HttpServletResponse response) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        if (!roleName.substring(0,4).equals("USER")){
            map.put("str","2");
            map.put("warning", "<font color='red'>请按指定的规则创建角色名（规则为:以USER开头后面+数字1-100其中一个,比如USER1）</font>");
        }else {
            //根据用户名查询用户对象
            Role role = roleService.findByRoleNameCheck(roleName);
            if (role != null) {
                map.put("str", "1");
                map.put("warning", "<font color='red'>抱歉，该角色已存在，请重新创建</font>");
            } else {
                map.put("str", "0");
                map.put("warning", "<font color='green'>恭喜，该角色可用</font>");
            }
        }
        JSONObject jsonObject= (JSONObject) JSONObject.toJSON(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(jsonObject.toJSONString());
    }
    /**
     * 保存操作
     * @param role
     * @return
     */
    @RequestMapping("/save")
    public String save(Role role){
        //保存
        roleService.save(role);
        //查询所有
        return "redirect:/role/findAll";
    }

    /**
     * 添加权限到角色的数据回显
     * @param roleId
     * @return
     */
    @RequestMapping("/addPermissionsToRoleUI")
    public ModelAndView addPermissionsToRoleUI(Integer roleId){
        //创建ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        //查询数据
        //所有的权限
        List<Permission> permissionList = permissionService.findAll();
        //已有的权限
        Role role = roleService.findById(roleId);
        //把已有的权限的id存储到字符串中
        StringBuilder sb = new StringBuilder();
        for (Permission permission : role.getPermissionList()) {
            sb.append(",");
            sb.append(permission.getId());
            sb.append(",");
        }
        //添加数据
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.addObject("str",sb.toString());
//
        modelAndView.addObject("roleId",role.getId());
        //指定页面
        modelAndView.setViewName("role-permission-add");
        //返回ModelAndView对象
        return  modelAndView;
    }

    /***
     * 添加权限和角色的关系
     * @param roleId
     * @param ids
     * @return
     */
    @RequestMapping("/addPermissionsToRole")
    public String addPermissionsToRole(Integer roleId, Integer[] ids){
        //保存关系
        roleService.savePermissionsToRole(roleId,ids);
        //查询所有
        return "redirect:/role/findAll";
    }

}
