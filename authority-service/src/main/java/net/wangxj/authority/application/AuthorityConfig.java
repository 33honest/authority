package net.wangxj.authority.application;


import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.support.jaxrs.FastJsonProvider;

import net.wangxj.authority.exception.GlobalExceptinMapper;
import net.wangxj.authority.exception.ValidateException;
import net.wangxj.authority.rest.api.AuthorityResourcesRestServiceApi;
import net.wangxj.authority.rest.api.AuthorityRoleRestServiceApi;
import net.wangxj.authority.rest.api.AuthorityUserRestServiceApi;
import net.wangxj.authority.rest.api.PlatformRestServiceApi;


/**
 * @author huoshan
 * created by 2017年5月14日 上午9:28:18
 * 
 */
@Component
@ApplicationPath("/api")
public class AuthorityConfig extends ResourceConfig{
	
	public AuthorityConfig(){
		register(PlatformRestServiceApi.class);
		register(AuthorityRoleRestServiceApi.class);
		register(AuthorityResourcesRestServiceApi.class);
		register(AuthorityUserRestServiceApi.class);
		register(GlobalExceptinMapper.class);
		property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
		register(new FastJsonFeature()).register(FastJsonProvider.class);
		register(CORSResponseFilter.class);
	}
	
	
}
