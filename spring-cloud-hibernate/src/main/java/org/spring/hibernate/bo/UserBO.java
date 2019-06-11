package org.spring.hibernate.bo;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank(message = "{user.username.notBlank}")
    private String username;

    @NotBlank(message = "{user.password.notBlank}")
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
