
package net.wangxj.authority.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityResourcesDTO;
import net.wangxj.authority.service.share.AuthorityResourcesShareService;
import net.wangxj.authority.web.service.AuthorityResourcesWebService;
import net.wangxj.util.validate.Severity.Error;

@Service(value="AuthorityResourcesWebService")
public class AuthorityResourcesWebServiceImpl implements AuthorityResourcesWebService{
	
	private static Logger logger = Logger.getLogger(AuthorityResourcesWebServiceImpl.class);
	
	@Resource
	private AuthorityResourcesShareService authorityResourcesShareService;

	@Override
	public String getPageList(String jsonStr) {
		JSONObject jsonObj = JSONObject.parseObject(jsonStr);
		String order = jsonObj.getString("order");
		Integer limit = jsonObj.getInteger("limit");
		Integer offset = jsonObj.getInteger("offset");
		String sort = jsonObj.getString("sort");
		
		AuthorityResourcesDTO resourceDto = new AuthorityResourcesDTO();
		Integer pageNum = offset/limit + 1;
		Integer page =  0;
		Integer count = 0;
		String jsonString = "[]";
		//条件查询
		Response<AuthorityResourcesDTO> pageListResponse = authorityResourcesShareService.queryPageListByCondition(resourceDto, pageNum, limit, order, sort);
		Response<Integer> countResponse = authorityResourcesShareService.getCountByCondition(resourceDto, true);
		logger.debug(pageListResponse.getMessage());
		if(pageListResponse.getCode() == 0L && countResponse.getCode() == 0L){
			List<AuthorityResourcesDTO> data = pageListResponse.getData();
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
	public String add(AuthorityResourcesDTO dto) {
		dto.setResourceAddBy(getUserId());
		Response<Integer> addRespo = authorityResourcesShareService.add(dto);
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
	public String isRepeatField(AuthorityResourcesDTO dto) {
		//待校验
		String resourceUuid = dto.getResourceUuid();
		dto.setResourceUuid(null);
		//与正在修改的resource是同一个，则不重复
		AuthorityResourcesDTO validate = new AuthorityResourcesDTO();
		validate.setResourceUuid(resourceUuid);
		Response<AuthorityResourcesDTO> queryConditionResp = authorityResourcesShareService.queryListByCondition(dto);
		if(queryConditionResp.getCode() == 0L){
			if(queryConditionResp.getData().size() == 0L){
				return "false";
			}//条件查询只有一个而且该资源是正在修改的resource则返回false
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
	public String edit(AuthorityResourcesDTO dto) {
		dto.setResourceEditBy(getUserId());
		Response<Integer> editRes = authorityResourcesShareService.modifyByUuid(dto);
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
	public String delete(AuthorityResourcesDTO dto) {
		dto.setResourceDelBy(getUserId());
		Response<Integer> deleteRespo = authorityResourcesShareService.deleteByUuid(dto);
		String message = deleteRespo.getMessage();
		Long code = deleteRespo.getCode();
		logger.debug(message);
		boolean isError = message.indexOf(Error.class.getSimpleName())>=0;
		if((code == 1L && isError) || code == -1L){
			deleteRespo.setCode(-1L);
			deleteRespo.setMessage("删除失败");
			deleteRespo.setData(null);
			return JSON.toJSONString(deleteRespo);
		}
		return JSON.toJSONString(deleteRespo);
	}

	@Override
	public String deleteBatch(List<String> uuidList) {
		List<AuthorityResourcesDTO> resourceList = new ArrayList<>();
		uuidList.forEach(uuid -> {
			AuthorityResourcesDTO resourceDto = new AuthorityResourcesDTO();
			resourceDto.setResourceUuid(uuid);
			resourceDto.setResourceDelBy(getUserId());
			resourceList.add(resourceDto);
		});
		Response<Integer> deleteByBatchResp = authorityResourcesShareService.deleteByBatch(resourceList);
		String message = deleteByBatchResp.getMessage();
		Long code = deleteByBatchResp.getCode();
		logger.debug(message);
		boolean isError = message.indexOf(Error.class.getSimpleName()) >=0;
		if((code == 1L && isError) || code == -1L){
			deleteByBatchResp.setCode(-1L);
			deleteByBatchResp.setMessage("删除失败");
			deleteByBatchResp.setData(null);
			return JSON.toJSONString(deleteByBatchResp);
		}
		return JSON.toJSONString(deleteByBatchResp);
	}
	
	public String getUserId(){
		return "de0c7b2480494fda98db82f7a4707649";
	}
	
}
