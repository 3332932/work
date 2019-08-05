package com.cn.springboot.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class Pay {
    @ApiModelProperty(value="用户名",name="userName",example="user")
    private String userName;
    @ApiModelProperty(value="余额",name="money",example="20.02")
    private BigDecimal money;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
