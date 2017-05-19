package net.wangxj.authority.service.rest.api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import net.wangxj.authority.exception.ValidateException;
import net.wangxj.authority.po.PO;
import net.wangxj.authority.service.rest.AuthorityRestService;
import net.wangxj.util.annotation.util.JsonFieldUtil;
import net.wangxj.util.annotation.util.NotRepeatUtil;
import net.wangxj.util.annotation.util.XmlElementUtil;
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
	 * 验证失败
	 * @param errorMsg
	 * @return
	 */
	public Response failValidate(Object errorMsg){
		return Response.status(Status.BAD_REQUEST).entity(errorMsg).build();
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
