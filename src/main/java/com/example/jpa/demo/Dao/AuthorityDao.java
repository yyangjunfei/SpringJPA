package com.example.jpa.demo.Dao;
import com.example.jpa.demo.Entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * 用户服务数据接口类
 *
 * @author yang
 * @since 2019-07-24
 */
public interface AuthorityDao extends JpaRepository<Authority,Long> {

    @Query(nativeQuery = true,value = "SELECT `authority`.* FROM `authority` LEFT JOIN `user_authority` ON (`authority`.authority_id=`user_authority`.authority_id) LEFT JOIN `user` ON (`user_authority`.user_id=`user`.user_id) WHERE `user`.user_id=:userId")
    List<Authority> findAuthorityByUserId(@Param("userId") Long userId);

}
