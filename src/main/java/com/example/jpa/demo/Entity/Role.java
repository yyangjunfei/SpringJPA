package com.example.jpa.demo.Entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 角色实体类
 *
 * @author yang
 * @since 2018-03-12
 */
@Entity
@Table(name = "ROLE")
@Builder
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @NotEmpty(message = "角色不能为空")
    @Size(min=3, max=20)
    @Column(name = "role_name",length = 32,nullable = false, unique = true)
    private String roleName;

    @ManyToMany(mappedBy = "roleList")
    private List<User> userList;
}
