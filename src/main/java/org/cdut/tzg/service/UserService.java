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
     * 注册用户
     */
    String isregister(Long id, String schoolNumber, String username, String password, String phoneNumber,
                      String address, String email,Integer isForzen, Integer totalSold, Integer grade,
                      String avatar, String moneyCode, Integer role);
}
