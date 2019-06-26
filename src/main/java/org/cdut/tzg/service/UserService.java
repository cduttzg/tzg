package org.cdut.tzg.service;

import org.cdut.tzg.model.User;

import java.util.List;

/**
 * @author anlan
 * @date 2019/6/25 8:37
 */
public interface UserService {
    /**
     * 查找所有用户
     */
    List<User> findAll();

    /**
     * 根据姓名查找用户
     */
    User findUserByName(String username);
    int register(Integer id,String schoolNumber,String username,String password,String phoneNumber,String address
            ,String email,Integer isFrozen,Integer totalSold,Integer grade,String avatar,String moneyCode, Integer role);



    /**
     * 根据id查找用户
     */
    User findUserById(Long userId);

    /**
     * 获取所有冻结用户
     */
    List<User> getAllFreezeUsers();
    /**
     * 冻结指定id用户
     */
    int setFreezeUser(String schoolNum);
    /**
     * 根据学号查找用户
     */
    User getUserBySchoolNum(String schoolNum);
}
