package org.cdut.tzg.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author anlan
 * @date 2019/6/25 9:35
 */
public class Goods implements Serializable {

    private Long id;
    private Long userId;
    private Integer type;
    private String title;
    private String content;
    private Float price;
    private String image;
    private Date createTime;
    private Integer stock;
}
