package com.wvkia.simpleSpring;

import com.wvkia.simpleSpring.model.UserModel;

/**
 * @author wukai
 * @date 2019/4/9
 */
public class SimpleIoCMain {
    public static void main(String[] args) throws Exception {
        String location = SimpleIoC.class.getClassLoader().getResource("simpleIoC.xml").getFile();
        SimpleIoC simpleIoC = new SimpleIoC(location);
        UserModel userModel = (UserModel) simpleIoC.getBean("userModel");
        System.out.println(userModel.getId() + "_" + userModel.getName());
    }
}
