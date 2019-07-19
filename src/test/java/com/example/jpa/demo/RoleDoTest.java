package com.example.jpa.demo;

import com.example.jpa.demo.Dao.RoleDao;
import com.example.jpa.demo.Entity.RoleDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleDoTest {

    @Autowired
    private RoleDao roleDao;

    @Test
    public void testAddRole() {
        RoleDO roleDO = new RoleDO();
        roleDO.setId(1L);
        roleDO.setName("主角");
        roleDao.save(roleDO);
        roleDO.setId(2L);
        roleDO.setName("大侠");
        roleDao.save(roleDO);
        roleDO.setId(3L);
        roleDO.setName("配角");
        roleDao.save(roleDO);
    }
}
