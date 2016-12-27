
package net.wangxj.authority.web.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.PlatformDTO;
import net.wangxj.authority.service.share.PlatformShareService;

@Service(value="PlatformWebService")
public class PlatformWebService {
	
	@Resource
	private PlatformShareService platformShareService;
	
	public Response<PlatformDTO> selectInfo(){
		PlatformDTO platformDto = new PlatformDTO();
		
		Response<PlatformDTO> platformResponse = platformShareService.queryListByCondition(platformDto);
		
		return platformResponse;
	}
	
}
