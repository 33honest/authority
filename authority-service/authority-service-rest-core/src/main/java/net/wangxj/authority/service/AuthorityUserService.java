package net.wangxj.authority.service;

import java.util.List;

import net.wangxj.authority.po.AuthorityUserPO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:42
 */

public interface AuthorityUserService extends AuthorityService<AuthorityUserPO>{
	
	/**
	 * 为用户授予角色列表
	 * @param userUuid 用户uuid
	 * @param platformUuid 资源所在平台uuid
	 * @param rolesUuidList 角色的uuid列表
	 * @param addBy 添加人uuid
	 * @return 返回是否全部授权成功
	 */
	public Boolean grantRoles(String userUuid , String platformUuid , List<String> rolesUuidList,String addBy);
}
