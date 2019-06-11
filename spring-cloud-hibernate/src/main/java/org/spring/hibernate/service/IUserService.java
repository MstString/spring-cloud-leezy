package org.spring.hibernate.service;

import org.spring.hibernate.bo.UserBO;
import org.spring.hibernate.po.UserPO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IUserService {

    public UserBO findById(Integer id);

    public UserBO save(UserPO userPO);

    public void deleteById(Integer id);

    public List<UserBO> findAll();
}
