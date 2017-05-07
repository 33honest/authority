package net.wangxj.authority.service.rest.api;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import net.wangxj.authority.po.PO;
import net.wangxj.authority.service.rest.AuthorityRestService;
import net.wangxj.authority.service.rest.exception.ValidateException;
import net.wangxj.util.annotation.util.NotRepeatUtil;
import net.wangxj.util.validate.ValidateUtil;
import net.wangxj.util.validate.ValidationResult;

/**
 * @author huoshan
 * created by 2017年4月9日 上午8:26:12
 * 
 */
public abstract class AbstractAuthrotiyRestService implements AuthorityRestService {
	
	private static Logger logger = Logger.getLogger(AbstractAuthrotiyRestService.class);
	
	/**
	 * 验证PO 
	 * @param po  
	 * @param clazz 验证类型
	 * @return　如果返回null:验证通过,不为null验证未通过
	 * @throws ValidateException 
	 */
	public ValidationResult validatePo(PO po,Class clazz) throws ValidateException {
		try{
			ValidationResult validateRes = new ValidationResult();
			//验证
			validateRes = ValidateUtil.validateEntity(po, clazz);
			if(!validateRes.getIsPass()){
				return validateRes;
			}
		}catch (Exception e) {
			logger.debug("验证PO出错:", e);
			e.printStackTrace();
			throw new ValidateException("验证PO出错", e.getCause());
		}
		return null;
	}
	
	/**
	 * 验证,不可重覆字段
	 * @param po
	 * @param authorityService
	 * @return　　如果返回null:验证通过,不为null验证未通过
	 * @throws ValidateException 
	 */
	public ValidationResult validateRepeat(PO po) throws ValidateException  {
		try{
			//获取po中注解有@NotRepeat的字段 返回:{{"fieldName" : po}}
			List<Map<String, Object>> fieldList = NotRepeatUtil.buildObjFromAnnotatedField(po);
			for (Map<String, Object> map : fieldList) {
				Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
				while(iterator.hasNext()){
					Entry<String, Object> nameObjEntry = iterator.next();
					String fieldName = nameObjEntry.getKey();
					PO singleFieldPo = (PO) nameObjEntry.getValue();
					String errorMsg;
					errorMsg = validateRepeat(singleFieldPo, fieldName);
					logger.debug("验证" + fieldName + ":---->" + errorMsg);
					//如果不重复
					if(errorMsg == null){
						continue;
					}
					else{
						ValidationResult validateResult = new ValidationResult();
						validateResult.setErrorMsg(errorMsg);
						return validateResult;
					}
				}
			}
		}catch (Exception e) {
			logger.debug("验证出错", e);
			e.printStackTrace();
			throw new ValidateException("验证出错", e.getCause());
		}
		
		return null;
	}
	
	/**
	 * 校验po和不可重复字段
	 * @param po
	 * @param clazz
	 * @return
	 * @throws ValidateException 
	 */
	public ValidationResult validatePoAndNotRepeadField(PO po , Class clazz) throws ValidateException{
		if(validatePo(po, clazz) != null){
			return validatePo(po, clazz);
		} else{
			if(validateRepeat(po) != null){
				return validateRepeat(po);
			}
			else{
				return null;
			}
		}
	}
	
	/**
	 * 具体校验重复逻辑，由子类实现,不重复返回Null,重复不为null
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	protected abstract String validateRepeat(PO po, String fieldName) throws NoSuchFieldException, SecurityException;
	
	
	/**
	 * 验证失败
	 * @param errorMsg
	 * @return
	 */
	public Response failValidate(Object errorMsg){
		return Response.status(Status.BAD_REQUEST).entity(errorMsg).build();
	}
	/**
	 * 服务器内部发生错误（内部程序发生异常,错误等）
	 * @return
	 */
	public Response innerErrorResp(){
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{error:　服务器内部发生错误}").build();
	}
	
	/**
	 * 成功执行
	 * @param returnObj
	 * @return
	 */
	public Response success(Object returnObj){
		return Response.ok().entity(JSONObject.toJSONString(returnObj)).build();
	}
	
	
	
	
	
}
