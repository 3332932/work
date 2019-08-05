package com.cn.springboot.model;

import io.swagger.annotations.ApiModelProperty;

public class DUser {
    @ApiModelProperty(value="用户名",name="name",example="des")
    private String name;

    @ApiModelProperty(value="用户名",name="password",example = "des")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
