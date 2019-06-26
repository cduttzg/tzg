package org.cdut.tzg.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cdut.tzg.model.Orders;

import java.util.Date;
import java.util.List;

/**
 * @author Carhung
 * @date 2019/6/25 16:03
 */
@Mapper
public interface OrdersMapper {

    /**
     * 获取总订单数量
     */
    @Select("select count(*) from orders")
    int getAllOrdersCount();
    /**
     * 获取指定日期订单数量
     */
    @Select("select count(*) from orders where datediff(completed_time,#{date}) = 0")
    int getCompletedOrdersCount(Date date);
}
