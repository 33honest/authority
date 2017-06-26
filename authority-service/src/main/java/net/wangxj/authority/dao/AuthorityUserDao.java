

package net.wangxj.authority.dao;

import java.util.List;

import net.wangxj.authority.dao.base.IBaseDao;
import net.wangxj.authority.po.AuthorityUserPO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:42
 */

public interface AuthorityUserDao extends IBaseDao<AuthorityUserPO>{

	/**
	 * @param userPo
	 * @return
	 */
	Integer delete(AuthorityUserPO userPo);

	/**
	 * 分页查询
	 * @param search
	 * @param pageNum
	 * @param limit
	 * @param order
	 * @param sort
	 * @return
	 */
	List<AuthorityUserPO> search(String search, Integer pageNum, Integer limit, String order, String sort);

	/**
	 * 搜索查询数量
	 * @param search
	 * @return
	 */
	Integer searchCount(String search);
	
}
