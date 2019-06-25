package org.cdut.tzg.controller;

import org.cdut.tzg.model.User;
import org.cdut.tzg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author anlan
 * @date 2019/6/25 8:39
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll(){
        List<User> users = userService.findAll();
        return users;
    }

    @RequestMapping("/find")
    @ResponseBody
    public List<User> find(){
        List<User> users = userService.findAll();
        return users;
    }

}
