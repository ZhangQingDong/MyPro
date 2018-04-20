package com.example.zqd.myproject.model.bean;

/**
 * <p>Title: com.example.zqd.myproject.model.bean</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/20 9:18
 */

/**
 * var code: String,
 * var token: String,
 * var userId: String,
 * var userName: String,
 * var verify: String
 */
public class LoginBean {

    private String code;

    private String token;

    private String userId;

    private String userName;

    private String verify;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }
}
