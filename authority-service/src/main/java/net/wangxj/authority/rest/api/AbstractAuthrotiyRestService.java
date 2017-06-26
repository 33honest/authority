package net.wangxj.authority.rest.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import net.wangxj.authority.rest.AuthorityRestService;

/**
 * @author huoshan
 * created by 2017年4月9日 上午8:26:12
 * 
 */
public abstract class AbstractAuthrotiyRestService implements AuthorityRestService {
	
	@SuppressWarnings("unused")
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
