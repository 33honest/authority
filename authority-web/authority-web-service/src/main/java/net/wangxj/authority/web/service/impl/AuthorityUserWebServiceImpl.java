
package net.wangxj.authority.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityUserDTO;
import net.wangxj.authority.service.share.AuthorityUserShareService;
import net.wangxj.authority.web.service.AuthorityUserWebService;

@Service(value="AuthorityUserWebService")
public class AuthorityUserWebServiceImpl implements AuthorityUserWebService {
	
	@Resource
	private AuthorityUserShareService authorityUserShareService;
	
	@Override
	public Response<AuthorityUserDTO> selectInfo(){
		AuthorityUserDTO authorityUserDto = new AuthorityUserDTO();
		
		Response<AuthorityUserDTO> authorityUserResponse = authorityUserShareService.queryListByCondition(authorityUserDto);
		
		return authorityUserResponse;
	}
	
}
