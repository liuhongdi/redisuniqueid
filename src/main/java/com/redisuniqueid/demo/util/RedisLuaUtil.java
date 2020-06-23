package com.redisuniqueid.demo.util;

import com.redisuniqueid.demo.constant.ResponseCode;
import com.redisuniqueid.demo.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RedisLuaUtil {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final Logger logger = LogManager.getLogger("bussniesslog");
    /*
    run a lua script
    luaFileName: lua file name,no path
    keyList: list for redis key
    return:lua return value,type is string
    */
    public String runLuaScript(String luaFileName,List<String> keyList) {
        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/"+luaFileName)));
        redisScript.setResultType(String.class);

        String argsone = "none";
        //String result = stringRedisTemplate.execute(redisScript, keyList,argsone);
        String result = "";
        try {
            result = stringRedisTemplate.execute(redisScript, keyList,argsone);
        } catch (Exception e) {
            logger.error("发生异常",e);
            throw new ServiceException(ResponseCode.LUA_ERROR.getMsg());
        }

        return result;
    }
}
