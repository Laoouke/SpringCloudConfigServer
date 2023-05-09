package com.itheima.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Value("${foo}")
    String foo;

    @RequestMapping(value = "/foo")
    public String hi() {
        return foo;
    }

}

/*
程序运行结果：
（分别启动config-server和config-client，观察控制台日志，再使用浏览器访问http://localhost:8762/foo）
页面输出：foo version 1
*/

//试试：修改git远程仓库foo的值（1改成2），在不重启config-client情况下刷新页码，能否得到新值？再只重启client端试试。请得出结论。

/*
小结：
1.同config-server本地获取配置一样，项目基本架构类似，不同的是config-client-dev.yml被放到了远程git仓库，而在config-server配置文件中需配置其地址等信息进行拉取。
2.一旦git远程仓库中的配置文件值修改，client不能自动刷新获取，需重新启动服务。
3.项目需要spring-cloud-starter-bus-amqp依赖。
*/
