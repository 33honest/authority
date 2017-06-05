
package net.wangxj.authority.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController{
	
	@RequestMapping("/")
	public String selectInfo(){
		
		return "index";
	}
	
	@RequestMapping("/403")
	public String to403(){
		
		return "error/permission_died";
	}
	
}