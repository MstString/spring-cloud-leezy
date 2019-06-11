package org.spring.mybatis.service;

import org.spring.mybatis.domain.User;
import org.spring.mybatis.vo.UserVO;

import java.util.List;

public interface IUserService {
    public User getUser(UserVO userVO);

    public List<User> getUsers();

    public String addUser(UserVO userVO);

    public String updateUser(UserVO userVO);

    public String deleteUser(UserVO userVO);
}
