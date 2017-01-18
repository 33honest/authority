
import javax.annotation.Resource;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.wangxj.authority.service.share.PlatformShareService;
import net.wangxj.util.string.StringUtil;
import net.wangxj.util.string.UuidUtil;
import net.wangxj.authority.Response;
import net.wangxj.authority.dto.PlatformDTO;
import net.wangxj.authority.po.PlatformPO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/dubbo_use_test.xml"})
public class PlatformTest{
	
	@Resource
	private PlatformShareService platformShareService;
	
	@Test
	public void testSelectInfo(){
		
		PlatformDTO platformDto =  new PlatformDTO();
		
		Response<PlatformDTO> response = platformShareService.queryListByCondition(platformDto,false);
		
		System.out.println(response);
		System.out.println(response.getData());
	}
	
	@Test
	public void testAdd(){
		PlatformDTO platformDto = new PlatformDTO();
		assertEquals("传入参数不可为空", platformShareService.add(platformDto).getMessage());
	
	}
	
	@Test
	public void testCopy(){
		PlatformPO po = new PlatformPO();
		BeanUtils.copyProperties(null, po);
		System.out.println(po);
	}
	
}