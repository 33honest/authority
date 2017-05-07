package net.wangxj.authority.service;

import java.util.List;

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
	public Integer modifyByUuid(T po);
	
	/**
	 * conditions of the query
	 */
	public List<T> queryListByCondition(T po);
	
	/**
	 * paging query
	 * @param po
	 * @param pageNum  
	 * @param limit
	 * @param order
	 * @param sort
	 * @return
	 */
	public List<T> queryPageListByCondition(T po, int pageNum, int limit,String order,String sort);
	
	/**
	 * 依据条件查询数量
	 * @param po
	 * @return
	 */
	public Integer getCountByCondition(T po);
	/**
	 * batch update
	 * @param poList
	 * @return
	 */
	public Integer modifyByBatch(List<T> poList);
}
