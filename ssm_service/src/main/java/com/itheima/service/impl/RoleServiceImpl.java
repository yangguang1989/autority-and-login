package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangjianguang
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(Integer roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public void savePermissionsToRole(Integer roleId, Integer[] ids) {
        //先删除原来的关系
        roleDao.delPermissionsFromRole(roleId);
        //维护新的关系
        if(ids != null){
            for (Integer permissionId : ids) {
                roleDao.savePermissionToRole( permissionId,roleId);
            }
        }
    }
}
