
package net.wangxj.authority.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.wangxj.authority.Response;
import net.wangxj.authority.constant.DataDictionaryConstant;
import net.wangxj.authority.dto.AuthorityUserDTO;
import net.wangxj.authority.service.share.AuthorityUserShareService;
import net.wangxj.authority.web.service.AuthorityUserWebService;
import net.wangxj.authority.web.service.PlatformWebService;
import net.wangxj.util.validate.Severity.Error;

@Service(value="AuthorityUserWebService")
public class AuthorityUserWebServiceImpl implements AuthorityUserWebService {
	
	private static Logger logger = Logger.getLogger(AuthorityUserWebServiceImpl.class);
	@Resource
	private AuthorityUserShareService authorityUserShareService;

	@Override
	public String getPageList(String jsonStr) {

		JSONObject jsonObj = JSONObject.parseObject(jsonStr);
		String order = jsonObj.getString("order");
		Integer limit = jsonObj.getInteger("limit");
		Integer offset = jsonObj.getInteger("offset");
		String sort = jsonObj.getString("sort");
		
		AuthorityUserDTO userDto = new AuthorityUserDTO();
		Integer pageNum = offset/limit + 1;
		Integer page =  0;
		Integer count = 0;
		String jsonString = "[]";
		//条件查询
		Response<AuthorityUserDTO> pageListResponse = authorityUserShareService.queryPageListByCondition(userDto, pageNum, limit, order, sort);
		Response<Integer> countResponse = authorityUserShareService.getCountByCondition(userDto, true);
		logger.debug(pageListResponse.getMessage());
		if(pageListResponse.getCode() == 0L && countResponse.getCode() == 0L){
			List<AuthorityUserDTO> data = pageListResponse.getData();
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
	public String add(AuthorityUserDTO dto) {
		dto.setUserAddBy(getUserId());
		dto.setUserAddType(DataDictionaryConstant.USER_ADDTYPE_INNER_VALUE);
		Response<Integer> addRes = authorityUserShareService.add(dto);
		String message = addRes.getMessage();
		Long code = addRes.getCode();
		logger.debug(message);
		boolean isError= message.indexOf(Error.class.getSimpleName())>=0;
		if((code == 1L && isError) || code == -1L){
			addRes.setCode(-1L);
			addRes.setMessage("增加失败");
			addRes.setData(null);
			return JSON.toJSONString(addRes);
		}
		return JSON.toJSONString(addRes);
	}

	@Override
	public String isRepeatField(AuthorityUserDTO dto) {
		
		Response<Integer> countRespo = authorityUserShareService.getCountByCondition(dto, false);
		Response<Integer> allCountRes = authorityUserShareService.getCountByCondition(new AuthorityUserDTO(), false);
		if(countRespo.getCode() == 0L && allCountRes.getCode() == 0L){
			logger.debug("查询是否重复字段成功");
			Integer count = countRespo.getResObject();
			Integer allCount = allCountRes.getResObject();
			return (count > 0 && count <= allCount) ? "true" : "false"; 
		}
		return JSON.toJSONString(countRespo);
	}

	@Override
	public String edit(AuthorityUserDTO dto) {
		dto.setUserEditBy(getUserId());
		Response<Integer> editRes = authorityUserShareService.modifyByUuid(dto);
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
	public String delete(AuthorityUserDTO dto) {
		dto.setUserDelBy(getUserId());
		Response<Integer> deleteRespon = authorityUserShareService.deleteByUuid(dto);
		String message = deleteRespon.getMessage();
		Long code = deleteRespon.getCode();
		logger.debug(message);
		boolean isError = message.indexOf(Error.class.getSimpleName())>=0;
		if((code == 1L && isError) || code == -1L){
			deleteRespon.setCode(-1L);
			deleteRespon.setMessage("删除失败");
			deleteRespon.setData(null);
			return JSON.toJSONString(deleteRespon);
		}
		return JSON.toJSONString(deleteRespon);
	}

	@Override
	public String deleteBatch(List<String> uuidList) {
		
		List<AuthorityUserDTO> userList = new ArrayList<>();
		uuidList.forEach(uuid -> {
			AuthorityUserDTO userDto = new AuthorityUserDTO();
			userDto.setUserUuid(uuid);
			userDto.setUserDelBy(getUserId());
			userList.add(userDto);
		});
		Response<Integer> countRespo = authorityUserShareService.deleteByBatch(userList);
		String message = countRespo.getMessage();
		Long code = countRespo.getCode();
		logger.debug(message);
		boolean isError = message.indexOf(Error.class.getSimpleName()) >=0;
		if((code == 1L && isError) || code == -1L){
			countRespo.setCode(-1L);
			countRespo.setMessage("删除失败");
			countRespo.setData(null);
			return JSON.toJSONString(countRespo);
		}
		return JSON.toJSONString(countRespo);
	}
	
	public String getUserId(){
		return "de0c7b2480494fda98db82f7a4707649";
	}
	
}
