package net.wangxj.authority.web.service;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityRoleDTO;

public interface AuthorityRoleWebService {

	Response<AuthorityRoleDTO> selectInfo();

}
