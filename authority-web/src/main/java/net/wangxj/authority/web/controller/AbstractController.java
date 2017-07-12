package net.wangxj.authority.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;

import net.wangxj.authority.plugin.LoginUserDetails;
import net.wangxj.authority.web.constant.DataDictionaryConstant;
import net.wangxj.authority.web.dto.AuthorityUserDTO;
import net.wangxj.authority.web.dto.PlatformDTO;
import net.wangxj.util.jersey.JerseyClient;
import net.wangxj.util.jersey.RequestMethod;

/**
 * @author huoshan
 * created by 2017年5月26日 下午4:37:57
 * 
 */
@Controller
public class AbstractController<T> {
	
	private static Logger logger = Logger.getLogger(AbstractController.class);
	@Value("${authority.service.rest.baseurl}")
	public String BASE_URL;
	
	public Map<String,String> getPlatMap(){
		Map<String, String> platMap = new HashMap<>();
		//参数
	    Map<String,Object> paramMap = new HashMap<>();
	    paramMap.put("search", "");
	    paramMap.put("page_number", 1);
	    paramMap.put("limit", 20);
	    paramMap.put("order", "desc");
	    paramMap.put("sort", "platform_add_time");
	    String platformStr = JerseyClient.rest(RequestMethod.GET, BASE_URL + "/platforms", null, null, paramMap, null);
	    logger.debug("查询结果:--->" + platformStr);
		@SuppressWarnings("rawtypes")
		Map resultMap = (Map) JSONObject.parse(platformStr);
		if(resultMap != null){
			List<PlatformDTO> data = JSONObject.parseArray(JSONObject.toJSONString(resultMap.get("data")), PlatformDTO.class);
			if(data != null){
				for (PlatformDTO platformDTO : data) {
					platMap.put(platformDTO.getPlatformName(), platformDTO.getPlatformUuid());
				}
			}
		}
		return platMap;
	}
	
	
	public String getPlatformName(String platformUuid){
		List<String> pathList = new ArrayList<>();
		pathList.add("platforms");
		pathList.add(platformUuid);
		String platformInfo = JerseyClient.rest(RequestMethod.GET, BASE_URL + "", pathList, null, null, null);
		PlatformDTO platformDto = JSONObject.parseObject(platformInfo,PlatformDTO.class);
		return platformDto != null ? platformDto.getPlatformName() : "";
	}
	
	public String getUserName(String userUuid){
		if(userUuid != null && !"".equals(userUuid)){
			List<String> pathList = new ArrayList<>();
			pathList.add("users");
			pathList.add(userUuid);
			String userInfo = JerseyClient.rest(RequestMethod.GET, BASE_URL + "",pathList,null, null, null);
			AuthorityUserDTO user = JSONObject.parseObject(userInfo, AuthorityUserDTO.class);
			return user != null ? user.getUserLoginName() : "";
		}
		else{
			return "";
		}
	}
	
	public String getUserId(){
		LoginUserDetails principal = (LoginUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principal.getUser_id();
	}
	
	public String getPlatformStatus(Integer platformsStatus){
		return DataDictionaryConstant.getPlatformStatusKey(platformsStatus);
	}
	
}
