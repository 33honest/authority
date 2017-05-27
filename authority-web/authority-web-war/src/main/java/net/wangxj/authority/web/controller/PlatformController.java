package net.wangxj.authority.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.wangxj.authority.jersey.RequestMethod;
import net.wangxj.authority.web.constant.DataDictionaryConstant;
import net.wangxj.authority.web.dto.PlatformDTO;
import net.wangxj.util.string.StringUtil;

/**
 * 平台管理
 * @author huoshan
 *
 */
@RequestMapping("platform")
@Controller
public class PlatformController extends AbstractController<PlatformDTO>{
	
	private static Logger logger = Logger.getLogger(PlatformController.class);
	
	@RequestMapping("/")
	public String toPlatform(){
		return "platform/platform";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public JSONObject platformList(@RequestBody String jsonStr){
		JSONObject jsonObj = JSONObject.parseObject(jsonStr);
		String search = jsonObj.getString("search");
		String order = jsonObj.getString("order");
		Integer limit = jsonObj.getInteger("limit");
		Integer offset = jsonObj.getInteger("offset");
		String sort = jsonObj.getString("sort");
		
		Integer pageNum = offset/limit + 1;
		Integer page = 0;
		Integer count = 0;
		String jsonString="[]";
		//查询参数
		Map<String,Object> queryParamMap = new HashMap<>();
		queryParamMap.put("search", search);
		queryParamMap.put("page_number", pageNum);
		queryParamMap.put("limit", limit);
		queryParamMap.put("order", order);
		queryParamMap.put("sort", sort);
		//条件查询
		String pageResult = rest(RequestMethod.GET, "/platforms",null, null, queryParamMap, "");
		logger.debug("查询结果:--->" + pageResult);
		Map resultMap = (Map) JSONObject.parse(pageResult);
		List<PlatformDTO> data = JSONObject.parseArray(JSONObject.toJSONString(resultMap.get("data")), PlatformDTO.class);
		List<PlatformDTO> resutData = new ArrayList<>();
		if(data != null){
			for (PlatformDTO platformDTO : data) {
				platformDTO.setPlatformAddByName(getUserName(platformDTO.getPlatformAddBy()));
				platformDTO.setPlatformStatusName(getPlatformStatus(platformDTO.getPlatformStatus()));
			}
		}
		count = (Integer) resultMap.get("count");
		page = count/limit + 1;
		JSONObject json = new JSONObject();
		json.put("rows", data);
		json.put("total", count);
		json.put("page", page);
		
		return json;
	}
	
	@RequestMapping("/getStatusList")
	@ResponseBody
	public String getStatusList(){
		return JSONObject.toJSONString(DataDictionaryConstant.platformStatusKeyValueMap);
		
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String addPlatform(String platform_name , String platform_domain , String platform_sign, Integer platform_status){
		PlatformDTO platform = new PlatformDTO();
		platform.setPlatformName(platform_name);
		platform.setPlatformDomainName(platform_domain);
		platform.setPlatformSign(platform_sign);
		platform.setPlatformStatus(platform_status);
		platform.setPlatformAddBy(getUserId());
		return  rest(RequestMethod.POST, "/platforms", null, null, null, JSONObject.toJSONString(platform));
	}
	
//	@RequestMapping("/edit")
//	@ResponseBody
//	public String editPlatform(PlatformDTO platformDto){
//		platformDto.set
//		return platformWebService.edit(platformDto);
//	}
	
//	@RequestMapping("/deleteBatch")
//	@ResponseBody
//	public String deleteByBatch(@RequestBody String uuidJson){
//		List<String> uuidList = (List<String>) JSONObject.parse(uuidJson);
//		return platformWebService.deleteBatch(uuidList);
//	}
//	
//	@RequestMapping("/delete")
//	@ResponseBody
//	public String deletePlatfom(PlatformDTO platformDto){
//		return platformWebService.delete(platformDto);
//	}
	
	@RequestMapping("/isRepeat")
	@ResponseBody
	public String isRepeatField(String platform_uuid ,String platform_name, String platform_sign, String platform_domain){
		List<String> pathList = new ArrayList<>();
		pathList.add("platforms");
		pathList.add("repeats");
		Map<String,Object> queryParam = new HashMap<>();
		queryParam.put("platform_uuid", platform_uuid);
		queryParam.put("platform_sign", platform_sign);
		queryParam.put("platform_domain", platform_domain);
		queryParam.put("platform_name", platform_name);
		String isRepeatResult = rest(RequestMethod.GET, "" , pathList , null,queryParam , null);
		logger.debug("返回数据: ---> " + isRepeatResult);
		return JSONObject.parseObject(isRepeatResult).getString("is_pass");
	}
	
//	@RequestMapping("/getList")
//	@ResponseBody
//	public String getPlatFormList(){
//		return platformWebService.getPlatList();
//	}
//	
}