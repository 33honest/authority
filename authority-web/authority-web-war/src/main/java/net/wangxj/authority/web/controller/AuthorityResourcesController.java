
package net.wangxj.authority.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import  net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityResourcesDTO;
import net.wangxj.authority.web.service.AuthorityResourcesWebService;

@RequestMapping("authorityResources")
@Controller
public class AuthorityResourcesController{
	
	@Resource
	private AuthorityResourcesWebService authorityResourcesWebService;
	
	
	@RequestMapping("/info")
	public String selectInfo(){
		
		Response<AuthorityResourcesDTO> selectInfo = authorityResourcesWebService.selectInfo();
		
		ModelAndView module = new ModelAndView();
		
		module.addObject("test", selectInfo);
		return "test";
	}
	
}