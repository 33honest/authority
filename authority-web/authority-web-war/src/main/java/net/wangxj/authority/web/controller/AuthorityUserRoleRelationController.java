
package net.wangxj.authority.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import  net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityUserRoleRelationDTO;

@RequestMapping("authorityUserRoleRelation")
@Controller
public class AuthorityUserRoleRelationController{
	
	@Resource
	private net.wangxj.authority.web.service.AuthorityUserRoleRelationWebService authorityUserRoleRelationWebService;
	
	
	@RequestMapping("/info")
	public String selectInfo(){
		
		Response<AuthorityUserRoleRelationDTO> selectInfo = authorityUserRoleRelationWebService.selectInfo();
		
		ModelAndView module = new ModelAndView();
		
		module.addObject("test", selectInfo);
		return "test";
	}
	
}