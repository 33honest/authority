/**
 * author: wangxj
 * create time: 上午11:18:49
 */
package net.wangxj.authority.service.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

/**
 * @author huoshan
 * created by 2017年5月7日 上午11:18:49
 * 
 */
public class ValidateException extends WebApplicationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1946651549770459955L;

	public ValidateException(){
		super(Status.BAD_REQUEST);
	}

	public ValidateException(String message){
		super(message);
	}
	
	
}
