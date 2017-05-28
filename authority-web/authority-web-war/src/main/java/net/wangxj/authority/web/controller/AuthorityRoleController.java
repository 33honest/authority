package net.wangxj.authority.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import net.wangxj.authority.jersey.RequestMethod;
import net.wangxj.authority.web.constant.DataDictionaryConstant;
import net.wangxj.authority.web.dto.AuthorityRoleDTO;

@RequestMapping("/role")
@Controller
public class AuthorityRoleController extends AbstractController<AuthorityRoleDTO>{
	
	private static Logger logger = Logger.getLogger(AuthorityRoleController.class);
	
	@RequestMapping("/")
	public String toRole(){
		return "role/role";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public JSONObject roleList(@RequestBody String jsonStr){
		
		JSONObject jsonObj = JSONObject.parseObject(jsonStr);
		String search = jsonObj.getString("search");
		String order = jsonObj.getString("order");
		Integer limit = jsonObj.getInteger("limit");
		Integer offset = jsonObj.getInteger("offset");
		String sort = jsonObj.getString("sort");
		
		Integer pageNum = offset/limit + 1;
		Integer page =  0;
		Integer count = 0;
		//查询参数
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("search", search);
		paramMap.put("page_number", pageNum);
		paramMap.put("limit", limit);
		paramMap.put("order", order);
		paramMap.put("sort", sort);
		logger.debug("查询参数:--->" + paramMap);
		String pageRes = rest(RequestMethod.GET, "/roles", null, null, paramMap, null);
		logger.debug("查询结果:--->" + pageRes);
		Map<String, Object> resMap = (Map)JSONObject.parse(pageRes);
		List<AuthorityRoleDTO> data = new ArrayList<>();
		if(resMap != null){
			 data = JSONObject.parseArray(JSONObject.toJSONString(resMap.get("data")), AuthorityRoleDTO.class);
			for (AuthorityRoleDTO authorityRoleDTO : data) {
				authorityRoleDTO.setRoleAddByName(getUserName(authorityRoleDTO.getRoleAddBy()));
				authorityRoleDTO.setRoleEditByName(getUserName(authorityRoleDTO.getRoleEditBy()));
				authorityRoleDTO.setRoleStatusName(DataDictionaryConstant.getRoleStatusKey(authorityRoleDTO.getRoleStatus()));
				authorityRoleDTO.setRolePlatformName(getPlatformName(authorityRoleDTO.getRolePlatformUuid()));
			}
		}
	    
		page = count/limit + 1;
		JSONObject json = new JSONObject();
		json.put("rows", data);
		json.put("total", count);
		json.put("page", page);
		
		return json;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String addRole(String role_name , Integer role_status, String role_platform_uuid){
		AuthorityRoleDTO roleDto = new AuthorityRoleDTO();
		roleDto.setRoleName(role_name);
		roleDto.setRoleStatus(role_status);
		roleDto.setRoleAddBy(getUserId());
		//request参数
		logger.debug("增加参数:-->" + roleDto);
		List<String> pathList = new ArrayList<>();
		pathList.add("platforms");
		pathList.add(role_platform_uuid);
		pathList.add("roles");
		
		return rest(RequestMethod.POST, "", pathList, null, null, JSONObject.toJSONString(roleDto));
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public String editRole(String role_name , Integer role_status, String role_platform_uuid,String role_uuid){
		AuthorityRoleDTO roleDto = new AuthorityRoleDTO();
		roleDto.setRoleName(role_name);
		roleDto.setRoleStatus(role_status);
		roleDto.setRoleEditBy(getUserId());
		roleDto.setRolePlatformUuid(role_platform_uuid);
		//request参数
		logger.debug("修改参数:-->" + roleDto);
		List<String> pathList = new ArrayList<>();
		pathList.add("roles");
		pathList.add(role_uuid);
		
		return rest(RequestMethod.PUT, "", pathList, null, null, JSONObject.toJSONString(roleDto));
	}
	
	@RequestMapping("/deleteBatch")
	@ResponseBody
	public String deleteByBatch(@RequestBody List<String> uuidList){
		String uuidStr = "";
		for (String uuid : uuidList) {
			uuidStr += uuid + ",";
		}
		Map<String,Object> queryParam = new HashMap<>();
		queryParam.put("delete_user", getUserId());
		queryParam.put("uuids", uuidStr.substring(0, uuidStr.lastIndexOf(",")));
		logger.debug("调用删除接口参数:" + queryParam);
		return rest(RequestMethod.DELETE, "/roles", null, null, queryParam, null);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteRole(String role_uuid){
		Map<String,Object> queryParam = new HashMap<>();
		queryParam.put("uuids", role_uuid);
		queryParam.put("delete_user", getUserId());
		return rest(RequestMethod.DELETE, "/roles", null, null, queryParam, null);
	}
	
	@RequestMapping("/isRepeat")
	@ResponseBody
	public String isRepeat(String role_name , String role_platform_uuid , String role_uuid){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("role_name", role_name);
		paramMap.put("role_platform_uuid", role_platform_uuid);
		paramMap.put("role_uuid", role_uuid);
		//查询参数
		logger.debug("验证重复查询参数-->" + paramMap);
		List<String> pathList = new ArrayList<>();
		pathList.add("roles");
		pathList.add("repeats");
		String isRepeatResult = rest(RequestMethod.GET, "", pathList, null, paramMap, null);
		logger.debug("验证结果:-->" + isRepeatResult);
		return JSONObject.parseObject(isRepeatResult).getString("is_pass");
        
	}
	
	@RequestMapping("/getStatusList")
	@ResponseBody
	public String getStatusList(){
		Map<String, Map> platformAndRoleStatu = new HashMap<>();
		platformAndRoleStatu.put("platform", getPlatMap());
		platformAndRoleStatu.put("role_status", DataDictionaryConstant.roleStatusKeyValueMap);
		return JSONObject.toJSONString(platformAndRoleStatu);
	}
	
	
}