package com.cn.properties;

import com.cn.properties.utils.PropertiesUtil;
import com.cn.properties.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class PropertiesHander {
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/")
    public String index(HttpServletRequest request, String properties, Model model) {
        String key = "properties";
        String hashkey = "properties";
        return getString(key, hashkey, model);
    }

    @RequestMapping("/pv/{project}/ev/{environment}")
    public String show(@PathVariable("project") String key, @PathVariable("environment") String hashkey, HttpServletRequest request, String properties, Model model) {
        return getString(key, hashkey, model);
    }

    private String getString(@PathVariable("project") String key, @PathVariable("environment") String hashkey, Model model) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        Object result = hashOperations.get(key, hashkey);
        model.addAttribute("str", result);
        model.addAttribute("pv", key);
        model.addAttribute("ev", hashkey);
        return "index";
    }

    @RequestMapping("/pv/{project}/ev/{environment}/add")
    public String add(@PathVariable("project") String key, @PathVariable("environment") String hashkey, HttpServletRequest request, String properties, Model model) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, hashkey, properties);
        model.addAttribute("str", properties);
        model.addAttribute("pv", key);
        model.addAttribute("ev", hashkey);
        return "index";
    }

    @RequestMapping("/pv/{project}/ev/{environment}/show")
    @ResponseBody
    public Object get(@PathVariable("project") String key, @PathVariable("environment") String hashkey) {
        return getObject(key, hashkey);
    }

    @RequestMapping("/show")
    @ResponseBody
    public Object get_simple(HttpServletRequest request) {
        String key = "properties";
        String hashkey = "properties";
        return getObject(key, hashkey);
    }

    private Object getObject(@PathVariable("project") String key, @PathVariable("environment") String hashkey) {
        Result result = new Result();
        HashOperations valueOperations = redisTemplate.opsForHash();
        String ojb = (String) valueOperations.get(key, hashkey);
        Map map = PropertiesUtil.propertiesSeparate("\r\n", "=", ojb);
        result.setCode(200);
        result.setMsg("success");
        result.setData(map);
        return result;
    }
}
