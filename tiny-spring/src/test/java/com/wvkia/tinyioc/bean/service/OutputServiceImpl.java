package com.wvkia.tinyioc.bean.service;

/**
 * @author wukai
 * @date 2019/3/12
 */
public class OutputServiceImpl implements OutputService {
    @Override
    public void output(String text) {
        System.out.println(text);
    }
}
