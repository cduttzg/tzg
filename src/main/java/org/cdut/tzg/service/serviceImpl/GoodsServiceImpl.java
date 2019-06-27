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
    public int publishSeekGood(Goods good) {

        return goodsMapper.publishSeekGood(good);
    }

    @Override
    public int deleteSeekGood(Long userId, Integer tag, String title) {
        return goodsMapper.deleteSeekGood(userId,tag,title);
    }

    @Override
    public Goods isExitSeekGoods(Long userId, Integer tag, String title) {
        return goodsMapper.isExitSeekGoods(userId,tag,title);
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
    public List<Goods> findSameTypeGoodsByType(Integer type) {
        return goodsMapper.findSameTypeGoodsByType(type);
    }

    @Override
    public List<Goods> findGoodsByTypeAndLimit(Integer type, int limit) {
        return goodsMapper.findGoodsByTypeAndLimit(type,limit);
    }

    @Override
    public List<Goods> getPutGoods(Long userid) {
        return goodsMapper.getPutGoods(userid);
    }

    @Override
    public String getGoodsNameById(Long goodsid) {
        return goodsMapper.getGoodsNameById(goodsid);
    }

    @Override
    public int addGoods(Goods goods) {
        return goodsMapper.addGoods(goods);
    }

    @Override
    public int updateTypeState(Long goodsId, Integer state) {
        return goodsMapper.updateTypeState(goodsId,state);
    }
}
