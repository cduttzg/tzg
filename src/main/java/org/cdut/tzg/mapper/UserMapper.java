package org.cdut.tzg.mapper;

import org.apache.ibatis.annotations.*;
import org.cdut.tzg.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author anlan
 * @date 2019/6/25 8:36
 */
@Mapper
public interface UserMapper {

    /**
     * 查找所有用户
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 根据姓名查找用户
     */
    @Select("select * from user where username = #{username}")
    User findUserByName(String username);

    /**
     * 根据用户名查找用户id
     * @param username
     * @return
     */
    @Select("select id from user where username = #{username}")
    Long findUserIdByUserName(String username);

    /**
     *插入用户数据
     */
    @Insert("insert into user(school_number,username,password,phone_number,address,email,isFrozen,totalSold,grade,avatar,moneyCode,role)" +
            " values (schoolNumber,username,password,phoneNumber,address,email,isFrozen,totalSold,grade,avatar,moneyCode,role)")
    int insert(User user);

    /**
     * 根据id查找用户
     */
    @Select("select * from user where id = #{userId}")
    User findUserById(Long userId);

    /**
     * 获取冻结用户
     */
    @Select("select username,school_number,phone_number,role from user where is_frozen = 1")
    List<User> getAllFreezeUsers();

    /**
     * 冻结指定id用户
     */
    @Update("update user set is_frozen = 1 where school_number = #{schoolNum}")
    int setFreezeUser(String schoolNum);

    /**
     * 根据学号查找用户
     */
    @Select("select * from user where school_number = #{schoolNum}")
    User getUserBySchoolNum(String schoolNum);

    /**
     * 根据用户名查找用户电话
     */
    @Select("select phone_number from user where username = #{username}")
    String getUserPhoneByUsername(String username);
    /**
     * 根据学号增加管理员
     */
    @Update("update user set role = 1 where school_number = #{schoolNum}")
    int setAdministrator(String schoolNum);

    /**
     * 根据学号删除管理员
     */
    @Update("update user set role = 0 where school_number = #{schoolNum}")
    int deletAdministrator(String schoolNum);

    /**
     * 获取所有管理员
     */
    @Select("select username,phone_number from user where role = 1 order by school_number")
    List<User> getAllAdministrator();

    /**
     * 更新用户信息
     */
    @Update("update user set phone_number = #{phoneNum}," +
            "email = #{email}," +
            "address = #{address}," +
            "avatar = #{avatar}," +
            "money_code = #{moneyCode}" +
            "where username = #{username}")
    int updateUserInformation(@Param("username") String username,
                              @Param("phoneNum") String phoneNum,
                              @Param("email") String email,
                              @Param("address") String address,
                              @Param("avatar") String avatar,
                              @Param("moneyCode") String moneyCode);

    /**
     * 根据用户id查找用户姓名
     */
    @Select("select username from user where id = #{userid}")
    String getUserNameById(@Param("userid") Long userid);

}
