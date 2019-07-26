package com.example.jpa.demo.Dao;
import com.example.jpa.demo.Entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Role_User extends JpaRepository<UserRole,Long> {

}
