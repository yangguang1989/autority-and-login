package com.itheima.service.impl;

import com.itheima.dao.PermissionDao;
import com.itheima.domain.Permission;

import com.itheima.service.PermissionService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangjianguang
 * @Version 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public List<Permission> findParentPermission() {
        return permissionDao.findParentPermission();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
