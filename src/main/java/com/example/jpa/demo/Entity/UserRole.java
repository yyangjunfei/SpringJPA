package com.example.jpa.demo.Entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 角色用户关系实体类
 *
 * @author yang
 * @since 2018-03-12
 */
@Entity
@Table(name = "USER_ROLE")
@Builder
@IdClass(UserRoleId.class)
@Data
public class UserRole{
    @Id
    private Long roleId;
    @Id
    private Long userId;
}
