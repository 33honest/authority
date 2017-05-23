

package net.wangxj.authority.dao;

import net.wangxj.authority.dao.base.IBaseDao;
import net.wangxj.authority.po.AuthorityRoleResourcesRelationPO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:41
 */

public interface AuthorityRoleResourcesRelationDao extends IBaseDao<AuthorityRoleResourcesRelationPO>{

	Integer deleteBy(String rrRoleUuid);
	/**
	 * 删除一个平台下角色与资源的所有对应关系
	 * @param platformUuid
	 * @return
	 */
	Integer deleteByPlatform(String platformUuid);
	/**
	 * 删除与该资源有关联的所有关联关系
	 * @param resourceUuid
	 */
	Integer deleteByResource(String resourceUuid);
	
}
