package com.github.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.TypeReference;

import org.stringtemplate.v4.ST;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonToDrlMain {
    public static void main(String[] args) {
        String json = "{\n" +
            "    \"key\": \"rwsj\",\n" +
            "    \"label\": \"任务时间\",\n" +
            "    \"valueType\": \"list-date\",\n" +
            "    \"value\": [\n" +
            "        \"2024-09-26\",\n" +
            "        \"2024-10-09\"\n" +
            "    ],\n" +
            "    \"chooseFlag\": \"1\",\n" +
            "    \"enableContainNull\": \"0\",\n" +
            "    \"type\": \"date-range\"\n" +
            "}";
        System.out.println(json);
        JSONPath.of("$.value[0]");
        Object startRwsj = JSONPath.eval(json, "$.value[0]");
        Object endRwsj = JSONPath.eval(json, "$.value[1]");

        ST st = new ST("(rwsj != null && (!rwsj.isBefore(LocalDate.parse('$startRwsj$'))) && (!rwsj.isAfter(LocalDate.parse('$endRwsj$'))))", '$', '$');
        st.add("startRwsj", startRwsj);
        st.add("endRwsj", endRwsj);
        System.out.println(st.render());

        json = "{\n" +
            "    \"personGroupCode\": \"02\",\n" +
            "    \"personGroupName\": \"资助参保\",\n" +
            "    \"dateType\": \"1\",\n" +
            "    \"date\": \"2024-09-30\",\n" +
            "    \"defaultStartFlag\": \"0\",\n" +
            "    \"rules\": [\n" +
            "        [\n" +
            "            {\n" +
            "                \"key\": \"priority\",\n" +
            "                \"label\": \"动员优先级\",\n" +
            "                \"valueType\": \"number\",\n" +
            "                \"value\": 100,\n" +
            "                \"chooseFlag\": \"0\",\n" +
            "                \"type\": \"default\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\": \"methodCode\",\n" +
            "                \"label\": \"动员方式\",\n" +
            "                \"valueType\": \"string\",\n" +
            "                \"value\": \"1\",\n" +
            "                \"valueBm\": \"DM_MOBILIZE_METHOD_CODE\",\n" +
            "                \"templateKey\": \"template\",\n" +
            "                \"templateLabel\": \"实现方式\",\n" +
            "                \"templateValueType\": \"string\",\n" +
            "                \"templateValue\": \"DXDY001\",\n" +
            "                \"templateValueBm\": \"DM_MOBILIZE_METHOD_TEMPLATE\",\n" +
            "                \"chooseFlag\": \"0\",\n" +
            "                \"type\": \"methodTemplate\"\n" +
            "            }\n" +
            "        ],\n" +
            "        [\n" +
            "            {\n" +
            "                \"key\": \"priority\",\n" +
            "                \"label\": \"动员优先级\",\n" +
            "                \"valueType\": \"number\",\n" +
            "                \"value\": 10,\n" +
            "                \"chooseFlag\": \"0\",\n" +
            "                \"type\": \"number\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\": \"methodCode\",\n" +
            "                \"label\": \"动员方式\",\n" +
            "                \"valueType\": \"string\",\n" +
            "                \"value\": \"1\",\n" +
            "                \"valueBm\": \"DM_MOBILIZE_METHOD_CODE\",\n" +
            "                \"templateKey\": \"template\",\n" +
            "                \"templateLabel\": \"实现方式\",\n" +
            "                \"templateValueType\": \"string\",\n" +
            "                \"templateValue\": \"RGDY001\",\n" +
            "                \"templateValueBm\": \"DM_MOBILIZE_METHOD_TEMPLATE\",\n" +
            "                \"chooseFlag\": \"0\",\n" +
            "                \"type\": \"methodTemplate\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\": \"rwsj\",\n" +
            "                \"label\": \"任务时间\",\n" +
            "                \"valueType\": \"list-date\",\n" +
            "                \"value\": [\n" +
            "                    \"2024-09-21\",\n" +
            "                    \"2024-10-08\"\n" +
            "                ],\n" +
            "                \"chooseFlag\": \"1\",\n" +
            "                \"enableContainNull\": \"0\",\n" +
            "                \"type\": \"date-range\"\n" +
            "            }\n" +
            "        ],\n" +
            "        [\n" +
            "            {\n" +
            "                \"key\": \"priority\",\n" +
            "                \"label\": \"动员优先级\",\n" +
            "                \"valueType\": \"number\",\n" +
            "                \"value\": 20,\n" +
            "                \"chooseFlag\": \"0\",\n" +
            "                \"type\": \"number\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\": \"methodCode\",\n" +
            "                \"label\": \"动员方式\",\n" +
            "                \"valueType\": \"string\",\n" +
            "                \"value\": \"1\",\n" +
            "                \"valueBm\": \"DM_MOBILIZE_METHOD_CODE\",\n" +
            "                \"templateKey\": \"template\",\n" +
            "                \"templateLabel\": \"实现方式\",\n" +
            "                \"templateValueType\": \"string\",\n" +
            "                \"templateValue\": \"RGDY001\",\n" +
            "                \"templateValueBm\": \"DM_MOBILIZE_METHOD_TEMPLATE\",\n" +
            "                \"chooseFlag\": \"0\",\n" +
            "                \"type\": \"methodTemplate\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\": \"rylb\",\n" +
            "                \"label\": \"人员类别\",\n" +
            "                \"valueType\": \"string\",\n" +
            "                \"value\": \"1\",\n" +
            "                \"valueBm\": \"DM_HEALTHCARE_RYLB\",\n" +
            "                \"chooseFlag\": \"1\",\n" +
            "                \"enableContainNull\": \"1\",\n" +
            "                \"type\": \"select\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\": \"zfjg\",\n" +
            "                \"label\": \"未参保原因\",\n" +
            "                \"valueType\": \"list-string\",\n" +
            "                \"value\": [\n" +
            "                    \"1\"\n" +
            "                ],\n" +
            "                \"valueBm\": \"DM_HEALTHCARE_ZFJG\",\n" +
            "                \"chooseFlag\": \"1\",\n" +
            "                \"enableContainNull\": \"0\",\n" +
            "                \"type\": \"select\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\": \"rwsj\",\n" +
            "                \"label\": \"任务时间\",\n" +
            "                \"valueType\": \"list-date\",\n" +
            "                \"value\": [\n" +
            "                    \"2024-09-26\",\n" +
            "                    \"2024-10-09\"\n" +
            "                ],\n" +
            "                \"chooseFlag\": \"1\",\n" +
            "                \"enableContainNull\": \"0\",\n" +
            "                \"type\": \"date-range\"\n" +
            "            }\n" +
            "        ]\n" +
            "    ]\n" +
            "}";
        Object startRwsjList = JSONPath.eval(json, "$..rules[*][*][?(@.key == 'rwsj')]..value");
        if (startRwsjList instanceof JSONArray) {
            JSONArray jsonArray = ((JSONArray) startRwsjList);
            if (jsonArray.isEmpty()) {
                System.out.println("empty");
            } else {
                System.out.println(startRwsjList);
                List<LocalDate> rwsjList = jsonArray.stream().flatMap(array -> ((JSONArray) array).stream()).map(obj -> (String) obj).map(LocalDate::parse).collect(Collectors.toList());
                Optional<LocalDate> minRwsj = rwsjList.stream().min(Comparator.naturalOrder());
                System.out.println(minRwsj.get().atStartOfDay());
                Optional<LocalDate> maxRwsj = rwsjList.stream().max(Comparator.naturalOrder());
                System.out.println(maxRwsj.get().plusDays(1).atStartOfDay());
            }
        }
        Object obj = JSONPath.eval(json, "$.rules");
        // System.out.println(obj);
        ST st1 = new ST("(hjXzqh != null && ($hjXzqh:{v | hjXzqh.startsWith('$v$')}; separator=\" || \"$))", '$', '$');
        st1.add("hjXzqh","1");
        System.out.println(st1.render());

        LocalDate csrq = LocalDate.parse("1992-10-13");
        int year = csrq.getYear();
        int currentYear = LocalDate.now().getYear();
        int age = currentYear - year;
        if (csrq.plusYears(age).isAfter(LocalDate.now())) {
            age = age - 1;
        }
        System.out.println(age);
    }
}
