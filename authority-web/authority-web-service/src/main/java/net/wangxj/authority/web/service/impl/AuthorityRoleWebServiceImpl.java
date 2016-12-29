
package net.wangxj.authority.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityRoleDTO;
import net.wangxj.authority.service.share.AuthorityRoleShareService;
import net.wangxj.authority.web.service.AuthorityRoleWebService;

@Service(value="AuthorityRoleWebService")
public class AuthorityRoleWebServiceImpl implements AuthorityRoleWebService{
	
	@Resource
	private AuthorityRoleShareService authorityRoleShareService;
	
	@Override
	public Response<AuthorityRoleDTO> selectInfo(){
		AuthorityRoleDTO authorityRoleDto = new AuthorityRoleDTO();
		
		Response<AuthorityRoleDTO> authorityRoleResponse = authorityRoleShareService.queryListByCondition(authorityRoleDto);
		
		return authorityRoleResponse;
	}
	
}
