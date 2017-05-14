package net.wangxj.authority.service.rest.api;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.groups.Default;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import net.wangxj.util.annotation.NotRepeat;
import net.wangxj.util.validate.ValidationResult;
import net.wangxj.util.validate.groups.AddValidate;
import net.wangxj.util.validate.groups.DeleteValidate;
import net.wangxj.util.validate.groups.EditValidate;
import net.wangxj.authority.po.PO;
import net.wangxj.authority.po.Page;
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
		platformPo.setPlatformUuid(uuid);
		ValidationResult validateResult = validatePoAndNotRepeadField(platformPo, EditValidate.class);
		if(validateResult != null){
			return failValidate(validateResult);
		}
		else{
			//update
			Map<String,Object> updateResMap = new HashMap<>();
			updateResMap.put("success", platformService.update(platformPo) == 1 ? true : false);
			return success(updateResMap);
		}
	}
	
	/**
	 * http://localhost:9000/platforms?page_number=2&limit=3&order=asc&sort=platform_uuid
	 * 分页查询
	 */
	@GET
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response pageQuery(@BeanParam Page page){
		ValidationResult validateResult = validatePo(page, Default.class);
		ValidationResult validateSortRes = validateSort(PlatformPO.class, page.getSort());
		if(validateResult != null){
			return failValidate(validateResult);
		}
		else if(validateSortRes != null){
			return failValidate(validateSortRes);
		}
		else{
			Map<String , Object>  pageResMap = platformService.pageQuery(new PlatformPO(), page);
			return success(pageResMap);
		}
	}

	/**
	 * 删除  http://localhost:9000/platforms?delete_user=de0c7b2480494fda98db82f7a4707649&uuids=5e669e868cf04483802efeebe1608f9f,51f43adfea7045ff8c76b1433110c864
	 * @param user 删除人
	 * @param uuids 
	 * @return
	 */
	@DELETE
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response delete(@QueryParam(value="delete_user")String user , @QueryParam(value="uuids")String uuids) {
		logger.debug("delete_user:" + user + "----uuids:" + uuids);
		List<PlatformPO> listPo = new ArrayList<>();
		for (String uuid : uuids.split(",")) {
			PlatformPO platformPo = new PlatformPO();
			platformPo.setPlatformUuid(uuid);
			platformPo.setPlatformDelBy(user);
			ValidationResult validateResult = validatePo(platformPo, DeleteValidate.class);
			if(validateResult != null){
				return failValidate(validateResult);
			}
			listPo.add(platformPo);
		}
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("success", platformService.deleteBatch(listPo) > 0 ? true : false);
		return success(resultMap);
	}

	@Override
	protected String validateRepeat(PO po, String fieldName) throws NoSuchFieldException, SecurityException {
		PlatformPO platformPo = (PlatformPO) po;
		List<PlatformPO> existListPo = platformService.query(platformPo);
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