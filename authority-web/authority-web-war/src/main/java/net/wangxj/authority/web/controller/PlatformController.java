
package net.wangxj.authority.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.wangxj.authority.constant.DataDictionaryConstant;
import net.wangxj.authority.dto.PlatformDTO;

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
		return platformWebService.getPlatformList(jsonStr);
	}
	
	@RequestMapping("/getStatusList")
	@ResponseBody
	public String getStatusList(){
		return JSONObject.toJSONString(DataDictionaryConstant.platformStatusKeyValueMap);
		
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String addPlatform(PlatformDTO platformDto){
		return platformWebService.add(platformDto);
	}
	
	@RequestMapping("/isRepeat")
	@ResponseBody
	public String isRepeatField(PlatformDTO platformDto){
		return platformWebService.isRepeatField(platformDto);
	}
	
}