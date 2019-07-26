package com.example.jpa.demo;
import com.example.jpa.demo.Dao.Role_User;
import com.example.jpa.demo.Entity.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Add_Role_User {

    @Autowired
    private Role_User role_user;

    @Test
    public void testAddRoleUser(){
        UserRole roleUserDO1 = UserRole.builder().roleId(1L).userId(1L).build();
        role_user.save(roleUserDO1);
        UserRole roleUserDO2 = UserRole.builder().roleId(1L).userId(2L).build();
        role_user.save(roleUserDO2);
        UserRole roleUserDO3 = UserRole.builder().roleId(1L).userId(3L).build();
        role_user.save(roleUserDO3);
        UserRole roleUserDO4 = UserRole.builder().roleId(1L).userId(4L).build();
        role_user.save(roleUserDO4);
        UserRole roleUserDO5 = UserRole.builder().roleId(1L).userId(5L).build();
        role_user.save(roleUserDO5);
        UserRole roleUserDO6 = UserRole.builder().roleId(2L).userId(1L).build();
        role_user.save(roleUserDO6);
        UserRole roleUserDO7 = UserRole.builder().roleId(2L).userId(3L).build();
        role_user.save(roleUserDO7);
    }
}
