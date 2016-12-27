
package net.wangxj.authority.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import  net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityRoleDTO;
import net.wangxj.authority.web.service.AuthorityRoleWebService;

@RequestMapping("authorityRole")
@Controller
public class AuthorityRoleController{
	
	@Resource
	private AuthorityRoleWebService authorityRoleWebService;
	
	
	@RequestMapping("/info")
	public String selectInfo(){
		
		Response<AuthorityRoleDTO> selectInfo = authorityRoleWebService.selectInfo();
		
		ModelAndView module = new ModelAndView();
		
		module.addObject("test", selectInfo);
		return "test";
	}
	
}