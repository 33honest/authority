
package net.wangxj.authority.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import  net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityRoleResourcesRelationDTO;
import net.wangxj.authority.web.service.AuthorityRoleResourcesRelationWebService;

@RequestMapping("authorityRoleResourcesRelation")
@Controller
public class AuthorityRoleResourcesRelationController{
	
	@Resource
	private AuthorityRoleResourcesRelationWebService authorityRoleResourcesRelationWebService;
	
	
	@RequestMapping("/info")
	public String selectInfo(){
		
		Response<AuthorityRoleResourcesRelationDTO> selectInfo = authorityRoleResourcesRelationWebService.selectInfo();
		
		ModelAndView module = new ModelAndView();
		
		module.addObject("test", selectInfo);
		return "test";
	}
	
}