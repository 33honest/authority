
package net.wangxj.authority.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityRoleResourcesRelationDTO;
import net.wangxj.authority.service.share.AuthorityRoleResourcesRelationShareService;
import net.wangxj.authority.web.service.AuthorityRoleResourcesRelationWebService;

@Service(value="AuthorityRoleResourcesRelationWebService")
public class AuthorityRoleResourcesRelationWebServiceImpl implements AuthorityRoleResourcesRelationWebService{
	
	@Resource
	private AuthorityRoleResourcesRelationShareService authorityRoleResourcesRelationShareService;
	
	@Override
	public Response<AuthorityRoleResourcesRelationDTO> selectInfo(){
		AuthorityRoleResourcesRelationDTO authorityRoleResourcesRelationDto = new AuthorityRoleResourcesRelationDTO();
		
		Response<AuthorityRoleResourcesRelationDTO> authorityRoleResourcesRelationResponse = authorityRoleResourcesRelationShareService.queryListByCondition(authorityRoleResourcesRelationDto);
		
		return authorityRoleResourcesRelationResponse;
	}
	
}
