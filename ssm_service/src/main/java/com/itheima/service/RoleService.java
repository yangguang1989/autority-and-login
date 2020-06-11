package com.itheima.service;

import com.itheima.domain.Role;

import java.util.List;

/**
 * @author yangjianguang
 * @Version 1.0
 */
public interface RoleService {
    /**
     * 查询所有
     * @return
     */
    List<Role> findAll();

    /**
     * 保存
     * @param role
     */
    void save(Role role);

    /**
     * 根据id查询角色对象（包含权限信息）
     * @param roleId
     * @return
     */
    Role findById(Integer roleId);

    /**
     * 保存权限和角色的关系
     * @param roleId
     * @param ids
     */
    void savePermissionsToRole(Integer roleId, Integer[] ids);
}
