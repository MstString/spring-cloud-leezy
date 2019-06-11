package org.spring.hibernate.vo;

import java.io.Serializable;

/**
 * @program: spring-cloud-leezy
 * @description: 用户前端交互实体类
 * @author: LEEZY
 * @create: 2019-03-20 10:37
 **/
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
