package net.wangxj.authority.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.log4j.Logger;

import net.wangxj.authority.DataDictionaryConstant.DataDictionaryConstant;
import net.wangxj.authority.dao.AuthorityRoleDao;
import net.wangxj.authority.po.AuthorityRolePO;
import net.wangxj.authority.po.PO;
import net.wangxj.authority.po.Page;
import net.wangxj.authority.service.AuthorityRoleService;
import net.wangxj.util.annotation.NotRepeat;
import net.wangxj.util.string.TimeUtil;
import net.wangxj.util.string.UuidUtil;

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
		authorityRolePo.setRoleAddTime(TimeUtil.getNowStr());
		authorityRolePo.setRoleIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		authorityRolePo.setRoleUuid(UuidUtil.newGUID());
		logger.debug("添加:-->" + authorityRolePo);
		return authorityRoleDao.insert(authorityRolePo);
	}
	
	@Override
	public Integer addBatch(List<AuthorityRolePO> listPo){
		Integer count = 0;
		for (AuthorityRolePO authorityRolePO : listPo) {
			this.add(authorityRolePO);
			logger.debug("添加第--" + (++count) + "--条");
		}
		logger.debug("总共添加--" + count + "--条");
		return count;
	}
	
	@Override
	public Integer update(AuthorityRolePO authorityRolePo) {
		authorityRolePo.setRoleEditTime(TimeUtil.getNowStr());
		logger.debug("更新数据:-->" + authorityRolePo);
		return authorityRoleDao.updateByUuid(authorityRolePo);
	}
	
	@Override
	public List<AuthorityRolePO> query(AuthorityRolePO authorityRolePo) {
		authorityRolePo.setRoleIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		logger.debug("查询条件:-->" + authorityRolePo);
		return authorityRoleDao.selectListByCondition(authorityRolePo);
	}
	
	@Override
	public Map<String, Object> pageQuery(AuthorityRolePO authorityRolePo,Page page) {
		authorityRolePo.setRoleIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		logger.debug("查询条件:userpo-->" + authorityRolePo + "--page-->" + page );
		List<AuthorityRolePO> listRolePo = authorityRoleDao.selectPageListByCondition(authorityRolePo, page.getPageNum(), page.getLimit(), page.getOrder(), page.getSort());
		Integer count = authorityRoleDao.getCountByCondition(authorityRolePo);
		Map<String, Object> pageQueryResultMap = new HashMap<>();
		pageQueryResultMap.put("data", listRolePo);
		pageQueryResultMap.put("count", count);
		logger.debug("查询结果:-->" + pageQueryResultMap);
		return pageQueryResultMap;
	}


	@Override
	public Integer getCount(AuthorityRolePO authorityRolePo) {
		authorityRolePo.setRoleIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		return authorityRoleDao.getCountByCondition(authorityRolePo);
	}
	
	@Override
	public Integer updateBatch(List<AuthorityRolePO> roleList){
		Integer count = 0;
		for (AuthorityRolePO authorityRolePO : roleList) {
			this.update(authorityRolePO);
			logger.debug("更新第--" + count + "--条");
		}
		logger.debug("总共更新--" + count + "条");
		return count;
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityService#delete(java.lang.Object)
	 */
	@Override
	public Integer delete(AuthorityRolePO rolePo) {
		rolePo.setRoleIsDelete(DataDictionaryConstant.ISDELETE_YES_VALUE);
		rolePo.setRoleDelTime(TimeUtil.getNowStr());
		return authorityRoleDao.updateByUuid(rolePo);
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityService#deleteBatch(java.util.List)
	 */
	@Override
	public Integer deleteBatch(List<AuthorityRolePO> listPo) {
		Integer count = 0;
		for (AuthorityRolePO authorityRolePO : listPo) {
			this.delete(authorityRolePO);
			logger.debug("删除第--" + (++count) + "--条");
		}
		logger.debug("总共删除" + count + "条");
		return count;
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityService#validateRepeat(net.wangxj.authority.po.PO, java.lang.String)
	 */
	@Override
	public String validateRepeat(PO singlePo ,PO originPo, String fieldName) throws NoSuchFieldException, SecurityException {
		AuthorityRolePO singleRolePo = (AuthorityRolePO) singlePo;
		AuthorityRolePO originRolePo = (AuthorityRolePO) originPo;
		singleRolePo.setRolePlatformUuid(originRolePo.getRolePlatformUuid());
		List<AuthorityRolePO> repeatResultList = authorityRoleDao.selectListByCondition(singleRolePo);
		if(repeatResultList == null || repeatResultList.size() == 0){
			return null;
		}else if(repeatResultList.size() == 1 && repeatResultList.get(0).getRoleUuid().equals(originRolePo.getRoleUuid())){
			return null;
		}else{
			Field annotatedNotRepeatFiled = AuthorityRolePO.class.getDeclaredField(fieldName);
			NotRepeat notRepeatAnnotation = annotatedNotRepeatFiled.getAnnotation(NotRepeat.class);
			return notRepeatAnnotation.message();
		}
	}
}
