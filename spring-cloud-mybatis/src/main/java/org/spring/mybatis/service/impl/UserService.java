package org.spring.mybatis.service.impl;

import com.alibaba.fastjson.JSON;
import org.spring.mybatis.dao.UserMapper;
import org.spring.mybatis.domain.User;
import org.spring.mybatis.domain.UserExample;
import org.spring.mybatis.service.IUserService;
import org.spring.mybatis.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserService {
    // @Resource默认是按名称进行注入的
    // @Resource
    /* @Autowired默认是按照类型进行注入的，如果不在Dao中的接口上写@Repository,这里会报错，因为Spring会拦截接口的bean注入,所以这里会报错，同时在
    Dao上加上@Mapper(或者在启动类上加上@MapperScan)才能注入bean到Spring容器,即使使用了Autowired也会有一个警告，Field injection is not recommended less
    则需要使用构造器注入*/
    private UserMapper userMapper;

    @Autowired
    public void DI(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    RedisTemplate redisTemplate;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public User getUser(UserVO userVO) {
        return  userMapper.selectByPrimaryKey(userVO.getId());
    }

    public List<User> getUsers() {
        UserExample userExample = new UserExample();
        List<User> userList = userMapper.selectByExample(userExample);

        redisTemplate.opsForList().leftPush("user:list", JSON.toJSONString(userList));
        return  userList;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public String addUser(UserVO userVO) {
        userMapper.insert(toUserMan(userVO));
        return "Insert User Data Success!";
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public String updateUser(UserVO userVO) {
        userMapper.updateByPrimaryKey(toUserMan(userVO));
        return "Update User Data Success!";
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public String deleteUser(UserVO userVO) {
        userMapper.deleteByPrimaryKey(userVO.getId());
        return "Delete User Data Success...";
    }

    private User toUserMan(UserVO userVO) {
        User user = new User();
        user.setId(userVO.getId());
        user.setUsername(userVO.getUsername());
        user.setPassword(userVO.getPassword());
        return user;
    }
}
