//
//package net.wangxj.authority.web.service.impl;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import net.wangxj.authority.Response;
//import net.wangxj.authority.dto.AuthorityUserRoleRelationDTO;
//import net.wangxj.authority.service.share.AuthorityUserRoleRelationShareService;
//import net.wangxj.authority.web.service.AuthorityUserRoleRelationWebService;
//
//@Service(value="AuthorityUserRoleRelationWebService")
//public class AuthorityUserRoleRelationWebServiceImpl implements AuthorityUserRoleRelationWebService {
//	
//	@Resource
//	private AuthorityUserRoleRelationShareService authorityUserRoleRelationShareService;
//	
//	@Override
//	public Response<AuthorityUserRoleRelationDTO> selectInfo(){
//		AuthorityUserRoleRelationDTO authorityUserRoleRelationDto = new AuthorityUserRoleRelationDTO();
//		
//		Response<AuthorityUserRoleRelationDTO> authorityUserRoleRelationResponse = authorityUserRoleRelationShareService.queryListByCondition(authorityUserRoleRelationDto);
//		
//		return authorityUserRoleRelationResponse;
//	}
//	
//}
