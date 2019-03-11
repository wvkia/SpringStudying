SpringMVC被设计为围绕DispatchServlet，DispatchServlet用来分发request、配置url映射、视图解析、并且支持上传文件
默认配置基于@Controller和@RequestMapping注解，在Spring3.0允许在使用@Controller和@PathVariable构建RESTFul应用

Spring视图解析：一个Controller通常准备一个modle map对应视图和数据，也可以直接写入数据response 流。

#### The DispatcherServlet
SpringMVC和其他MVC框架一样，基于Request驱动，围绕着一个中央servlet，用于分发请求到控制器。

![](mvc.png)

DispatcherServlet是一个实际的Servlet，继承至HttpServlet，你需要通过URL mapping映射请求到dispatcherServlet
```
public class MyWebapplicationInitialer implements WebApplicationInitializer{
    
    @Override
    public void onStartup(ServletContext container) {
        ServletRegistration.Dynamic registration = container.addServlet("example", new DispatcherServlet());
        registration.setLoadOnStartup(1);
        registration.addMapping("/example/*");
    }
}
```

以上表示所有以```/example``开始的request请求都会由名为 example 的dispatcherServlet分发处理。

如果使用web.xml可以通过
```
<web-app>
	<servlet>
		<servlet-name>example</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<load-on-startup>1</load-on-startup>    //表示启动优先级
	</servlet>

	<servlet-mapping>
		<servlet-name>example</servlet-name>
		<url-pattern>/example/*</url-pattern>
	</servlet-mapping>

</web-app>
```

