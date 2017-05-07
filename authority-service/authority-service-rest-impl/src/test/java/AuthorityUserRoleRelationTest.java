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
//import net.wangxj.authority.dto.AuthorityUserRoleRelationDTO;
//import net.wangxj.authority.service.rest.AuthorityUserRoleRelationShareService;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath*:/dubbo_use_test.xml"})
//public class AuthorityUserRoleRelationTest{
//	
//	@Resource
//	private AuthorityUserRoleRelationShareService authorityUserRoleRelationShareService;
//	
//	@Test
//	public void testSelectInfo(){
//		
//		AuthorityUserRoleRelationDTO authorityUserRoleRelationDto =  new AuthorityUserRoleRelationDTO();
//		
//		Response<AuthorityUserRoleRelationDTO> response = authorityUserRoleRelationShareService.queryListByCondition(authorityUserRoleRelationDto);
//		
//		System.out.println(response);
//		System.out.println(response.getData());
//	}
//}