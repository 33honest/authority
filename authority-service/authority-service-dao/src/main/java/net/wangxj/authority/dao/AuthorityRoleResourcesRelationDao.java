

package net.wangxj.authority.dao;

import net.wangxj.authority.dao.base.IBaseDao;
import net.wangxj.authority.po.AuthorityRoleResourcesRelationPO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:41
 */

public interface AuthorityRoleResourcesRelationDao extends IBaseDao<AuthorityRoleResourcesRelationPO>{

	Integer deleteBy(String rrRoleUuid);
	
}
