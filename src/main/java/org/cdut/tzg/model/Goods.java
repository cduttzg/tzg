package org.cdut.tzg.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author anlan
 * @date 2019/6/25 9:35
 */
public class Goods implements Serializable {

    private Long id;//商品id，自增
    private Long userId;//用户id
    private Integer type;//商品类型
    private String title;//商品标题
    private String content;//商品内容描述
    private Float price;//商品价格
    private String image;//商品图片
    private Date createTime;//物品创建时间
    private Integer stock;//库存
    private Integer tag;//求购标签

    public Integer getTag() { return tag; }

    public void setTag(Integer tag) { this.tag = tag; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", userId=" + userId +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", createTime=" + createTime +
                ", stock=" + stock +
                '}';
    }
}
