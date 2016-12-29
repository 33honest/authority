package net.wangxj.authority.web.service;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityUserRoleRelationDTO;

public interface AuthorityUserRoleRelationWebService {

	Response<AuthorityUserRoleRelationDTO> selectInfo();

}
