
package net.wangxj.authority.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@RequestMapping("platform")
@Controller
public class PlatformController{
	
	@Resource
	private net.wangxj.authority.web.service.PlatformWebService platformWebService;
	
	
	@RequestMapping("/")
	public String toPlatform(){
		
		return "platform/platform";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public String platformList(@RequestBody String jsonStr){
		JSONObject jsonObj = JSONObject.parseObject(jsonStr);
		String order = jsonObj.getString("order");
		Integer limit = jsonObj.getInteger("limit");
		Integer offset = jsonObj.getInteger("offset");
		
		return platformWebService.getPlatformList(order,limit,offset);
		
	}
	
}