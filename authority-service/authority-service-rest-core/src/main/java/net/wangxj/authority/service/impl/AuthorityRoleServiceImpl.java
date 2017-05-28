package net.wangxj.authority.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.log4j.Logger;

import net.wangxj.authority.DataDictionaryConstant.DataDictionaryConstant;
import net.wangxj.authority.dao.AuthorityResourcesDao;
import net.wangxj.authority.dao.AuthorityRoleDao;
import net.wangxj.authority.dao.AuthorityRoleResourcesRelationDao;
import net.wangxj.authority.dao.AuthorityUserRoleRelationDao;
import net.wangxj.authority.po.AuthorityResourcesPO;
import net.wangxj.authority.po.AuthorityRolePO;
import net.wangxj.authority.po.AuthorityRoleResourcesRelationPO;
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
	@Resource
	private AuthorityUserRoleRelationDao authorityUserRoleRelationDao;
	@Resource
	private AuthorityRoleResourcesRelationDao authorityRoleResourcesRelationDao;
	@Resource
	private AuthorityResourcesDao authorityResourcesDao;
	
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
		//删除角色与用户的所有关联
		authorityUserRoleRelationDao.deleteByRole(rolePo.getRoleUuid());
		//删除角色与资源的所有关联
		authorityRoleResourcesRelationDao.deleteBy(rolePo.getRoleUuid());
		//删除角色
		return authorityRoleDao.delete(rolePo);
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
		List<AuthorityRolePO> repeatResultList = this.query(singleRolePo);
		if(repeatResultList == null || repeatResultList.size() == 0){
			return null;
		}else if(repeatResultList.size() == 1 && repeatResultList.get(0).getRoleUuid().equals(originRolePo.getRoleUuid())){
			return null;
		}
		else{
			Field annotatedNotRepeatFiled = AuthorityRolePO.class.getDeclaredField(fieldName);
			NotRepeat notRepeatAnnotation = annotatedNotRepeatFiled.getAnnotation(NotRepeat.class);
			return notRepeatAnnotation.message();
		}
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityRoleService#grantResources(java.lang.String, java.util.List)
	 */
	@Override
	public Boolean grantResources(String roleUuid,String addUser, List<String> resourceUuids) {
		logger.debug("为角色" + roleUuid + "授予资源-->" + resourceUuids);
		//删除该角色下已被授予的所有资源
		Integer deletedCount = authorityRoleResourcesRelationDao.deleteBy(roleUuid);
		//开始新的授权
		Integer count = 0;
		for(String uuidGandType : resourceUuids){
			String[] uuidAndTypeArr = uuidGandType.split("-");
			String uuid = uuidAndTypeArr[0];
			String grantType = uuidAndTypeArr[1];
			AuthorityRoleResourcesRelationPO roleResourceRelationPo = new AuthorityRoleResourcesRelationPO();
			roleResourceRelationPo.setRrAddBy(addUser);
			roleResourceRelationPo.setRrAddTime(TimeUtil.getNowStr());
			roleResourceRelationPo.setRrGrantType(Integer.valueOf(grantType));
			roleResourceRelationPo.setRrIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
			roleResourceRelationPo.setRrResourceUuid(uuid);
			roleResourceRelationPo.setRrRoleUuid(roleUuid);
			roleResourceRelationPo.setRrUuid(UuidUtil.newGUID());
			//添加操作
			authorityRoleResourcesRelationDao.insert(roleResourceRelationPo);
			logger.debug("完成第--" + (++count) + "--条授权操作");
		}
		logger.debug("共完成--" + count + "--条授权操作");
		return resourceUuids.size() == count;
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityRoleService#getResources(java.lang.String)
	 */
	@Override
	public List<AuthorityResourcesPO> getResources(String roleUuid) {
		List<AuthorityResourcesPO> getResultList = new ArrayList<>();
		//获取该角色下所有角色uuid
		AuthorityRoleResourcesRelationPO roleResourcePo = new AuthorityRoleResourcesRelationPO();
		roleResourcePo.setRrRoleUuid(roleUuid);
		List<AuthorityRoleResourcesRelationPO> roleResourceListPo = authorityRoleResourcesRelationDao.selectListByCondition(roleResourcePo);
		logger.debug("角色" + roleUuid + "下的角色:-->" + roleResourceListPo);
		for (AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPO : roleResourceListPo) {
			//查询每个resource
			String resourceUuid = authorityRoleResourcesRelationPO.getRrResourceUuid();
			AuthorityResourcesPO resourcePO = new AuthorityResourcesPO();
			resourcePO.setResourceUuid(resourceUuid);
			getResultList.add(authorityResourcesDao.selectListByCondition(resourcePO).get(0));
		}
		logger.debug("查询结果:-->" + roleUuid + ":" + getResultList);
		return getResultList;
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityRoleService#search(java.lang.String, net.wangxj.authority.po.Page)
	 */
	@Override
	public Map<String, Object> search(String search, Page page) {
		List<AuthorityRolePO> listRolePo = authorityRoleDao.search(search, page.getPageNum(), page.getLimit(), page.getOrder(), page.getSort());
		Integer count = authorityRoleDao.searchCount(search);
		Map<String, Object> searchResultMap = new HashMap<>();
		searchResultMap.put("data", listRolePo);
		searchResultMap.put("count", count);
		logger.debug("查询结果:-->" + searchResultMap);
		return searchResultMap;
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityRoleService#searchCount(java.lang.String)
	 */
	@Override
	public Integer searchCount(String search) {
		// TODO Auto-generated method stub
		return null;
	}
}
