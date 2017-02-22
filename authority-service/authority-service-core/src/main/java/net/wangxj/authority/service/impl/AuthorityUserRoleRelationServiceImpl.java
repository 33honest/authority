

package net.wangxj.authority.service.impl;


import java.util.ArrayList;
import java.util.List;


import javax.annotation.Resource;
import org.apache.log4j.Logger;
import net.wangxj.authority.dao.AuthorityUserRoleRelationDao;
import net.wangxj.authority.po.AuthorityUserRoleRelationPO;
import net.wangxj.authority.service.AuthorityUserRoleRelationService;
import org.springframework.stereotype.Service;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:43
 */

@Service("authorityUserRoleRelationService")
public class AuthorityUserRoleRelationServiceImpl implements AuthorityUserRoleRelationService{
	
	private static Logger logger = Logger.getLogger(AuthorityUserRoleRelationServiceImpl.class);
	
	@Resource
	private AuthorityUserRoleRelationDao authorityUserRoleRelationDao;
	
	@Override
	public Integer add(AuthorityUserRoleRelationPO authorityUserRoleRelationPo) {
		return authorityUserRoleRelationDao.insert(authorityUserRoleRelationPo);
	}
	
	@Override
	public Integer addBatch(List<AuthorityUserRoleRelationPO> listPo){
		
		for (AuthorityUserRoleRelationPO authorityUserRoleRelationPO2 : listPo) {
			AuthorityUserRoleRelationPO deletePo = new AuthorityUserRoleRelationPO();
			deletePo.setUrUserUuid(authorityUserRoleRelationPO2.getUrUserUuid());
			authorityUserRoleRelationDao.deleteBy(deletePo);
		}
		
		return authorityUserRoleRelationDao.insertBatch(listPo);
	}
	
	@Override
	public List<AuthorityUserRoleRelationPO> queryPageListByCondition(AuthorityUserRoleRelationPO authorityUserRoleRelationPo, int pageNum, int limit) {
		
		return authorityUserRoleRelationDao.selectPageListByCondition(authorityUserRoleRelationPo, pageNum, limit, null, null);
	}


	@Override
	public Integer modifyByUuid(AuthorityUserRoleRelationPO authorityUserRoleRelationPo) {
		
		return authorityUserRoleRelationDao.updateByUuid(authorityUserRoleRelationPo);
	}

	@Override
	public List<AuthorityUserRoleRelationPO> queryListByCondition(AuthorityUserRoleRelationPO authorityUserRoleRelationPo) {
		
		return authorityUserRoleRelationDao.selectListByCondition(authorityUserRoleRelationPo);
	}

	@Override
	public Integer getCountByCondition(AuthorityUserRoleRelationPO authorityUserRoleRelationPo) {
		
		return authorityUserRoleRelationDao.getCountByCondition(authorityUserRoleRelationPo);
	}

}
