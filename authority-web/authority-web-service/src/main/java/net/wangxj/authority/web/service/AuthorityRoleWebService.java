package net.wangxj.authority.web.service;

import net.wangxj.authority.dto.AuthorityRoleDTO;

public interface AuthorityRoleWebService extends AuthorityWebService<AuthorityRoleDTO>{

	String getPlatformAndRoleStatus();

	String getByPlatform(AuthorityRoleDTO roleDto);

	String grantResource(String roleUuid, String resourceList);

}
