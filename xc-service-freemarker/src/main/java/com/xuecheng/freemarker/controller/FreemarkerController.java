package com.xuecheng.freemarker.controller;

import com.xuecheng.framework.exception.CustomException;
import com.xuecheng.framework.model.response.CommonCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
@RequestMapping("/freemarker")
@Slf4j
public class FreemarkerController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/banner")
    public String getFreemaker(Map<String, Object> map) {
        log.error("start to freemarker");

        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:31001/cms/config/getModel/5eae90677ab885781a2aecf9", Map.class);
        Map body = forEntity.getBody();
        if (null == body) {
            throw new CustomException(CommonCode.SERVER_ERROR);
        }
        map.putAll(body);
        return "index_banner";
    }
}
