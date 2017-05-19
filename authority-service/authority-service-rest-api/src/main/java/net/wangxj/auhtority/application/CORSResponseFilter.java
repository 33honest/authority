package net.wangxj.auhtority.application;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * @author huoshan
 * created by 2017年5月18日 下午5:30:27
 * 
 */
@Provider
public class CORSResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext creq, ContainerResponseContext cres) {
	    cres.getHeaders().add("Access-Control-Allow-Origin", "*");
	}

}
