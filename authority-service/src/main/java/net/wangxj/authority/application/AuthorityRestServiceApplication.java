package net.wangxj.authority.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author huoshan
 * created by 2017年6月25日 下午5:39:20
 * 
 */
@ComponentScan("net.wangxj.authority")
@SpringBootApplication
public class AuthorityRestServiceApplication extends SpringBootServletInitializer{

	public static void main(String[] args){
		new AuthorityRestServiceApplication()
				.configure(new SpringApplicationBuilder(AuthorityRestServiceApplication.class))
				.run(args);
	}
}
