package com.wvkia.tinyioc.bean.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * @author wukai
 * @date 2019/3/12
 */
public class ResourceLoaderTest {

    @Test
    public void getResource() throws IOException {
        ResourceLoader resourceLoader = new ResourceLoader();
        Resource resource = resourceLoader.getResource("ioc.xml");
        InputStream inputStream = resource.getInputStream();

        Assert.assertNotNull(inputStream);


        //读取文件
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

    }
}