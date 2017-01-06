package net.wangxj.authority.validate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Payload;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

/**
 * 
 * @author huoshan
 *
 */
public class ValidateUtil {

	private static Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();
	
	/**
	 * 校验实体
	 * @param <F>
	 * @param obj
	 * @return
	 */
	public static <T, F> ValidationResult validateEntity(T obj,Class<F> clazz){
		 ValidationResult result = new ValidationResult();
		 Set<ConstraintViolation<T>> set = validator.validate(obj, clazz);
		 if(set != null && set.size()>0){
			 Map<String,Map<String, Set<Class<? extends Payload>>>> errorMsg = new HashMap();
			 for(ConstraintViolation<T> cv : set){
				 Set<Class<? extends Payload>> payloads = cv.getConstraintDescriptor().getPayload();
				 //存放错误信息与错误级别
				 Map<String, Set<Class<? extends Payload>>> errPayload = new HashMap<>();
				 errPayload.put(cv.getMessage(), payloads);
				 errorMsg.put(cv.getPropertyPath().toString(), errPayload);
			 }
			 result.setErrorMsg(errorMsg);
		 }
		 else{
			 result.setPass(true);
		 }
		 return result;
	}
	
	/**
	 * 校验属性
	 * @param obj
	 * @param propertyName
	 * @return
	 */
	public static <T> ValidationResult validateProperty(T obj,String propertyName){
		ValidationResult result = new ValidationResult();
		 Set<ConstraintViolation<T>> set = validator.validateProperty(obj,propertyName,Default.class);
		 if( set != null && set.size()>0){
			 Map<String,Map<String, Set<Class<? extends Payload>>>> errorMsg = new HashMap();
			 for(ConstraintViolation<T> cv : set){
				 Set<Class<? extends Payload>> payloads = cv.getConstraintDescriptor().getPayload();
				 //存放错误信息与错误级别
				 Map<String, Set<Class<? extends Payload>>> errPayload = new HashMap<>();
				 errPayload.put(cv.getMessage(), payloads);
				 errorMsg.put(propertyName, errPayload);
			 }
			 result.setErrorMsg(errorMsg);
		 }
		 return result;
	}
}

