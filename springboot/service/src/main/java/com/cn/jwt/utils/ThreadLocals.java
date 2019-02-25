package com.cn.jwt.utils;

import com.cn.jwt.entity.User;

public class ThreadLocals {
    public static final InheritableThreadLocal<String> resCodeThl = new InheritableThreadLocal();
    public static final ThreadLocal<String> summaryThl = new ThreadLocal();
    public static final ThreadLocal<User> currentUserThl = new ThreadLocal();
    public static final ThreadLocal<String> ipAddrThl = new ThreadLocal();
    public static final ThreadLocal<Boolean> PERMISSION_FILTER = new ThreadLocal();
    public static final ThreadLocal<Exception> CONTEXT_ERROR = new ThreadLocal();
    public static final ThreadLocal<String> LANGUAGE = new ThreadLocal();

    public ThreadLocals() {
    }

    public static String getResCode() {
        return (String)resCodeThl.get();
    }

    public static void setResCode(String resCode) {
        resCodeThl.set(resCode);
    }

    public static String getSumPropertys() {
        return (String)summaryThl.get();
    }

    public static void setSumPropertys(String sumPropertys) {
        summaryThl.set(sumPropertys);
    }

    public static User getCurrentUser() {
        if (currentUserThl.get() == null) {
            User user = new User();
            user.setUserId(0L);
            user.setUsername("visitor");
            user.setNickName("visitor");
            currentUserThl.set(user);
        }

        return (User)currentUserThl.get();
    }

    public static void setCurrentUser(User currentUser) {
        currentUserThl.set(currentUser);
    }

    public static String getIpAddr() {
        return (String)ipAddrThl.get();
    }

    public static void setIpAddr(String ipAddr) {
        ipAddrThl.set(ipAddr);
    }

    public static void setLanguage(String language) {
        LANGUAGE.set(language);
    }

    public static String getLanguage() {
        return (String)LANGUAGE.get();
    }
}
