# authority
权限

一个单点单点登录权限系统.

## authority-plug     权限插件（每个项目只需添加该依赖就会拥有登录与权限控制）

添加 authority-plug 插件还需要在相应项目中添加以下配置

authority.service.rest.baseurl: http://localhost:9000/api

cas.server:
 login.url: https://sso.codeblog.net
 logout.url: https://sso.codeblog.net/logout

platform.sign: AUTHORITY

authority.service.rest.url: http://localhost:9000/api

spring.security.logout.url: /j_spring_cas_security_logout

denied.page: /403

## authority-service  权限 restful api

api:  http://localhost:9000/swagger-ui.html

## authority-web      用户权限管理后台

单点登录还需要有 CAS-server 单点登录服务器. https://github.com/freehuoshan/cas-server


![结构图](https://github.com/freehuoshan/authority/master/image/图标2.png)

