package com.example.jpa.demo.Dao;
import com.example.jpa.demo.Entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户服务数据接口类
 *
 * @author yang
 * @since 2018-03-12
 */

@Repository
public interface UserDao extends JpaRepository<UserDO,Long> {

    UserDO findByAcount(String account);

    UserDO findByAcountAndPwd(String account, String pwd);

    List<UserDO> findAllByIdGreaterThan(Long id);

    /*@Query("SELECT O FROM UserDO O WHERE O.name = :name1  OR O.name = :name2 ")*/ //PQL 的语法来定义一个查询
    @Query(nativeQuery = true, value = "SELECT * FROM AUTH_USER WHERE name = :name1  OR name = :name2 ")//@Query 注解中增加一个 nativeQuery = true 的属性，就可以采用原生 SQL 语句的方式来编写查询。
    List<UserDO> findTwoName(@Param("name1") String name1, @Param("name2") String name2);

    /*@Query("SELECT U FROM UserDO U ,RoleUserDO RU WHERE U.id = RU.userId AND RU.roleId = :roleId")*/
    @Query(nativeQuery = true,value = "SELECT U.* FROM AUTH_USER U ,AUTH_ROLE_USER RU WHERE U.id = RU.user_id AND RU.role_id = :roleId")
    List<UserDO> findUsersByRole(@Param("roleId") Long roleId);
}
