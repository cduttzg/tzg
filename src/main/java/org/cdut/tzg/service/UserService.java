package org.cdut.tzg.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.cdut.tzg.model.Goods;
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
     */
    int register(User user);



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

    /**
     *根据用户名查找id
     */
    Long findIdByUserName(String username);

    /**
     * 根据用户名找用户电话
     */
    String findPhoneByUsername(String username);
    /**
     * 根据学号增加管理员
     */
    int setAdministrator(String schoolNum);
    /**
     * 根据学号删除管理员
     */
    int deletAdministrator(String schoolNum);
}
