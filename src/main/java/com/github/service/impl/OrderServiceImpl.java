package com.github.service.impl;

import com.github.entity.GoodsOrder;
import com.github.service.DroolsService;
import com.github.service.OrderService;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sec
 * @version 1.0
 * @date 2023/2/5
 **/
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private static final String SUCCESS_CODE = "SUCCESS";

    @Resource
    private DroolsService droolsService;

    @Override
    public long getDiscountAmt(GoodsOrder goodsOrder) {
        KieSession kieSession = droolsService.newKieSession();
        try {
            kieSession.insert(goodsOrder);
            kieSession.fireAllRules();
            if (SUCCESS_CODE.equals(goodsOrder.getCode())) {
                return goodsOrder.getAmount();
            } else {
                LOGGER.warn("业务逻辑异常,code={},msg={}", goodsOrder.getCode(), goodsOrder.getMsg());
                throw new RuntimeException("规则处理异常");
            }
        } finally {
            if (kieSession != null) {
                kieSession.dispose();
            }
        }
    }

    @Override
    public String runRule() {
        KieSession kieSession = droolsService.newKieSession();
        try {
            GoodsOrder goodsOrder = new GoodsOrder();
            goodsOrder.setAmount(512);

            kieSession.getAgenda().getAgendaGroup("other-group").setFocus();
            kieSession.getAgenda().getActivationGroup("");

            kieSession.insert(goodsOrder);
            kieSession.fireAllRules();
            if (SUCCESS_CODE.equals(goodsOrder.getCode())) {
                return String.valueOf(goodsOrder.getAmount());
            } else {
                LOGGER.warn("业务逻辑异常,code={},msg={}", goodsOrder.getCode(), goodsOrder.getMsg());
                throw new RuntimeException("规则处理异常");
            }
        } finally {
            if (kieSession != null) {
                kieSession.dispose();
            }
        }
    }
}
