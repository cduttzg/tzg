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
     * 通过goodsid查找商品name
     */
    @Select("select title from goods where id = #{goodsid}")
    String getGoodsNameById(Long goodsid);


    /**
     * 获取指定日期上架商品
     */
    @Select("select count(*) from goods where datediff(created_time,#{date}) = 0")
    int getGoodsCount(Date date);

    /**
     * 发布求购信息
     */
    @Insert("insert into goods(user_id,type,title,content,price,image,stock,tag) values" +
            " (#{userId},5,#{title},#{content},#{price},#{image},#{stock},#{tag})")
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

    /**
     * 根据用户id获取当前用户的所有商品信息
     */
    @Select("select * from goods where type != 5 and user_id = #{userid}")
    List<Goods> getPutGoods(Long userid);

    /**
     * 添加商品
     */
    @Insert("insert into goods(user_id,type,title,content,price,image,stock,tag) values " +
            "(#{userId},#{type},#{title},#{content},#{price},#{image},#{stock},#{tag})")
    int addGoods(Goods goods);
    /**
     * 修改商品状态(上下架、商品种类、求购)
     */
    @Update("update goods set type = #{state} where id = #{goodsId}")
    int updateTypeState(@Param("goodsId") Long goodsId,@Param("state") Integer state);
    /**
     * 获取指定求购标签的商品数量
     */
    @Select("select count(*) from goods where tag = #{tag}")
    int getGoodsNumByTags(Integer tag);

    /**
     * 根据求购id删除求购
     */
    @Delete("delete from goods where id=#{goodid} and type = 5")
    int delSeekGoodByid(Integer goodid);

    /**
     * 模糊查询商品
     */
    @Select("select * from goods where title like concat('%',#{goodsName},'%') OR content LIKE CONCAT('%',#{goodsName},'%')")
    List<Goods> findGoods(String goodsName);

}
