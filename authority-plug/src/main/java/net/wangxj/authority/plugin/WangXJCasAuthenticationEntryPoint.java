package net.wangxj.authority.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.util.CommonUtils;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.util.Assert;

public class WangXJCasAuthenticationEntryPoint extends CasAuthenticationEntryPoint{
	
		public void afterPropertiesSet() throws Exception {
			Assert.hasLength(getLoginUrl(), "loginUrl must be specified");
			Assert.notNull(getServiceProperties(), "serviceProperties must be specified");
			/*Assert.notNull(this.serviceProperties.getService(),
					"serviceProperties.getService() cannot be null.");*/
		}
		
		protected String createServiceUrl(final HttpServletRequest request,
				final HttpServletResponse response) {
			
			String url = request.getRequestURL().toString();
			String uri = request.getRequestURI();
			int index = url.indexOf(uri);
			if(!"/".equals(uri)){
				url = url.substring(0, index);
			}else if(url.endsWith("/")){
				url = url.substring(0, url.length() - 1);
			}
			
			ServiceProperties servicePro = new ServiceProperties();
			servicePro.setService(url+"/login/cas");
			this.setServiceProperties(servicePro);
			
			return CommonUtils.constructServiceUrl(null, response,
					getServiceProperties().getService(), null,
					getServiceProperties().getArtifactParameter(),
					getEncodeServiceUrlWithSessionId());
		}
}