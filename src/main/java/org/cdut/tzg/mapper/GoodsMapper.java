package org.cdut.tzg.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cdut.tzg.model.Goods;

import java.util.Date;
import java.util.List;

/**
 * @author anlan
 * @date 2019/6/25 16:12
 */
@Mapper
public interface GoodsMapper {

    /**
     * 通过id查找商品
     */
    @Select("select * from goods where id = #{goodId}")
    Goods findGoodsById(Long goodId);

    /**
     * 获取指定日期上架商品
     */
    @Select("select count(*) from goods where datediff(created_time,#{date}) = 0")
    int getGoodsCount(Date date);

}
