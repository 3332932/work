package com.cn.facade.demo;

public interface Demo {
    Object get();
    Object cacheable(String s);
    Object CacheEvict(String s);
}
