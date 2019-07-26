package com.example.jpa.demo;
import com.example.jpa.demo.Dao.AuthorityDao;
import com.example.jpa.demo.Dao.UserDao;
import com.example.jpa.demo.Entity.Authority;
import com.example.jpa.demo.Entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorityDao authorityDao;

    @Test
    public void testLocate() {

        Optional<User> userDOOptional = userDao.findById(2L);

        if (userDOOptional.isPresent()) {
            User userDO = userDOOptional.get();
            System.out.println("name = " + userDO.getUserName());
            System.out.println("account = " + userDO.getUserAcount());
        }
    }

    @Test
    public void testFindAll() {
        List<User> userDOList = userDao.findAll(new Sort(Sort.Direction.ASC, "userAcount"));
        for (User userDO : userDOList) {
            System.out.println("name = " + userDO.getUserName());
            System.out.println("account = " + userDO.getUserAcount());
        }
    }

    @Test
    public void testFindByAccount() {
        User userDO = userDao.findByUserAcount("wentian");
        if (userDO != null) {
            System.out.println("name = " + userDO.getUserName());
            System.out.println("account = " + userDO.getUserAcount());
        }
    }

    @Test
    public void testFindByAccountAndPwd() {
        User userDO = userDao.findByUserAcountAndUserPwd("wentian","123456");
        if (userDO != null) {
            System.out.println("name = " + userDO.getUserName());
            System.out.println("account = " + userDO.getUserAcount());
        }
    }

    @Test
    public void testFindAllByIdGreaterThan() {
        List<User> userDOList = userDao.findAllByUserIdIsGreaterThan(2L);
        for(User userDO :userDOList){
            System.out.println("name = " + userDO.getUserName());
            System.out.println("account = " + userDO.getUserAcount());
        }
    }

    @Test
    public void testFindTwoName() {
        List<User> userDOList = userDao.findTwoUserName("风清扬","任我行");
        for(User userDO :userDOList){
            System.out.println("name = " + userDO.getUserName());
            System.out.println("account = " + userDO.getUserAcount());
        }
    }

   // 关联查询

    @Test
    public void testFindUsersByRole() {
        List<User> userDOList = userDao.findUsersByRoleId(2L);
        for(User userDO :userDOList){
            System.out.println("name = " + userDO.getUserName());
            System.out.println("account = " + userDO.getUserAcount());
        }
    }


    // 根据用户id查询用户权限
    @Test
    public void testFindAuthorityByUserId() {
        List<Authority> authorityList = authorityDao.findAuthorityByUserId(3L);
        authorityList.forEach(item->System.out.println(item.getAuthorityName()));
    }

}
