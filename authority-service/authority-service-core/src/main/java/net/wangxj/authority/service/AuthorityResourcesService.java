

package net.wangxj.authority.service;

import java.util.List;
import net.wangxj.authority.po.AuthorityResourcesPO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:38
 */

public interface AuthorityResourcesService{
	/**
	 * 新增
	 * @return
	 */
	public Integer add(AuthorityResourcesPO authorityResourcesPo);
	
	/**
	 * 批量添加
	 * @return
	 */
	public Integer addBatch(List<AuthorityResourcesPO> listPo);
	
	/**
	 * 根据主键ID修改
	 * @return
	 */
	public Integer modifyByUuid(AuthorityResourcesPO authorityResourcesPo);
	
	/**
	 * 条件查询
	 */
	public List<AuthorityResourcesPO> queryListByCondition(AuthorityResourcesPO authorityResourcesPo);
	
	/**
	 * 条件分页查询
	 */
	public List<AuthorityResourcesPO> queryPageListByCondition(AuthorityResourcesPO authorityResourcesPo, int pageNum, int limit, String order, String sort);
	
	/**
	 * 条件数量查询
	 * @return
	 */
	public Integer getCountByCondition(AuthorityResourcesPO authorityResourcesPo);
	/**
	 * 批量修改
	 * @param resourceList
	 * @return
	 */
	Integer modifyByBatch(List<AuthorityResourcesPO> resourceList);
}
