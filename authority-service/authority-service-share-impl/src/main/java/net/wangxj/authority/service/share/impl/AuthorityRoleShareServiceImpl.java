
package net.wangxj.authority.service.share.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import net.wangxj.authority.service.share.AuthorityRoleShareService;
import net.wangxj.authority.service.share.BaseAbstractAuthorityShareService;
import net.wangxj.util.string.TimeUtil;
import net.wangxj.util.string.UuidUtil;
import net.wangxj.util.validate.groups.AddValidate;
import net.wangxj.util.validate.groups.DeleteValidate;
import net.wangxj.util.validate.groups.EditValidate;
import net.wangxj.authority.Response;
import net.wangxj.authority.constant.DataDictionaryConstant;
import net.wangxj.authority.dto.AuthorityRoleDTO;
import net.wangxj.authority.dto.AuthorityUserDTO;
import net.wangxj.authority.po.AuthorityRolePO;
import net.wangxj.authority.po.AuthorityUserPO;
import net.wangxj.authority.service.AuthorityRoleService;
import net.wangxj.authority.service.AuthorityUserService;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:40
 */
public class AuthorityRoleShareServiceImpl extends BaseAbstractAuthorityShareService implements AuthorityRoleShareService{

	private static Logger logger = Logger.getLogger(AuthorityRoleShareServiceImpl.class);
	
	@Resource
	private AuthorityRoleService authorityRoleService;
	@Resource
	private AuthorityUserService authorityUserService;
	
	@Override
	public Response<Integer> add(AuthorityRoleDTO authorityRoleDto){
		
		Response<Integer> response = new Response<Integer>();
		AuthorityRolePO authorityRolepo = new AuthorityRolePO(); 
		//验证
		String validateRes = validateDto(authorityRoleDto, authorityRolepo, AddValidate.class);
		if(validateRes != null){
			response.setCode(1L);
			response.setMessage(validateRes);
			return response;
		}
		authorityRolepo.setRoleAddTime(TimeUtil.getNowStr());
		authorityRolepo.setRoleIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		authorityRolepo.setRoleUuid(UuidUtil.newGUID());
		Integer uuid = authorityRoleService.add(authorityRolepo);
		if(uuid>0){
			logger.debug("新增authorityRoleDTO成功");
			response.setCode(0L);
			response.setResObject(uuid);
			response.setMessage("添加成功");
		}
		else{
			logger.debug("新增authorityRoleDTO失败");
		}
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
		AuthorityRolePO authorityRolepo = new AuthorityRolePO();
		//验证
		String validateRes = validateDto(authorityRoleDto, authorityRolepo, EditValidate.class);
		if(validateRes != null){
			response.setCode(1L);
			response.setMessage(validateRes);
			return response;
		}
		authorityRolepo.setRoleEditTime(TimeUtil.getNowStr());
		Integer count = authorityRoleService.modifyByUuid(authorityRolepo);
		if(count > 0){
			logger.debug("authorityRole修改成功");
			response.setCode(0L);
			response.setResObject(count);
			response.setMessage("成功");
		}
		else{
			logger.debug("修改authorityRole失败");
		}
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
	public Response<AuthorityRoleDTO> queryPageListByCondition(AuthorityRoleDTO authorityRoleDto, int pageNum, int limit,String order,String sort){
		
		Response<AuthorityRoleDTO> response = new Response<>();
		if(authorityRoleDto == null){
			response.setMessage("authorityRoleDto参数不可为空");
			return response;
		}
		AuthorityRolePO authorityRolePo = new AuthorityRolePO();
		List<AuthorityRolePO> listPo = new ArrayList<>();
		List<AuthorityRoleDTO> listDto = new ArrayList<>();
		BeanUtils.copyProperties(authorityRoleDto, authorityRolePo);
		authorityRolePo.setRoleIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		listPo = authorityRoleService.queryPageListByCondition(authorityRolePo,pageNum,limit,order,sort);
		for (AuthorityRolePO authorityRolePo2 : listPo) {
			//添加人
			AuthorityUserPO userPo = new AuthorityUserPO();
			userPo.setUserUuid(authorityRolePo2.getRoleAddBy());
			AuthorityUserPO addUser = authorityUserService.queryListByCondition(userPo).get(0);
			//编辑人
			AuthorityUserPO editUserPo = new AuthorityUserPO();
			editUserPo.setUserUuid(authorityRolePo2.getRoleEditBy());
			List<AuthorityUserPO> editUserList = authorityUserService.queryListByCondition(editUserPo);
			//删除人
			AuthorityUserPO deleteUserPo = new AuthorityUserPO();
			deleteUserPo.setUserUuid(authorityRolePo2.getRoleDelBy());
			List<AuthorityUserPO> deleteUserList = authorityUserService.queryListByCondition(deleteUserPo);
			
			AuthorityRoleDTO authorityRoleDto2 = new AuthorityRoleDTO();
			BeanUtils.copyProperties(authorityRolePo2, authorityRoleDto2);
			authorityRoleDto2.setRoleAddByName(addUser.getUserLoginName());
			authorityRoleDto2.setRoleEditByName(editUserList.size() != 1 ? "-" : editUserList.get(0).getUserLoginName());
			authorityRoleDto2.setRoleDelByName(deleteUserList.size() != 1 ? "-" : deleteUserList.get(0).getUserLoginName());
			listDto.add(authorityRoleDto2);
		}
		response.setCode(0L);
		response.setData(listDto);
		response.setMessage("成功");
		
		return response;
	}
	
	@Override
	public Response<Integer> getCountByCondition(AuthorityRoleDTO authorityRoleDto, boolean noDelete){
		
		Response<Integer> response = new Response<>();
		if(authorityRoleDto == null){
			response.setMessage("authorityRoleDto参数不可为空");
			return response;
		}
		AuthorityRolePO authorityRolepo = new AuthorityRolePO(); 
		BeanUtils.copyProperties(authorityRoleDto, authorityRolepo);
		if(noDelete){
			authorityRolepo.setRoleIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		}
		Integer count = authorityRoleService.getCountByCondition(authorityRolepo);
		logger.info("count查询成功");
		
		response.setCode(0L);
		response.setResObject(count);
		response.setMessage("成功");
		return response;
	}
	
	@Override
	public Response<Integer> deleteByUuid(AuthorityRoleDTO roleDto){
		Response<Integer> response = new Response<>();
		AuthorityRolePO authorityRolePO = new AuthorityRolePO();
		//验证
		String validateRes = validateDto(roleDto, authorityRolePO, DeleteValidate.class);
		if(validateRes != null){
			response.setCode(1L);
			response.setMessage(validateRes);
			return response;
		}
		authorityRolePO.setRoleDelTime(TimeUtil.getNowStr());
		authorityRolePO.setRoleIsDelete(DataDictionaryConstant.ISDELETE_YES_VALUE);
		Integer count = authorityRoleService.modifyByUuid(authorityRolePO);
		if(count > 0){
			logger.debug("删除authorityRoleDto成功");
			response.setCode(0L);
			response.setResObject(count);
			response.setMessage("成功");
		}
		else{
			logger.debug("删除authorityRoleDto失败");
		}
		return response;
	}
	
	@Override
	public Response<Integer> deleteByBatch(List<AuthorityRoleDTO> roleList){
		Response<Integer> response = new Response<>();
		List<AuthorityRolePO> rolePOList = new ArrayList<>();
		//验证
		for (AuthorityRoleDTO roleDto : roleList) {
			AuthorityRolePO rolePo = new AuthorityRolePO();
			String validateMsg = validateDto(roleDto, rolePo, DeleteValidate.class);
			if(validateMsg != null){
				response.setCode(1L);
				response.setMessage(validateMsg);
				return response;
			}
			rolePo.setRoleDelTime(TimeUtil.getNowStr());
			rolePo.setRoleIsDelete(DataDictionaryConstant.ISDELETE_YES_VALUE);
			rolePOList.add(rolePo);
		}
		Integer count = authorityRoleService.modifyByBatch(rolePOList);
		if(count == roleList.size()){
			logger.debug("批量删除authorityRole成功");
			response.setCode(0L);
			response.setResObject(count);
			response.setMessage("成功");
		}
		else{
			logger.debug("批量删除authorityRoleDto成功");
		}
		return response;
	}

}