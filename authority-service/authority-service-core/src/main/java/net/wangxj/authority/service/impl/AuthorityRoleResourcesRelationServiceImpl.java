

package net.wangxj.authority.service.impl;


import java.util.List;


import javax.annotation.Resource;
import org.apache.log4j.Logger;
import net.wangxj.authority.dao.AuthorityRoleResourcesRelationDao;
import net.wangxj.authority.po.AuthorityRoleResourcesRelationPO;
import net.wangxj.authority.service.AuthorityRoleResourcesRelationService;
import org.springframework.stereotype.Service;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:41
 */

@Service("authorityRoleResourcesRelationService")
public class AuthorityRoleResourcesRelationServiceImpl implements AuthorityRoleResourcesRelationService{
	
	private static Logger logger = Logger.getLogger(AuthorityRoleResourcesRelationServiceImpl.class);
	
	@Resource
	private AuthorityRoleResourcesRelationDao authorityRoleResourcesRelationDao;
	
	@Override
	public Integer add(AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo) {
		
		return authorityRoleResourcesRelationDao.insert(authorityRoleResourcesRelationPo);
	}
	
	@Override
	public Integer addBatch(List<AuthorityRoleResourcesRelationPO> listPo){
		return authorityRoleResourcesRelationDao.insertBatch(listPo);
	}
	
	@Override
	public List<AuthorityRoleResourcesRelationPO> queryPageListByCondition(AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo, int pageNum, int limit) {
		
		return authorityRoleResourcesRelationDao.selectPageListByCondition(authorityRoleResourcesRelationPo, pageNum, limit);
	}


	@Override
	public Integer modifyByUuid(AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo) {
		
		return authorityRoleResourcesRelationDao.updateByUuid(authorityRoleResourcesRelationPo);
	}

	@Override
	public List<AuthorityRoleResourcesRelationPO> queryListByCondition(AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo) {
		
		return authorityRoleResourcesRelationDao.selectListByCondition(authorityRoleResourcesRelationPo);
	}

	@Override
	public Integer getCountByCondition(AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo) {
		
		return authorityRoleResourcesRelationDao.getCountByCondition(authorityRoleResourcesRelationPo);
	}

}
