

package net.wangxj.authority.service;

import java.util.List;
import net.wangxj.authority.po.AuthorityRolePO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:40
 */

public interface AuthorityRoleService{
	/**
	 * 新增
	 * @return
	 */
	public Integer add(AuthorityRolePO authorityRolePo);
	
	/**
	 * 批量添加
	 * @return
	 */
	public Integer addBatch(List<AuthorityRolePO> listPo);
	
	/**
	 * 根据主键ID修改
	 * @return
	 */
	public Integer modifyByUuid(AuthorityRolePO authorityRolePo);
	
	/**
	 * 条件查询
	 */
	public List<AuthorityRolePO> queryListByCondition(AuthorityRolePO authorityRolePo);
	
	/**
	 * 条件分页查询
	 */
	public List<AuthorityRolePO> queryPageListByCondition(AuthorityRolePO authorityRolePo, int pageNum, int limit,String order,String sort);
	
	/**
	 * 条件数量查询
	 * @return
	 */
	public Integer getCountByCondition(AuthorityRolePO authorityRolePo);
	/**
	 * 批量修改
	 * @param roleList
	 * @return
	 */
	Integer modifyByBatch(List<AuthorityRolePO> roleList);
}
