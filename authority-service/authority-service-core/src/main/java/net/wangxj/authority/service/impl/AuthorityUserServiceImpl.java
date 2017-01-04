

package net.wangxj.authority.service.impl;


import java.util.List;


import javax.annotation.Resource;
import org.apache.log4j.Logger;
import net.wangxj.authority.dao.AuthorityUserDao;
import net.wangxj.authority.po.AuthorityUserPO;
import net.wangxj.authority.service.AuthorityUserService;
import org.springframework.stereotype.Service;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:42
 */

@Service("authorityUserService")
public class AuthorityUserServiceImpl implements AuthorityUserService{
	
	private static Logger logger = Logger.getLogger(AuthorityUserServiceImpl.class);
	
	@Resource
	private AuthorityUserDao authorityUserDao;
	
	@Override
	public Integer add(AuthorityUserPO authorityUserPo) {
		
		return authorityUserDao.insert(authorityUserPo);
	}
	
	@Override
	public Integer addBatch(List<AuthorityUserPO> listPo){
		return authorityUserDao.insertBatch(listPo);
	}
	
	@Override
	public List<AuthorityUserPO> queryPageListByCondition(AuthorityUserPO authorityUserPo, int pageNum, int limit) {
		
		return authorityUserDao.selectPageListByCondition(authorityUserPo, pageNum, limit, null, null);
	}


	@Override
	public Integer modifyByUuid(AuthorityUserPO authorityUserPo) {
		
		return authorityUserDao.updateByUuid(authorityUserPo);
	}

	@Override
	public List<AuthorityUserPO> queryListByCondition(AuthorityUserPO authorityUserPo) {
		
		return authorityUserDao.selectListByCondition(authorityUserPo);
	}

	@Override
	public Integer getCountByCondition(AuthorityUserPO authorityUserPo) {
		
		return authorityUserDao.getCountByCondition(authorityUserPo);
	}

}
