package net.wangxj.authority.service.share;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;

import net.wangxj.authority.dto.DTO;
import net.wangxj.authority.po.PO;
import net.wangxj.util.validate.Severity.Error;
import net.wangxj.util.validate.ValidateUtil;
import net.wangxj.util.validate.ValidationResult;

public abstract class BaseAbstractAuthorityShareService implements AuthorityShareService {
	
	/**
	 * 验证DTO
	 * @param dto　要验证dto
	 * @param po  
	 * @param clazz 验证类型
	 * @return　如果返回null:验证通过,不为null验证未通过
	 */
	public String validateDto(DTO dto,PO po,Class clazz){
		ValidationResult validateRes = new ValidationResult();
		if(dto == null){
			validateRes.setPass(false);
			//设置验证结果
			Set<String> levelStr = new HashSet<>();
			Map<String, Map<String,Set<String>>> errorMsg = new HashMap<>();
			Map<String, Set<String>> levelError = new HashMap<String, Set<String>>();
			levelStr.add(Error.class.getSimpleName());
			levelError.put("dto不可为空", levelStr);
			validateRes.setErrorMsg(errorMsg);
			return JSON.toJSONString(levelError);
		}
		BeanUtils.copyProperties(dto, po);
		//验证
		validateRes = ValidateUtil.validateEntity(po, clazz);
		if(!validateRes.isPass()){
			return JSON.toJSONString(validateRes);
		}
		return null;
	}
}
