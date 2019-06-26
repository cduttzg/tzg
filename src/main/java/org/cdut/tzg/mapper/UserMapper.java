package org.cdut.tzg.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
     *插入用户数据
     */
    @Insert("insert into user(id,schoolNumber,username,password,phoneNumber,address,email,isFrozen,totalSold,grade,avatar,moneyCode,role)" +
            " values (#{id},#{schoolNumber},#{username},#{password},#{phoneNumber},#{address},#{email},#{isFrozen},#{totalSold},#{grade},#{avatar},#{moneyCode},#{role})")
    int insert(Integer id,String schoolNumber,String username,String password,String phoneNumber,String address
            ,String email,Integer isFrozen,Integer totalSold,Integer grade,String avatar,String moneyCode, Integer role);
}
