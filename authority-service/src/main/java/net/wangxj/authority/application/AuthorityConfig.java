package net.wangxj.authority.application;


import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.support.jaxrs.FastJsonProvider;

/**
 * @author huoshan
 * created by 2017年5月14日 上午9:28:18
 * 
 */
@Component
@ApplicationPath("/api")
public class AuthorityConfig extends ResourceConfig{
	
	public AuthorityConfig(){
		packages("net.wangxj.authority.rest.api,net.wangxj.authority.exception");
		property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
		register(new FastJsonFeature()).register(FastJsonProvider.class);
		register(CORSResponseFilter.class);
	}
	
	
}
