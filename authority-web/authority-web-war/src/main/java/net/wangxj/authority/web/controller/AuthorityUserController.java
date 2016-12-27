
package net.wangxj.authority.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import  net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityUserDTO;
import net.wangxj.authority.web.service.AuthorityUserWebService;

@RequestMapping("authorityUser")
@Controller
public class AuthorityUserController{
	
	@Resource
	private AuthorityUserWebService authorityUserWebService;
	
	
	@RequestMapping("/info")
	public ModelAndView selectInfo(){
		
		Response<AuthorityUserDTO> selectInfo = authorityUserWebService.selectInfo();
		
		ModelAndView module = new ModelAndView();
		
		module.addObject("test", selectInfo);
		module.setViewName("test");
		return module;
	}
	
}