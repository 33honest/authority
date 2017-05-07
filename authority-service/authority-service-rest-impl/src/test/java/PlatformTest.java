import static org.junit.Assert.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockActionRequest;

import com.alibaba.fastjson.JSONObject;
import net.wangxj.authority.service.rest.api.PlatformRestServiceApi;
import net.wangxj.util.string.UuidUtil;
import net.wangxj.util.validate.ValidationResult;


public class PlatformTest extends JerseyTest{
	
	public static final String BASE_URI = "platforms/";
	
	/* (non-Javadoc)
	 * @see org.glassfish.jersey.test.JerseyTest#configure()
	 */
	@Override
	protected Application configure() {
		return new ResourceConfig(PlatformRestServiceApi.class);
	}
	
	@Test
	public void testAddPost(){
		final WebTarget addTarget = target(BASE_URI);
		final Invocation.Builder invocationBuilder = addTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = invocationBuilder.post(null);
		ValidationResult resultValidate = response.readEntity(ValidationResult.class);
		assertFalse(resultValidate.getIsPass());
		assertTrue(resultValidate.getErrorMsg() != null && resultValidate.getErrorMsg().length() > 0);
		
		
		JSONObject platformJson = new JSONObject();
		platformJson.put("platform_name", "测试1");
		platformJson.put("platform_sign", "CE_SHI1");
		platformJson.put("platform_domain", "ceshi.com");
		platformJson.put("platform_add_user", UuidUtil.newGUID());
		platformJson.put("platform_status", 3);
		System.out.println(platformJson.toJSONString());
//		Response response2 = invocationBuilder.post(Entity<T>)
//		System.out.println(response2.readEntity(Integer.class));
		
		
		
	}
	
}