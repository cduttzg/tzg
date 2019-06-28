package org.cdut.tzg.service.serviceImpl;

import org.cdut.tzg.mapper.UserMapper;
import org.cdut.tzg.model.Goods;
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
    public int register(User user) {
        int res = 0;
        try {
            res = userMapper.insert(user);
        } catch (Exception e){
            System.out.println(e);
        }
        return res;
    }

    @Override
    public Long findIdByUserName(String username) {
        return userMapper.findUserIdByUserName(username);
    }

    @Override
    public User findUserById(Long userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public List<User> getAllFreezeUsers(){
        return userMapper.getAllFreezeUsers();
    }

    @Override
    public int setFreezeUser(String schoolNum){
        return userMapper.setFreezeUser(schoolNum);
    }

    @Override
    public User getUserBySchoolNum(String schoolNum){
        return userMapper.getUserBySchoolNum(schoolNum);
    }

    @Override
    public String findPhoneByUsername(String username) {
        return userMapper.getUserPhoneByUsername(username);
    }

    @Override
    public int setAdministrator(String schoolNum){
        return userMapper.setAdministrator(schoolNum);
    }

    @Override
    public int deletAdministrator(String schoolNum){
        return userMapper.deletAdministrator(schoolNum);
    }

    @Override
    public List<User> getAllAdministrator(){
        return userMapper.getAllAdministrator();
    }

    @Override
    public int updateUserInformation(String username,String phoneNum,String email,String address,String avatar,String moneyCode){
        return userMapper.updateUserInformation(username,phoneNum,email,address,avatar,moneyCode);
    }

    @Override
    public String getUserNameById(Long userid) {
        return userMapper.getUserNameById(userid);
    }
}
