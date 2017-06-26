

package net.wangxj.authority.dao;

import java.util.List;

import net.wangxj.authority.dao.base.IBaseDao;
import net.wangxj.authority.po.PlatformPO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:44
 */

public interface PlatformDao extends IBaseDao<PlatformPO>{

	/**
	 * 删除平台
	 * @param po
	 * @return
	 */
	Integer delete(PlatformPO po);

	/**
	 * 搜索分页
	 * @param search
	 * @param pageNum
	 * @param limit
	 * @param order
	 * @param sort
	 * @return
	 */
	List<PlatformPO> search(String search, Integer pageNum, Integer limit, String order, String sort);

	/**
	 * 搜索数量
	 * @param search
	 * @return
	 */
	Integer searchCount(String search);
	
}
