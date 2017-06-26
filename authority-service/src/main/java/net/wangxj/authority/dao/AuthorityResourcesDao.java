package net.wangxj.authority.dao;

import java.util.List;

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
	/**
	 * 根据条件查询，带有字节点
	 * @param resourcePO
	 * @return
	 */
	public List<AuthorityResourcesPO> hasChildListByCondition(AuthorityResourcesPO resourcePO);
}
