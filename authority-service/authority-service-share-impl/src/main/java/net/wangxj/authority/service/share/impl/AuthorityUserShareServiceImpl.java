
package net.wangxj.authority.service.share.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import net.wangxj.authority.service.share.AuthorityUserShareService;
import net.wangxj.authority.service.share.BaseAbstractAuthorityShareService;
import net.wangxj.util.string.TimeUtil;
import net.wangxj.util.string.UuidUtil;
import net.wangxj.util.validate.groups.AddValidate;
import net.wangxj.util.validate.groups.DeleteValidate;
import net.wangxj.util.validate.groups.EditValidate;
import net.wangxj.authority.Response;
import net.wangxj.authority.constant.DataDictionaryConstant;
import net.wangxj.authority.dto.AuthorityUserDTO;
import net.wangxj.authority.dto.PlatformDTO;
import net.wangxj.authority.po.AuthorityUserPO;
import net.wangxj.authority.service.AuthorityUserService;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:42
 */
public class AuthorityUserShareServiceImpl extends BaseAbstractAuthorityShareService implements AuthorityUserShareService{

	private static Logger logger = Logger.getLogger(AuthorityUserShareServiceImpl.class);
	
	@Resource
	private AuthorityUserService authorityUserService;
	
	@Override
	public Response<Integer> add(AuthorityUserDTO authorityUserDto){
		
		Response<Integer> response = new Response<Integer>();
		AuthorityUserPO authorityUserpo = new AuthorityUserPO(); 
		//验证
		String validateRes = validateDto(authorityUserDto, authorityUserpo, AddValidate.class);
		if(validateRes != null){
			response.setCode(1L);
			response.setMessage(validateRes);
			return response;
		}
		//设置增加时间，主键
		authorityUserpo.setUserAddTime(TimeUtil.getNowStr());
		authorityUserpo.setUserIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		authorityUserpo.setUserUuid(UuidUtil.newGUID());
		Integer uuid;
		try {
			uuid = authorityUserService.add(authorityUserpo);
			if(uuid>0){
				logger.debug("新增authorityUserDTO成功");
				response.setCode(0L);
				response.setResObject(uuid);
				response.setMessage("添加成功");
			}
			else{
				logger.debug("新增authorityUserDTO失败");
			}
		} catch (Exception e) {
			logger.debug("增加失败", e);
		}
		
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
		logger.debug("新增listauthorityUserDTO成功");
		response.setCode(0L);
		response.setResObject(count);
		return response;
	}
	
	@Override
	public Response<Integer> modifyByUuid(AuthorityUserDTO authorityUserDto){
		
		Response<Integer> response = new Response<>();
		AuthorityUserPO authorityUserpo = new AuthorityUserPO(); 
		//验证
		String validateRes = validateDto(authorityUserDto, authorityUserpo, EditValidate.class);
		if(validateRes != null){
			response.setCode(1L);
			response.setMessage(validateRes);
			return response;
		}
		authorityUserpo.setUserEditTime(TimeUtil.getNowStr());
		Integer count = authorityUserService.modifyByUuid(authorityUserpo);
		if(count > 0){
			logger.debug("authorityUser修改成功");
			response.setCode(0L);
			response.setResObject(count);
			response.setMessage("成功");
		}
		else{
			logger.debug("修改authorityUser失败");
		}
			
		return response;
	}
	
	@Override
	public Response<AuthorityUserDTO> queryListByCondition(AuthorityUserDTO authorityUserDto,boolean noDelete){
		
		Response<AuthorityUserDTO> response = new Response<>();
		if(authorityUserDto == null){
			response.setMessage("authorityUserDto参数不可为空");
			return response;
		}
		AuthorityUserPO authorityUserPo = new AuthorityUserPO();
		List<AuthorityUserPO> listPo = new ArrayList<>();
		List<AuthorityUserDTO> listDto = new ArrayList<>();
		BeanUtils.copyProperties(authorityUserDto, authorityUserPo);
		if(noDelete){
			authorityUserPo.setUserIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		}
		listPo = authorityUserService.queryListByCondition(authorityUserPo);
		logger.debug("查询queryList成功");
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
	public Response<AuthorityUserDTO> queryPageListByCondition(AuthorityUserDTO authorityUserDto, int pageNum, int limit,String order,String sort){
		
		Response<AuthorityUserDTO> response = new Response<>();
		if(authorityUserDto == null){
			response.setMessage("authorityUserDto参数不可为空");
			return response;
		}
		AuthorityUserPO authorityUserPo = new AuthorityUserPO();
		List<AuthorityUserPO> listPo = new ArrayList<>();
		List<AuthorityUserDTO> listDto = new ArrayList<>();
		BeanUtils.copyProperties(authorityUserDto, authorityUserPo);
		authorityUserPo.setUserIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		listPo = authorityUserService.queryPageListByCondition(authorityUserPo,pageNum,limit,order,sort);
		for (AuthorityUserPO authorityUserPo2 : listPo) {
			//添加人
			AuthorityUserPO userPo = new AuthorityUserPO();
			userPo.setUserUuid(authorityUserPo2.getUserAddBy());
			AuthorityUserPO addUserPo = authorityUserService.queryListByCondition(userPo).get(0);
			//编辑人
			AuthorityUserPO editUserPo = new AuthorityUserPO();
			editUserPo.setUserUuid(authorityUserPo2.getUserEditBy());
			List<AuthorityUserPO> editUserList = authorityUserService.queryListByCondition(editUserPo);
			
			//删除人
			AuthorityUserPO delUserPo = new AuthorityUserPO();
			delUserPo.setUserUuid(authorityUserPo2.getUserDelBy());
			List<AuthorityUserPO> delUserList = authorityUserService.queryListByCondition(delUserPo);
			
			AuthorityUserDTO authorityUserDto2 = new AuthorityUserDTO();
			BeanUtils.copyProperties(authorityUserPo2, authorityUserDto2);
			authorityUserDto2.setUserAddByName(addUserPo.getUserLoginName());
			authorityUserDto2.setUserEditByName(editUserList.size() != 1 ? "-" : editUserList.get(0).getUserLoginName());
			authorityUserDto2.setUserDelByName(delUserList.size() != 1 ? "-" : delUserList.get(0).getUserLoginName());
			listDto.add(authorityUserDto2);
		}
		response.setCode(0L);
		response.setData(listDto);
		response.setMessage("成功");
		
		return response;
	}
	
	@Override
	public Response<Integer> getCountByCondition(AuthorityUserDTO authorityUserDto,boolean noDelete){
		
		Response<Integer> response = new Response<>();
		if(authorityUserDto == null){
			response.setMessage("authorityUserDto参数不可为空");
			return response;
		}
		AuthorityUserPO authorityUserpo = new AuthorityUserPO(); 
		BeanUtils.copyProperties(authorityUserDto, authorityUserpo);
		if(noDelete){
			authorityUserpo.setUserIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		}
		Integer count = authorityUserService.getCountByCondition(authorityUserpo);
		logger.debug("count查询成功");
		
		response.setCode(0L);
		response.setResObject(count);
		response.setMessage("成功");
			
		return response;
	}
	
	@Override
	public Response<Integer> deleteByUuid(AuthorityUserDTO authorityUserDto){
		Response<Integer> response = new Response<>();
		AuthorityUserPO authorityUserPo = new AuthorityUserPO();
		//验证
		String validateRes = validateDto(authorityUserDto, authorityUserPo, DeleteValidate.class);
		if(validateRes != null){
			response.setCode(1L);
			response.setMessage(validateRes);
			return response;
		}
		authorityUserPo.setUserDelTime(TimeUtil.getNowStr());
		authorityUserPo.setUserIsDelete(DataDictionaryConstant.ISDELETE_YES_VALUE);
		Integer count = authorityUserService.modifyByUuid(authorityUserPo);
		if(count > 0){
			logger.debug("删除authorityUser成功");
			response.setCode(0L);
			response.setResObject(count);
			response.setMessage("成功");
		}
		else{
			logger.debug("删除authorityUser失败");
		}
		return response;
	}
	
	@Override
	public Response<Integer> deleteByBatch(List<AuthorityUserDTO> authorityUserList ){
		
		Response<Integer> response = new Response<>();
		List<AuthorityUserPO> userPoList = new ArrayList<>();
		//验证
		for (AuthorityUserDTO authorityDto : authorityUserList) {
			AuthorityUserPO authorityPo = new AuthorityUserPO();
			String validateRes = validateDto(authorityDto, authorityPo, DeleteValidate.class);
			if(validateRes != null){
				response.setCode(1L);
				response.setMessage(validateRes);
				return response;
			}
			authorityPo.setUserDelTime(TimeUtil.getNowStr());
			authorityPo.setUserIsDelete(DataDictionaryConstant.ISDELETE_YES_VALUE);
			userPoList.add(authorityPo);
		}
		Integer count = authorityUserService.modifyByBatch(userPoList);
		if(count == authorityUserList.size()){
			logger.debug("批量删除authorityUser成功");
			response.setCode(0L);
			response.setResObject(count);
			response.setMessage("成功");
		}
		else{
			logger.debug("删除authorityUser失败");
		}
		return response;
	} 
	

}