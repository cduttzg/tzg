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
    int publishSeekGood(Goods good);
    /**
     * 通过good中userid 、tpye、title删除求购信息
     */
    @Delete("delete from goods where user_id=#{userId} and title=#{title} and tag=#{tag}")
    int deleteSeekGood(@Param("userId") Long userId,@Param("tag") Integer tag,@Param("title") String title);

    /**
     * 通过good中userid 、tpye、title查找求购信息是否已经存在
     */
    @Select("select * from goods where user_id=#{userId} and title=#{title} and tag=#{tag}")
    Goods isExitSeekGoods(@Param("userId") Long userId,@Param("tag") Integer tag,@Param("title") String title);
    /**
     * 更新商品库存
     */
    @Update("update goods set stock=#{newNumber} where id=#{goodsId}")
    int updateGoodsStock(@Param("goodsId") Long goodsId, @Param("newNumber") Integer newNumber);

    /**
     * 根据用户名查找求购信息
     */
    @Select("select * from goods where user_id =#{userid} and type=5")
    List<Goods> selectSeekGoodsByUserid(Long userid);

    /**
     * 根据种类信息取得所有相同种类的商品
     */
    @Select("select * from goods where type=#{type}")
    List<Goods> findSameTypeGoodsByType(Integer type);

    /**
     * 根据类型查询n条商品
     */
    @Select("select * from goods where type = #{type} limit #{limit}")
    List<Goods> findGoodsByTypeAndLimit(@Param("type") Integer type, @Param("limit") int limit);

}
