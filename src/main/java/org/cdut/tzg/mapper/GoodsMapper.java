package org.cdut.tzg.mapper;

import org.apache.ibatis.annotations.*;
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

    /**
     * 发布求购信息
     */
    @Insert("insert into goods(user_id,type,title,content,price,image,stock,tag) values" +
            " (#{userId},5,#{title},#{content},#{price},#{image},#{stock},5)")
    int publishSeekGood(@Param("userId")Long userId,@Param("tag")Integer tag,@Param("title")String title,@Param("content")String content,@Param("price")Float price,@Param("stock")Integer stock,@Param("image")String image);

    /**
     * 通过good中userid 、tpye、title删除求购信息
     */
    @Delete("delete from goods where user_id=#{userId} and title=#{title} and tag=#{tag}")
    int deleteSeekGood(@Param("userId") Long userId,@Param("tag") Integer tag,@Param("title") String title);

    /**
     * 更新商品库存
     */
    @Update("update goods set stock=#{newNumber} where id=#{goodsId}")
    int updateGoodsStock(@Param("goodsId") Long goodsId, @Param("newNumber") Integer newNumber);

    /**
     * 根据用户名查找求购信息
     */
    @Select("select * from goods where username =#{username} and type=5")
    List<Goods> selectSeekGoodsByUsername(String username);
}
