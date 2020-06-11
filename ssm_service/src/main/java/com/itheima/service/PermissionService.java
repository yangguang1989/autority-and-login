package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

/**
 * @author yangjianguang
 * @Version 1.0
 */
public interface PermissionService {
    /**
     * 查询所有
     * @return
     */
    List<Permission> findAll();

    /**
     * 查询父权限
     * @return
     */
    List<Permission> findParentPermission();

    /**
     * 保存权限
     * @param permission
     */
    void save(Permission permission);
}
