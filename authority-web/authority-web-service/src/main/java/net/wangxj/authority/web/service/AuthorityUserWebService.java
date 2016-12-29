package net.wangxj.authority.web.service;

import net.wangxj.authority.Response;
import net.wangxj.authority.dto.AuthorityUserDTO;

public interface AuthorityUserWebService {

	Response<AuthorityUserDTO> selectInfo();

}
