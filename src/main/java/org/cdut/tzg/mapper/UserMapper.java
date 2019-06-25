package org.cdut.tzg.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cdut.tzg.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author anlan
 * @date 2019/6/25 8:36
 */
@Mapper
public interface UserMapper{
    /**11
     * 查找所有用户
     */
    @Select("select * from user")
     List<User> findAll();

    /**
     * 注册用户
     */
    @Insert("INSERT INTO USER(id,schoolNumber,username,password,phoneNumber,address,email,isForzen,totalSold,grade,avatar,moneyCode,role)" +
            " VALUES(#{id}, #{SchoolNumber},#{username},#{password},#{phoneNumber},#{address},#{email},#{isForzen},#{totalSold},#{grade}" +
            ",#{avatar},#{moneyCode},#{role})")
    int insert(@Param("id")Long id,@Param("schoolNumber") String schoolNumber,@Param("username") String username,
               @Param("password") String password,@Param("phoneNumber") String phoneNumber,
               @Param("address")String address, @Param("email")String email,@Param("isForzen")Integer isForzen,
               @Param("totalSold") Integer totalSold,@Param("grade") Integer grade,
               @Param("avatar")String avatar,@Param("moneyCode") String moneyCode, @Param("role")Integer role );
  /*  @Insert("INSERT INTO USER(id,schoolNumber,username,password,phoneNumber,address,email,isForzen,totalSold,grade,avatar,moneyCode,role)" +
            " VALUES(#{user.id},#{user.schoolNumber},#{user.username},#{user.password},#{user.phoneNumber}," +
            "#{user.address},#{user.email},#{user.isForzen},#{user.totalSold},#{user.grade}" +
            ",#{user.avatar},#{user.moneyCode},#{user.role})")
    int insert(@Param("user")User user);*/
}
