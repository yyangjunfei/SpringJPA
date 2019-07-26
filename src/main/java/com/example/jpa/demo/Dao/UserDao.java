package com.example.jpa.demo.Dao;
import com.example.jpa.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 用户服务数据接口类
 *
 * @author yang
 * @since 2019-07-24
 */

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    User findByUserAcount(String userAcount);

    User findByUserName(String userName);

    User findByUserAcountAndUserPwd(String userAcount, String userPwd);

    List<User> findAllByUserIdIsGreaterThan(Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM USER WHERE user_name = :name1  OR user_name = :name2 ")//@Query 注解中增加一个 nativeQuery = true 的属性，就可以采用原生 SQL 语句的方式来编写查询。
    List<User> findTwoUserName(@Param("name1") String name1, @Param("name2") String name2);

    @Query(nativeQuery = true,value = "SELECT `user`.* FROM `role` LEFT JOIN `user_role` ON (`role`.role_id=`user_role`.role_id) LEFT JOIN `user` ON (`user_role`.user_id=`user`.user_id) WHERE `role`.role_id=:roleId")
    List<User> findUsersByRoleId(@Param("roleId") Long roleId);
}
