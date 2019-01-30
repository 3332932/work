package com.cn.jwt.utils;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yshh44
 */
public class WorkUtils {
    public static String order ="ascending";

    public static String toColumn(String str) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);

    }

    public static void startPage(HttpServletRequest request) {
        String current = request.getParameter("current");
        String size = request.getParameter("size");
        if (StringUtils.isAnyEmpty(current, size)) {
            current = "1";
            size = "10";
        }
        PageHelper.startPage(Integer.valueOf(current), Integer.valueOf(size));
    }

    public static void sort(Wrapper wrapper, HttpServletRequest request) {
        sort(wrapper,"create_time",request);
    }
    public static void sort(Wrapper wrapper, String defaultSort, HttpServletRequest request){
        QueryWrapper queryWrapper = (QueryWrapper)wrapper;
        String column = request.getParameter("column");
        String order = request.getParameter("order");
        if (StringUtils.isNoneEmpty(column,order)){
            if (WorkUtils.order.equals(order)){
                queryWrapper.orderByAsc(toColumn(column));
            }else{
                queryWrapper.orderByDesc(toColumn(column));
            }
        }else {
            if(StringUtils.isNotEmpty(defaultSort)){
                queryWrapper.orderByDesc(toColumn(defaultSort));
            }
        }
    }

    /**
     * 返回码 0
     * 返回信息 success
     * @return
     */
    public static Map<String, Object> getSuccessResultMap() {
        return getResultMap("0", "success");
    }

    /**
     * 返回码 -1
     * 返回信息 service error
     * @return
     */
    public static Map<String, Object> getErrorResultMap() {
        return getResultMap("-1", "service error");
    }

    /**
     * 返回码 0
     * 返回信息 success
     * 返回数据  data
     * 返回数据条数 total
     * @return
     */
    public static Map<String, Object> getResultPageDataMap(Object data,Long total) {
        return getResultMap("0", "success",data,total);
    }
    /**
     * 返回码 0
     * 返回信息 success
     * 返回数据  data
     * @return
     */
    public static Map<String, Object> getResultDataMap(Object data) {
        return getResultMap("0", "success",data,null);
    }
    /**
     * 返回码 ？
     * 返回信息 ？
     * 返回数据  data
     * @return
     */
    public static Map<String, Object> getResultMap(String retCode, String retMsg, Object data) {
        return getResultMap(retCode, retMsg, data, null);
    }

    public static Map<String, Object> getResultMap(String retCode, String retMsg) {
        return getResultMap(retCode, retMsg, null, null);
    }

    public static Map<String, Object> getResultMap(String retCode, String retMsg, Object data, Long total) {
        Map<String, Object> map = new HashMap<>(8);
        Map<String, Object> flag = new HashMap<>(8);
        flag.put("retCode", retCode);
        flag.put("retMsg", retMsg);
        map.put("flag", flag);
        if (data != null) {
            map.put("data", data);
        }
        if (total != null) {
            map.put("total", total);
        }
        return map;
    }


    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-real-ip");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
