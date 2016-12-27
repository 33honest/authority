

package net.wangxj.authority.service;

import java.util.List;
import net.wangxj.authority.po.AuthorityUserRoleRelationPO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:43
 */

public interface AuthorityUserRoleRelationService{
	/**
	 * 新增
	 * @return
	 */
	public Integer add(AuthorityUserRoleRelationPO authorityUserRoleRelationPo);
	
	/**
	 * 批量添加
	 * @return
	 */
	public Integer addBatch(List<AuthorityUserRoleRelationPO> listPo);
	
	/**
	 * 根据主键ID修改
	 * @return
	 */
	public Integer modifyByUuid(AuthorityUserRoleRelationPO authorityUserRoleRelationPo);
	
	/**
	 * 条件查询
	 */
	public List<AuthorityUserRoleRelationPO> queryListByCondition(AuthorityUserRoleRelationPO authorityUserRoleRelationPo);
	
	/**
	 * 条件分页查询
	 */
	public List<AuthorityUserRoleRelationPO> queryPageListByCondition(AuthorityUserRoleRelationPO authorityUserRoleRelationPo, int pageNum, int limit);
	
	/**
	 * 条件数量查询
	 * @return
	 */
	public Integer getCountByCondition(AuthorityUserRoleRelationPO authorityUserRoleRelationPo);
}
