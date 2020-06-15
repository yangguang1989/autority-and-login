package com.itheima.dao;

import com.itheima.domain.Role;

import java.util.List;

/**
 * @author yangjianguang
 * @Version 1.0
 */
public interface RoleDao {
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
     * 根据用户id查询角色列表
     * @param userId
     * @return
     */
    List<Role> findRolesByUserId(Integer userId);
    /**
     * 根据id查询角色对象（包含权限信息）
     * @param roleId
     * @return
     */
    Role findById(Integer roleId);

    /**
     * 删除原来的关系
     * @param roleId
     */
    void delPermissionsFromRole(Integer roleId);

    /**
     * 维护新的关系
     * @param permissionId
     * @param roleId
     */
    void savePermissionToRole(Integer permissionId, Integer roleId);

    Role findByRoleNameCheck(String roleName);

}
