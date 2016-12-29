
package net.wangxj.authority.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityResourcesDTO;
import net.wangxj.authority.service.share.AuthorityResourcesShareService;
import net.wangxj.authority.web.service.AuthorityResourcesWebService;

@Service(value="AuthorityResourcesWebService")
public class AuthorityResourcesWebServiceImpl implements AuthorityResourcesWebService{
	
	@Resource
	private AuthorityResourcesShareService authorityResourcesShareService;
	
	@Override
	public Response<AuthorityResourcesDTO> selectInfo(){
		AuthorityResourcesDTO authorityResourcesDto = new AuthorityResourcesDTO();
		
		Response<AuthorityResourcesDTO> authorityResourcesResponse = authorityResourcesShareService.queryListByCondition(authorityResourcesDto);
		
		return authorityResourcesResponse;
	}
	
}
