
package net.wangxj.authority.service.share.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import net.wangxj.authority.service.share.AuthorityRoleShareService;
import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityRoleDTO;
import net.wangxj.authority.po.AuthorityRolePO;
import net.wangxj.authority.service.AuthorityRoleService;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:40
 */
public class AuthorityRoleShareServiceImpl implements AuthorityRoleShareService{

	private static Logger logger = Logger.getLogger(AuthorityRoleShareServiceImpl.class);
	
	@Resource
	private AuthorityRoleService authorityRoleService;
	
	@Override
	public Response<Integer> add(AuthorityRoleDTO authorityRoleDto){
		
		Response<Integer> response = new Response<Integer>();
		
		if(authorityRoleDto == null){
			response.setMessage("传入参数不可为空");
			return response;
		}
		
		AuthorityRolePO authorityRolepo = new AuthorityRolePO(); 
		BeanUtils.copyProperties(authorityRoleDto, authorityRolepo);
		
		Integer uuid = authorityRoleService.add(authorityRolepo);
		logger.info("新增authorityRoleDTO成功");
		response.setCode(0L);
		response.setResObject(uuid);
		return response;
	}
	
	@Override
	public Response<Integer> addBatch(List<AuthorityRoleDTO> listDto){
		
		Response<Integer> response = new Response<Integer>();
		
		if(listDto == null && listDto.size()>0){
			response.setMessage("传入参数不可为空");
			return response;
		}
		
		List<AuthorityRolePO> listPo = new ArrayList<>();
		for(AuthorityRoleDTO authorityRoleDto : listDto){
			AuthorityRolePO authorityRolePo = new AuthorityRolePO();
			BeanUtils.copyProperties(authorityRoleDto,authorityRolePo);
			listPo.add(authorityRolePo);
		}
		Integer count = authorityRoleService.addBatch(listPo);
		logger.info("新增listauthorityRoleDTO成功");
		response.setCode(0L);
		response.setResObject(count);
		return response;
	}
	
	@Override
	public Response<Integer> modifyByUuid(AuthorityRoleDTO authorityRoleDto){
		
		Response<Integer> response = new Response<>();
		if(authorityRoleDto == null){
			response.setMessage("authorityRoleDto参数不可为空");
			return response;
		}
		AuthorityRolePO authorityRolepo = new AuthorityRolePO(); 
		BeanUtils.copyProperties(authorityRoleDto, authorityRolepo);
		
		Integer count = authorityRoleService.modifyByUuid(authorityRolepo);
		logger.info("modifyauthorityRoleDTO修改成功");
			
		response.setCode(0L);
		response.setResObject(count);
		response.setMessage("成功");
			
		return response;
	}
	
	@Override
	public Response<AuthorityRoleDTO> queryListByCondition(AuthorityRoleDTO authorityRoleDto){
		
		Response<AuthorityRoleDTO> response = new Response<>();
		if(authorityRoleDto == null){
			response.setMessage("authorityRoleDto参数不可为空");
			return response;
		}
		AuthorityRolePO authorityRolePo = new AuthorityRolePO();
		List<AuthorityRolePO> listPo = new ArrayList<>();
		List<AuthorityRoleDTO> listDto = new ArrayList<>();
		BeanUtils.copyProperties(authorityRoleDto, authorityRolePo);
		
		listPo = authorityRoleService.queryListByCondition(authorityRolePo);
		logger.info("查询queryList成功");
		for (AuthorityRolePO authorityRolePo2 : listPo) {
			AuthorityRoleDTO authorityRoleDto2 = new AuthorityRoleDTO();
			BeanUtils.copyProperties(authorityRolePo2, authorityRoleDto2);
			listDto.add(authorityRoleDto2);
		}
		response.setCode(0L);
		response.setData(listDto);
		response.setMessage("成功");
		
		return response;
	}
	
	@Override
	public Response<AuthorityRoleDTO> queryPageListByCondition(AuthorityRoleDTO authorityRoleDto, int pageNum, int limit){
		
		Response<AuthorityRoleDTO> response = new Response<>();
		if(authorityRoleDto == null){
			response.setMessage("authorityRoleDto参数不可为空");
			return response;
		}
		AuthorityRolePO authorityRolePo = new AuthorityRolePO();
		List<AuthorityRolePO> listPo = new ArrayList<>();
		List<AuthorityRoleDTO> listDto = new ArrayList<>();
		BeanUtils.copyProperties(authorityRoleDto, authorityRolePo);
		
		listPo = authorityRoleService.queryPageListByCondition(authorityRolePo,pageNum,limit);
		for (AuthorityRolePO authorityRolePo2 : listPo) {
			AuthorityRoleDTO authorityRoleDto2 = new AuthorityRoleDTO();
			BeanUtils.copyProperties(authorityRolePo2, authorityRoleDto2);
			listDto.add(authorityRoleDto2);
		}
		response.setCode(0L);
		response.setData(listDto);
		response.setMessage("成功");
		
		return response;
	}
	
	@Override
	public Response<Integer> getCountByCondition(AuthorityRoleDTO authorityRoleDto){
		
		Response<Integer> response = new Response<>();
		if(authorityRoleDto == null){
			response.setMessage("authorityRoleDto参数不可为空");
			return response;
		}
		AuthorityRolePO authorityRolepo = new AuthorityRolePO(); 
		BeanUtils.copyProperties(authorityRoleDto, authorityRolepo);
		
		Integer count = authorityRoleService.getCountByCondition(authorityRolepo);
		logger.info("count查询成功");
		
		response.setCode(0L);
		response.setResObject(count);
		response.setMessage("成功");
			
		return response;
	}
	

}