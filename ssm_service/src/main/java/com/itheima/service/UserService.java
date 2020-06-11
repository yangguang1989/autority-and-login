package com.itheima.service;

import com.itheima.domain.SysUser;

import java.util.List;

/**
 * @author yangjianguang
 * @Version 1.0
 */
public interface UserService {
    /**
     * 查询所有
     * @return
     */
    List<SysUser> findAll();

    /**
     * 保存
     * @param user
     */
    void save(SysUser user);

    /**
     * 验证用户名是否唯一：根据用户查询用户对象
     * @param username
     * @return
     */
    SysUser findByUsernameCheck(String username);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    SysUser findById(Integer id);

    /**
     * 保存用户和角色的关系
     * @param userId
     * @param ids
     */
    void saveRolesToUser(Integer userId, Integer[] ids);

    void delete(Integer id);

    SysUser findByUsername(String username);
}
