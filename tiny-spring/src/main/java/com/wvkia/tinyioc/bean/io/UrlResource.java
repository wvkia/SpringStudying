package com.wvkia.tinyioc.bean.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Url资源的一个定义，实现Resource接口，通过Url获取资源
 * @author wukai
 * @date 2019/3/11
 */
public class UrlResource implements Resource {
    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();

        return urlConnection.getInputStream();
    }
}
