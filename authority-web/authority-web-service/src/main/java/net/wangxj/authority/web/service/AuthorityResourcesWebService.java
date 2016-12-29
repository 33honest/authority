package net.wangxj.authority.web.service;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityResourcesDTO;

public interface AuthorityResourcesWebService {

	Response<AuthorityResourcesDTO> selectInfo();

}
