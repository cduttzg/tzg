package org.cdut.tzg.service.serviceImpl;

import org.cdut.tzg.mapper.UserMapper;
import org.cdut.tzg.model.User;
import org.cdut.tzg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author anlan
 * @date 2019/6/25 8:38
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public int register(Integer id, String schoolNumber, String username, String password, String phoneNumber, String address, String email, Integer isFrozen, Integer totalSold, Integer grade, String avatar, String moneyCode, Integer role) {
        return userMapper.insert(id,schoolNumber,username,password,phoneNumber,address,email,isFrozen,totalSold,grade,avatar,moneyCode,role);
    }

    @Override
    public User findUserById(Long userId) {
        return userMapper.findUserById(userId);
    }
}
