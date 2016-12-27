
package net.wangxj.authority.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import  net.wangxj.authority.Response;
import net.wangxj.authority.dto.PlatformDTO;
import net.wangxj.authority.web.service.PlatformWebService;

@RequestMapping("platform")
@Controller
public class PlatformController{
	
	@Resource
	private PlatformWebService platformWebService;
	
	
	@RequestMapping("/info")
	public String selectInfo(){
		
		Response<PlatformDTO> selectInfo = platformWebService.selectInfo();
		
		ModelAndView module = new ModelAndView();
		
		module.addObject("test", selectInfo);
		return "test";
	}
	
}