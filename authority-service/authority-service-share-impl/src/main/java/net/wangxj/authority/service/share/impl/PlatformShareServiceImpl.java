
package net.wangxj.authority.service.share.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import net.wangxj.authority.service.share.PlatformShareService;
import net.wangxj.util.validate.ValidateUtil;
import net.wangxj.util.validate.ValidationResult;
import net.wangxj.util.validate.groups.AddValidate;
import net.wangxj.authority.Response;
import net.wangxj.authority.dto.PlatformDTO;
import net.wangxj.authority.po.PlatformPO;
import net.wangxj.authority.service.PlatformService;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:44
 */
public class PlatformShareServiceImpl implements PlatformShareService{

	private static Logger logger = Logger.getLogger(PlatformShareServiceImpl.class);
	
	@Resource
	private PlatformService platformService;
	
	@Override
	public Response<Integer> add(PlatformDTO platformDto){
		
		Response<Integer> response = new Response<Integer>();
		
		if(platformDto == null){
			response.setMessage("传入参数不可为空");
			return response;
		}
		PlatformPO platformpo = new PlatformPO(); 
		BeanUtils.copyProperties(platformDto, platformpo);
		//验证
		ValidationResult validateRes = ValidateUtil.validateEntity(platformpo, AddValidate.class);
		if(!validateRes.isPass()){
			response.setCode(-1L);
			response.setMessage("校验失败");
			return response;
		}
		Integer uuid = platformService.add(platformpo);
		if(uuid>0){
			logger.info("新增platformDTO成功");
			response.setCode(0L);
			response.setResObject(uuid);
		}
		else{
			logger.info("新增platformDTO失败");
		}
		return response;
	}
	
	@Override
	public Response<Integer> addBatch(List<PlatformDTO> listDto){
		
		Response<Integer> response = new Response<Integer>();
		
		if(listDto == null && listDto.size()>0){
			response.setMessage("传入参数不可为空");
			return response;
		}
		
		List<PlatformPO> listPo = new ArrayList<>();
		for(PlatformDTO platformDto : listDto){
			PlatformPO platformPo = new PlatformPO();
			BeanUtils.copyProperties(platformDto,platformPo);
			listPo.add(platformPo);
		}
		Integer count = platformService.addBatch(listPo);
		logger.info("新增listplatformDTO成功");
		response.setCode(0L);
		response.setResObject(count);
		return response;
	}
	
	@Override
	public Response<Integer> modifyByUuid(PlatformDTO platformDto){
		
		Response<Integer> response = new Response<>();
		if(platformDto == null){
			response.setMessage("platformDto参数不可为空");
			return response;
		}
		PlatformPO platformpo = new PlatformPO(); 
		BeanUtils.copyProperties(platformDto, platformpo);
		
		Integer count = platformService.modifyByUuid(platformpo);
		logger.info("modifyplatformDTO修改成功");
			
		response.setCode(0L);
		response.setResObject(count);
		response.setMessage("成功");
			
		return response;
	}
	
	@Override
	public Response<PlatformDTO> queryListByCondition(PlatformDTO platformDto){
		
		Response<PlatformDTO> response = new Response<>();
		if(platformDto == null){
			response.setMessage("platformDto参数不可为空");
			return response;
		}
		PlatformPO platformPo = new PlatformPO();
		List<PlatformPO> listPo = new ArrayList<>();
		List<PlatformDTO> listDto = new ArrayList<>();
		BeanUtils.copyProperties(platformDto, platformPo);
		
		listPo = platformService.queryListByCondition(platformPo);
		logger.info("查询queryList成功");
		for (PlatformPO platformPo2 : listPo) {
			PlatformDTO platformDto2 = new PlatformDTO();
			BeanUtils.copyProperties(platformPo2, platformDto2);
			listDto.add(platformDto2);
		}
		response.setCode(0L);
		response.setData(listDto);
		response.setMessage("成功");
		
		return response;
	}
	
	@Override
	public Response<PlatformDTO> queryPageListByCondition(PlatformDTO platformDto, int pageNum, int limit, String order, String sort){
		
		Response<PlatformDTO> response = new Response<>();
		if(platformDto == null){
			response.setMessage("platformDto参数不可为空");
			return response;
		}
		PlatformPO platformPo = new PlatformPO();
		List<PlatformPO> listPo = new ArrayList<>();
		List<PlatformDTO> listDto = new ArrayList<>();
		BeanUtils.copyProperties(platformDto, platformPo);
		
		listPo = platformService.queryPageListByCondition(platformPo,pageNum,limit,order,sort);
		for (PlatformPO platformPo2 : listPo) {
			PlatformDTO platformDto2 = new PlatformDTO();
			BeanUtils.copyProperties(platformPo2, platformDto2);
			listDto.add(platformDto2);
		}
		response.setCode(0L);
		response.setData(listDto);
		response.setMessage("成功");
		
		return response;
	}
	
	@Override
	public Response<Integer> getCountByCondition(PlatformDTO platformDto){
		
		Response<Integer> response = new Response<>();
		if(platformDto == null){
			response.setMessage("platformDto参数不可为空");
			return response;
		}
		PlatformPO platformpo = new PlatformPO(); 
		BeanUtils.copyProperties(platformDto, platformpo);
		
		Integer count = platformService.getCountByCondition(platformpo);
		logger.info("count查询成功");
		
		response.setCode(0L);
		response.setResObject(count);
		response.setMessage("成功");
			
		return response;
	}
}