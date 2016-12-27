
package net.wangxj.authority.service.share.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import net.wangxj.authority.service.share.AuthorityUserShareService;
import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityUserDTO;
import net.wangxj.authority.po.AuthorityUserPO;
import net.wangxj.authority.service.AuthorityUserService;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:42
 */
public class AuthorityUserShareServiceImpl implements AuthorityUserShareService{

	private static Logger logger = Logger.getLogger(AuthorityUserShareServiceImpl.class);
	
	@Resource
	private AuthorityUserService authorityUserService;
	
	@Override
	public Response<Integer> add(AuthorityUserDTO authorityUserDto){
		
		Response<Integer> response = new Response<Integer>();
		
		if(authorityUserDto == null){
			response.setMessage("传入参数不可为空");
			return response;
		}
		
		AuthorityUserPO authorityUserpo = new AuthorityUserPO(); 
		BeanUtils.copyProperties(authorityUserDto, authorityUserpo);
		
		Integer uuid = authorityUserService.add(authorityUserpo);
		logger.info("新增authorityUserDTO成功");
		response.setCode(0L);
		response.setResObject(uuid);
		return response;
	}
	
	@Override
	public Response<Integer> addBatch(List<AuthorityUserDTO> listDto){
		
		Response<Integer> response = new Response<Integer>();
		
		if(listDto == null && listDto.size()>0){
			response.setMessage("传入参数不可为空");
			return response;
		}
		
		List<AuthorityUserPO> listPo = new ArrayList<>();
		for(AuthorityUserDTO authorityUserDto : listDto){
			AuthorityUserPO authorityUserPo = new AuthorityUserPO();
			BeanUtils.copyProperties(authorityUserDto,authorityUserPo);
			listPo.add(authorityUserPo);
		}
		Integer count = authorityUserService.addBatch(listPo);
		logger.info("新增listauthorityUserDTO成功");
		response.setCode(0L);
		response.setResObject(count);
		return response;
	}
	
	@Override
	public Response<Integer> modifyByUuid(AuthorityUserDTO authorityUserDto){
		
		Response<Integer> response = new Response<>();
		if(authorityUserDto == null){
			response.setMessage("authorityUserDto参数不可为空");
			return response;
		}
		AuthorityUserPO authorityUserpo = new AuthorityUserPO(); 
		BeanUtils.copyProperties(authorityUserDto, authorityUserpo);
		
		Integer count = authorityUserService.modifyByUuid(authorityUserpo);
		logger.info("modifyauthorityUserDTO修改成功");
			
		response.setCode(0L);
		response.setResObject(count);
		response.setMessage("成功");
			
		return response;
	}
	
	@Override
	public Response<AuthorityUserDTO> queryListByCondition(AuthorityUserDTO authorityUserDto){
		
		Response<AuthorityUserDTO> response = new Response<>();
		if(authorityUserDto == null){
			response.setMessage("authorityUserDto参数不可为空");
			return response;
		}
		AuthorityUserPO authorityUserPo = new AuthorityUserPO();
		List<AuthorityUserPO> listPo = new ArrayList<>();
		List<AuthorityUserDTO> listDto = new ArrayList<>();
		BeanUtils.copyProperties(authorityUserDto, authorityUserPo);
		
		listPo = authorityUserService.queryListByCondition(authorityUserPo);
		logger.info("查询queryList成功");
		for (AuthorityUserPO authorityUserPo2 : listPo) {
			AuthorityUserDTO authorityUserDto2 = new AuthorityUserDTO();
			BeanUtils.copyProperties(authorityUserPo2, authorityUserDto2);
			listDto.add(authorityUserDto2);
		}
		response.setCode(0L);
		response.setData(listDto);
		response.setMessage("成功");
		
		return response;
	}
	
	@Override
	public Response<AuthorityUserDTO> queryPageListByCondition(AuthorityUserDTO authorityUserDto, int pageNum, int limit){
		
		Response<AuthorityUserDTO> response = new Response<>();
		if(authorityUserDto == null){
			response.setMessage("authorityUserDto参数不可为空");
			return response;
		}
		AuthorityUserPO authorityUserPo = new AuthorityUserPO();
		List<AuthorityUserPO> listPo = new ArrayList<>();
		List<AuthorityUserDTO> listDto = new ArrayList<>();
		BeanUtils.copyProperties(authorityUserDto, authorityUserPo);
		
		listPo = authorityUserService.queryPageListByCondition(authorityUserPo,pageNum,limit);
		for (AuthorityUserPO authorityUserPo2 : listPo) {
			AuthorityUserDTO authorityUserDto2 = new AuthorityUserDTO();
			BeanUtils.copyProperties(authorityUserPo2, authorityUserDto2);
			listDto.add(authorityUserDto2);
		}
		response.setCode(0L);
		response.setData(listDto);
		response.setMessage("成功");
		
		return response;
	}
	
	@Override
	public Response<Integer> getCountByCondition(AuthorityUserDTO authorityUserDto){
		
		Response<Integer> response = new Response<>();
		if(authorityUserDto == null){
			response.setMessage("authorityUserDto参数不可为空");
			return response;
		}
		AuthorityUserPO authorityUserpo = new AuthorityUserPO(); 
		BeanUtils.copyProperties(authorityUserDto, authorityUserpo);
		
		Integer count = authorityUserService.getCountByCondition(authorityUserpo);
		logger.info("count查询成功");
		
		response.setCode(0L);
		response.setResObject(count);
		response.setMessage("成功");
			
		return response;
	}
	

}