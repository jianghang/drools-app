package com.github.service;


import com.github.entity.GoodsOrder;

/**
 * @author sec
 * @version 1.0
 * @date 2023/2/5
 **/
public interface OrderService {

    long getDiscountAmt(GoodsOrder goodsOrder);

    String runRule();

    String runDynamicRule();
}
