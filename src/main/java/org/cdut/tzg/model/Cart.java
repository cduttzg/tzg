package org.cdut.tzg.model;

import java.io.Serializable;

/**
 * @author anlan
 * @date 2019/6/25 9:29
 */
public class Cart implements Serializable {
    private Long id;
    private Long buyerId;
    private Long SellerId;
    private Long goodsId;
    private Integer number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getSellerId() {
        return SellerId;
    }

    public void setSellerId(Long sellerId) {
        SellerId = sellerId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", buyerId=" + buyerId +
                ", SellerId=" + SellerId +
                ", goodsId=" + goodsId +
                ", number=" + number +
                '}';
    }
}
