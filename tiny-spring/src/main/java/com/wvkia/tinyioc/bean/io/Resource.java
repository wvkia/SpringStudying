package com.wvkia.tinyioc.bean.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * spring内部定位资源的接口
 * @author wukai
 * @date 2019/3/11
 */
public interface Resource {

    //获取资源数据流
    InputStream getInputStream() throws IOException;
}
