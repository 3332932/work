package com.cn.handel;

import com.cn.handel.i.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Object obj = new Object();
        st(obj,(i)->getString(i));
    }


    public static void st(Object obj, BaseTest i){
        i.get(5);
    }

    public static List<String> getString(int i){
        List<String> str = new ArrayList<>();
        str.add("1");
        str.add("2");
        return str;
    }

}
