package org.cdut.tzg.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
    @Insert("insert into user(id,schoolNumber,username,password,phoneNumber,address,email,isFrozen,totalSold,grade,avatar,moneyCode,role)" +
            " values (#{id},#{schoolNumber},#{username},#{password},#{phoneNumber},#{address},#{email},#{isFrozen},#{totalSold},#{grade},#{avatar},#{moneyCode},#{role})")
    int insert(Integer id,String schoolNumber,String username,String password,String phoneNumber,String address
            ,String email,Integer isFrozen,Integer totalSold,Integer grade,String avatar,String moneyCode, Integer role);

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

}
