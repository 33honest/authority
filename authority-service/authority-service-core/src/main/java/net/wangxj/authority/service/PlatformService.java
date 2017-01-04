

package net.wangxj.authority.service;

import java.util.List;
import net.wangxj.authority.po.PlatformPO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:44
 */

public interface PlatformService{
	/**
	 * 新增
	 * @return
	 */
	public Integer add(PlatformPO platformPo);
	
	/**
	 * 批量添加
	 * @return
	 */
	public Integer addBatch(List<PlatformPO> listPo);
	
	/**
	 * 根据主键ID修改
	 * @return
	 */
	public Integer modifyByUuid(PlatformPO platformPo);
	
	/**
	 * 条件查询
	 */
	public List<PlatformPO> queryListByCondition(PlatformPO platformPo);
	
	/**
	 * 条件分页查询
	 */
	public List<PlatformPO> queryPageListByCondition(PlatformPO platformPo, int pageNum, int limit,String order,String sort);
	
	/**
	 * 条件数量查询
	 * @return
	 */
	public Integer getCountByCondition(PlatformPO platformPo);
}
