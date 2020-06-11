package com.itheima;

import com.itheima.domain.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


public class TestBCryptPasswordEncoder {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Test
    public void test() {
        String password ="1111";
        String encode = bCryptPasswordEncoder.encode(password);
        SysUser sysUser = new SysUser();
        sysUser.setPassword(encode);
        System.out.println(sysUser);
    }
}
