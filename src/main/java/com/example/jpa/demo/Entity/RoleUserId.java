package com.example.jpa.demo.Entity;

import java.io.Serializable;

/**
 * 联合主键对象
 *
 * @author yang
 * @since 2018-03-12
 */
public class RoleUserId implements Serializable {
    private Long roleId;
    private Long userId;
}
