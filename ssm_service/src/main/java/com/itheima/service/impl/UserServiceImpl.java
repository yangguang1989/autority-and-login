package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author yangjianguang
 * @Version 1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService,UserDetailsService {
    private static Logger log = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    /**
     * 根据用户名载入用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            log.info("登录的用户是:"+username);
        try {
            //1. 根据用户名从数据库查询用户信息(包含了角色信息)
            SysUser user = userDao.findByUsername(username);
            if (user==null){
                throw new RuntimeException("该用户不存在");
            }
            //2. 创建用户详情对象，并返回
            if(user != null){
                //创建用户详情对象
                //创建角色集合对象
                Collection<GrantedAuthority> list = new ArrayList<>();
                //角色名称要从数据库中查询
                for (Role role : user.getRoleList()) {
                    //查看用户拥有哪些角色
                    log.info("该用户拥有的角色:"+role);
                    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_"+role.getRoleName());
                    //添加到集合中
                    list.add(simpleGrantedAuthority);
                }
                /**
                 *  参数1：用户名
                 *  参数2：密码
                 *  参数3：该用户的角色
                 */
                User userDetails = new User(user.getUsername(),user.getPassword(),list);
                return userDetails;
            }
        } catch (RuntimeException e) {
             e.printStackTrace();
            log.info("该用户:"+username+"登录失败");
        }
        return null;
    }

    @Override
    public List<SysUser> findAll() {
        return userDao.findAll();
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void save(SysUser user) {
        //获取明文密码
        String password = user.getPassword();
        //把密码进行加盐加密(安全框架)
        String encode = passwordEncoder.encode(password);
        //赋值user对象
        user.setPassword(encode);
        userDao.save(user);
    }

    @Override
    public SysUser findByUsernameCheck(String username) {
        return userDao.findByUsernameCheck(username);
    }

    @Override
    public SysUser findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public void saveRolesToUser(Integer userId, Integer[] ids) {
        //删除原来的所有关系
        userDao.delRolesFromUser(userId);
        //维护新的关系
        if(ids != null){
            for (Integer roleId : ids) {
                userDao.saveRoleToUser(userId,roleId);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        userDao.deleteByUserId(id);
    }

    @Override
    public SysUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void updateByUsername(SysUser user) {
        try {
            int i =  userDao.updateByUsername(user);
            if (i==1){
                log.info("更新该用户"+user.getUsername()+"信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
