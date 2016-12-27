
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.wangxj.authority.service.share.AuthorityUserShareService;
import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityUserDTO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/dubbo_use_test.xml"})
public class AuthorityUserTest{
	
	@Resource
	private AuthorityUserShareService authorityUserShareService;
	
	@Test
	public void testSelectInfo(){
		
		AuthorityUserDTO authorityUserDto =  new AuthorityUserDTO();
		
		Response<AuthorityUserDTO> response = authorityUserShareService.queryListByCondition(authorityUserDto);
		
		System.out.println(response);
		System.out.println(response.getData());
	}
}