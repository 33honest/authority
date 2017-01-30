

package net.wangxj.authority.service.impl;


import java.util.List;


import javax.annotation.Resource;
import org.apache.log4j.Logger;
import net.wangxj.authority.dao.AuthorityRoleDao;
import net.wangxj.authority.po.AuthorityRolePO;
import net.wangxj.authority.service.AuthorityRoleService;
import net.wangxj.util.string.StringUtil;

import org.springframework.stereotype.Service;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:40
 */

@Service("authorityRoleService")
public class AuthorityRoleServiceImpl implements AuthorityRoleService{
	
	private static Logger logger = Logger.getLogger(AuthorityRoleServiceImpl.class);
	
	@Resource
	private AuthorityRoleDao authorityRoleDao;
	
	@Override
	public Integer add(AuthorityRolePO authorityRolePo) {
		
		return authorityRoleDao.insert(authorityRolePo);
	}
	
	@Override
	public Integer addBatch(List<AuthorityRolePO> listPo){
		return authorityRoleDao.insertBatch(listPo);
	}
	
	@Override
	public List<AuthorityRolePO> queryPageListByCondition(AuthorityRolePO authorityRolePo, int pageNum, int limit,String order,String sort) {
		sort = StringUtil.getNumpReverse(sort);
		return authorityRoleDao.selectPageListByCondition(authorityRolePo, pageNum, limit, order, sort);
	}


	@Override
	public Integer modifyByUuid(AuthorityRolePO authorityRolePo) {
		
		return authorityRoleDao.updateByUuid(authorityRolePo);
	}

	@Override
	public List<AuthorityRolePO> queryListByCondition(AuthorityRolePO authorityRolePo) {
		
		return authorityRoleDao.selectListByCondition(authorityRolePo);
	}

	@Override
	public Integer getCountByCondition(AuthorityRolePO authorityRolePo) {
		
		return authorityRoleDao.getCountByCondition(authorityRolePo);
	}
	
	@Override
	public Integer modifyByBatch(List<AuthorityRolePO> roleList){
		return authorityRoleDao.modifyByBatch(roleList);
	}
	

}
