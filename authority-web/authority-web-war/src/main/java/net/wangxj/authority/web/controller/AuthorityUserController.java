//
//package net.wangxj.authority.web.controller;
//
//import java.lang.reflect.Array;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.alibaba.dubbo.remoting.exchange.Request;
//import com.alibaba.fastjson.JSONObject;
//
//import net.wangxj.authority.constant.DataDictionaryConstant;
//import net.wangxj.authority.dto.AuthorityUserDTO;
//import net.wangxj.authority.dto.AuthorityUserRoleRelationDTO;
///**
// * 用户管理
// * @author huoshan
// *
// */
//@RequestMapping("/user")
//@Controller
//public class AuthorityUserController{
//	
//	@Resource
//	private net.wangxj.authority.web.service.AuthorityUserWebService authorityUserWebService;
//	
//	
//	@RequestMapping("/")
//	public String toUser(){
//		return "user/user";
//	}
//	
//	@RequestMapping("/list")
//	@ResponseBody
//	public String userList(@RequestBody String jsonStr){
//		return authorityUserWebService.getPageList(jsonStr);
//	}
//	
//	@RequestMapping("/add")
//	@ResponseBody
//	public String addUser(AuthorityUserDTO authorityUserDto){
//		return authorityUserWebService.add(authorityUserDto);
//	}
//	
//	@RequestMapping("/edit")
//	@ResponseBody
//	public String editUser(AuthorityUserDTO authorityUserDto){
//		return authorityUserWebService.edit(authorityUserDto);
//	}
//	
//	@RequestMapping("/deleteBatch")
//	@ResponseBody
//	public String deleteByBatch(@RequestBody String uuidJson){
//		List<String> uuidList = (List<String>)JSONObject.parse(uuidJson);
//		return authorityUserWebService.deleteBatch(uuidList);
//	}
//	
//	@RequestMapping("/delete")
//	@ResponseBody
//	public String deleteUser(AuthorityUserDTO authorityUserDto){
//		return authorityUserWebService.delete(authorityUserDto);
//	}
//	
//	@RequestMapping("/isRepeat")
//	@ResponseBody
//	public String isRepeatField(AuthorityUserDTO authorityDto){
//		return authorityUserWebService.isRepeatField(authorityDto);
//	}
//	
//	@RequestMapping("/getSelectList")
//	@ResponseBody
//	public String getSelectList(){
//		Map<String, Map<String,Integer>> map = new HashMap<>();
//		map.put("userStatus", DataDictionaryConstant.userStatusKeyValueMap);
//		map.put("userType", DataDictionaryConstant.userTypeKeyValueMap);
//		return JSONObject.toJSONString(map);
//	}
//	
//	@RequestMapping("/grandRole")
//	@ResponseBody
//	public String grandRole(@RequestParam String roleList,@RequestParam String urUserUuid, @RequestParam String platform){
//		return authorityUserWebService.grandRole(urUserUuid,roleList,platform);
//	}
//}