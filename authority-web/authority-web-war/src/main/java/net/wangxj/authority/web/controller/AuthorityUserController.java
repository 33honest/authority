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
import net.wangxj.authority.web.dto.AuthorityUserDTO;
/**
 * 用户管理
 * @author huoshan
 *
 */
@RequestMapping("/user")
@Controller
public class AuthorityUserController extends AbstractController<AuthorityUserDTO>{
	
	private static Logger logger = Logger.getLogger(AuthorityUserController.class);
	
	@RequestMapping("/")
	public String toUser(){
		return "user/user";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public JSONObject userList(@RequestBody String jsonStr){
		JSONObject jsonObj = JSONObject.parseObject(jsonStr);
		String search = jsonObj.getString("search");
		String order = jsonObj.getString("order");
		Integer limit = jsonObj.getInteger("limit");
		Integer offset = jsonObj.getInteger("offset");
		String sort = jsonObj.getString("sort");
		
		Integer pageNum = offset/limit + 1;
		Integer page =  0;
		Integer count = 0;
		//条件查询
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("search", search);
		paramMap.put("page_number", pageNum);
		paramMap.put("limit", limit);
		paramMap.put("order", order);
		paramMap.put("sort", sort);
		logger.debug("分页查询参数:--->" + paramMap);
	    String pageResultStr = rest(RequestMethod.GET, "/users", null, null, paramMap, null);
	    logger.debug("分页查询结果:--->" + pageResultStr);
	    Map resultMap = (Map) JSONObject.parse(pageResultStr);
		List<AuthorityUserDTO> data = JSONObject.parseArray(JSONObject.toJSONString(resultMap.get("data")), AuthorityUserDTO.class);
		for (AuthorityUserDTO authorityUserDTO : data) {
			authorityUserDTO.setUserAddByName(getUserName(authorityUserDTO.getUserAddBy()));
			authorityUserDTO.setUserEditByName(getUserName(authorityUserDTO.getUserEditBy()));
			authorityUserDTO.setUserAddTypeName(DataDictionaryConstant.getUserADDTypeKey(authorityUserDTO.getUserAddType()));
			authorityUserDTO.setUserTypeName(DataDictionaryConstant.getUserTypeKey(authorityUserDTO.getUserType()));
			authorityUserDTO.setUserStatusName(DataDictionaryConstant.getUserStatuKey(authorityUserDTO.getUserStatus()));
		}
			
		page = count/limit + 1;
		JSONObject json = new JSONObject();
		json.put("rows", data);
		json.put("total", resultMap.get("count"));
		json.put("page", page);
		return json;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String addUser(String user_login_name ,String user_email,String user_login_password,
								String user_phone , Integer user_status , Integer user_type){
		AuthorityUserDTO userDto = new AuthorityUserDTO();
		userDto.setUserLoginName(user_login_name);
		userDto.setUserEmail(user_email);
		userDto.setUserLoginPwd(user_login_password);
		userDto.setUserPhone(user_phone);
		userDto.setUserStatus(user_status);
		userDto.setUserType(user_type);
		userDto.setUserAddBy(getUserId());
		userDto.setUserAddType(DataDictionaryConstant.USER_ADDTYPE_INNER_VALUE);
		
		return  rest(RequestMethod.POST, "/users", null, null, null, JSONObject.toJSONString(userDto));
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public String editUser(String user_login_name ,String user_email,String user_login_password,
			String user_phone , Integer user_status , Integer user_type,String user_uuid){
		
		AuthorityUserDTO userDto = new AuthorityUserDTO();
		userDto.setUserLoginName(user_login_name);
		userDto.setUserEmail(user_email);
		userDto.setUserLoginPwd(user_login_password);
		userDto.setUserPhone(user_phone);
		userDto.setUserStatus(user_status);
		userDto.setUserType(user_type);
		userDto.setUserEditBy(getUserId());
		List<String> pathList = new ArrayList<>();
		pathList.add("users");
		pathList.add(user_uuid);
		
		return rest(RequestMethod.PUT, "", pathList, null, null, JSONObject.toJSONString(userDto));
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
		return rest(RequestMethod.DELETE, "/users", null, null, queryParam, null);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteUser(String user_uuid){
		Map<String,Object> queryParam = new HashMap<>();
		queryParam.put("delete_user", getUserId());
		queryParam.put("uuids", user_uuid);
		return rest(RequestMethod.DELETE, "/users", null, null, queryParam, null);
	}
	
	@RequestMapping("/isRepeat")
	@ResponseBody
	public String isRepeatField(String user_uuid , String user_email , String user_phone , String user_login_name){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("user_uuid", user_uuid);
		paramMap.put("user_email", user_email);
		paramMap.put("user_phone", user_phone);
		paramMap.put("user_login_name", user_login_name);
		List<String> pathList = new ArrayList<>();
		pathList.add("users");
		pathList.add("repeats");
		logger.debug("查询重复参数:--->" + paramMap);
		String isRepeatresult = rest(RequestMethod.GET, "", pathList, null, paramMap, null);
		logger.debug("验证结果--->" + isRepeatresult);
		return JSONObject.parseObject(isRepeatresult).getString("is_pass");
	}
	
	@RequestMapping("/getSelectList")
	@ResponseBody
	public String getSelectList(){
		Map<String, Map<String,Integer>> map = new HashMap<>();
		map.put("user_status", DataDictionaryConstant.userStatusKeyValueMap);
		map.put("user_type", DataDictionaryConstant.userTypeKeyValueMap);
		return JSONObject.toJSONString(map);
	}
	
//	@RequestMapping("/grandRole")
//	@ResponseBody
//	public String grandRole(@RequestParam String roleList,@RequestParam String urUserUuid, @RequestParam String platform){
//		return authorityUserWebService.grandRole(urUserUuid,roleList,platform);
//	}
}