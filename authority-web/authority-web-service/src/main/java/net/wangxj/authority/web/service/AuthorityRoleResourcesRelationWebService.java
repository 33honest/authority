
package net.wangxj.authority.web.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityRoleResourcesRelationDTO;
import net.wangxj.authority.service.share.AuthorityRoleResourcesRelationShareService;

@Service(value="AuthorityRoleResourcesRelationWebService")
public class AuthorityRoleResourcesRelationWebService {
	
	@Resource
	private AuthorityRoleResourcesRelationShareService authorityRoleResourcesRelationShareService;
	
	public Response<AuthorityRoleResourcesRelationDTO> selectInfo(){
		AuthorityRoleResourcesRelationDTO authorityRoleResourcesRelationDto = new AuthorityRoleResourcesRelationDTO();
		
		Response<AuthorityRoleResourcesRelationDTO> authorityRoleResourcesRelationResponse = authorityRoleResourcesRelationShareService.queryListByCondition(authorityRoleResourcesRelationDto);
		
		return authorityRoleResourcesRelationResponse;
	}
	
}
