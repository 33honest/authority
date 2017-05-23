package net.wangxj.authority.dao;

import net.wangxj.authority.dao.base.IBaseDao;
import net.wangxj.authority.po.AuthorityResourcesPO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:38
 */

public interface AuthorityResourcesDao extends IBaseDao<AuthorityResourcesPO>{
	
	/**
	 * 删除资源
	 * @param resourcePo
	 * @return
	 */
	public Integer delete(AuthorityResourcesPO resourcePo);
}
