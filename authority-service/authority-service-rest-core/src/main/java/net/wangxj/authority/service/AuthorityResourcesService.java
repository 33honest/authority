package net.wangxj.authority.service;

import java.util.List;

import net.wangxj.authority.po.AuthorityResourcesPO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:38
 */

public interface AuthorityResourcesService extends AuthorityService<AuthorityResourcesPO>{

	/**
	 * 查询一个平台下的资源树
	 * @param resourcePo
	 * @return
	 */
	Object queryResourceTreeByPlatform(AuthorityResourcesPO resourcePo);

	/**
	 * 获取访问一个资源需要的角色
	 * @param resourceUuid
	 * @return
	 */
	List<AuthorityResourcesPO> roles(String resourceUuid);
}
