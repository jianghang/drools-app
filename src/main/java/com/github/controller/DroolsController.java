package com.github.controller;


import com.github.service.DroolsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sec
 * @version 1.0
 * @date 2023/2/6
 **/
@RestController
public class DroolsController {

    @Resource
    private DroolsService droolsService;

    @GetMapping("/reload")
    public String reloadRules() {
        droolsService.reloadKieContainer();
        return "SUCCESS";
    }

    @GetMapping("/getRule")
    public Object getRule(int id) {
        return droolsService.getRule(id);
    }
}