package net.wangxj.authority.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import net.wangxj.authority.exception.ValidateException;
import net.wangxj.authority.po.PO;
import net.wangxj.authority.po.Page;
import net.wangxj.util.annotation.util.JsonFieldUtil;
import net.wangxj.util.annotation.util.NotRepeatUtil;
import net.wangxj.util.reflect.ClazzUtil;
import net.wangxj.util.validate.ValidateUtil;
import net.wangxj.util.validate.ValidationResult;

public interface AuthorityService<T> {
	
	public static Logger logger = Logger.getLogger(AuthorityService.class);
	
	/**
	 * add
	 * @return
	 * @throws Exception 
	 */
	public String add(T po) throws Exception;
	
	/**
	 * batch add
	 * @return
	 * @throws Exception 
	 */
	public Integer addBatch(List<T> listPo) throws Exception;
	
	/**
	 * update by uuid
	 * @return
	 * @throws Exception 
	 */
	public Integer update(T po) throws Exception;
	
	/**
	 * conditions of the query
	 */
	public List<T> query(T po);
	
	/**
	 * paging query
	 * @param po
	 * @param page
	 * @return
	 */
	public Map<String,Object> pageQuery(T po, Page page);
	
	/**
	 * 依据条件查询数量
	 * @param po
	 * @return
	 */
	public Integer getCount(T po);
	/**
	 * batch update by uuids
	 * @param poList
	 * @return
	 * @throws Exception 
	 */
	public Integer updateBatch(List<T> poList) throws Exception;
	/**
	 * delete by uuid
	 * @param po
	 * @return
	 */
	public Integer delete(T po);
	/**
	 * delete batch by uuids
	 * @param listPo
	 * @return
	 */
	public Integer deleteBatch(List<T> listPo);
	
	/**
	 * 验证Object
	 * @param po  
	 * @param clazz 验证类型
	 * @return　如果返回null:验证通过,不为null验证未通过
	 * @throws ValidateException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public default ValidationResult validatePo(Object po,Class clazz) throws ValidateException {
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
			throw new ValidateException("验证PO出错" + po);
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
	public default ValidationResult validateRepeat(PO po) throws ValidateException  {
		try{
			//获取po中注解有@NotRepeat的字段 返回:{{"fieldName" : po}}
			List<Map<String, Object>> fieldList = NotRepeatUtil.buildObjFromAnnotatedField(po);
			for (Map<String, Object> map : fieldList) {
				Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
				while(iterator.hasNext()){
					Entry<String, Object> nameObjEntry = iterator.next();
					String fieldName = nameObjEntry.getKey();
					PO singleFieldPo = (PO) nameObjEntry.getValue();
					//如果该对象中所有熟悉没值，则跳过
					if(ClazzUtil.isEmptyofObj(singleFieldPo)){
						continue;
					}
					String errorMsg;
					errorMsg = validateRepeat(singleFieldPo,po, fieldName);
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
			throw new ValidateException("验证不可重复出错");
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
	@SuppressWarnings("rawtypes")
	public default ValidationResult validatePoAndNotRepeadField(PO po , Class clazz) throws ValidateException{
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
	public String validateRepeat(PO single,PO originPo, String fieldName) throws NoSuchFieldException, SecurityException;
	
	/**
	 * 校验obj中@Jsonfield是否存在name为sortStr的注解
	 * @param obj
	 * @param sortStr
	 * @return
	 * @throws ValidateException 
	 */
	public default ValidationResult validateSort(Class<? extends PO> poClazz, String sortStr) throws ValidateException{
		try{
			return JsonFieldUtil.isExistName(poClazz, sortStr);
		}catch(Exception ex){
			throw new ValidateException("校验sort字段出错:----->" + sortStr);
		}
	}
}
