package com.wvkia.tinyioc.bean;

import com.wvkia.tinyioc.BeanReference;
import com.wvkia.tinyioc.bean.AbstractBeanDefinitionReader;
import com.wvkia.tinyioc.bean.BeanDefinition;
import com.wvkia.tinyioc.bean.PropertyValue;
import com.wvkia.tinyioc.bean.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * BeanDefinitionReader的一个实现
 * 从xml文件中读取bean的定义
 * @author wukai
 * @date 2019/3/11
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    //从流从读取bean的定义
    protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {

        //使用Document读取xml文件
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);

        //解析bean
        registeBeanDefinitions(document);

        //关闭流
        inputStream.close();

    }

    public void registeBeanDefinitions(Document doc) {
        //获取xml 根节点
        Element root = doc.getDocumentElement();

        //解析xml
        parseBeanDefinitions(root);
    }

    //解析xml
    protected void parseBeanDefinitions(Element root) {
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                processBeanDefinition(ele);
            }
        }
    }

    /**
     * 处理bean标签
     * @param element
     */
    protected void processBeanDefinition(Element element) {
        //获取bean属性为id的值作为bean的name
        String name = element.getAttribute("id");
        //获取bean属性为class值作为bean的class
        String className = element.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();

        //解析property属性
        processProperty(element, beanDefinition);

        //设置className的同时，在内部设置class
        beanDefinition.setBeanClassName(className);

        //注册类定义，将bean的定义放到map中
        getRegistry().put(name, beanDefinition);
    }

    //解析单个element的属性，然后将beanDefinition补充完整
    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");

                //如果是name属性
                if (value != null && value.length() > 0) {
                    //这个地方就将bean的属性给赋值进去
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                }else {
                    //如果不是name，那么是ref属性
                    String ref = propertyEle.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("configuration problem:<property> element for property '" +
                                name + "' must specify a ref or value");
                    }
                    //将bean属性的ref引用放进去
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }
}
