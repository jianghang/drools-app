package com.github.entity;

import lombok.Data;

@Data
public class GoodsOrder {

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单金额，单位分
     */
    private long amount;

    /**
     * 返回编码
     */
    private String code = "SUCCESS";

    /**
     * 编码描述
     */
    private String msg;
}
