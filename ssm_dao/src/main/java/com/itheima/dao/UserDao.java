package com.itheima.dao;

import com.itheima.domain.SysUser;

import java.util.List;

/**
 * @author yangjianguang
 * @Version 1.0
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUser findByUsername(String username);

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
     * 验证用户名是否唯一
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
     * 删除用户已有的角色
     * @param userId
     */
    void delRolesFromUser(Integer userId);

    /**
     * 维护用户和角色新的关系
     * @param roleId
     * @param userId
     */
    void saveRoleToUser(Integer userId, Integer roleId );

    void deleteByUserId(Integer id);
}
