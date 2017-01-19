

package net.wangxj.authority.dao;

import java.util.List;

import net.wangxj.authority.dao.base.IBaseDao;
import net.wangxj.authority.po.AuthorityUserPO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:42
 */

public interface AuthorityUserDao extends IBaseDao<AuthorityUserPO>{

	Integer modifyByBatch(List<AuthorityUserPO> userList);
	
}
