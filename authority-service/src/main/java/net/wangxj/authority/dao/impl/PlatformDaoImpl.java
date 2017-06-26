

package net.wangxj.authority.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.github.pagehelper.PageHelper;

import net.wangxj.authority.dao.base.BaseSessionDaoSupport;
import net.wangxj.authority.po.PlatformPO;
import net.wangxj.util.string.TimeUtil;
import net.wangxj.authority.DataDictionaryConstant.DataDictionaryConstant;
import net.wangxj.authority.dao.PlatformDao;
import org.apache.log4j.Logger;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:44
 */

@Repository("platformDao")
public class PlatformDaoImpl extends BaseSessionDaoSupport implements PlatformDao{

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(PlatformDaoImpl.class);
	
	@Override
	public Integer insert(PlatformPO platformPo) {
		return super.getSqlSession().update("PlatformPOMapper.insert", platformPo);
	}
	
	@Override
	public Integer updateByUuid(PlatformPO platformPo) {
	
		return super.getSqlSession().update("PlatformPOMapper.updateByUuid", platformPo);
	}
	
	@Override
	public List<PlatformPO> selectListByCondition(PlatformPO platformPo) {
		Map<String, Object> map = new HashMap<>();
		map.put("platform",platformPo);
		return super.getSqlSession().selectList("PlatformPOMapper.selectByCondition", map);
	}
	
	@Override
	public List<PlatformPO> selectPageListByCondition(PlatformPO platformPo, int pageNum, int limit,String order,String sort) {
		Map<String, Object> map = new HashMap<>();
		map.put("platform", platformPo);
		map.put("order", order);
		map.put("sort", sort);
		PageHelper.startPage(pageNum, limit);
		return super.getSqlSession().selectList("PlatformPOMapper.selectByCondition", map);
	}

	@Override
	public Integer getCountByCondition(PlatformPO platformPo) {
		return (Integer)super.getSqlSession().selectOne("PlatformPOMapper.selectCountByCondition", platformPo);
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.dao.PlatformDao#delete(net.wangxj.authority.po.PlatformPO)
	 */
	@Override
	public Integer delete(PlatformPO platformPo) {
		platformPo.setPlatformDelTime(TimeUtil.getNowStr());
		platformPo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_YES_VALUE);
		return super.getSqlSession().delete("PlatformPOMapper.updateByUuid", platformPo);
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.dao.PlatformDao#search(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public List<PlatformPO> search(String search, Integer pageNum, Integer limit, String order, String sort) {
		Map<String, Object> map = new HashMap<>();
		map.put("search", search);
		map.put("order", order);
		map.put("sort", sort);
		PageHelper.startPage(pageNum, limit);
		return super.getSqlSession().selectList("PlatformPOMapper.search", map);
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.dao.PlatformDao#searchCount(java.lang.String)
	 */
	@Override
	public Integer searchCount(String search) {
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("search", search);
		return super.getSqlSession().selectOne("PlatformPOMapper.searchCount", paramMap);
	}
	
}
 