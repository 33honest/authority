package net.wangxj.authority.web.service;

import java.lang.reflect.Array;

import net.wangxj.authority.dto.AuthorityUserDTO;
import net.wangxj.authority.dto.AuthorityUserRoleRelationDTO;

public interface AuthorityUserWebService extends AuthorityWebService<AuthorityUserDTO>{

	String grandRole(String userId, String roleStr);
}
