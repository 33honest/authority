

package net.wangxj.authority.service;

import java.util.List;
import java.util.Map;

import net.wangxj.authority.po.AuthorityResourcesPO;
import net.wangxj.authority.po.AuthorityRolePO;
import net.wangxj.authority.po.Page;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:40
 */

public interface AuthorityRoleService extends AuthorityService<AuthorityRolePO>{
	
	/**
	 * 为角色授予资源
	 * @param roleUuid 被授予角色uuid
	 * @param resourceUuids 授予资源uuid列表{"resourceuuid-grantype",.......}
	 * @param addUserUuid 添加人uuid
	 * @return 是否全部授予成功
	 */
	public Boolean grantResources(String roleUuid ,String addUserUuid, List<String> resourceUuids);
	/**
	 * 获取角色下所有资源
	 * @param roleUuid
	 * @return
	 */
	public List<AuthorityResourcesPO> getResources(String roleUuid);
	/**
	 * 搜索分页
	 * @param search
	 * @param page
	 * @return
	 */
	public Map<String, Object> search(String search, Page page);
	/**
	 * 搜索分页数量
	 * @param search
	 * @return
	 */
	public Integer  searchCount(String search);
}
