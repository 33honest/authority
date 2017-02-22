
package net.wangxj.authority.service.share.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import net.wangxj.authority.service.share.AuthorityUserRoleRelationShareService;
import net.wangxj.util.string.TimeUtil;
import net.wangxj.util.string.UuidUtil;
import net.wangxj.authority.Response;
import net.wangxj.authority.constant.DataDictionaryConstant;
import net.wangxj.authority.dto.AuthorityUserRoleRelationDTO;
import net.wangxj.authority.po.AuthorityUserRoleRelationPO;
import net.wangxj.authority.service.AuthorityUserRoleRelationService;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:43
 */
public class AuthorityUserRoleRelationShareServiceImpl implements AuthorityUserRoleRelationShareService{

	private static Logger logger = Logger.getLogger(AuthorityUserRoleRelationShareServiceImpl.class);
	
	@Resource
	private AuthorityUserRoleRelationService authorityUserRoleRelationService;
	
	@Override
	public Response<Integer> add(AuthorityUserRoleRelationDTO authorityUserRoleRelationDto){
		
		Response<Integer> response = new Response<Integer>();
		
		if(authorityUserRoleRelationDto == null){
			response.setMessage("传入参数不可为空");
			return response;
		}
		
		AuthorityUserRoleRelationPO authorityUserRoleRelationpo = new AuthorityUserRoleRelationPO(); 
		BeanUtils.copyProperties(authorityUserRoleRelationDto, authorityUserRoleRelationpo);
		
		Integer uuid = authorityUserRoleRelationService.add(authorityUserRoleRelationpo);
		logger.info("新增authorityUserRoleRelationDTO成功");
		response.setCode(0L);
		response.setResObject(uuid);
		return response;
	}
	
	@Override
	public Response<Integer> addBatch(List<AuthorityUserRoleRelationDTO> listDto){
		
		Response<Integer> response = new Response<Integer>();
		
		if(listDto == null && listDto.size()>0){
			response.setMessage("传入参数不可为空");
			return response;
		}
		
		List<AuthorityUserRoleRelationPO> listPo = new ArrayList<>();
		for(AuthorityUserRoleRelationDTO authorityUserRoleRelationDto : listDto){
			AuthorityUserRoleRelationPO authorityUserRoleRelationPo = new AuthorityUserRoleRelationPO();
			BeanUtils.copyProperties(authorityUserRoleRelationDto,authorityUserRoleRelationPo);
			authorityUserRoleRelationPo.setUrAddTime(TimeUtil.getNowStr());
			authorityUserRoleRelationPo.setUrIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
			authorityUserRoleRelationPo.setUrUuid(UuidUtil.newGUID());
			listPo.add(authorityUserRoleRelationPo);
		}
		Integer count = authorityUserRoleRelationService.addBatch(listPo);
		logger.info("新增listauthorityUserRoleRelationDTO成功");
		response.setCode(0L);
		response.setResObject(count);
		response.setMessage("成功");
		return response;
	}
	
	@Override
	public Response<Integer> modifyByUuid(AuthorityUserRoleRelationDTO authorityUserRoleRelationDto){
		
		Response<Integer> response = new Response<>();
		if(authorityUserRoleRelationDto == null){
			response.setMessage("authorityUserRoleRelationDto参数不可为空");
			return response;
		}
		AuthorityUserRoleRelationPO authorityUserRoleRelationpo = new AuthorityUserRoleRelationPO(); 
		BeanUtils.copyProperties(authorityUserRoleRelationDto, authorityUserRoleRelationpo);
		
		Integer count = authorityUserRoleRelationService.modifyByUuid(authorityUserRoleRelationpo);
		logger.info("modifyauthorityUserRoleRelationDTO修改成功");
			
		response.setCode(0L);
		response.setResObject(count);
		response.setMessage("成功");
			
		return response;
	}
	
	@Override
	public Response<AuthorityUserRoleRelationDTO> queryListByCondition(AuthorityUserRoleRelationDTO authorityUserRoleRelationDto){
		
		Response<AuthorityUserRoleRelationDTO> response = new Response<>();
		if(authorityUserRoleRelationDto == null){
			response.setMessage("authorityUserRoleRelationDto参数不可为空");
			return response;
		}
		AuthorityUserRoleRelationPO authorityUserRoleRelationPo = new AuthorityUserRoleRelationPO();
		List<AuthorityUserRoleRelationPO> listPo = new ArrayList<>();
		List<AuthorityUserRoleRelationDTO> listDto = new ArrayList<>();
		BeanUtils.copyProperties(authorityUserRoleRelationDto, authorityUserRoleRelationPo);
		
		listPo = authorityUserRoleRelationService.queryListByCondition(authorityUserRoleRelationPo);
		logger.info("查询queryList成功");
		for (AuthorityUserRoleRelationPO authorityUserRoleRelationPo2 : listPo) {
			AuthorityUserRoleRelationDTO authorityUserRoleRelationDto2 = new AuthorityUserRoleRelationDTO();
			BeanUtils.copyProperties(authorityUserRoleRelationPo2, authorityUserRoleRelationDto2);
			listDto.add(authorityUserRoleRelationDto2);
		}
		response.setCode(0L);
		response.setData(listDto);
		response.setMessage("成功");
		
		return response;
	}
	
	@Override
	public Response<AuthorityUserRoleRelationDTO> queryPageListByCondition(AuthorityUserRoleRelationDTO authorityUserRoleRelationDto, int pageNum, int limit){
		
		Response<AuthorityUserRoleRelationDTO> response = new Response<>();
		if(authorityUserRoleRelationDto == null){
			response.setMessage("authorityUserRoleRelationDto参数不可为空");
			return response;
		}
		AuthorityUserRoleRelationPO authorityUserRoleRelationPo = new AuthorityUserRoleRelationPO();
		List<AuthorityUserRoleRelationPO> listPo = new ArrayList<>();
		List<AuthorityUserRoleRelationDTO> listDto = new ArrayList<>();
		BeanUtils.copyProperties(authorityUserRoleRelationDto, authorityUserRoleRelationPo);
		
		listPo = authorityUserRoleRelationService.queryPageListByCondition(authorityUserRoleRelationPo,pageNum,limit);
		for (AuthorityUserRoleRelationPO authorityUserRoleRelationPo2 : listPo) {
			AuthorityUserRoleRelationDTO authorityUserRoleRelationDto2 = new AuthorityUserRoleRelationDTO();
			BeanUtils.copyProperties(authorityUserRoleRelationPo2, authorityUserRoleRelationDto2);
			listDto.add(authorityUserRoleRelationDto2);
		}
		response.setCode(0L);
		response.setData(listDto);
		response.setMessage("成功");
		
		return response;
	}
	
	@Override
	public Response<Integer> getCountByCondition(AuthorityUserRoleRelationDTO authorityUserRoleRelationDto){
		
		Response<Integer> response = new Response<>();
		if(authorityUserRoleRelationDto == null){
			response.setMessage("authorityUserRoleRelationDto参数不可为空");
			return response;
		}
		AuthorityUserRoleRelationPO authorityUserRoleRelationpo = new AuthorityUserRoleRelationPO(); 
		BeanUtils.copyProperties(authorityUserRoleRelationDto, authorityUserRoleRelationpo);
		
		Integer count = authorityUserRoleRelationService.getCountByCondition(authorityUserRoleRelationpo);
		logger.info("count查询成功");
		
		response.setCode(0L);
		response.setResObject(count);
		response.setMessage("成功");
			
		return response;
	}
	

}