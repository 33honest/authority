
package net.wangxj.authority.web.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityRoleDTO;
import net.wangxj.authority.service.share.AuthorityRoleShareService;

@Service(value="AuthorityRoleWebService")
public class AuthorityRoleWebService {
	
	@Resource
	private AuthorityRoleShareService authorityRoleShareService;
	
	public Response<AuthorityRoleDTO> selectInfo(){
		AuthorityRoleDTO authorityRoleDto = new AuthorityRoleDTO();
		
		Response<AuthorityRoleDTO> authorityRoleResponse = authorityRoleShareService.queryListByCondition(authorityRoleDto);
		
		return authorityRoleResponse;
	}
	
}
