package com.itheima.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 *   在服务端获取用户名
 * @author yangjianguang
 * @Version 1.0
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/showUsername")
    public void showUsername(HttpServletRequest request){
        //获取session对象
        HttpSession session = request.getSession();
        // 获取所有的session域中的属性名, 返回枚举
        Enumeration attributeNames = session.getAttributeNames();
        //遍历枚举类型
        //attributeNames.hasMoreElements() 判断是否有更多的元素
        //attributeNames.nextElement()获取枚举的下一个元素
        //安全框架在登录成功后在session中存储的属性名：SPRING_SECURITY_CONTEXT
        //while(attributeNames.hasMoreElements()){
         //   System.out.println(attributeNames.nextElement());
        //}
        Object o = session.getAttribute("SPRING_SECURITY_CONTEXT");
        //转换类型: 转换为上下文对象
        SecurityContext securityContext = (SecurityContext) o;
        //获取认证对象
        Authentication authentication = securityContext.getAuthentication();
        //获取用户对象"  principal : 重要的
        Object principal = authentication.getPrincipal();
        User user = (User) principal;
        //获取用户名
        String username = user.getUsername();
        System.out.println(username);

        //根据帮助类获取上下文对象
        SecurityContext securityContext1 = SecurityContextHolder.getContext();
        System.out.println(securityContext == securityContext1);
    }
}
