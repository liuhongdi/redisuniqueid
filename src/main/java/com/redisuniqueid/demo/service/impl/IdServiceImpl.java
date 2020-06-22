package com.redisuniqueid.demo.service.impl;

import com.redisuniqueid.demo.service.IdService;
import com.redisuniqueid.demo.util.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class IdServiceImpl implements IdService {

    @Resource
    private RedisLuaUtil redisLuaUtil;

    /*
    * 调用lua得到唯一id
    * 返回:唯一的自增id,字符串形式
    * */
    @Override
    public String getId(String idType) {
        List<String> keyList = new ArrayList();
        keyList.add(idType);
        String res = redisLuaUtil.runLuaScript("id.lua",keyList);
        System.out.println("-----res:"+res);
        return res;
    }
}