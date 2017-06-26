package net.wangxj.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("net.wangxj.authority")
@SpringBootApplication
public class AuthorityWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorityWebApplication.class, args);
	}
}
