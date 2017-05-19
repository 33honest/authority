package net.wangxj.authority.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.github.pagehelper.PageHelper;

import net.wangxj.authority.dao.base.BaseSessionDaoSupport;
import net.wangxj.authority.po.AuthorityUserPO;
import net.wangxj.authority.dao.AuthorityUserDao;
import org.apache.log4j.Logger;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:42
 */

@Repository("authorityUserDao")
public class AuthorityUserDaoImpl extends BaseSessionDaoSupport implements AuthorityUserDao{

	private static Logger logger = Logger.getLogger(AuthorityUserDaoImpl.class);
	
	@Override
	public Integer insert(AuthorityUserPO authorityUserPo) {
		return super.getSqlSession().update("AuthorityUserPOMapper.insert", authorityUserPo);
	}
	
	
	@Override
	public Integer updateByUuid(AuthorityUserPO authorityUserPo) {
	
		return super.getSqlSession().update("AuthorityUserPOMapper.updateByUuid", authorityUserPo);
	}
	
	@Override
	public List<AuthorityUserPO> selectListByCondition(AuthorityUserPO authorityUserPo) {
		Map<String, Object> map = new HashMap<>();
		map.put("user", authorityUserPo);
		return super.getSqlSession().selectList("AuthorityUserPOMapper.selectByCondition", map);
	}
	
	@Override
	public List<AuthorityUserPO> selectPageListByCondition(AuthorityUserPO authorityUserPo, int pageNum, int limit,String order,String sort) {
		Map<String, Object> map = new HashMap<>();
		map.put("user", authorityUserPo);
		map.put("order", order);
		map.put("sort", sort);
		PageHelper.startPage(pageNum, limit);
		return super.getSqlSession().selectList("AuthorityUserPOMapper.selectByCondition", map);
	}

	@Override
	public Integer getCountByCondition(AuthorityUserPO authorityUserPo) {
		
		return (Integer)super.getSqlSession().selectOne("AuthorityUserPOMapper.selectCountByCondition", authorityUserPo);
	}
	
	
}
