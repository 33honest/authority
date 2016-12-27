

package net.wangxj.authority.service;

import java.util.List;
import net.wangxj.authority.po.AuthorityRoleResourcesRelationPO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:41
 */

public interface AuthorityRoleResourcesRelationService{
	/**
	 * 新增
	 * @return
	 */
	public Integer add(AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo);
	
	/**
	 * 批量添加
	 * @return
	 */
	public Integer addBatch(List<AuthorityRoleResourcesRelationPO> listPo);
	
	/**
	 * 根据主键ID修改
	 * @return
	 */
	public Integer modifyByUuid(AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo);
	
	/**
	 * 条件查询
	 */
	public List<AuthorityRoleResourcesRelationPO> queryListByCondition(AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo);
	
	/**
	 * 条件分页查询
	 */
	public List<AuthorityRoleResourcesRelationPO> queryPageListByCondition(AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo, int pageNum, int limit);
	
	/**
	 * 条件数量查询
	 * @return
	 */
	public Integer getCountByCondition(AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo);
}
