import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.alibaba.fastjson.support.jaxrs.FastJsonProvider;

import net.wangxj.auhtority.application.CORSResponseFilter;
import net.wangxj.auhtority.application.FastJsonFeature;


public class PlatformTest /*extends JerseyTest*/{
	
	public static final String BASE_URI = "http://localhost:9000/api";
	
	/* (non-Javadoc)
	 * @see org.glassfish.jersey.test.JerseyTest#configure()
	 */
	/*@Override
	protected Application configure() {
//		packages(",");
//		property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
//		register(new FastJsonFeature()).register(FastJsonProvider.class);
//		register(CORSResponseFilter.class);
		
		ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.packages(true, "net.wangxj.authority.service.rest.api", "net.wangxj.authority.exception");
		resourceConfig.register(new FastJsonFeature()).register(FastJsonProvider.class);
		resourceConfig.register(CORSResponseFilter.class);
		return resourceConfig;
	}*/
	
	@Test
	public void testGet(){
		ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
		resourceConfig.register(new FastJsonFeature()).register(FastJsonProvider.class);
		
		Client newClient = ClientBuilder.newClient(resourceConfig);
		WebTarget webtarget = newClient.target(BASE_URI).path("platforms").queryParam("search", "").queryParam("page_number", 1).queryParam("limit", 3).
				 queryParam("order", "desc").queryParam("sort", "platform_uuid");
		Builder invocationBuilder = webtarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.get();
//		String readEntity = response.readEntity(String.class);
		Map readEntity = response.readEntity(Map.class);
		System.out.println(readEntity);
	}
	
	@Test
	public void testAddPost(){
		
		
		
		
		
	}
	
	
	
}