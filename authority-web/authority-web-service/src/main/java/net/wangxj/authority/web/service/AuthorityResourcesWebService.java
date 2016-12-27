
package net.wangxj.authority.web.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityResourcesDTO;
import net.wangxj.authority.service.share.AuthorityResourcesShareService;

@Service(value="AuthorityResourcesWebService")
public class AuthorityResourcesWebService {
	
	@Resource
	private AuthorityResourcesShareService authorityResourcesShareService;
	
	public Response<AuthorityResourcesDTO> selectInfo(){
		AuthorityResourcesDTO authorityResourcesDto = new AuthorityResourcesDTO();
		
		Response<AuthorityResourcesDTO> authorityResourcesResponse = authorityResourcesShareService.queryListByCondition(authorityResourcesDto);
		
		return authorityResourcesResponse;
	}
	
}
