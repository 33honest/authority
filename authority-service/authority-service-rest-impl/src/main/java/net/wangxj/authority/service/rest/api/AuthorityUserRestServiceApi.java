package net.wangxj.authority.service.rest.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import net.wangxj.util.string.TimeUtil;
import net.wangxj.util.string.UuidUtil;
import net.wangxj.util.validate.ValidationResult;
import net.wangxj.util.validate.groups.AddValidate;
import net.wangxj.util.validate.groups.DeleteValidate;
import net.wangxj.util.validate.groups.EditValidate;
import net.wangxj.authority.po.AuthorityUserPO;
import net.wangxj.authority.service.AuthorityUserService;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:42
 */
@Path("/users")
@Produces({"application/json"})
@Consumes({"application/json"})
public class AuthorityUserRestServiceApi extends AbstractAuthrotiyRestService{

	private static Logger logger = Logger.getLogger(AuthorityUserRestServiceApi.class);
	
	@Autowired
	private AuthorityUserService authorityUserService;
	
	/**
	 * 添加用户
	 * @param userPo
	 * @return
	 * @throws Exception
	 * 
	 * apidoc------------>
	 * @api {POST} /users 添加用户
	 * @apiExample {curl} curl请求示例:
	 * curl -H "Content-Type:application/json" -H "Accept:application/json" -i -X POST -d '
	 * {"user_login_name":"CE_SHI",
	 *  "user_login_password":"123456",
	 *  "user_email":"ceshi@qq.com",
	 *  "user_phone":"13211563421",
	 *  "user_status":1,
	 *  "user_type" : 1,
	 *  "user_add_type" : 1,
	 *  "user_add_by" : "0cf700bfd72142c498ff7508aa2603c3"}' http://localhost:9000/api/users 
	 *  @apiGroup users
	 *  
	 */
	@POST
	public Response add(AuthorityUserPO userPo) throws Exception{
		logger.debug("验证---->");
		ValidationResult addValidateResult = authorityUserService.validatePoAndNotRepeadField(userPo, AddValidate.class);
		logger.debug("验证结果:--->" + addValidateResult);
		if(addValidateResult != null){
			return failValidate(addValidateResult);
		}else{
			logger.debug("添加操作开始---->");
			Map<String , Boolean> addResMap = new HashMap<>();
			addResMap.put("success", authorityUserService.add(userPo) == 1 ? true : false);
			return success(addResMap);
		}
	}
	
	/*@Override
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
			logger.debug("批量删除authorityUser失败");
		}
		return response;
	} 
	*/

}