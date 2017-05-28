package net.wangxj.authority.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import net.wangxj.authority.jersey.HttpConnectPool;
import net.wangxj.authority.jersey.RequestMethod;
import net.wangxj.authority.web.constant.DataDictionaryConstant;
import net.wangxj.authority.web.dto.AuthorityUserDTO;
import net.wangxj.authority.web.dto.PlatformDTO;

/**
 * @author huoshan
 * created by 2017年5月26日 下午4:37:57
 * 
 */
public class AbstractController<T> {
	
	private static Logger logger = Logger.getLogger(AbstractController.class);
	
	public String BASE_URL = "http://localhost:9000/api";
	
	public Map<String,String> getPlatMap(){
		Map<String, String> platMap = new HashMap<>();
		//参数
	    Map<String,Object> paramMap = new HashMap<>();
	    paramMap.put("search", "");
	    paramMap.put("page_number", 1);
	    paramMap.put("limit", 20);
	    paramMap.put("order", "desc");
	    paramMap.put("sort", "platform_add_time");
	    String platformStr = rest(RequestMethod.GET, "/platforms", null, null, paramMap, null);
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
		String platformInfo = rest(RequestMethod.GET, "", pathList, null, null, null);
		PlatformDTO platformDto = JSONObject.parseObject(platformInfo,PlatformDTO.class);
		return platformDto != null ? platformDto.getPlatformName() : "";
	}
	
	public String getUserName(String userUuid){
		if(userUuid != null && !"".equals(userUuid)){
			List<String> pathList = new ArrayList<>();
			pathList.add("users");
			pathList.add(userUuid);
			String userInfo = rest(RequestMethod.GET, "",pathList,null, null, null);
			AuthorityUserDTO user = JSONObject.parseObject(userInfo, AuthorityUserDTO.class);
			return user != null ? user.getUserLoginName() : "";
		}
		else{
			return "";
		}
	}
	
	public String getUserId(){
		return "de0c7b2480494fda98db82f7a4707649";
	}
	
	public String getPlatformStatus(Integer platformsStatus){
		return DataDictionaryConstant.getPlatformStatusKey(platformsStatus);
	}
	
	public String rest(RequestMethod method , String requestUrl , List<String> pathList, Map<String,Object> headParams, Map<String,Object> queryParams, String requestData){
		
		Client client = HttpConnectPool.newClient();
		WebTarget webTarget = client.target(BASE_URL+requestUrl);
		if(pathList != null){
			for (String path : pathList) {
				webTarget = webTarget.path(path);
			}
			
		}
		//构造查询参数
		if(queryParams != null){
			for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
				  webTarget = webTarget.queryParam(entry.getKey(), entry.getValue());
			}
		}
		
		Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		//构造请求头
		if(headParams != null){
			for(Map.Entry<String, Object> entry : headParams.entrySet()){
				invocationBuilder.header(entry.getKey(), entry.getValue());
			}
		}
		//发起请求
		Response response;
		Entity<String> entity;
		switch (method) {
			case GET:
				response = invocationBuilder.get();
				return response.readEntity(String.class);
			case POST:
				entity = Entity.entity(requestData, MediaType.APPLICATION_JSON);
				response = invocationBuilder.post(entity);
				return response.readEntity(String.class);
			case PUT:
				entity = Entity.entity(requestData, MediaType.APPLICATION_JSON);
				response = invocationBuilder.put(entity);
				return response.readEntity(String.class);
			case DELETE:
				response = invocationBuilder.delete();
				return response.readEntity(String.class);
			default:
				return null;
		}
	}
}
