package org.spring.mybatis.controller;

import org.spring.mybatis.domain.User;
import org.spring.mybatis.service.impl.UserService;
import org.spring.mybatis.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    public UserService userService;

    @GetMapping("/getUser")
    public String getUser(@RequestParam int userid) {
        UserVO userVO = new UserVO();
        userVO.setId(userid);
        User user = userService.getUser(userVO);
        return "Username:" + user.getUsername();
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public String updateUser() {
        /*BaseException baseException = new BaseException();*/
        UserVO userVO = new UserVO();
        userVO.setId(1);
        userVO.setUsername("John");
        userVO.setPassword("233333");
        /*try {*/
            userService.updateUser(userVO);
        /*} catch (Exception exception) {
            baseException = new BaseException(321, "UNERROR", "updateERROR", null);
        }*/
       return "Update User Success!";
    }

    @PostMapping(value ="/addUser")
    public String addUser(@RequestParam String username, @RequestParam String password) {
        UserVO userVO = new UserVO();
        userVO.setUsername(username);
        userVO.setPassword(password);
        userService.addUser(userVO);
        return "Add User Data Success!";
    }

    @DeleteMapping(value = "deleteUser")
    public String deleteUser(@RequestParam int userid) {
        UserVO userVO = new UserVO();
        userVO.setId(userid);
        userService.deleteUser(userVO);
        return "Delete User Data Success!";
    }
}
