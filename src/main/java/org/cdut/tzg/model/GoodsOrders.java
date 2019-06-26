package org.cdut.tzg.model;

import java.io.Serializable;

/**
 * @author anlan
 * @date 2019/6/26 9:18
 */
public class GoodsOrders implements Serializable {
    private Long id;
    private Long ordersId;
    private Long goodsId;
    private Long sellerId;
    private Integer number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "GoodsOrders{" +
                "id=" + id +
                ", ordersId=" + ordersId +
                ", goodsId=" + goodsId +
                ", sellerId=" + sellerId +
                ", number=" + number +
                '}';
    }
}
