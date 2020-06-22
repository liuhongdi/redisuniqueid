package com.redisuniqueid.demo.controller;

import com.redisuniqueid.demo.service.IdService;
import com.redisuniqueid.demo.util.ServerResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")

public class OrderController {

    @Resource
    private IdService idService;

    /*
    *
    * 返回唯一的自增id
    *
    * */
    @GetMapping("/getid")
    public ServerResponseUtil getToken() {
        String idType="formtoken";
        String res = idService.getId(idType);
        Map<String,String> resMap = new HashMap<String,String>();
        resMap.put("id",res);
        return ServerResponseUtil.success(resMap);
    }
}
