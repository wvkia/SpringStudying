package com.wvkia.tinyioc.demo;

import com.wvkia.tinyioc.HelloWorldService;

/**
 * @author wukai
 * @date 2019/3/14
 */
public class HelloWorldServiceSPImpl implements HelloWorldService {
    @Override
    public void hello() {
        System.out.println("hello world!");
    }
}
