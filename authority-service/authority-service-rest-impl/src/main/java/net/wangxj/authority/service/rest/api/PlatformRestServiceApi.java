package net.wangxj.authority.service.rest.api;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import net.wangxj.util.annotation.NotRepeat;
import net.wangxj.util.validate.ValidationResult;
import net.wangxj.util.validate.groups.AddValidate;
import net.wangxj.util.validate.groups.EditValidate;
import net.wangxj.authority.po.PO;
import net.wangxj.authority.po.PlatformPO;
import net.wangxj.authority.service.PlatformService;
import net.wangxj.authority.service.rest.exception.ValidateException;

/**
 * 
 * @author huoshan
 * created by 2017年4月8日 上午11:10:35
 *
 */
@Path("/platforms")
public class PlatformRestServiceApi extends AbstractAuthrotiyRestService {

	private static Logger logger = Logger.getLogger(PlatformRestServiceApi.class);
	
	@Autowired
	private PlatformService platformService;
	
	/**
	 * 添加平台
	 * @param platformPo
	 * @return {success: true/false]
	 */
	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response add(PlatformPO platformPo){
		//validate
		ValidationResult validateRes;
		try {
			validateRes = validatePoAndNotRepeadField(platformPo, AddValidate.class);
			if(validateRes != null){
				return failValidate(validateRes);
			}
			else{
				//添加
				Map<String,Object> result = new HashMap<>();
				result.put("success",  platformService.add(platformPo) == 1 ? true : false);
				return success(result);
			}
		} catch (ValidateException validateException) {
			logger.debug("验证出错,抛出异常----->", validateException);
			validateException.printStackTrace();
			return innerErrorResp();
		} catch (Exception otherException) {
			logger.debug("添加平台业务出错:--->", otherException);
			otherException.printStackTrace();
			return innerErrorResp();
		}
	}

	/**
	 * 修改平台(根据uuid)
	 * @param platformPo
	 * @return
	 */
	@PUT
	@Path("/{uuid}")
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response update(@PathParam(value = "uuid") String uuid,PlatformPO platformPo){
		try {
			platformPo.setPlatformUuid(uuid);
			ValidationResult validateResult = validatePoAndNotRepeadField(platformPo, EditValidate.class);
			if(validateResult != null){
				return failValidate(validateResult);
			}
			else{
				//update
				Map<String,Object> updateResMap = new HashMap<>();
				updateResMap.put("success", platformService.modifyByUuid(platformPo) == 1 ? true : false);
				return success(updateResMap);
			}
		} catch (ValidateException validateException) {
			logger.debug("修改验证出错: --->", validateException);
			validateException.printStackTrace();
			return innerErrorResp();
		}catch(Exception ex){
			logger.debug("修改逻辑出现错误:---->", ex);
			ex.printStackTrace();
			return innerErrorResp();
		}
	}
	
	/**
	 * 条件查询
	 * @param platformPo
	 * @return
	 */
	@GET
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response queryByConditions(PlatformPO platformPo){
		try{
			List<PlatformPO> conditionResult = platformService.queryListByCondition(platformPo);
			return success(conditionResult);
		}catch(Exception ex){
			logger.debug("条件查询出错:----->", ex);
			ex.printStackTrace();
			return innerErrorResp();
		}
	}
	
	
	public Response queryPageListByCondition(PlatformPO platformPo, int pageNum, int limit, String order, String sort){
		
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
			//添加人
			AuthorityUserPO userPo = new AuthorityUserPO();
			userPo.setUserUuid(platformPo2.getPlatformAddBy());
			AuthorityUserPO authorityUserPO = authorityUserService.queryListByCondition(userPo).get(0);
			//编辑人
			AuthorityUserPO editUserPo = new AuthorityUserPO();
			editUserPo.setUserUuid(platformPo2.getPlatformEditBy());
			List<AuthorityUserPO> editUserList = authorityUserService.queryListByCondition(editUserPo);
			
			//删除人
			AuthorityUserPO delUserPo = new AuthorityUserPO();
			delUserPo.setUserUuid(platformPo2.getPlatformDelBy());
			List<AuthorityUserPO> delUserList = authorityUserService.queryListByCondition(delUserPo);
			
			PlatformDTO platformDto2 = new PlatformDTO();
			BeanUtils.copyProperties(platformPo2, platformDto2);
			platformDto2.setPlatformAddByName(authorityUserPO.getUserLoginName());
			platformDto2.setPlatformEditByName(editUserList.isEmpty() ? "" : editUserList.get(0).getUserLoginName());
			platformDto2.setPlatformDelByName(delUserList.isEmpty() ? "" : delUserList.get(0).getUserLoginName());
			listDto.add(platformDto2);
		}
//		response.setCode(0L);
//		response.setData(listDto);
//		response.setMessage("成功");
//		
//		return response;
//	}
//	
//	@Override
//	public Response<Integer> getCountByCondition(PlatformDTO platformDto,boolean noDelete){
//		
//		Response<Integer> response = new Response<>();
//		if(platformDto == null){
//			response.setMessage("platformDto参数不可为空");
//			return response;
//		}
//		PlatformPO platformpo = new PlatformPO(); 
//		BeanUtils.copyProperties(platformDto, platformpo);
//		if(noDelete){
//			platformpo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
//		}
//		Integer count = platformService.getCountByCondition(platformpo);
//		logger.debug("count查询成功");
//		
//		response.setCode(0L);
//		response.setResObject(count);
//		response.setMessage("成功");
//			
//		return response;
//	}
//
//	@Override
//	public Response<Integer> deleteByUuid(PlatformDTO platformDto) {
//		Response<Integer> response = new Response<>();
//		PlatformPO platformpo = new PlatformPO(); 
//		//验证
//		String validateRes = validateDto(platformDto, platformpo, DeleteValidate.class);
//		if(validateRes != null){
//			response.setCode(1L);
//			response.setMessage(validateRes);
//			return response;
//		}
//		platformpo.setPlatformDelTime(TimeUtil.getNowStr());
//		platformpo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_YES_VALUE);
//		Integer count = platformService.modifyByUuid(platformpo);
//		if(count > 0){
//			logger.debug("deleteplatformDTO修改成功");
//			response.setCode(0L);
//			response.setResObject(count);
//			response.setMessage("成功");
//		}
//		else{
//			logger.debug("删除platform失败");
//		}
//		return response;
//	}
//
//	@Override
//	public Response<Integer> deleteByBatch(List<PlatformDTO> platformList) {
//		Response<Integer> response = new Response<>();
//		List<PlatformPO> platformPoList = new ArrayList<>();
//		//验证
//		for (PlatformDTO platformDto : platformList) {
//			PlatformPO platformPo = new PlatformPO();
//			String validateRes = validateDto(platformDto, platformPo, DeleteValidate.class);
//			if(validateRes != null){
//				response.setCode(1L);
//				response.setMessage(validateRes);
//				return response;
//			}
//			platformPo.setPlatformDelTime(TimeUtil.getNowStr());
//			platformPo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_YES_VALUE);
//			platformPoList.add(platformPo);
//		}
//		Integer count = platformService.modifyByBatch(platformPoList);
//		if(count == platformList.size()){
//			logger.debug("批量删除platform成功");
//			response.setCode(0L);
//			response.setResObject(count);
//			response.setMessage("成功");
//		}
//		else{
//			logger.debug("删除platform失败");
//		}
//		return response;
//	}
//	

	@Override
	protected String validateRepeat(PO po, String fieldName) throws NoSuchFieldException, SecurityException {
		PlatformPO platformPo = (PlatformPO) po;
		List<PlatformPO> existListPo = platformService.queryListByCondition(platformPo);
		if(existListPo == null || existListPo.size() == 0){
			return null;
		}
		else if(existListPo.size() == 1 && existListPo.get(0).getPlatformUuid().equals(platformPo.getPlatformUuid())){
			return null;
		}
		else{
			Field annotatedNotRepeatFiled = PlatformPO.class.getDeclaredField(fieldName);
			NotRepeat notRepeatAnnotation = annotatedNotRepeatFiled.getAnnotation(NotRepeat.class);
			return notRepeatAnnotation.message();
		}
	}

	
}