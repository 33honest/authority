
package net.wangxj.authority.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.wangxj.authority.Response;
import net.wangxj.authority.constant.DataDictionaryConstant;
import net.wangxj.authority.dto.AuthorityRoleDTO;
import net.wangxj.authority.dto.PlatformDTO;
import net.wangxj.authority.service.share.AuthorityRoleShareService;
import net.wangxj.authority.service.share.PlatformShareService;
import net.wangxj.authority.web.service.AuthorityRoleWebService;
import net.wangxj.util.validate.Severity.Error;

@Service(value="AuthorityRoleWebService")
public class AuthorityRoleWebServiceImpl implements AuthorityRoleWebService{
	
	private static Logger logger = Logger.getLogger(AuthorityRoleWebServiceImpl.class);
	
	@Resource
	private AuthorityRoleShareService authorityRoleShareService;
	@Resource
	private PlatformShareService platformShareService;

	@Override
	public String getPageList(String jsonStr) {
		
		JSONObject jsonObj = JSONObject.parseObject(jsonStr);
		String order = jsonObj.getString("order");
		Integer limit = jsonObj.getInteger("limit");
		Integer offset = jsonObj.getInteger("offset");
		String sort = jsonObj.getString("sort");
		
		AuthorityRoleDTO roleDto = new AuthorityRoleDTO();
		Integer pageNum = offset/limit + 1;
		Integer page =  0;
		Integer count = 0;
		String jsonString = "[]";
		//条件查询
		Response<AuthorityRoleDTO> pageListResponse = authorityRoleShareService.queryPageListByCondition(roleDto, pageNum, limit, order, sort);
		Response<Integer> countResponse = authorityRoleShareService.getCountByCondition(roleDto, true);
		logger.debug(pageListResponse.getMessage());
		if(pageListResponse.getCode() == 0L && countResponse.getCode() == 0L){
			List<AuthorityRoleDTO> data = pageListResponse.getData();
			count = countResponse.getResObject();
			page = count/limit + 1;
			JSONObject json = new JSONObject();
			json.put("rows", data);
			json.put("total", count);
			json.put("page", page);
			jsonString = JSON.toJSONString(json);
		}
		return jsonString;
	}

	@Override
	public String add(AuthorityRoleDTO dto) {
		dto.setRoleAddBy(getUserId());
		Response<Integer> addRespo = authorityRoleShareService.add(dto);
		String message = addRespo.getMessage();
		Long code = addRespo.getCode();
		logger.debug(message);
		boolean isError= message.indexOf(Error.class.getSimpleName())>=0;
		if((code == 1L && isError) || code == -1L){
			addRespo.setCode(-1L);
			addRespo.setMessage("增加失败");
			addRespo.setData(null);
			return JSON.toJSONString(addRespo);
		}
		return JSON.toJSONString(addRespo);
	}

	@Override
	public String isRepeatField(AuthorityRoleDTO dto) {
		//待校验roleuuid
		String roleUuid = dto.getRoleUuid();
		dto.setRoleUuid(null);
		//与正在修改的role是同一个，则不重复
		AuthorityRoleDTO validate = new AuthorityRoleDTO();
		validate.setRoleUuid(roleUuid);
		Response<AuthorityRoleDTO> queryConditionResp = authorityRoleShareService.queryListByCondition(dto);
		if(queryConditionResp.getCode() == 0L){
			if(queryConditionResp.getData().size() == 0){//未查询到匹配角色
				return "false";
			}//条件查询只有一个而且该角色正是正在修改的role则返回false
			else if(queryConditionResp.getData().size() == 1 && queryConditionResp.getData().contains(validate)){
				return "false";
			}
			else{
				return "true";
			}
		}
		return null;
	}

	@Override
	public String edit(AuthorityRoleDTO dto) {
		dto.setRoleEditBy(getUserId());
		Response<Integer> editRes = authorityRoleShareService.modifyByUuid(dto);
		String message = editRes.getMessage();
		Long code = editRes.getCode();
		logger.debug(message);
		boolean isError = message.indexOf(Error.class.getSimpleName())>=0;
		if((code == 1L && isError) || code == -1L){
			editRes.setCode(-1L);
			editRes.setMessage("编辑失败");
			editRes.setData(null);
			return JSON.toJSONString(editRes);
		}
		return JSON.toJSONString(editRes);
	}

	@Override
	public String delete(AuthorityRoleDTO dto) {
		dto.setRoleDelBy(getUserId());
		Response<Integer> deleteRes = authorityRoleShareService.deleteByUuid(dto);
		String message = deleteRes.getMessage();
		Long code = deleteRes.getCode();
		logger.debug(message);
		boolean isError = message.indexOf(Error.class.getSimpleName())>=0;
		if((code == 1L && isError) || code == -1L){
			deleteRes.setCode(-1L);
			deleteRes.setMessage("删除失败");
			deleteRes.setData(null);
			return JSON.toJSONString(deleteRes);
		}
		return JSON.toJSONString(deleteRes);
	}

	@Override
	public String deleteBatch(List<String> uuidList) {
		List<AuthorityRoleDTO> roleList = new ArrayList<>();
		uuidList.forEach(uuid -> {
			AuthorityRoleDTO roleDto = new AuthorityRoleDTO();
			roleDto.setRoleUuid(uuid);
			roleDto.setRoleDelBy(getUserId());
			roleList.add(roleDto);
		});
		Response<Integer> delBatchRes = authorityRoleShareService.deleteByBatch(roleList);
		String message = delBatchRes.getMessage();
		Long code = delBatchRes.getCode();
		logger.debug(message);
		boolean isError = message.indexOf(Error.class.getSimpleName()) >=0;
		if((code == 1L && isError) || code == -1L){
			delBatchRes.setCode(-1L);
			delBatchRes.setMessage("删除失败");
			delBatchRes.setData(null);
			return JSON.toJSONString(delBatchRes);
		}
		return JSON.toJSONString(delBatchRes);
	}
	
	public String getUserId(){
		return "de0c7b2480494fda98db82f7a4707649";
	}

	@Override
	public String getPlatformAndRoleStatus() {
		List<PlatformDTO> platformList = platformShareService.queryListByCondition(new PlatformDTO(), true).getData();
		Map<String, Map> platformAndRoleStatu = new HashMap<>();
		Map<String, String> platformMap = new HashMap<>();
		for (PlatformDTO platformDTO : platformList) {
			platformMap.put(platformDTO.getPlatformName(), platformDTO.getPlatformUuid());
		}
		platformAndRoleStatu.put("platform", platformMap);
		platformAndRoleStatu.put("roleStatus", DataDictionaryConstant.roleStatusKeyValueMap);
		return JSONObject.toJSONString(platformAndRoleStatu);
	}
}
