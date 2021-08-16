package com.ea.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ItemVO {
    private Long id;
    private String title;
    private String sellPoint;
    private Long price;
    private Integer num;
    private String barcode;
    private String image;
    private Long cid;
    private Byte status;
    private Date created;
    private Date updated;
}
