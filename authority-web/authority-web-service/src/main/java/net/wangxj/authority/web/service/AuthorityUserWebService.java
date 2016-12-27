
package net.wangxj.authority.web.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityUserDTO;
import net.wangxj.authority.service.share.AuthorityUserShareService;

@Service(value="AuthorityUserWebService")
public class AuthorityUserWebService {
	
	@Resource
	private AuthorityUserShareService authorityUserShareService;
	
	public Response<AuthorityUserDTO> selectInfo(){
		AuthorityUserDTO authorityUserDto = new AuthorityUserDTO();
		
		Response<AuthorityUserDTO> authorityUserResponse = authorityUserShareService.queryListByCondition(authorityUserDto);
		
		return authorityUserResponse;
	}
	
}
