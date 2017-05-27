//
//package net.wangxj.authority.web.service.impl;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.annotation.Resource;
//
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Service;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//
//import net.wangxj.authority.Response;
//import net.wangxj.authority.constant.DataDictionaryConstant;
//import net.wangxj.authority.dto.AuthorityResourcesDTO;
//import net.wangxj.authority.dto.PlatformDTO;
//import net.wangxj.authority.service.share.AuthorityResourcesShareService;
//import net.wangxj.authority.service.share.PlatformShareService;
//import net.wangxj.authority.web.service.AuthorityResourcesWebService;
//import net.wangxj.util.validate.Severity.Error;
//
//@Service(value="AuthorityResourcesWebService")
//public class AuthorityResourcesWebServiceImpl implements AuthorityResourcesWebService{
//	
//	private static Logger logger = Logger.getLogger(AuthorityResourcesWebServiceImpl.class);
//	
//	@Resource
//	private AuthorityResourcesShareService authorityResourcesShareService;
//	@Resource
//	private PlatformShareService platformShareService;
//
//	@Override
//	public String getPageList(String jsonStr) {
//		JSONObject jsonObj = JSONObject.parseObject(jsonStr);
//		String order = jsonObj.getString("order");
//		Integer limit = jsonObj.getInteger("limit");
//		Integer offset = jsonObj.getInteger("offset");
//		String sort = jsonObj.getString("sort");
//		
//		AuthorityResourcesDTO resourceDto = new AuthorityResourcesDTO();
//		Integer pageNum = offset/limit + 1;
//		Integer page =  0;
//		Integer count = 0;
//		String jsonString = "[]";
//		//条件查询
//		Response<AuthorityResourcesDTO> pageListResponse = authorityResourcesShareService.queryPageListByCondition(resourceDto, pageNum, limit, order, sort);
//		Response<Integer> countResponse = authorityResourcesShareService.getCountByCondition(resourceDto, true);
//		logger.debug(pageListResponse.getMessage());
//		if(pageListResponse.getCode() == 0L && countResponse.getCode() == 0L){
//			List<AuthorityResourcesDTO> data = pageListResponse.getData();
//			count = countResponse.getResObject();
//			page = count/limit + 1;
//			JSONObject json = new JSONObject();
//			json.put("rows", data);
//			json.put("total", count);
//			json.put("page", page);
//			jsonString = JSON.toJSONString(json);
//		}
//		return jsonString;
//	}
//
//	@Override
//	public String add(AuthorityResourcesDTO dto) {
//		dto.setResourceAddBy(getUserId());
//		Response<Integer> addRespo = authorityResourcesShareService.add(dto);
//		String message = addRespo.getMessage();
//		Long code = addRespo.getCode();
//		logger.debug(message);
//		boolean isError= message.indexOf(Error.class.getSimpleName())>=0;
//		if((code == 1L && isError) || code == -1L){
//			addRespo.setCode(-1L);
//			addRespo.setMessage("增加失败");
//			addRespo.setData(null);
//			return JSON.toJSONString(addRespo);
//		}
//		return JSON.toJSONString(addRespo);
//	}
//
//	@Override
//	public String isRepeatField(AuthorityResourcesDTO dto) {
//		//待校验
//		String resourceUuid = dto.getResourceUuid();
//		dto.setResourceUuid(null);
//		//与正在修改的resource是同一个，则不重复
//		AuthorityResourcesDTO validate = new AuthorityResourcesDTO();
//		validate.setResourceUuid(resourceUuid);
//		Response<AuthorityResourcesDTO> queryConditionResp = authorityResourcesShareService.queryListByCondition(dto);
//		if(queryConditionResp.getCode() == 0L){
//			if(queryConditionResp.getData().size() == 0L){
//				return "false";
//			}//条件查询只有一个而且该资源是正在修改的resource则返回false
//			else if(queryConditionResp.getData().size() == 1 && queryConditionResp.getData().contains(validate)){
//				return "false";
//			}
//			else{
//				return "true";
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public String edit(AuthorityResourcesDTO dto) {
//		dto.setResourceEditBy(getUserId());
//		Response<Integer> editRes = authorityResourcesShareService.modifyByUuid(dto);
//		String message = editRes.getMessage();
//		Long code = editRes.getCode();
//		logger.debug(message);
//		boolean isError = message.indexOf(Error.class.getSimpleName())>=0;
//		if((code == 1L && isError) || code == -1L){
//			editRes.setCode(-1L);
//			editRes.setMessage("编辑失败");
//			editRes.setData(null);
//			return JSON.toJSONString(editRes);
//		}
//		return JSON.toJSONString(editRes);
//	}
//
//	@Override
//	public String delete(AuthorityResourcesDTO dto) {
//		dto.setResourceDelBy(getUserId());
//		Response<Integer> deleteRespo = authorityResourcesShareService.deleteByUuid(dto);
//		String message = deleteRespo.getMessage();
//		Long code = deleteRespo.getCode();
//		logger.debug(message);
//		boolean isError = message.indexOf(Error.class.getSimpleName())>=0;
//		if((code == 1L && isError) || code == -1L){
//			deleteRespo.setCode(-1L);
//			deleteRespo.setMessage("删除失败");
//			deleteRespo.setData(null);
//			return JSON.toJSONString(deleteRespo);
//		}
//		return JSON.toJSONString(deleteRespo);
//	}
//
//	@Override
//	public String deleteBatch(List<String> uuidList) {
//		List<AuthorityResourcesDTO> resourceList = new ArrayList<>();
//		uuidList.forEach(uuid -> {
//			AuthorityResourcesDTO resourceDto = new AuthorityResourcesDTO();
//			resourceDto.setResourceUuid(uuid);
//			resourceDto.setResourceDelBy(getUserId());
//			resourceList.add(resourceDto);
//		});
//		Response<Integer> deleteByBatchResp = authorityResourcesShareService.deleteByBatch(resourceList);
//		String message = deleteByBatchResp.getMessage();
//		Long code = deleteByBatchResp.getCode();
//		logger.debug(message);
//		boolean isError = message.indexOf(Error.class.getSimpleName()) >=0;
//		if((code == 1L && isError) || code == -1L){
//			deleteByBatchResp.setCode(-1L);
//			deleteByBatchResp.setMessage("删除失败");
//			deleteByBatchResp.setData(null);
//			return JSON.toJSONString(deleteByBatchResp);
//		}
//		return JSON.toJSONString(deleteByBatchResp);
//	}
//
//	@Override
//	public String initSelect(AuthorityResourcesDTO resourceDto) {
//		Integer resourveLevel = resourceDto.getResourceLevel();
//		Integer parentLevel = 1;
//		//平台列表
//		List<PlatformDTO> platformList = platformShareService.queryListByCondition(new PlatformDTO(), true).getData();
//		Map<String, Map> selectMap = new HashMap<>();
//		Map<String, String> platformMap = new HashMap<>();
//		for (PlatformDTO platformDTO : platformList) {
//			platformMap.put(platformDTO.getPlatformName(), platformDTO.getPlatformUuid());
//		}
//		selectMap.put("platform", platformMap);
//		//取第一个平台
//		Collection<String> values = platformMap.values();
//		String platformUuid = values.size() > 0 ? values.iterator().next() : null;
//		//父级列表
//		if(platformUuid == null){
//			selectMap.put("parent", null);
//		}
//		else{
//			AuthorityResourcesDTO resourceDto1 = new AuthorityResourcesDTO();
//			resourceDto1.setResourcePlatformUuid(platformUuid);
//			parentLevel = resourveLevel == 1 ? 1 : resourveLevel - 1;
//			resourceDto1.setResourceLevel(parentLevel);
//			List<AuthorityResourcesDTO> parentResourceList = authorityResourcesShareService.queryListByCondition(resourceDto1).getData();
//			Map<String, String> parentMap = new HashMap<>();
//			for (AuthorityResourcesDTO parentResourceDto : parentResourceList) {
//				parentMap.put(parentResourceDto.getResourceName(), parentResourceDto.getResourceUuid());
//			}
//			selectMap.put("parent", parentMap);
//		}
//		selectMap.put("resourceStatus", DataDictionaryConstant.resourceStatusKeyValueMap);
//		return JSONObject.toJSONString(selectMap);
//	}
//	
//	public String getUserId(){
//		return "de0c7b2480494fda98db82f7a4707649";
//	}
//
//	@Override
//	public String changeBy(AuthorityResourcesDTO paramResosurceDto) {
//		String platformUuid = paramResosurceDto.getResourcePlatformUuid();
//		Integer level = paramResosurceDto.getResourceLevel();
//		
//		AuthorityResourcesDTO resourceDto = new AuthorityResourcesDTO();
//		resourceDto.setResourcePlatformUuid(platformUuid);
//		resourceDto.setResourceLevel(level == 1 ? 1 : level - 1);
//		Map<String, Map> changeByMap = new HashMap<>();
//		Map<String, String> parentMap = new HashMap<>();
//		List<AuthorityResourcesDTO> data = authorityResourcesShareService.queryListByCondition(resourceDto).getData();
//		for (AuthorityResourcesDTO authorityResourcesDTO : data) {
//			parentMap.put(authorityResourcesDTO.getResourceName(), authorityResourcesDTO.getResourceUuid());
//		}
//		changeByMap.put("parent", parentMap);
//		
//		return JSONObject.toJSONString(changeByMap);
//	}
//
//	@Override
//	public String getListByPlatform(AuthorityResourcesDTO resourceDto) {
//		Response<AuthorityResourcesDTO> response = authorityResourcesShareService.queryListByCondition(resourceDto); 
//		List<AuthorityResourcesDTO> list = response.getData();
//		return JSONObject.toJSONString(list);
//	}
//}
