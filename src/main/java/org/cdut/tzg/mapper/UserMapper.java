package org.cdut.tzg.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cdut.tzg.model.User;

import java.util.List;

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
}
