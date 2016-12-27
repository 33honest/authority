
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.wangxj.authority.service.share.PlatformShareService;
import net.wangxj.authority.Response;
import net.wangxj.authority.dto.PlatformDTO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/dubbo_use_test.xml"})
public class PlatformTest{
	
	@Resource
	private PlatformShareService platformShareService;
	
	@Test
	public void testSelectInfo(){
		
		PlatformDTO platformDto =  new PlatformDTO();
		
		Response<PlatformDTO> response = platformShareService.queryListByCondition(platformDto);
		
		System.out.println(response);
		System.out.println(response.getData());
	}
}