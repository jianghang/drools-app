package com.github.service.impl;

import com.alibaba.fastjson2.JSON;
import com.github.entity.GoodsOrder;

import org.drools.core.util.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DroolsMain {
    public static void main(String[] args) {
        LocalDateTime currentTime = LocalDateTime.parse("2024-09-25 11:05:58", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDate startDate = LocalDate.parse("2024-09-26");
        LocalDate endDate = LocalDate.parse("2024-10-09");
        System.out.println(currentTime.toLocalDate());
        System.out.println(currentTime.toLocalDate().isEqual(currentTime.toLocalDate()));
        boolean isIn = ((!currentTime.toLocalDate().isBefore(startDate)) && (!currentTime.toLocalDate().isAfter(endDate)));
        System.out.println(isIn);

        long days = ChronoUnit.DAYS.between(LocalDate.parse("2024-09-26"), LocalDate.now());
        System.out.println(days);

        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setAge(1);
        goodsOrder.setLxhm("");
        goodsOrder.setName("yyy");
        System.out.println(JSON.toJSONString(goodsOrder));
        Field[] fields = GoodsOrder.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = ReflectionUtils.getField(field, goodsOrder);
            System.out.println(field.getName() + " : " + value);
            if (value instanceof String) {
                String valueStr = (String) value;
                if (StringUtils.isEmpty(valueStr)) {
                    ReflectionUtils.setField(field, goodsOrder, null);
                }
            }
        }
        System.out.println(JSON.toJSONString(goodsOrder));
    }
}
