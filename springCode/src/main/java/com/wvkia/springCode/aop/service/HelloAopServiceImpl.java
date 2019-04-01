package com.wvkia.springCode.aop.service;

/**
 * @author wukai
 * @date 2019/3/21
 */
public class HelloAopServiceImpl implements HelloAopService {
    public void sayHello() {
        System.out.println("hello!");
    }

    public void sayBye() {
        System.out.println("bye");
    }
}
