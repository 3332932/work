package com.cn.properties.utils;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class PropertiesUtil {

    public static Map propertiesSeparate(String separate1, String separate2, String str){
        Map<String,String> map = new HashMap<>();
        if (!StringUtils.isEmpty(str)){
            String[] list = str.split(separate1);
            for (String strTemp : list){
                String[] temt = strTemp.split(separate2);
                if (temt.length<1){

                }else if(temt.length<2&&temt.length>0){
                    map.put(temt[0],null);
                }else {
                    map.put(temt[0],temt[1]);
                }

            }
        }

        return map;
    }
}
