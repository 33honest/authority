package net.wangxj.authority.validate;

import java.util.Map;
import java.util.Set;

import javax.validation.Payload;

/**
 * 校验结果
 * @author huoshan
 *
 */
public class ValidationResult {

	//校验结果是否通过
	private boolean isPass = false;
	//校验信息
	private Map<String,Map<String,Set<Class<? extends Payload>>>> errorMsg;
	
	public boolean isPass() {
		return isPass;
	}
	public void setPass(boolean isPass) {
		this.isPass = isPass;
	}
	public Map<String, Map<String, Set<Class<? extends Payload>>>> getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(Map<String, Map<String, Set<Class<? extends Payload>>>> errorMsg) {
		this.errorMsg = errorMsg;
	}
	@Override
	public String toString() {
		return "ValidationResult [isPass=" + isPass + ", errorMsg=" + errorMsg + "]";
	}
}
