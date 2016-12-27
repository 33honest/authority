
package net.wangxj.authority.web.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityUserRoleRelationDTO;
import net.wangxj.authority.service.share.AuthorityUserRoleRelationShareService;

@Service(value="AuthorityUserRoleRelationWebService")
public class AuthorityUserRoleRelationWebService {
	
	@Resource
	private AuthorityUserRoleRelationShareService authorityUserRoleRelationShareService;
	
	public Response<AuthorityUserRoleRelationDTO> selectInfo(){
		AuthorityUserRoleRelationDTO authorityUserRoleRelationDto = new AuthorityUserRoleRelationDTO();
		
		Response<AuthorityUserRoleRelationDTO> authorityUserRoleRelationResponse = authorityUserRoleRelationShareService.queryListByCondition(authorityUserRoleRelationDto);
		
		return authorityUserRoleRelationResponse;
	}
	
}
