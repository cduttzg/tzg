package org.cdut.tzg.service.serviceImpl;

import org.cdut.tzg.mapper.GoodsMapper;
import org.cdut.tzg.model.Goods;
import org.cdut.tzg.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
