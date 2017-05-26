//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import net.wangxj.authority.Response;
//import net.wangxj.authority.dto.AuthorityRoleDTO;
//import net.wangxj.authority.service.rest.AuthorityRoleShareService;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath*:/dubbo_use_test.xml"})
//public class AuthorityRoleTest{
//	
//	@Resource
//	private AuthorityRoleShareService authorityRoleShareService;
//	
//	@Test
//	public void testSelectInfo(){
//		
//		AuthorityRoleDTO authorityRoleDto =  new AuthorityRoleDTO();
//		
//		Response<AuthorityRoleDTO> response = authorityRoleShareService.queryListByCondition(authorityRoleDto);
//		
//		System.out.println(response);
//		System.out.println(response.getData());
//	}
//}