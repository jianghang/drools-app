package com.github.service.impl;

import com.github.entity.Rules;
import com.github.mapper.RulesMapper;
import com.github.service.DroolsService;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author sec
 * @version 1.0
 * @date 2023/2/6
 **/
@Slf4j
@Service("droolsService")
public class DroolsServiceImpl implements DroolsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsServiceImpl.class);

    /**
     * 存储规则的磁盘根路径，可通过SpringBoot的配置文件来配置，以区分不同环境。
     */
//    private static final String RULES_PATH = "D:/Code/IdeaProject/drools-app/src/main/resources/rules";
    private static final String RULES_PATH = "F:/gitlab-project/drools-app/src/main/resources/rules";

    private static final KieServices KIE_SERVICES = KieServices.get();

    private static volatile KieContainer KIE_CONTAINER = null;

    @Autowired
    private RulesMapper rulesMapper;

    @Override
    public KieContainer getKieContainer() {
        if (KIE_CONTAINER == null) {
            synchronized (KieContainer.class) {
                KIE_CONTAINER = initInstance();
            }
        }
        return KIE_CONTAINER;
    }

    private KieContainer initInstance() {
        final KieRepository kieRepository = KIE_SERVICES.getRepository();
        // 创建一个KieModule并添加到KieRepository中
        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
        KieBuilder kieBuilder = KIE_SERVICES.newKieBuilder(initKieFileSystem());
        // 如果构建有错误信息，则抛出异常。异常逻辑可根据具体业务逻辑定制化处理。
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            LOGGER.error("规则构建失败，返回原有KIE_CONTAINER");
            return KIE_CONTAINER;
        }
        kieBuilder.buildAll();
        return KIE_SERVICES.newKieContainer(kieRepository.getDefaultReleaseId());
    }

    @Override
    public synchronized KieContainer reloadKieContainer() {
        KIE_CONTAINER = this.initInstance();
        return KIE_CONTAINER;
    }

    @Override
    public KieSession newKieSession() {
        KieSession kieSession = getKieContainer().newKieSession();
        KieRuntimeLogger consoleLogger = KIE_SERVICES.getLoggers().newConsoleLogger(kieSession);
        return kieSession;
    }

    @Override
    public KieSession newKieSession(String sessionName) {
        return getKieContainer().newKieSession(sessionName);
    }

    @Override
    public String getRule(int id) {
        Rules rules = rulesMapper.selectById(id);
        if (Objects.nonNull(rules)) {
            return rules.getRuleContent();
        }
        return null;
    }

    /**
     * 初始化KieFileSystem
     */
    private KieFileSystem initKieFileSystem() {
       KieFileSystem kieFileSystem = KIE_SERVICES.newKieFileSystem();
       for (File file : getRuleFiles()) {
           kieFileSystem.write(ResourceFactory.newFileResource(file.getAbsolutePath()));
       }
       return kieFileSystem;
//         KieFileSystem kieFileSystem = KIE_SERVICES.newKieFileSystem();
//         List<Rules> rulesList = rulesMapper.selectList(null);
//         for (Rules rule : rulesList) {
//             String drl = rule.getRuleContent();
//             log.info(drl);
// //            kieFileSystem.write(RULES_PATH + "/" + rule.getRuleName() + ".drl", drl);
//             kieFileSystem.write(ResourceFactory.newByteArrayResource(drl.getBytes(StandardCharsets.UTF_8))
//                     .setSourcePath(RULES_PATH + "/" + rule.getRuleName() + ".drl"));
//         }
//         return kieFileSystem;
    }

    /**
     * 获取指定目录下的所有规则文件。
     * PS：此处只是简单实现，正常需递归遍历调用，获取所有子目录、子文件。
     */
    private List<File> getRuleFiles() {
        List<File> fileList = new ArrayList<>();
        File file = new File(RULES_PATH);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null) {
                fileList.addAll(Arrays.asList(files));
            }
        }
        return fileList;
    }


}
