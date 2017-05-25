

package net.wangxj.authority.dao;

import java.util.List;

import net.wangxj.authority.dao.base.IBaseDao;
import net.wangxj.authority.po.AuthorityResourcesPO;
import net.wangxj.authority.po.AuthorityRolePO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:40
 */

public interface AuthorityRoleDao extends IBaseDao<AuthorityRolePO>{
	/**
	 * 删除角色
	 * @param rolePo
	 * @return
	 */
	public Integer delete(AuthorityRolePO rolePo);

	/**
	 * 根据资源获取角色
	 * @param resourceUuid
	 * @return
	 */
	public List<AuthorityResourcesPO> getRoleByResource(String resourceUuid);

	/**
	 * 搜索分页
	 * @param search
	 * @param pageNum
	 * @param limit
	 * @param order
	 * @param sort
	 * @return
	 */
	public List<AuthorityRolePO> search(String search, Integer pageNum, Integer limit, String order, String sort);

	/**
	 * 搜索数量
	 * @param search
	 * @return
	 */
	public Integer searchCount(String search);
}
