package com.github.service.impl;

import com.github.entity.GoodsOrder;
import com.github.service.DroolsService;
import com.github.service.OrderService;
import com.github.utils.DroolsDebugAction;

import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.kie.api.runtime.KieSession;
import org.kie.soup.commons.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            // goodsOrder.setAmount(512);
            // goodsOrder.setName("301");
            goodsOrder.setName(null);
            List<String> personGroupCodeList = new ArrayList<>();
            personGroupCodeList.add("01");
            personGroupCodeList.add("2");
            goodsOrder.setPersonGroupCodeList(personGroupCodeList);
            // goodsOrder.setName("");
            goodsOrder.setRwsj(LocalDateTime.parse("2024-09-26 11:05:58", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toLocalDate());

            goodsOrder.setAge(null);
            goodsOrder.setLxhm("1");

            kieSession.getAgenda().getAgendaGroup("other-group").setFocus();
            kieSession.getAgenda().getActivationGroup("");

            kieSession.insert(goodsOrder);
            // kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("amount-equals-512"));
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

    @Override
    public String runDynamicRule() {
        KieSession kieSession = droolsService.newKieSession();
        try {
            DroolsDebugAction.LOCAL_LOG_LIST.set(new ArrayList<>());
            Map<String, Object> goodsOrder = new HashMap<>();
            // goodsOrder.setAmount(512);
            // goodsOrder.setName("301");
            goodsOrder.put("name", "30xdgdg");
            List<String> personGroupCodeList = new ArrayList<>();
            personGroupCodeList.add("01");
            personGroupCodeList.add("3");
            goodsOrder.put("personGroupCodeList", personGroupCodeList);
            // goodsOrder.setName("");
            goodsOrder.put("rwsj", LocalDateTime.parse("2024-09-26 11:05:58", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toLocalDate());

            goodsOrder.put("age", null);
            goodsOrder.put("lxhm", "1");

            kieSession.getAgenda().getAgendaGroup("other-group-map").setFocus();
            kieSession.getAgenda().getActivationGroup("");

            kieSession.insert(goodsOrder);
            // kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("amount-equals-512"));
            kieSession.fireAllRules();

            System.out.println(DroolsDebugAction.LOCAL_LOG_LIST.get());
        } finally {
            if (kieSession != null) {
                kieSession.dispose();
            }
            DroolsDebugAction.LOCAL_LOG_LIST.remove();
        }
        return null;
    }
}
