package com.wvkia.tinyioc.bean.io;

import java.net.URL;

/**
 * 资源加载器
 * @author wukai
 * @date 2019/3/11
 */
public class ResourceLoader {

    //获取资源的resource接口
    //location是资源定位符
    public Resource getResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }

}
