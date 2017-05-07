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
//import net.wangxj.util.string.UuidUtil;
//import net.wangxj.authority.Response;
//import net.wangxj.authority.constant.DataDictionaryConstant;
//import net.wangxj.authority.dto.AuthorityUserDTO;
//import net.wangxj.authority.service.rest.AuthorityUserShareService;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath*:/dubbo_use_test.xml"})
//public class AuthorityUserTest{
//	
//	@Resource
//	private AuthorityUserShareService authorityUserShareService;
//	
//	@Test
//	public void testSelectInfo(){
//		
//		AuthorityUserDTO authorityUserDto =  new AuthorityUserDTO();
//		
//		Response<AuthorityUserDTO> response = authorityUserShareService.queryListByCondition(authorityUserDto,true);
//		
//		System.out.println(response);
//		System.out.println(response.getData());
//	}
//	
//	@Test
//	public void testAdd(){
//		AuthorityUserDTO userDto = new AuthorityUserDTO(UuidUtil.newGUID(), "aaa", "asfafasf", "sfass@fgmail.com",
//				"15311256734", DataDictionaryConstant.USER_STATUS_YESACTIVE_VALUE, null,
//				null, UuidUtil.newGUID(), null,
//				null, null, null,
//				DataDictionaryConstant.USER_TYPE_INNER_VALUE, null, DataDictionaryConstant.USER_ADDTYPE_INNER_VALUE,
//				null, null, null,
//				null, null, null);
//		Response<Integer> addRespon = authorityUserShareService.add(userDto);
//		System.out.println(addRespon);
//	}
//}