
package net.wangxj.authority.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import net.wangxj.authority.dto.AuthorityResourcesDTO;
import net.wangxj.authority.web.service.AuthorityResourcesWebService;

@RequestMapping("/resource")
@Controller
public class AuthorityResourcesController{
	
	@Resource
	private AuthorityResourcesWebService authorityResourcesWebService;
	
	@RequestMapping("/")
	public String toResource(){
		return "resource/resource";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public String resourceList(@RequestBody String jsonStr){
		return authorityResourcesWebService.getPageList(jsonStr);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String addResource(AuthorityResourcesDTO resourceDto){
		return authorityResourcesWebService.add(resourceDto);
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public String editRole(AuthorityResourcesDTO resourceDto){
		return authorityResourcesWebService.edit(resourceDto);
	}
	
	@RequestMapping("/deleteBatch")
	@ResponseBody
	public String deleteByBatch(@RequestBody String uuidJson){
		List<String> uuidList = (List<String>)JSONObject.parse(uuidJson);
		return authorityResourcesWebService.deleteBatch(uuidList);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteResource(AuthorityResourcesDTO resourceDto){
		return authorityResourcesWebService.delete(resourceDto);
	}
	
	@RequestMapping("/isRepeat")
	@ResponseBody
	public String isRepeat(AuthorityResourcesDTO resourceDto){
		return authorityResourcesWebService.isRepeatField(resourceDto);
	}
	
}