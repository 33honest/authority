
package net.wangxj.authority.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.PlatformDTO;
import net.wangxj.authority.service.share.PlatformShareService;
import net.wangxj.authority.web.service.AuthorityWebService;
import net.wangxj.authority.web.service.PlatformWebService;
import net.wangxj.util.string.UuidUtil;
import net.wangxj.util.validate.Severity.Error;
import net.wangxj.util.validate.ValidationResult;

@Service(value="PlatformWebService")
public class PlatformWebServiceImpl implements AuthorityWebService<PlatformDTO>{
	
	private static Logger logger = Logger.getLogger(PlatformWebService.class);
	
	@Resource
	private PlatformShareService platformShareService;
	
	@Override
	public String getPageList(String jsonStr){
		JSONObject jsonObj = JSONObject.parseObject(jsonStr);
		String order = jsonObj.getString("order");
		Integer limit = jsonObj.getInteger("limit");
		Integer offset = jsonObj.getInteger("offset");
		String sort = jsonObj.getString("sort");
		
		PlatformDTO platformDto = new PlatformDTO();
		Integer pageNum = offset/limit + 1;
		Integer page = 0;
		Integer count = 0;
		String jsonString="[]";
		//条件查询
		Response<PlatformDTO> platformResponse = platformShareService.queryPageListByCondition(platformDto, pageNum,limit,order,sort);
		Response<Integer> countRespo = platformShareService.getCountByCondition(platformDto,true);
		logger.debug(platformResponse.getMessage());
		if(platformResponse.getCode() == 0L && countRespo.getCode() == 0L){
			List<PlatformDTO> data = platformResponse.getData();
			count = countRespo.getResObject();
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
	public String add(PlatformDTO platformDto) {
		platformDto.setPlatformAddBy(getUserId());
		Response<Integer> addRes = platformShareService.add(platformDto);
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
	public String edit(PlatformDTO platformDto) {
		platformDto.setPlatformEditBy(getUserId());
		Response<Integer> editRes = platformShareService.modifyByUuid(platformDto);
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
	public String isRepeatField(PlatformDTO platformDto) {
		Response<Integer> countByCondition = platformShareService.getCountByCondition(platformDto,false);
		Response<Integer> allCountResp = platformShareService.getCountByCondition(new PlatformDTO(),false);
		if(countByCondition.getCode() == 0L && allCountResp.getCode()==0L){
			logger.debug("查询是否重复字段成功");
			Integer count = countByCondition.getResObject();
			Integer allCount = allCountResp.getResObject();
			return (count > 0 && count <= allCount) ? "true" : "false"; 
		}
		return JSON.toJSONString(countByCondition);
	}
	
	public String getUserId(){
		return "de0c7b2480494fda98db82f7a4707649";
	}

	@Override
	public String delete(PlatformDTO platformDto) {
		platformDto.setPlatformDelBy(getUserId());
		Response<Integer> deleteRespon = platformShareService.deleteByUuid(platformDto);
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
		List<PlatformDTO> platformList = new ArrayList<>();
		uuidList.forEach(uuid -> {
			PlatformDTO platformDto = new PlatformDTO();
			platformDto.setPlatformUuid(uuid);
			platformDto.setPlatformDelBy(getUserId());
			platformList.add(platformDto);
			});
		Response<Integer> countRespo = platformShareService.deleteByBatch(platformList);
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

	
}
