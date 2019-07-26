package com.example.jpa.demo.Config;

import lombok.Data;
@Data
public class BrowerProperties {

    /**
     * 登录页面 不配置 为 默认标准登录界面
     */
    private String loginPage;

    private LoginInType loginInType;

}