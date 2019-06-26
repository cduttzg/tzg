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
    private Integer number;
    private Date createdTime;
    private Date completedTime;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(Date completedTime) {
        this.completedTime = completedTime;
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
                ", number=" + number +
                ", createdTime=" + createdTime +
                ", completedTime=" + completedTime +
                ", state=" + state +
                '}';
    }
}
