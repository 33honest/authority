

package net.wangxj.authority.service.impl;


import java.util.List;


import javax.annotation.Resource;
import org.apache.log4j.Logger;
import net.wangxj.authority.dao.AuthorityResourcesDao;
import net.wangxj.authority.po.AuthorityResourcesPO;
import net.wangxj.authority.service.AuthorityResourcesService;
import net.wangxj.util.string.StringUtil;

import org.springframework.stereotype.Service;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:38
 */

@Service("authorityResourcesService")
public class AuthorityResourcesServiceImpl implements AuthorityResourcesService{
	
	private static Logger logger = Logger.getLogger(AuthorityResourcesServiceImpl.class);
	
	@Resource
	private AuthorityResourcesDao authorityResourcesDao;
	
	@Override
	public Integer add(AuthorityResourcesPO authorityResourcesPo) {
		
		return authorityResourcesDao.insert(authorityResourcesPo);
	}
	
	@Override
	public Integer addBatch(List<AuthorityResourcesPO> listPo){
		return authorityResourcesDao.insertBatch(listPo);
	}
	
	@Override
	public List<AuthorityResourcesPO> queryPageListByCondition(AuthorityResourcesPO authorityResourcesPo, int pageNum, int limit,String order, String sort) {
		sort = StringUtil.getNumpReverse(sort);
		return authorityResourcesDao.selectPageListByCondition(authorityResourcesPo, pageNum, limit, order, sort);
	}


	@Override
	public Integer modifyByUuid(AuthorityResourcesPO authorityResourcesPo) {
		
		return authorityResourcesDao.updateByUuid(authorityResourcesPo);
	}

	@Override
	public List<AuthorityResourcesPO> queryListByCondition(AuthorityResourcesPO authorityResourcesPo) {
		
		return authorityResourcesDao.selectListByCondition(authorityResourcesPo);
	}

	@Override
	public Integer getCountByCondition(AuthorityResourcesPO authorityResourcesPo) {
		
		return authorityResourcesDao.getCountByCondition(authorityResourcesPo);
	}
	
	@Override
	public Integer modifyByBatch(List<AuthorityResourcesPO> resourceList){
		return authorityResourcesDao.modifyByBatch(resourceList);
	}

}
