package com.example.jpa.demo.Dao;

import com.example.jpa.demo.Entity.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<RoleDO,Long> {

}
