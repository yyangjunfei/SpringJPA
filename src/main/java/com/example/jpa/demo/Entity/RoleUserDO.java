package com.example.jpa.demo.Entity;

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
@IdClass(RoleUserId.class)
@Table(name = "AUTH_ROLE_USER")
public class RoleUserDO  {
    @Id
    private Long roleId;
    @Id
    private Long userId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
