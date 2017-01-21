
package net.wangxj.authority.web.controller;

import java.util.List;

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

/**
 * 平台管理
 * @author huoshan
 *
 */
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
		return platformWebService.getPageList(jsonStr);
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
	
	@RequestMapping("/edit")
	@ResponseBody
	public String editPlatform(PlatformDTO platformDto){
		return platformWebService.edit(platformDto);
	}
	
	@RequestMapping("/deleteBatch")
	@ResponseBody
	public String deleteByBatch(@RequestBody String uuidJson){
		List<String> uuidList = (List<String>) JSONObject.parse(uuidJson);
		return platformWebService.deleteBatch(uuidList);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String deletePlatfom(PlatformDTO platformDto){
		return platformWebService.delete(platformDto);
	}
	
	@RequestMapping("/isRepeat")
	@ResponseBody
	public String isRepeatField(PlatformDTO platformDto){
		return platformWebService.isRepeatField(platformDto);
	}
	
}