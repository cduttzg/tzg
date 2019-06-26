package org.cdut.tzg.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
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

    /**
     * 根据填的数据进行用户注册
     * @param id
     * @param schoolNumber
     * @param username
     * @param password
     * @param phoneNumber
     * @param address
     * @param email
     * @param isFrozen
     * @param totalSold
     * @param grade
     * @param avatar
     * @param moneyCode
     * @param role
     * @return
     */
    int register(Integer id,String schoolNumber,String username,String password,String phoneNumber,String address
            ,String email,Integer isFrozen,Integer totalSold,Integer grade,String avatar,String moneyCode, Integer role);

    Long findIdByUserName(String username);
}
