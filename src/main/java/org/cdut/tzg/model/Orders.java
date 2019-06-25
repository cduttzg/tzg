package org.cdut.tzg.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author anlan
 * @date 2019/6/25 9:32
 */
public class Orders implements Serializable {

    private Long id;
    private Long buyerId;
    private Long SellerId;
    private Long goodsId;
    private Integer nember;
    private Date createdTime;
    private Integer state;

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

    public Integer getNember() {
        return nember;
    }

    public void setNember(Integer nember) {
        this.nember = nember;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", buyerId=" + buyerId +
                ", SellerId=" + SellerId +
                ", goodsId=" + goodsId +
                ", nember=" + nember +
                ", createdTime=" + createdTime +
                ", state=" + state +
                '}';
    }
}
