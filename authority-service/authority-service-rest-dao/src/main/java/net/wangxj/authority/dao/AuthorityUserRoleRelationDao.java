

package net.wangxj.authority.dao;

import net.wangxj.authority.dao.base.IBaseDao;
import net.wangxj.authority.po.AuthorityUserRoleRelationPO;
import net.wangxj.authority.po.PlatformPO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:43
 */

public interface AuthorityUserRoleRelationDao extends IBaseDao<AuthorityUserRoleRelationPO>{

	/**
	 * 删除一个用户在该平台下的所有角色
	 * @param authorityUserRoleRelationPo
	 * @param platformUuid
	 * @return
	 */
	Integer deleteBy(AuthorityUserRoleRelationPO authorityUserRoleRelationPo,String platformUuid);
	/**
	 * 删除一个平台下所有角色与用户的对应关系
	 * @param platformUuid
	 * @return
	 */
	Integer deleteByPlatform(String platformUuid);
	/**
	 * 删除一个角色与所有用户的关联
	 * @param roleUuid
	 */
	Integer deleteByRole(String roleUuid);
	/**
	 * 取消该用户所有角色所有赋权
	 * @param userUuid
	 */
	Integer deleteByUser(String userUuid);
	
}
