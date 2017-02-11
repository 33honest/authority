
package net.wangxj.authority.service.share.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import net.wangxj.authority.service.share.AuthorityResourcesShareService;
import net.wangxj.authority.service.share.BaseAbstractAuthorityShareService;
import net.wangxj.util.string.TimeUtil;
import net.wangxj.util.validate.groups.AddValidate;
import net.wangxj.util.validate.groups.DeleteValidate;
import net.wangxj.util.validate.groups.EditValidate;
import net.wangxj.authority.Response;
import net.wangxj.authority.constant.DataDictionaryConstant;
import net.wangxj.authority.dto.AuthorityResourcesDTO;
import net.wangxj.authority.po.AuthorityResourcesPO;
import net.wangxj.authority.po.AuthorityUserPO;
import net.wangxj.authority.po.PlatformPO;
import net.wangxj.authority.service.AuthorityResourcesService;
import net.wangxj.authority.service.AuthorityUserService;
import net.wangxj.authority.service.PlatformService;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:38
 */
public class AuthorityResourcesShareServiceImpl extends BaseAbstractAuthorityShareService implements AuthorityResourcesShareService{

	private static Logger logger = Logger.getLogger(AuthorityResourcesShareServiceImpl.class);
	
	@Resource
	private AuthorityResourcesService authorityResourcesService;
	@Resource
	private AuthorityUserService authorityUserService;
	@Resource
	private PlatformService platformService;
	
	@Override
	public Response<Integer> add(AuthorityResourcesDTO authorityResourcesDto){
		
		Response<Integer> response = new Response<Integer>();
		AuthorityResourcesPO authorityResourcespo = new AuthorityResourcesPO(); 
		//验证
		String validateRes = validateDto(authorityResourcesDto, authorityResourcespo, AddValidate.class);
		if(validateRes != null){
			response.setCode(1L);
			response.setMessage(validateRes);
			return response;
		}
		authorityResourcespo.setResourceAddTime(TimeUtil.getNowStr());
		authorityResourcespo.setResourceIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		Integer uuid = authorityResourcesService.add(authorityResourcespo);
		if(uuid>0){
			logger.debug("新增authorityResourceDto成功");
			response.setCode(0L);
			response.setResObject(uuid);
			response.setMessage("添加成功");
		}
		else{
			logger.debug("新增authorityResourceDto失败");
		}
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
		AuthorityResourcesPO authorityResourcespo = new AuthorityResourcesPO(); 
		//验证
		String validateRes = validateDto(authorityResourcesDto, authorityResourcespo, EditValidate.class);
		if(validateRes != null){
			response.setCode(1L);
			response.setMessage(validateRes);
			return response;
		}
		authorityResourcespo.setResourceEditTime(TimeUtil.getNowStr());
		Integer count = authorityResourcesService.modifyByUuid(authorityResourcespo);
		if(count > 0){
			logger.debug("authorityResourceDto修改成功");
			response.setCode(0L);
			response.setResObject(count);
			response.setMessage("成功");
		}else{
			logger.debug("修改authorityResourceDto失败");
		}
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
		authorityResourcesPo.setResourceIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
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
	public Response<AuthorityResourcesDTO> queryPageListByCondition(AuthorityResourcesDTO authorityResourcesDto, int pageNum, int limit, String order, String sort){
		
		Response<AuthorityResourcesDTO> response = new Response<>();
		if(authorityResourcesDto == null){
			response.setMessage("authorityResourcesDto参数不可为空");
			return response;
		}
		AuthorityResourcesPO authorityResourcesPo = new AuthorityResourcesPO();
		List<AuthorityResourcesPO> listPo = new ArrayList<>();
		List<AuthorityResourcesDTO> listDto = new ArrayList<>();
		BeanUtils.copyProperties(authorityResourcesDto, authorityResourcesPo);
		authorityResourcesPo.setResourceIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		listPo = authorityResourcesService.queryPageListByCondition(authorityResourcesPo,pageNum,limit,order,sort);
		for (AuthorityResourcesPO authorityResourcesPo2 : listPo) {
			//添加人
			AuthorityUserPO addUser = new AuthorityUserPO();
			addUser.setUserUuid(authorityResourcesPo2.getResourceAddBy());
			AuthorityUserPO addUserPo = authorityUserService.queryListByCondition(addUser).get(0);
			//编辑人
			AuthorityUserPO editUser = new AuthorityUserPO();
			editUser.setUserUuid(authorityResourcesPo2.getResourceEditBy());
			List<AuthorityUserPO> editUserList = authorityUserService.queryListByCondition(editUser);
			//删除人
			AuthorityUserPO deleteUser = new AuthorityUserPO();
			deleteUser.setUserUuid(authorityResourcesPo2.getResourceDelBy());
			List<AuthorityUserPO> deleteUserList = authorityUserService.queryListByCondition(deleteUser);
			//所属平台
			PlatformPO platformPo = new PlatformPO();
			platformPo.setPlatformUuid(authorityResourcesPo2.getResourcePlatformUuid());
			PlatformPO platformPO2 = platformService.queryListByCondition(platformPo).get(0);
			//父级资源
			AuthorityResourcesPO parentPo = new AuthorityResourcesPO();
			parentPo.setResourceParentUuid(authorityResourcesPo2.getResourceParentUuid());
			AuthorityResourcesPO parentResource = authorityResourcesService.queryListByCondition(parentPo).get(0);
			
			AuthorityResourcesDTO authorityResourcesDto2 = new AuthorityResourcesDTO();
			BeanUtils.copyProperties(authorityResourcesPo2, authorityResourcesDto2);
			authorityResourcesDto2.setResourceAddByName(addUserPo.getUserLoginName());
			authorityResourcesDto2.setResourceEditByName(editUserList.size() != 1 ? "-" : editUserList.get(0).getUserLoginName());
			authorityResourcesDto2.setResourceDelByName(deleteUserList.size() !=1 ? "-" : deleteUserList.get(0).getUserLoginName());
			authorityResourcesDto2.setResourcePlatformName(platformPO2.getPlatformName());
			authorityResourcesDto2.setResourceParentName(parentResource.getResourceName());
			listDto.add(authorityResourcesDto2);
		}
		response.setCode(0L);
		response.setData(listDto);
		response.setMessage("成功");
		
		return response;
	}
	
	@Override
	public Response<Integer> getCountByCondition(AuthorityResourcesDTO authorityResourcesDto, boolean noDelete){
		
		Response<Integer> response = new Response<>();
		if(authorityResourcesDto == null){
			response.setMessage("authorityResourcesDto参数不可为空");
			return response;
		}
		AuthorityResourcesPO authorityResourcespo = new AuthorityResourcesPO(); 
		BeanUtils.copyProperties(authorityResourcesDto, authorityResourcespo);
		if(noDelete){
			authorityResourcespo.setResourceIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		}
		Integer count = authorityResourcesService.getCountByCondition(authorityResourcespo);
		logger.info("count查询成功");
		
		response.setCode(0L);
		response.setResObject(count);
		response.setMessage("成功");
			
		return response;
	}
	
	@Override
	public Response<Integer> deleteByUuid(AuthorityResourcesDTO resourceDto){
		Response<Integer> response = new Response<>();
		AuthorityResourcesPO authorityResourcePo = new AuthorityResourcesPO();
		//验证
		String validateRes = validateDto(resourceDto, authorityResourcePo, DeleteValidate.class);
		if(validateRes != null){
			response.setCode(1L);
			response.setMessage(validateRes);
			return response;
		}
		authorityResourcePo.setResourceDelTime(TimeUtil.getNowStr());
		authorityResourcePo.setResourceIsDelete(DataDictionaryConstant.ISDELETE_YES_VALUE);
		Integer count = authorityResourcesService.modifyByUuid(authorityResourcePo);
		if(count > 0){
			logger.debug("删除authorityResourceDto成功");
			response.setCode(0L);
			response.setResObject(count);
			response.setMessage("成功");
		}
		else{
			logger.debug("删除authorityResourceDto失败");
		}
		return response;
	}
	
	@Override
	public Response<Integer> deleteByBatch(List<AuthorityResourcesDTO> listResource){
		Response<Integer> response = new Response<>();
		List<AuthorityResourcesPO> resourcePOList = new ArrayList<>();
		//验证
		for (AuthorityResourcesDTO authorityResourcesDTO : listResource) {
			AuthorityResourcesPO resourcePo = new AuthorityResourcesPO();
			String validateMsg = validateDto(authorityResourcesDTO, resourcePo, DeleteValidate.class);
			if(validateMsg != null){
				response.setCode(1L);
				response.setMessage(validateMsg);
				return response;
			}
			resourcePo.setResourceDelTime(TimeUtil.getNowStr());
			resourcePo.setResourceIsDelete(DataDictionaryConstant.ISDELETE_YES_VALUE);
			resourcePOList.add(resourcePo);
		}
		Integer count = authorityResourcesService.modifyByBatch(resourcePOList);
		if(count == listResource.size()){
			logger.debug("批量删除authorityResource成功");
			response.setCode(0L);
			response.setResObject(count);
			response.setMessage("成功");
		}else{
			logger.debug("批量删除authorityResourceDto成功");
		}
		return response;
	}

}