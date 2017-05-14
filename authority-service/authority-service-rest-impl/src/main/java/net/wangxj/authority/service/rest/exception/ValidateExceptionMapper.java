package net.wangxj.authority.service.rest.exception;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * @author huoshan
 * created by 2017年5月14日 下午3:34:21
 * 
 */
@Provider
public class ValidateExceptionMapper implements ExceptionMapper<Exception> {

	Logger log = LoggerFactory.getLogger(ValidateException.class);
	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(Exception exception) {
		
		Map<String , Object> errorMap = new HashMap<String, Object>();
		errorMap.put("error", "服务器内部发生错误");
		
		if(exception instanceof ValidateException){
			ValidateException validateException = (ValidateException)exception;
			log.debug("验证发生异常----->", validateException);
		}
		else{
			log.debug("业务逻辑功能发生异常----->" , exception);
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(JSONObject.toJSONString(errorMap)).build();
	}

}
