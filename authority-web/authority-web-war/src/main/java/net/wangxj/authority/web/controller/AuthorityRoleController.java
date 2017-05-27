//
//package net.wangxj.authority.web.controller;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.alibaba.fastjson.JSONObject;
//
//import net.wangxj.authority.constant.DataDictionaryConstant;
//import net.wangxj.authority.dto.AuthorityRoleDTO;
//import net.wangxj.authority.web.service.AuthorityRoleWebService;
//
//@RequestMapping("/role")
//@Controller
//public class AuthorityRoleController{
//	
//	@Resource
//	private AuthorityRoleWebService authorityRoleWebService;
//	
//	@RequestMapping("/")
//	public String toRole(){
//		return "role/role";
//	}
//	
//	@RequestMapping("/list")
//	@ResponseBody
//	public String roleList(@RequestBody String jsonStr){
//		return authorityRoleWebService.getPageList(jsonStr);
//	}
//	
//	@RequestMapping("/add")
//	@ResponseBody
//	public String addRole(AuthorityRoleDTO authorityRoleDto){
//		return authorityRoleWebService.add(authorityRoleDto);
//	}
//	
//	@RequestMapping("/edit")
//	@ResponseBody
//	public String editRole(AuthorityRoleDTO authorityRoleDto){
//		return authorityRoleWebService.edit(authorityRoleDto);
//	}
//	
//	@RequestMapping("/deleteBatch")
//	@ResponseBody
//	public String deleteByBatch(@RequestBody String uuidJson){
//		List<String> uuidList = (List<String>)JSONObject.parse(uuidJson);
//		return authorityRoleWebService.deleteBatch(uuidList);
//	}
//	
//	@RequestMapping("/delete")
//	@ResponseBody
//	public String deleteRole(AuthorityRoleDTO authorityRoleDto){
//		return authorityRoleWebService.delete(authorityRoleDto);
//	}
//	
//	@RequestMapping("/isRepeat")
//	@ResponseBody
//	public String isRepeat(AuthorityRoleDTO roleDto){
//		return authorityRoleWebService.isRepeatField(roleDto);
//	}
//	
//	@RequestMapping("/getStatusList")
//	@ResponseBody
//	public String getStatusList(){
//		return authorityRoleWebService.getPlatformAndRoleStatus();
//	}
//	
//	@RequestMapping("/getListByPlatform")
//	@ResponseBody
//	public String getListByPlatform(AuthorityRoleDTO roleDto){
//		return authorityRoleWebService.getByPlatform(roleDto);
//	}
//	
//	@RequestMapping("/grantResource")
//	@ResponseBody
//	public String grantResource(@RequestParam String roleUuid, @RequestParam String resourceList){
//		return authorityRoleWebService.grantResource(roleUuid,resourceList);
//	}
//	
//	
//	
//	
//}