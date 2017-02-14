package net.wangxj.authority.web.service;

import net.wangxj.authority.dto.AuthorityResourcesDTO;

public interface AuthorityResourcesWebService extends AuthorityWebService<AuthorityResourcesDTO> {

	String initSelect(AuthorityResourcesDTO resourceDto);

	String changeBy(AuthorityResourcesDTO resourceDto);
}
