package net.wangxj.authority.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.github.pagehelper.PageHelper;

import net.wangxj.authority.dao.base.BaseSessionDaoSupport;
import net.wangxj.authority.po.AuthorityResourcesPO;
import net.wangxj.util.string.TimeUtil;
import net.wangxj.authority.DataDictionaryConstant.DataDictionaryConstant;
import net.wangxj.authority.dao.AuthorityResourcesDao;
import org.apache.log4j.Logger;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:38
 */

@Repository("authorityResourcesDao")
public class AuthorityResourcesDaoImpl extends BaseSessionDaoSupport implements AuthorityResourcesDao{

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(AuthorityResourcesDaoImpl.class);
	
	@Override
	public Integer insert(AuthorityResourcesPO authorityResourcesPo) {
		return super.getSqlSession().update("AuthorityResourcesPOMapper.insert", authorityResourcesPo);
	}
	
	
	@Override
	public Integer updateByUuid(AuthorityResourcesPO authorityResourcesPo) {
	
		return super.getSqlSession().update("AuthorityResourcesPOMapper.updateByUuid", authorityResourcesPo);
	}
	
	@Override
	public List<AuthorityResourcesPO> selectListByCondition(AuthorityResourcesPO authorityResourcesPo) {
		Map<String, Object> map = new HashMap<>();
		map.put("resource", authorityResourcesPo);
		return super.getSqlSession().selectList("AuthorityResourcesPOMapper.selectByCondition", map);
	}
	
	@Override
	public List<AuthorityResourcesPO> selectPageListByCondition(AuthorityResourcesPO authorityResourcesPo, int pageNum, int limit,String order,String sort) {
		Map<String, Object> map = new HashMap<>();
		map.put("resource", authorityResourcesPo);
		map.put("order", order);
		map.put("sort", sort);
		PageHelper.startPage(pageNum, limit);
		return super.getSqlSession().selectList("AuthorityResourcesPOMapper.selectByCondition", map);
	}

	@Override
	public Integer getCountByCondition(AuthorityResourcesPO authorityResourcesPo) {
		
		return (Integer)super.getSqlSession().selectOne("AuthorityResourcesPOMapper.selectCountByCondition", authorityResourcesPo);
	}


	/* (non-Javadoc)
	 * @see net.wangxj.authority.dao.AuthorityResourcesDao#delete(net.wangxj.authority.po.AuthorityResourcesPO)
	 */
	@Override
	public Integer delete(AuthorityResourcesPO resourcePo) {
		resourcePo.setResourceDelTime(TimeUtil.getNowStr());
		resourcePo.setResourceIsDelete(DataDictionaryConstant.ISDELETE_YES_VALUE);
		return super.getSqlSession().update("AuthorityResourcesPOMapper.updateByUuid", resourcePo);
	}


	/* (non-Javadoc)
	 * @see net.wangxj.authority.dao.AuthorityResourcesDao#hasChildListByCondition(net.wangxj.authority.po.AuthorityResourcesPO)
	 */
	@Override
	public List<AuthorityResourcesPO> hasChildListByCondition(AuthorityResourcesPO resourcePO) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("resource", resourcePO);
		return super.getSqlSession().selectList("AuthorityResourcesPOMapper.selectByConditionHasChildList", map);
	}
	
}
