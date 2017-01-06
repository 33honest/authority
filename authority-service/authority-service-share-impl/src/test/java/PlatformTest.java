
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huoshan.util.string.UuidUtil;

import net.wangxj.authority.service.share.PlatformShareService;
import net.wangxj.authority.validate.ValidateUtil;
import net.wangxj.authority.validate.groups.AddValidate;
import net.wangxj.authority.validate.groups.EditValidate;
import net.wangxj.authority.Response;
import net.wangxj.authority.dto.PlatformDTO;

//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath*:/dubbo_use_test.xml"})
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
	
	@Test
	public void testAddValidate(){
		
		PlatformDTO platformDto = new PlatformDTO(UuidUtil.newGUID(), "限", "12",
				"wangxj.net", "2015-12-12 23:23:23", UuidUtil.newGUID(), null,
				null, 0, null, 0, null);
		
		System.out.println(ValidateUtil.validateEntity(platformDto, AddValidate.class));
		
	}
	
	@Test
	public void testEditValidate(){
		
		PlatformDTO platformDto = new PlatformDTO(UuidUtil.newGUID(), null, "12",
				"11", "2015-12-12 23:23:23", UuidUtil.newGUID(), null,
				null, 0, null, 0, null);
		
		System.out.println(ValidateUtil.validateEntity(platformDto, EditValidate.class));
	}
}