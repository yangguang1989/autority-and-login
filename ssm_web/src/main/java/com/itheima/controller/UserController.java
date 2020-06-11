package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangjianguang
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger log = LogManager.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll")
    @Secured({"ROLE_ADMIN"})
    public ModelAndView findAll(){
        log.info("查询所有用户");
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //查询数据
        List<SysUser> userList =  userService.findAll();
        // 添加数据到模型中
        modelAndView.addObject("userList",userList);
        //指定视图页面
        modelAndView.setViewName("user-list");
        //返回ModelAndView对象
        log.info("所有用户"+userList);
        return modelAndView;

    }
    /**
     * 查询个人
     * @return
     */
    @RequestMapping("/findOne")
    public ModelAndView findOne(){
        SecurityContext context = SecurityContextHolder.getContext();
        Object principal = context.getAuthentication().getPrincipal();
        User user = (User) principal;
        //获取用户名
        String username = user.getUsername();
        log.info("查询的该客户是:"+username);
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        ArrayList<String> permissionList = new ArrayList<String>();
        ArrayList<SysUser> userList = new ArrayList<SysUser>();
        //查询数据
        SysUser sysUser =  userService.findByUsername(username);
        for (Role role : sysUser.getRoleList()) {
            for (Permission permission : role.getPermissionList()) {
                permissionList.add(permission.getPermissionName());
                log.info("该用户拥有的权限:"+permission.getPermissionName());
            }
        }
        // 添加数据到模型中
        userList.add(sysUser);
        modelAndView.addObject("userList",userList);
        //modelAndView.addObject("user",user);
        modelAndView.addObject("permissionList",permissionList);
        //指定视图页面
        modelAndView.setViewName("user-one");
        //返回ModelAndView对象
        log.info("该用户"+user);
        return modelAndView;

    }
    /**
     * 保存用户
     * @param user
     * @return
     */
    @RequestMapping("/save")
    @Secured({"ROLE_ADMIN","ROLE_USER_CREATE"})
    public String save(SysUser user){
        //保存操作
        userService.save(user);
        log.info("保存用户:"+user.getUsername()+"成功");
        //查询所有
        return "redirect:/user/findAll";
    }
    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @Secured({"ROLE_ADMIN","ROLE_USER_DELETE"})
    public String delete(Integer id){
        log.info("准备要删除的用户id是:"+id);
        userService.delete(id);
        log.info("删除该用户:"+id+"成功");
        return "redirect:/user/findAll";
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("/update")
    @Secured({"ROLE_ADMIN","ROLE_USER_UPDATE"})
    public ModelAndView update(Integer id){
        log.info("准备要修改的用户id是:"+id);
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //查询数据
        SysUser user =  userService.findById(id);
        // 添加数据到模型中
        modelAndView.addObject("user",user);
        //指定视图页面
        modelAndView.setViewName("user-update");
        //返回ModelAndView对象
        return modelAndView;
    }
    /**
     * 验证用户名是否存在
     * @param username
     */
    @ResponseBody
    @RequestMapping("/checkUsername")
    public String checkUsername(String username){
        //根据用户名查询用户对象
        SysUser user = userService.findByUsernameCheck(username);
        if(user == null){
            return "0";
        }else{
            return "1";
        }
    }

    /**
     * 查询用户的详情
     * @param id
     * @return
     */
    @RequestMapping("/details")
    public ModelAndView details(Integer id){
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //查询数据
        SysUser user = userService.findById(id);
        // 添加数据到模型中
        modelAndView.addObject("user",user);
        //指定视图页面
        modelAndView.setViewName("user-show");
        //返回ModelAndView对象
        return modelAndView;
    }

    @Autowired
    RoleService roleService;

    /**
     * 添加角色到用户的数据回显
     * @param userId
     * @return
     */
    @RequestMapping("/addRolesToUserUI")
    public ModelAndView addRolesToUserUI(Integer userId){
        log.info("准备要添加角色用户id是:"+userId);
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //查询数据
        //需要所有的角色对象
        List<Role> roleList = roleService.findAll();
        //需要该用户已有的角色
        //根据用户id获取一个用户对象（包含了角色信息）
        SysUser user = userService.findById(userId);
        //把该用户已有的角色的id存储到一个字符串中
        StringBuilder sb = new StringBuilder();
        //sb = ,4,,5,,6,,123,
        for (Role role : user.getRoleList()) {
            sb.append(",");
            sb.append(role.getId());
            sb.append(",");
        }
        // 添加数据到模型中
        modelAndView.addObject("roleList",roleList);
        modelAndView.addObject("str",sb.toString());
        modelAndView.addObject("userId",user.getId());
        //指定视图页面
        modelAndView.setViewName("user-role-add");
        //返回ModelAndView对象
        return modelAndView;
    }

    /**
     * 添加角色到用户
     * @param userId  用户的id
     * @param ids  要把角色（id）分配给用户
     * @return
     */
    @RequestMapping("/addRolesToUser")
    @Secured({"ROLE_ADMIN"})
    public String addRolesToUser(Integer userId, Integer[] ids){
        //保存角色和用户的关系
        userService.saveRolesToUser(userId,ids);
        //查询所有
        return "redirect:/user/findAll";
    }
}
