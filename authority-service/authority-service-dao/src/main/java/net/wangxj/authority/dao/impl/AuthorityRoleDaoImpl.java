

package net.wangxj.authority.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.github.pagehelper.PageHelper;

import net.wangxj.authority.dao.base.BaseSessionDaoSupport;
import net.wangxj.authority.po.AuthorityRolePO;
import net.wangxj.authority.dao.AuthorityRoleDao;
import org.apache.log4j.Logger;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:40
 */

@Repository("authorityRoleDao")
public class AuthorityRoleDaoImpl extends BaseSessionDaoSupport implements AuthorityRoleDao{

	private static Logger logger = Logger.getLogger(AuthorityRoleDaoImpl.class);
	
	@Override
	public Integer insert(AuthorityRolePO authorityRolePo) {
		return super.getSqlSession().update("AuthorityRolePOMapper.insert", authorityRolePo);
	}
	
	@Override
	public Integer insertBatch(List<AuthorityRolePO> listPo){
		Integer count=0;
		for(AuthorityRolePO authorityRolePo : listPo){
			this.insert(authorityRolePo);
			count++;
		}
		return count;
	}
	
	@Override
	public Integer updateByUuid(AuthorityRolePO authorityRolePo) {
	
		return super.getSqlSession().update("AuthorityRolePOMapper.updateByUuid", authorityRolePo);
	}
	
	@Override
	public List<AuthorityRolePO> selectListByCondition(AuthorityRolePO authorityRolePo) {
		
		return super.getSqlSession().selectList("AuthorityRolePOMapper.selectByCondition", authorityRolePo);
	}
	
	@Override
	public List<AuthorityRolePO> selectPageListByCondition(AuthorityRolePO authorityRolePo, int pageNum, int limit) {
		PageHelper.startPage(pageNum, limit);
		return super.getSqlSession().selectList("AuthorityRolePOMapper.selectByCondition", authorityRolePo);
	}

	@Override
	public Integer getCountByCondition(AuthorityRolePO authorityRolePo) {
		
		return (Integer)super.getSqlSession().selectOne("AuthorityRolePOMapper.selectCountByCondition", authorityRolePo);
	}
	
}
