package org.cdut.tzg.service.serviceImpl;

import org.cdut.tzg.mapper.GoodsMapper;
import org.cdut.tzg.model.Goods;
import org.cdut.tzg.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author anlan
 * @date 2019/6/25 16:24
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Goods findGoodsById(Long goodId) {
        return goodsMapper.findGoodsById(goodId);
    }
    @Override
    public int getGoodsCount(Date date){
        return goodsMapper.getGoodsCount(date);
    }

    @Override
    public int publishSeekGood(Long userId, Integer tag, String title, String content, Float price, Integer stock, String image) {
        return goodsMapper.publishSeekGood(userId,tag,title,content,price,stock,image);
    }

    @Override
    public int deleteSeekGood(Long userId, Integer tag, String title) {
        return goodsMapper.deleteSeekGood(userId,tag,title);
    }

    @Override
    public int updateGoodsStock(Long goodsId,Integer newNumber) {
       return goodsMapper.updateGoodsStock(goodsId,newNumber);
    }

    @Override
    public List<Goods> getAllSeekGoodsByUserId(Long userid) {
        return goodsMapper.selectSeekGoodsByUserid(userid);
    }

    @Override
    public List<Goods> findGoodsByTypeAndLimit(Integer type, int limit) {
        return goodsMapper.findGoodsByTypeAndLimit(type,limit);
    }
}
