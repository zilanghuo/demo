package com.zilanghuo.log;


/**
 * Created by laiwufa on 2020-12-23
 */

public class ReceiveUserDTO {

    //类型，1、新用户 2、修改密码
    private Integer type;

    //工号
    private String userCode;

    // 用户名
    private String userName;

    //密码
    private String password;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
