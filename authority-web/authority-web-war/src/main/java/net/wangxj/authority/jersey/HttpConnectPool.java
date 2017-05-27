package net.wangxj.authority.jersey;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

import com.alibaba.fastjson.support.jaxrs.FastJsonProvider;

/**
 * @author huoshan
 * created by 2017年5月26日 下午4:06:10
 * 
 */
public class HttpConnectPool {
	
	/**
	 * 创建client
	 * @return
	 */
	public static Client newClient(){
		ClientConfig clientConfig = new ClientConfig();
		PoolingHttpClientConnectionManager poolingManager = new PoolingHttpClientConnectionManager();
		poolingManager.setMaxTotal(20000);
		poolingManager.setDefaultMaxPerRoute(10000);
		clientConfig.property(ApacheClientProperties.CONNECTION_MANAGER, poolingManager);
		clientConfig.connectorProvider(new ApacheConnectorProvider());
		
		clientConfig.register(new FastJsonFeature()).register(FastJsonProvider.class);
		clientConfig.property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
		return ClientBuilder.newClient(clientConfig);
	}
}
