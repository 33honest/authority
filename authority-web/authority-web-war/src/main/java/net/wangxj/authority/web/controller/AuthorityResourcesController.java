
package net.wangxj.authority.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import net.wangxj.authority.jersey.RequestMethod;
import net.wangxj.authority.web.constant.DataDictionaryConstant;
import net.wangxj.authority.web.dto.AuthorityResourcesDTO;


@RequestMapping("/resource")
@Controller
public class AuthorityResourcesController extends AbstractController<AuthorityResourcesDTO>{
	
	private static Logger logger = Logger.getLogger(AuthorityResourcesController.class);
	
	@RequestMapping("/")
	public String toResource(){
		return "resource/resource";
	}
	
	@RequestMapping("/toTree")
	public String toTree(HttpServletRequest request , Model model){
		model.addAttribute("platform_uuid", request.getParameter("platform_uuid"));
		return "resource/tree";
	}
	
	@RequestMapping("/tree")
	@ResponseBody
	public String resourceList(String platform_uuid){
		
		List<String> pathList = new ArrayList<>();
		pathList.add("platforms");
		pathList.add(platform_uuid);
		pathList.add("resources");
		String treeJson = rest(RequestMethod.GET, "", pathList, null, null, null);
		logger.debug("资源树:-->" + treeJson);
		Map jsonMap = (Map) JSONObject.parse(treeJson);
		AuthorityResourcesDTO resourceDto = JSONObject.parseObject(treeJson, AuthorityResourcesDTO.class);
		String jsonString = "{}";
		if(resourceDto != null){
			jsonString = JSONObject.toJSONString(toJsonTree(resourceDto), true);
		}
		return jsonString;
	}
	
	public Map<String, Object> toJsonTree(AuthorityResourcesDTO resourceDto){
		Map<String,Object> resMap = new HashMap<>();
			if(resourceDto.getChildList() != null && resourceDto.getChildList().size() > 0){
				List<Map<String,Object>> childList = new ArrayList<>(); 
				for (AuthorityResourcesDTO childDto : resourceDto.getChildList()) {
					childList.add(toJsonTree(childDto));
				}
				
				resMap.put("name", resourceDto.getResourceUrl());
				resMap.put("children", childList);
				resMap.put("uuid", resourceDto.getResourceUuid());
				resMap.put("level", resourceDto.getResourceLevel());
				resMap.put("order", resourceDto.getResourceOrder());
			}
			else{
				Map<String,Object> namesizeMap = new HashMap<>();
				namesizeMap.put("name", resourceDto.getResourceUrl());
			    namesizeMap.put("size", resourceDto.getResourceUrl() != null ? resourceDto.getResourceUrl().length() : 0);
			    namesizeMap.put("uuid", resourceDto.getResourceUuid());
			    namesizeMap.put("level", resourceDto.getResourceLevel());
			    namesizeMap.put("order", resourceDto.getResourceOrder());
			    return namesizeMap;
			}
		return resMap;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String addResource(Integer order , Integer level , String parent_uuid,
								String url,String platform_uuid){
		AuthorityResourcesDTO resourceDto = new AuthorityResourcesDTO();
		resourceDto.setResourcePlatformUuid(platform_uuid);
		resourceDto.setResourceStatus(DataDictionaryConstant.RESOURCE_STATUS_ACTIVE_VALUE);
		resourceDto.setResourceUrl(url);
		resourceDto.setResourceLevel(level);
		resourceDto.setResourceOrder(order);
		resourceDto.setResourceParentUuid(parent_uuid);
		resourceDto.setResourceAddBy(getUserId());
		logger.debug("添加资源:-->" + resourceDto);
		List<String> pathList = new ArrayList<>();
		pathList.add("platforms");
		pathList.add(platform_uuid);
		pathList.add("resources");
		return rest(RequestMethod.POST, "", pathList, null, null, JSONObject.toJSONString(resourceDto));
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String editRole(Integer order , Integer level , String parent_uuid,
			String url,String uuid,String platform_uuid){
		AuthorityResourcesDTO resourceDto = new AuthorityResourcesDTO();
		resourceDto.setResourcePlatformUuid(platform_uuid);
		resourceDto.setResourceStatus(DataDictionaryConstant.RESOURCE_STATUS_ACTIVE_VALUE);
		resourceDto.setResourceUrl(url);
		resourceDto.setResourceLevel(level);
		resourceDto.setResourceOrder(order);
		resourceDto.setResourceParentUuid(parent_uuid);
		resourceDto.setResourceEditBy(getUserId());
		logger.debug("修改资源:-->" + resourceDto);
		List<String> pathList = new ArrayList<>();
		pathList.add("resources");
		pathList.add(uuid);
		return rest(RequestMethod.PUT, "", pathList, null, null, JSONObject.toJSONString(resourceDto));
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteResource(@RequestParam(value = "uuids[]") String[] uuids){
		List<String> resourceUuids = Arrays.asList(uuids);
		String uuidStr = "";
		for (String resUuid : resourceUuids) {
			uuidStr += resUuid + ",";
		}
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("delete_user", getUserId());
		paramMap.put("uuids", uuidStr.substring(0, uuidStr.lastIndexOf(",")));
		logger.debug("删除参数:" + paramMap);
		return rest(RequestMethod.DELETE, "/resources", null, null, paramMap, null);
	}
	
	@RequestMapping("/isRepeat")
	@ResponseBody
	public String isRepeat(String value, String uuid , String platform_uuid){
		List<String> pathList = new ArrayList<>();
		pathList.add("resources");
		pathList.add("repeats");
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("resource_uuid", uuid);
		paramMap.put("resource_url", value);
		paramMap.put("resource_platform_uuid", platform_uuid);
		logger.debug("查询参数:" + paramMap);
		return rest(RequestMethod.GET, "", pathList, null,paramMap, null);
	}
	
	@RequestMapping("/platforms")
	@ResponseBody
	public String platforms(){
		return JSONObject.toJSONString(getPlatMap());
	}

	
}