package com.example.jpa.demo.Entity;

import java.io.Serializable;

/**
 * 联合主键对象
 * @author yang
 * @since 2019-07-24
 */
public class UserAuthorityId implements Serializable {

    private Long authorityId;

    private Long userId;
}
