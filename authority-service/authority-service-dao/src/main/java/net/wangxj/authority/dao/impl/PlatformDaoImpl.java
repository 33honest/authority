

package net.wangxj.authority.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.github.pagehelper.PageHelper;

import net.wangxj.authority.dao.base.BaseSessionDaoSupport;
import net.wangxj.authority.po.PlatformPO;
import net.wangxj.authority.dao.PlatformDao;
import org.apache.log4j.Logger;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:44
 */

@Repository("platformDao")
public class PlatformDaoImpl extends BaseSessionDaoSupport implements PlatformDao{

	private static Logger logger = Logger.getLogger(PlatformDaoImpl.class);
	
	@Override
	public Integer insert(PlatformPO platformPo) {
		return super.getSqlSession().update("PlatformPOMapper.insert", platformPo);
	}
	
	@Override
	public Integer insertBatch(List<PlatformPO> listPo){
		Integer count=0;
		for(PlatformPO platformPo : listPo){
			this.insert(platformPo);
			count++;
		}
		return count;
	}
	
	@Override
	public Integer updateByUuid(PlatformPO platformPo) {
	
		return super.getSqlSession().update("PlatformPOMapper.updateByUuid", platformPo);
	}
	
	@Override
	public List<PlatformPO> selectListByCondition(PlatformPO platformPo) {
		
		return super.getSqlSession().selectList("PlatformPOMapper.selectByCondition", platformPo);
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

	@Override
	public Integer modifyByBatch(List<PlatformPO> platformPoList) {
		int count = 0;
		for (PlatformPO platformPO : platformPoList) {
			this.updateByUuid(platformPO);
			count++;
		}
		return count;
	}
	
}
