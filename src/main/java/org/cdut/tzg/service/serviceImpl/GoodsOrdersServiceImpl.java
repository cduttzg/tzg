package org.cdut.tzg.service.serviceImpl;

import org.cdut.tzg.mapper.GoodsOrdersMapper;
import org.cdut.tzg.model.GoodsOrders;
import org.cdut.tzg.service.GoodsOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author anlan
 * @date 2019/6/26 9:22
 */
@Service
public class GoodsOrdersServiceImpl implements GoodsOrdersService {

    @Autowired
    private GoodsOrdersMapper goodsOrdersMapper;


    @Override
    public List<GoodsOrders> findTheOrdersDetailById(Long ordersId) {
        return goodsOrdersMapper.findTheOrdersDetailById(ordersId);
    }

    @Override
    public int addGoodsOrders(GoodsOrders goodsOrders) {
        return goodsOrdersMapper.addGoodsOrders(goodsOrders);
    }
}
