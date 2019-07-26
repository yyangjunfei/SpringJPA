package com.example.jpa.demo;

import com.example.jpa.demo.Dao.RoleDao;
import com.example.jpa.demo.Entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Add_Role {

    @Autowired
    private RoleDao roleDao;

    @Test
    public void testAddRole() {
        Role roleDO1 = Role.builder().roleId(1L).roleName("影视主角").build();
        roleDao.save(roleDO1);
        Role roleDO2 = Role.builder().roleId(2L).roleName("影视大侠").build();
        roleDao.save(roleDO2);
        Role roleDO3 = Role.builder().roleId(3L).roleName("影视配角").build();
        roleDao.save(roleDO3);
    }
}
