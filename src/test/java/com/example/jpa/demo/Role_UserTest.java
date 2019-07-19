package com.example.jpa.demo;

import com.example.jpa.demo.Dao.Role_User;
import com.example.jpa.demo.Entity.RoleUserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Role_UserTest {

    @Autowired
    private Role_User role_user;

    @Test
    public void testAddRoleUser(){
        RoleUserDO roleUserDO = new RoleUserDO();

        roleUserDO.setRoleId(1L);
        roleUserDO.setUserId(1L);
        role_user.save(roleUserDO);

        roleUserDO.setRoleId(1L);
        roleUserDO.setUserId(2L);
        role_user.save(roleUserDO);

        roleUserDO.setRoleId(1L);
        roleUserDO.setUserId(3L);
        role_user.save(roleUserDO);

        roleUserDO.setRoleId(1L);
        roleUserDO.setUserId(4L);
        role_user.save(roleUserDO);

        roleUserDO.setRoleId(1L);
        roleUserDO.setUserId(5L);
        role_user.save(roleUserDO);

        roleUserDO.setRoleId(2L);
        roleUserDO.setUserId(1L);
        role_user.save(roleUserDO);

        roleUserDO.setRoleId(2L);
        roleUserDO.setUserId(3L);
        role_user.save(roleUserDO);
    }
}
