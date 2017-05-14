package net.wangxj.authority.service;

import java.util.List;
import java.util.Map;

import net.wangxj.authority.po.Page;

public interface AuthorityService<T> {
	/**
	 * add
	 * @return
	 */
	public Integer add(T po);
	
	/**
	 * batch add
	 * @return
	 */
	public Integer addBatch(List<T> listPo);
	
	/**
	 * update by uuid
	 * @return
	 */
	public Integer update(T po);
	
	/**
	 * conditions of the query
	 */
	public List<T> query(T po);
	
	/**
	 * paging query
	 * @param po
	 * @param page
	 * @return
	 */
	public Map<String,Object> pageQuery(T po, Page page);
	
	/**
	 * 依据条件查询数量
	 * @param po
	 * @return
	 */
	public Integer getCount(T po);
	/**
	 * batch update
	 * @param poList
	 * @return
	 */
	public Integer updateBatch(List<T> poList);
	/**
	 * delete
	 * @param po
	 * @return
	 */
	public Integer delete(T po);
	/**
	 * delete batch
	 * @param listPo
	 * @return
	 */
	public Integer deleteBatch(List<T> listPo);
}
