
package net.wangxj.authority.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.PlatformDTO;
import net.wangxj.authority.service.share.PlatformShareService;
import net.wangxj.authority.web.service.PlatformWebService;

@Service(value="PlatformWebService")
public class PlatformWebServiceImpl implements PlatformWebService {
	
	private static Logger logger = Logger.getLogger(PlatformWebService.class);
	
	@Resource
	private PlatformShareService platformShareService;
	
	@Override
	public String getPlatformList(String order,Integer limit,Integer offset ){
		PlatformDTO platformDto = new PlatformDTO();
		Integer pageNum = offset/limit + 1;
		Integer page = 0;
		Integer count = 0;
		String jsonString="[]";
		//条件查询
		Response<PlatformDTO> platformResponse = platformShareService.queryPageListByCondition(platformDto, pageNum, limit);
		Response<Integer> countRespo = platformShareService.getCountByCondition(platformDto);
		logger.debug(platformResponse.getMessage());
		if(platformResponse.getCode() == 0L && countRespo.getCode() == 0L){
			List<PlatformDTO> data = platformResponse.getData();
			count = countRespo.getResObject();
			page = count/limit + 1;
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("rows", data);
			jsonObj.put("total", count);
			jsonObj.put("page", page);
			jsonString = JSON.toJSONString(jsonObj);
		}
		
		return jsonString;
	}
	
}
