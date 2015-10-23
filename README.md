
## TinySample的安装运行说明
1. 获取Tiny项目最新资源。TinySample项目依赖Tiny项目的组件，请检查总pom文件里面的tiny_version是正式版本还是快照版本，如果是快照版本需要客户端有tiny项目的源代码，如果是正式版本则用户可以从Maven仓库自动下载。
   <br>
   一般而言，TinySample依赖的是最新的tiny版本，正式版本会比快照版本低一位，假设最新快照版本2.0.25-SNAPSHOT,那么最新正式版本是2.0.24
   <br>
2. 安装时，建议采用maven clean install，直接在Tiny项目根目录运行，Maven会自动安装当前全部工程。
   <br>
   如果提示maven组件找不到，若是tiny组件，请检查tiny_version是不是快照版本，若是可以换成正式版本，或者本地下载tiny项目安装；如果还不行，请检查maven的配置。
	 <br>
3. 选择目标web子工程，在子工程的根目录运行maven jetty:run或者maven tomcat:run ,启动相关的web容器
   <br>TinySample项目是web项目的开发示例，演示tiny组件在web开发中的应用。
   <br>注意web工程和非web工程，容器启动命令只能在web工程运行，非web工程启动会报错。


## 附录：

	├── dslsample目录
	├── dslsample / org.tinygroup.studytiny	// 演示DSL方式实现用户的增删改查的web工程
	├── dts-sample //分布式事务测试用例
	├── org.tinygroup.DemoSite	// 网站构建实例
	├── org.tinygroup.tinysite2	// 
	├── org.tinygroup.tinyuisample	// 演示了tiny的页面文件与布局文件的关系，和渲染机制
	├── quickstart目录
	├── quickstart / quickstart.dslcrudweb	// 教学任务---DSL操作数据库案例
	├── quickstart / quickstart.fouroperate	// 教学任务---示例：四则运算
	├── quickstart / quickstart.studytiny	//  快速入门---十分钟搭建Web应用 studytiny工程
	├── quickstart / quickstart.webhello //  教学任务---Hello案例
	├── template目录
	├── template / quickstart.servlettemplate	// Tiny模板引擎和Servlet整合案例
	├── template / quickstart.springmvctemplate	// Tiny模板引擎和SpringMVC整合案例
	├── template / quickstart.studytemplate	//  Tiny模板引擎语法 指令等学习案例
	├── tiny-dts  //  基于tiny服务的dts测试
	├── tinyonline  //  基于tiny服务的dts测试
	├── top.develop.demo	// develop骨架工程案例，如何开发一个数据库操作的示例
	├── top.j2ee.site	// 
	├── websample目录
	├── websample / org.tinygroup.crud	// DSL操作数据库案例
	├── websample / org.tinygroup.fouroperate // 四则运算
	├── websample / org.tinygroup.helloworld	// Hello案例
	├── websample / org.tinygroup.websample	// 演示weblayer各种开发方式：XML服务、注解服务、流程编排、TinyMVC、SpringMvc等
	├── websample / org.tinygroup.webservicesample	// webService案例集合
	│

   