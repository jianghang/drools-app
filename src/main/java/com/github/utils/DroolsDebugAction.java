package com.github.utils;

import org.drools.core.definitions.rule.impl.RuleImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DroolsDebugAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsDebugAction.class);
    public static ThreadLocal<List<String>> LOCAL_LOG_LIST = new ThreadLocal<>();

    public static void info(String content, RuleImpl rule) {
        LOGGER.info("Rule[{}]. And the message is '{}'", rule.getName(), content);
        // 其他操作或日志输出
        LOCAL_LOG_LIST.get().add(content);
    }

    public static void error(String content, RuleImpl rule) {
        LOGGER.info("Rule[{}]. And something error with '{}'", rule.getName(), content);
        // 其他操作或日志输出
    }
}
