
package net.wangxj.authority.service.share.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import net.wangxj.authority.service.share.AuthorityResourcesShareService;
import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityResourcesDTO;
import net.wangxj.authority.po.AuthorityResourcesPO;
import net.wangxj.authority.service.AuthorityResourcesService;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:38
 */
public class AuthorityResourcesShareServiceImpl implements AuthorityResourcesShareService{

	private static Logger logger = Logger.getLogger(AuthorityResourcesShareServiceImpl.class);
	
	@Resource
	private AuthorityResourcesService authorityResourcesService;
	
	@Override
	public Response<Integer> add(AuthorityResourcesDTO authorityResourcesDto){
		
		Response<Integer> response = new Response<Integer>();
		
		if(authorityResourcesDto == null){
			response.setMessage("传入参数不可为空");
			return response;
		}
		
		AuthorityResourcesPO authorityResourcespo = new AuthorityResourcesPO(); 
		BeanUtils.copyProperties(authorityResourcesDto, authorityResourcespo);
		
		Integer uuid = authorityResourcesService.add(authorityResourcespo);
		logger.info("新增authorityResourcesDTO成功");
		response.setCode(0L);
		response.setResObject(uuid);
		return response;
	}
	
	@Override
	public Response<Integer> addBatch(List<AuthorityResourcesDTO> listDto){
		
		Response<Integer> response = new Response<Integer>();
		
		if(listDto == null && listDto.size()>0){
			response.setMessage("传入参数不可为空");
			return response;
		}
		
		List<AuthorityResourcesPO> listPo = new ArrayList<>();
		for(AuthorityResourcesDTO authorityResourcesDto : listDto){
			AuthorityResourcesPO authorityResourcesPo = new AuthorityResourcesPO();
			BeanUtils.copyProperties(authorityResourcesDto,authorityResourcesPo);
			listPo.add(authorityResourcesPo);
		}
		Integer count = authorityResourcesService.addBatch(listPo);
		logger.info("新增listauthorityResourcesDTO成功");
		response.setCode(0L);
		response.setResObject(count);
		return response;
	}
	
	@Override
	public Response<Integer> modifyByUuid(AuthorityResourcesDTO authorityResourcesDto){
		
		Response<Integer> response = new Response<>();
		if(authorityResourcesDto == null){
			response.setMessage("authorityResourcesDto参数不可为空");
			return response;
		}
		AuthorityResourcesPO authorityResourcespo = new AuthorityResourcesPO(); 
		BeanUtils.copyProperties(authorityResourcesDto, authorityResourcespo);
		
		Integer count = authorityResourcesService.modifyByUuid(authorityResourcespo);
		logger.info("modifyauthorityResourcesDTO修改成功");
			
		response.setCode(0L);
		response.setResObject(count);
		response.setMessage("成功");
			
		return response;
	}
	
	@Override
	public Response<AuthorityResourcesDTO> queryListByCondition(AuthorityResourcesDTO authorityResourcesDto){
		
		Response<AuthorityResourcesDTO> response = new Response<>();
		if(authorityResourcesDto == null){
			response.setMessage("authorityResourcesDto参数不可为空");
			return response;
		}
		AuthorityResourcesPO authorityResourcesPo = new AuthorityResourcesPO();
		List<AuthorityResourcesPO> listPo = new ArrayList<>();
		List<AuthorityResourcesDTO> listDto = new ArrayList<>();
		BeanUtils.copyProperties(authorityResourcesDto, authorityResourcesPo);
		
		listPo = authorityResourcesService.queryListByCondition(authorityResourcesPo);
		logger.info("查询queryList成功");
		for (AuthorityResourcesPO authorityResourcesPo2 : listPo) {
			AuthorityResourcesDTO authorityResourcesDto2 = new AuthorityResourcesDTO();
			BeanUtils.copyProperties(authorityResourcesPo2, authorityResourcesDto2);
			listDto.add(authorityResourcesDto2);
		}
		response.setCode(0L);
		response.setData(listDto);
		response.setMessage("成功");
		
		return response;
	}
	
	@Override
	public Response<AuthorityResourcesDTO> queryPageListByCondition(AuthorityResourcesDTO authorityResourcesDto, int pageNum, int limit){
		
		Response<AuthorityResourcesDTO> response = new Response<>();
		if(authorityResourcesDto == null){
			response.setMessage("authorityResourcesDto参数不可为空");
			return response;
		}
		AuthorityResourcesPO authorityResourcesPo = new AuthorityResourcesPO();
		List<AuthorityResourcesPO> listPo = new ArrayList<>();
		List<AuthorityResourcesDTO> listDto = new ArrayList<>();
		BeanUtils.copyProperties(authorityResourcesDto, authorityResourcesPo);
		
		listPo = authorityResourcesService.queryPageListByCondition(authorityResourcesPo,pageNum,limit);
		for (AuthorityResourcesPO authorityResourcesPo2 : listPo) {
			AuthorityResourcesDTO authorityResourcesDto2 = new AuthorityResourcesDTO();
			BeanUtils.copyProperties(authorityResourcesPo2, authorityResourcesDto2);
			listDto.add(authorityResourcesDto2);
		}
		response.setCode(0L);
		response.setData(listDto);
		response.setMessage("成功");
		
		return response;
	}
	
	@Override
	public Response<Integer> getCountByCondition(AuthorityResourcesDTO authorityResourcesDto){
		
		Response<Integer> response = new Response<>();
		if(authorityResourcesDto == null){
			response.setMessage("authorityResourcesDto参数不可为空");
			return response;
		}
		AuthorityResourcesPO authorityResourcespo = new AuthorityResourcesPO(); 
		BeanUtils.copyProperties(authorityResourcesDto, authorityResourcespo);
		
		Integer count = authorityResourcesService.getCountByCondition(authorityResourcespo);
		logger.info("count查询成功");
		
		response.setCode(0L);
		response.setResObject(count);
		response.setMessage("成功");
			
		return response;
	}
	

}