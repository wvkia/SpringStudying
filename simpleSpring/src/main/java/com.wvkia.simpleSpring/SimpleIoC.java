package com.wvkia.simpleSpring;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wukai
 * @date 2019/4/9
 */
public class SimpleIoC {
    //IoC容器
    private Map<String, Object> beanMap = new HashMap<String, Object>();

    public SimpleIoC(String file) throws Exception {
        loadBean(file);
    }

    public Object getBean(String beanName) {
        Object bean = beanMap.get(beanName);
        if (null == bean) {
            throw new IllegalArgumentException("can't find bean with name " + beanName);
        }
        return bean;
    }
    private void loadBean(String location) throws Exception {
        //加载xml文件
        InputStream inputStream = new FileInputStream(location);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);

        //获取根节点
        Element root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();

        //遍历bean标签
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                String id = ele.getAttribute("id");
                String className = ele.getAttribute("class");

                //加载beanClass
                Class beanClass = null;
                try {
                    beanClass = Class.forName(className);

                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }

                //
                Object bean = beanClass.newInstance();

                //遍历property标签

                NodeList propertyNodes = ele.getElementsByTagName("property");
                for (int j = 0; j < propertyNodes.getLength(); j++) {
                    Node propertyNode = propertyNodes.item(j);
                    if (propertyNode instanceof Element) {
                        Element propertyEle = (Element) propertyNode;
                        String name = propertyEle.getAttribute("name");
                        String value = propertyEle.getAttribute("value");

                        //反射设置值
                        Field field = bean.getClass().getDeclaredField(name);
                        field.setAccessible(true);

                        if (value != null && value.length() > 0) {
                            field.set(bean, value);
                        }else {
                            String ref = propertyEle.getAttribute("ref");
                            if (null == ref || ref.length() == 0) {
                                throw new IllegalArgumentException("ref config error");
                            }

                            //填充属性
                            field.set(bean, getBean(ref));
                        }

                    }
                }
                //注册bean
                registerBean(id, bean);

            }
        }
    }

    private void registerBean(String id, Object bean) {
        beanMap.put(id, bean);
    }
}
