

package net.wangxj.authority.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.github.pagehelper.PageHelper;

import net.wangxj.authority.dao.base.BaseSessionDaoSupport;
import net.wangxj.authority.po.AuthorityRoleResourcesRelationPO;
import net.wangxj.authority.dao.AuthorityRoleResourcesRelationDao;
import org.apache.log4j.Logger;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:41
 */

@Repository("authorityRoleResourcesRelationDao")
public class AuthorityRoleResourcesRelationDaoImpl extends BaseSessionDaoSupport implements AuthorityRoleResourcesRelationDao{

	private static Logger logger = Logger.getLogger(AuthorityRoleResourcesRelationDaoImpl.class);
	
	@Override
	public Integer insert(AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo) {
		return super.getSqlSession().update("AuthorityRoleResourcesRelationPOMapper.insert", authorityRoleResourcesRelationPo);
	}
	
	
	@Override
	public Integer updateByUuid(AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo) {
	
		return super.getSqlSession().update("AuthorityRoleResourcesRelationPOMapper.updateByUuid", authorityRoleResourcesRelationPo);
	}
	
	@Override
	public List<AuthorityRoleResourcesRelationPO> selectListByCondition(AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo) {
		
		return super.getSqlSession().selectList("AuthorityRoleResourcesRelationPOMapper.selectByCondition", authorityRoleResourcesRelationPo);
	}
	
	@Override
	public List<AuthorityRoleResourcesRelationPO> selectPageListByCondition(AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo, int pageNum, int limit,String order,String sort) {
		PageHelper.startPage(pageNum, limit);
		return super.getSqlSession().selectList("AuthorityRoleResourcesRelationPOMapper.selectByCondition", authorityRoleResourcesRelationPo);
	}

	@Override
	public Integer getCountByCondition(AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo) {
		
		return (Integer)super.getSqlSession().selectOne("AuthorityRoleResourcesRelationPOMapper.selectCountByCondition", authorityRoleResourcesRelationPo);
	}

	/**
	 * 删除一个角色下的所有资源
	 */
	@Override
	public Integer deleteBy(String rrRoleUuid) {
		
		return super.getSqlSession().delete("AuthorityRoleResourcesRelationPOMapper.delete", rrRoleUuid);
	}


	/* (non-Javadoc)
	 * @see net.wangxj.authority.dao.AuthorityRoleResourcesRelationDao#deleteByPlatform(java.lang.String)
	 */
	@Override
	public Integer deleteByPlatform(String platformUuid) {
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("platformUuid", platformUuid);
		return super.getSqlSession().delete("AuthorityRoleResourcesRelationPOMapper.deleteByPlatform" ,paramMap);
	}


	/* (non-Javadoc)
	 * @see net.wangxj.authority.dao.AuthorityRoleResourcesRelationDao#deleteByResource(java.lang.String)
	 */
	@Override
	public Integer deleteByResource(String resourceUuid) {
		return super.getSqlSession().delete("AuthorityRoleResourcesRelationPOMapper.deleteByResource", resourceUuid);
	}
	
}
