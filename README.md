# Authority
---

Authority 是一个集成权限控制与单点登录的系统。


Authority 由以下三部分组成:

* authority-service  提供用户与权限的 restful api 服务
* authority-web      权限管理后台
* authority-plug     权限插件

这里还需要 Cas 单点登录服务器,在这里 [Cas 单点登录服务器](https://github.com/freehuoshan/cas-server)


---

## 准备

环境要求:  

* java8+
* mysql

一些基本参数说明:

* 单点登录服务器 CAS 域名: http://sso.localcodeblog.net  (根据需要配置为你自己的域名)
* 权限管理后台域名: http://authority.localcodeblog.net   (根据需要配置为你自己的域名)


由于 CAS 要求必须使用 https 协议,所以我们需要生成 https 证书:

生成证书详细步骤可以参考这里: [CAS单点登录证书配置](http://freehuoshan.github.io/2016/03/31/CAS%E5%8D%95%E7%82%B9%E7%99%BB%E5%BD%95%E8%AF%81%E4%B9%A6%E9%85%8D%E7%BD%AE/)，该文章中 CAS 单点登录域名为 sso.com, 这里需要修改为我们自己的域名,在这里是: sso.localcodeblog.net

Nginx 配置: 

    server{
        listen 80;
        server_name authority.localcodeblog.net;
        proxy_set_header Host $host;
        proxy_set_header X-Real-Ip $remote_addr;
        proxy_set_header X-Forwarded-For $remote_addr;

        location / {
                proxy_pass      http://authority.localcodeblog.net:8083/;
        }
    }

    server{
        listen 80;
        listen 443 ssl;
        ssl_certificate         /opt/keystore/sso.localcodeblog.net.crt;
        ssl_certificate_key     /opt/keystore/sso.localcodeblog.net.key;
        proxy_set_header Host $host;
        proxy_set_header X-Real-Ip $remote_addr;
        proxy_set_header X-Forwarded-For $remote_addr;

        server_name     sso.localcodeblog.net;

        location / {
                proxy_pass      http://sso.localcodeblog.net:8082/;
        }

    }


在代码根目录下: authority_structure.sql 是权限表结构文件,authority_data.sql 是基本管理后台和一些测试用户权限数据。在 mysql 客户端分别执行这两个文件。

---
 
 
 在以上步骤中我们已经完成了单点登录证书,与 nginx 中为单点登录服务器与管理后台的反向代理配置后,接下来是完成代码中的一些相关配置,然后启动。
 
 
### 单点登录服务器中数据库配置


在 WEB-INF/spring-configuration/applicationContext-db.xml 中:


    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
		<property name="driverClassName" value="com.mysql.jdbc.Driver" />
		<property name="url" value="jdbc:mysql://localhost:3306/authority?useUnicode=true&amp;characterEncoding=utf-8" />
		<property name="username" value="root" />
		<property name="password" value="llhua123" />
		<property name="maxActive" value="30" />
		<property name="maxIdle" value="30" />
		<property name="maxWait" value="10" />
		<property name="testOnBorrow" value="true" />
	</bean>
	
修改为自己的相关配置。

### authority-service 中相关配置

    spring.datasource:
        type: com.alibaba.druid.pool.DruidDataSource
        url: jdbc:mysql://localhost:3306/authority?useUnicode=true&characterEncoding=UTF-8
        username: root
        password: llhua123
        max-active: 30
    
    spring.jersey.type: servlet

    mybatis:
        config-location: classpath:mybatis/sqlMapConfig.xml

    logging:
        #日志路径
        file: /home/huoshan/log/authority.log
        level.root: DEBUG
    
    server:
        port: 9000
        
 在这里只需更需自己需求修改数据库配置和日志路径即可
 
### authority-web 中相关配置
 
    logging:
        file: /home/huoshan/log/authority_web.log
        level.root: DEBUG

    spring.freemarker:
        cache: false
        charset: UTF-8
        enabled: true
        suffix: .ftl

    server:
        port: 8083
 
    authority.service.rest.baseurl: http://localhost:9000/api


    cas.server:
        login.url: https://sso.localcodeblog.net
        logout.url: https://sso.localcodeblog.net/logout

    platform.sign: AUTHORITY
    authority.service.rest.url: http://localhost:9000/api
    spring.security.logout.url: /j_spring_cas_security_logout
    denied.page: /403

    security.basic.enabled: false
 
 
 在这里只需根据自己需求修改单点登录域名与 authority-service 地址和日志存放路径即可。
 
 
### 配置域名
 
在 hosts 文件中配置映射:

    127.0.0.1	sso.localcodeblog.net
    127.0.0.1	authority.localcodeblog.net


---

## 运行

将 [Cas 单点登录服务器](https://github.com/freehuoshan/cas-server) 部署到 Tomcat 中, 将 server.xml 文件中 

    <Context docBase="cas-server" path="/" reloadable="true" source="org.eclipse.jst.jee.server:cas-server"/></Host>
    
    
中 path 修改为 / 根路径。

* 启动 Cas 服务器。
* 启动 authority-service 
* 启动 authority-web

---

## 访问

在浏览器中访问: [http://authority.localcodeblog.net/](http://authority.localcodeblog.net/)
显示结果:

![http://7xire1.com1.z0.glb.clouddn.com/2017-10-18%2014-40-05%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE.png](http://7xire1.com1.z0.glb.clouddn.com/2017-10-18%2014-40-05%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE.png)


默认用户名密码:

1416236046@qq.com    123456


登录后:

![http://7xire1.com1.z0.glb.clouddn.com/2017-10-18%2014-44-50%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE.png](http://7xire1.com1.z0.glb.clouddn.com/2017-10-18%2014-44-50%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE.png)

![http://7xire1.com1.z0.glb.clouddn.com/2017-10-18%2014-44-30%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE.png](http://7xire1.com1.z0.glb.clouddn.com/2017-10-18%2014-44-30%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE.png)

![http://7xire1.com1.z0.glb.clouddn.com/2017-10-18%2014-44-06%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE.png](http://7xire1.com1.z0.glb.clouddn.com/2017-10-18%2014-44-06%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE.png)



---

## Authority 结构图

![http://7xire1.com1.z0.glb.clouddn.com/%E5%9B%BE%E8%A1%A82.png](http://7xire1.com1.z0.glb.clouddn.com/%E5%9B%BE%E8%A1%A82.png)



