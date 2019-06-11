package org.spring.hibernate.service.impl;

import net.bytebuddy.implementation.bytecode.Throw;
import org.spring.cloud.common.util.CopyUtil;
import org.spring.hibernate.bo.UserBO;
import org.spring.hibernate.repository.UserRepository;
import org.spring.hibernate.po.UserPO;
import org.spring.hibernate.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    public UserBO findById(Integer id) {
        return CopyUtil.copyBean(userRepository.findById(id).get(), UserBO.class);
    }

    public UserBO save(UserPO userPO) {
            // throw new RuntimeException("RuntimeException");
            // 如果截获了异常则Spring 事务不会回滚，需要手动回滚
        UserBO userBO = new UserBO();
        try {
           userBO = CopyUtil.copyBean(userRepository.save(userPO), UserBO.class);
           // throw new SQLException();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return userBO;
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public List<UserBO> findAll() {
        List<UserPO> userList = userRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
        return CopyUtil.copyList(userList, UserBO.class);
    }

}
