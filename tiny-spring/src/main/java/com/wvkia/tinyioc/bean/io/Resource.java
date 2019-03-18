package com.wvkia.tinyioc.bean.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * spring内部定位资源的接口，标示一个外部资源
 * 以 Resource 接口为核心发散的几个类，都是用于解决 IOC 容器中内容从哪来的问题，也就是配置文件从哪读取、如何读取
 * @author wukai
 * @date 2019/3/11
 */
public interface Resource {

    //获取资源数据流
    InputStream getInputStream() throws IOException;
}
