package net.wangxj.authority.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import net.wangxj.authority.web.constant.DataDictionaryConstant;
import net.wangxj.authority.web.dto.AuthorityResourcesDTO;
import net.wangxj.authority.web.dto.AuthorityRoleDTO;
import net.wangxj.authority.web.dto.AuthorityUserDTO;
import net.wangxj.util.jersey.JerseyClient;
import net.wangxj.util.jersey.RequestMethod;

/**
 * @author huoshan
 * created by 2017年6月2日 上午9:19:12
 * 
 */
@RequestMapping(value = "grant")
@Controller
public class GrantController extends AbstractController<Object>{

	private static Logger logger = Logger.getLogger(GrantController.class);
	
	@RequestMapping("/users")
	public String toGrantUsers(){
		return "grant/users";
	}
	
	@RequestMapping("/roles")
	public String toGrantRoles(){
		return "grant/roles";
	}
	
	/**
	 * 为用户授权角色
	 * @param role_uuids
	 * @param user_uuid
	 * @param platform_uuid
	 * @return
	 */
	@RequestMapping("/toUser")
	@ResponseBody
	public String grantToUser(String role_uuids , String user_uuid ,String platform_uuid){
		if(role_uuids.indexOf(",") > 0){
			role_uuids = role_uuids.substring(0, role_uuids.lastIndexOf(","));
		}
		List<String> pathList = new ArrayList<>();
		pathList.add("users");
		pathList.add(user_uuid);
		pathList.add("roles");
		logger.debug("路径:" + pathList);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("platform_uuid", platform_uuid);
		paramMap.put("add_user", getUserId());
		paramMap.put("role_uuids", role_uuids);
		logger.debug("授权参数:" + paramMap);
		
		return JerseyClient.rest(RequestMethod.PUT,BASE_URL + "", pathList, null, paramMap, "");
	}
	
	//用户与平台列表
	@RequestMapping("/selectList")
	@ResponseBody
	public Map<String, Object> getUsers(){
		Map<String,Object> userAndPlatMap = new HashMap<>();
		List<String> pathList = new ArrayList<String>();
		pathList.add("users");
		pathList.add("list");
		String usersJsonStr = JerseyClient.rest(RequestMethod.GET,BASE_URL + "", pathList, null, null, null);
		List<AuthorityUserDTO> userList = JSONObject.parseArray(usersJsonStr, AuthorityUserDTO.class);
		Map<String,String> userMap = new HashMap<>();
		if(userList != null && userList.size() > 0){
			for(AuthorityUserDTO userDto : userList){
				userMap.put(userDto.getUserLoginName(), userDto.getUserUuid());
			}
		}
		logger.debug("用户列表:" + userMap);
		Map<String, String> platMap = getPlatMap();
		logger.debug("平台列表" + platMap);
		userAndPlatMap.put("user", userMap);
		userAndPlatMap.put("plat", platMap);
		return userAndPlatMap;
	}
	
	
	//授权与未授权列表
	@RequestMapping("/grantRoles")  
	@ResponseBody
	public Map<String, Object> getRoles(String platform_uuid,String user_uuid){
		List<AuthorityRoleDTO> rolesByPlat = getRolesByPlat(platform_uuid);
		List<AuthorityRoleDTO> rolesByUser = getRolesByUser(user_uuid,platform_uuid);
		//未授予用户的角色
		List<AuthorityRoleDTO> unGrantList = new ArrayList<>();
		for (AuthorityRoleDTO platRole : rolesByPlat) {
			if(rolesByUser != null && rolesByUser.size() >0){
				if(!rolesByUser.contains(platRole)){
					unGrantList.add(platRole);
				}
			}else{
				unGrantList = rolesByPlat;
			}
		}
		Map<String,String> grantMap = new HashMap<>();
		Map<String,String> unGrantMap = new HashMap<>();
		if(rolesByUser != null && rolesByUser.size() > 0){
			for(AuthorityRoleDTO grantRoleDto : rolesByUser){
				grantMap.put(grantRoleDto.getRoleName(), grantRoleDto.getRoleUuid());
			}
		}
		if(unGrantList != null && unGrantList.size() > 0){
			for(AuthorityRoleDTO unGrantRoleDto : unGrantList){
				unGrantMap.put(unGrantRoleDto.getRoleName(), unGrantRoleDto.getRoleUuid());
			}
		}
		Map<String,Object> rolesMap = new HashMap<>();
		rolesMap.put("grantRoles", grantMap);
		rolesMap.put("unGrantRoles", unGrantMap);
		return rolesMap;
	}
	
	
	/**
	 * 为角色授予资源
	 * @return
	 */
	@RequestMapping("/toRole")
	@ResponseBody
	public String grantToRole(String role_uuid ,String resource_uuids){
		List<String> resourceList = new ArrayList<>();
		List<String> uuidAndTypeList = new ArrayList<>();
		String resUuidsStr = "";
		if(resource_uuids.indexOf(",") > 0){
			for(String resourceUuid : resource_uuids.split(",")){
				if("".equals(resourceUuid.trim())){
					continue;
				}
				else{
					resourceList.add(resourceUuid);
				}
			}
			for(String resUuid : resourceList){
				uuidAndTypeList.add(resUuid+"-"+DataDictionaryConstant.GRANTTYPE_WRITEREAD_VALUE);
			}
			for(String resUuidTmp : uuidAndTypeList){
				resUuidsStr += resUuidTmp+",";
			}
			resUuidsStr = resUuidsStr.substring(0, resUuidsStr.lastIndexOf(","));
		}
		
		List<String> pathList = new ArrayList<>();
		pathList.add("roles");
		pathList.add(role_uuid);
		pathList.add("resources");
		logger.debug("路径:" + pathList);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("add_user", getUserId());
		paramMap.put("resource_uuids", resUuidsStr);
		logger.debug("授权参数:" + paramMap);
		
		return JerseyClient.rest(RequestMethod.PUT,BASE_URL + "", pathList, null, paramMap, "");
	}
	
	/**
	 * 平台列表
	 * @return
	 */
	@RequestMapping("/platformList")
	@ResponseBody
	public String getPlatformList(){
		return JSONObject.toJSONString(getPlatMap());
	}
	
	@RequestMapping("/roleList")
	@ResponseBody
	public String getRoleList(String platform_uuid){
		List<AuthorityRoleDTO> rolesList = getRolesByPlat(platform_uuid);
		Map<String,String> roleMap = new HashMap<>();
		for (AuthorityRoleDTO authorityRoleDTO : rolesList) {
			roleMap.put(authorityRoleDTO.getRoleName(),authorityRoleDTO.getRoleUuid());
		}
		return JSONObject.toJSONString(roleMap);
	}
	
	/**
	 * 获取授予与未授予资源列表
	 * @param role_uuid
	 * @param platform_uuid
	 * @return
	 */
	@RequestMapping("/grantResources")
	@ResponseBody
	public Map<String, Object> getGrantResourcesAndUnGrantResource(String role_uuid , String platform_uuid){
		//平台下所有资源
		List<AuthorityResourcesDTO> resourcesByPlat = getResourcesByPlat(platform_uuid);
		//资源下所有资源
		List<AuthorityResourcesDTO> resourceByRole = getResourceByRole(role_uuid);
		//未授予该角色的所有资源
		List<AuthorityResourcesDTO> unGrantResourceList = new ArrayList<>();
		if(resourceByRole != null && resourceByRole.size() > 0){
			for (AuthorityResourcesDTO platResource : resourcesByPlat) {
				if(!resourceByRole.contains(platResource)){
					unGrantResourceList.add(platResource);
				}
			}
		}else{
			unGrantResourceList = resourcesByPlat;
		}
		//已授予角色map
		Map<String,String> grantMap = new HashMap<>();
		//未授予角色的map
		Map<String,String> unGrantMap = new HashMap<>();
		if(resourceByRole != null && resourceByRole.size() > 0){
			for(AuthorityResourcesDTO grantResource : resourceByRole){
				grantMap.put(grantResource.getResourceUrl(), grantResource.getResourceUuid());
			}
		}
		
		if(unGrantResourceList != null && unGrantResourceList.size() > 0){
			for(AuthorityResourcesDTO unGrantResource : unGrantResourceList){
				unGrantMap.put(unGrantResource.getResourceUrl(), unGrantResource.getResourceUuid());
			}
		}
		//最后封装
		Map<String,Object> resourceMap = new HashMap<>();
		resourceMap.put("unGrant", unGrantMap);
		resourceMap.put("grant", grantMap);
		logger.debug("角色授权情况:" + resourceMap);
		return resourceMap;
	}
	
	/**
	 * 角色拥有的所有资源
	 * @param role_uuid
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<AuthorityResourcesDTO> getResourceByRole(String role_uuid){
		List<String> pathList = new ArrayList<>();
		pathList.add("roles");
		pathList.add(role_uuid);
		pathList.add("resources");
		String resourcesByRole = JerseyClient.rest(RequestMethod.GET,BASE_URL + "", pathList, null, null, null);
		logger.debug("角色" + role_uuid + "拥有的资源:" + resourcesByRole);
		Map resourceMap = JSONObject.parseObject(resourcesByRole, Map.class);
		String resourcesJsonStr = JSONObject.toJSONString(resourceMap.get("data"));
		
		return JSONObject.parseArray(resourcesJsonStr, AuthorityResourcesDTO.class);
	}
	
	/**
	 * 平台下所有资源
	 * @param platform_uuid
	 * @return
	 */
	public List<AuthorityResourcesDTO> getResourcesByPlat(String platform_uuid){
		List<String> pathList = new ArrayList<>();
		pathList.add("platforms");
		pathList.add(platform_uuid);
		pathList.add("resources");
		logger.debug("查询参数:-->" + pathList);
		String treeResources = JerseyClient.rest(RequestMethod.GET,BASE_URL + "", pathList, null, null, null);
		logger.debug("平台" + platform_uuid + "的资源树:-->" + treeResources);
		AuthorityResourcesDTO topResource = JSONObject.parseObject(treeResources, AuthorityResourcesDTO.class);
		List<AuthorityResourcesDTO> allChildResource = treeToList(topResource);
		return allChildResource;
	}
	
	/**
	 * 资源树转为列表
	 * @param resourceDto
	 * @return
	 */
	public List<AuthorityResourcesDTO> treeToList(AuthorityResourcesDTO resourceDto){
		List<AuthorityResourcesDTO> allList = new ArrayList<>();
		if(resourceDto != null && resourceDto.getChildList() != null && resourceDto.getChildList().size() > 0){
			List<AuthorityResourcesDTO> childList = new ArrayList<>();
			for (AuthorityResourcesDTO childResourceDto : resourceDto.getChildList()) {
				childList.addAll(treeToList(childResourceDto));
			}
			allList.add(resourceDto);
			allList.addAll(childList);
		}else{
			List<AuthorityResourcesDTO> selfList = new ArrayList<>();
			selfList.add(resourceDto);
			return selfList;
		}
		return allList;
	}
	
	/**
	 * 用户拥有的角色
	 * @param user_uuid
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<AuthorityRoleDTO> getRolesByUser(String user_uuid,String platform_uuid){
		List<String> pathListByUser = new ArrayList<>();
		pathListByUser.add("users");
		pathListByUser.add(user_uuid);
		pathListByUser.add(platform_uuid);
		pathListByUser.add("roles");
		logger.debug("用户下所有角色查询参数:" + pathListByUser);
		String userRolesStr = JerseyClient.rest(RequestMethod.GET,BASE_URL + "", pathListByUser, null, null, null);
		logger.debug("用户" + user_uuid + "拥有的角色:" + userRolesStr);
		Map rolesMap = JSONObject.parseObject(userRolesStr, Map.class);
		String rolesListJsonStr = JSONObject.toJSONString(rolesMap.get("data"));
		return JSONObject.parseArray(rolesListJsonStr, AuthorityRoleDTO.class);
	}
	
	/**
	 * 平台下的所有角色列表
	 * @param platform_uuid
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<AuthorityRoleDTO> getRolesByPlat(String platform_uuid){
		List<String> pathListByPlat = new ArrayList<>();
		pathListByPlat.add("platforms");
		pathListByPlat.add(platform_uuid);
		pathListByPlat.add("roles");
		logger.debug("根据平台查询参数路径:" + pathListByPlat);
		String platRolesStr = JerseyClient.rest(RequestMethod.GET,BASE_URL + "", pathListByPlat, null, null, null);
		logger.debug("平台" + platform_uuid + "下的角色:" + platRolesStr);
		Map rolesMap = JSONObject.parseObject(platRolesStr, Map.class);
		String rolesListJsonStr = JSONObject.toJSONString(rolesMap.get("data"));
		return JSONObject.parseArray(rolesListJsonStr, AuthorityRoleDTO.class);
	}
	
	
	
	
}
