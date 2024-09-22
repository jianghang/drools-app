package com.github.utils;

import org.drools.core.definitions.rule.impl.RuleImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroolsAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsAction.class);

    public static void info(String content, RuleImpl rule) {
        LOGGER.info("Rule[{}] is matched. And the message is '{}'", rule.getName(), content);
        // 其他操作或日志输出
    }

    public static void error(String content, RuleImpl rule) {
        LOGGER.info("Rule[{}]is matched. And something error with '{}'", rule.getName(), content);
        // 其他操作或日志输出
    }
}
