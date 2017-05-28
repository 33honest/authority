package net.wangxj.authority.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.log4j.Logger;

import net.wangxj.authority.DataDictionaryConstant.DataDictionaryConstant;
import net.wangxj.authority.dao.AuthorityRoleDao;
import net.wangxj.authority.dao.AuthorityUserDao;
import net.wangxj.authority.dao.AuthorityUserRoleRelationDao;
import net.wangxj.authority.po.AuthorityResourcesPO;
import net.wangxj.authority.po.AuthorityRolePO;
import net.wangxj.authority.po.AuthorityUserPO;
import net.wangxj.authority.po.AuthorityUserRoleRelationPO;
import net.wangxj.authority.po.PO;
import net.wangxj.authority.po.Page;
import net.wangxj.authority.service.AuthorityUserService;
import net.wangxj.util.annotation.NotRepeat;
import net.wangxj.util.encry.PBKDF2SHA1;
import net.wangxj.util.string.TimeUtil;
import net.wangxj.util.string.UuidUtil;

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
	@Resource
	private AuthorityUserRoleRelationDao authorityUserRoleRelationDao;
	@Resource
	private AuthorityRoleDao authorityRoleDao;
	
	@Override
	public Integer add(AuthorityUserPO authorityUserPo) throws Exception {
		try {
			String userLoginPwd = authorityUserPo.getUserLoginPwd();
			authorityUserPo.setUserLoginPwd(PBKDF2SHA1.generateStorngPasswordHash(userLoginPwd));
		} catch (Exception e) {
			logger.debug("加密失败", e);
			throw e;
		}
		authorityUserPo.setUserUuid(UuidUtil.newGUID());
		authorityUserPo.setUserAddTime(TimeUtil.getNowStr());
		authorityUserPo.setUserIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		logger.debug("插入:--->" + authorityUserPo);
		return authorityUserDao.insert(authorityUserPo);
	}
	
	@Override
	public Integer addBatch(List<AuthorityUserPO> listPo) throws Exception{
		Integer count = 0;
		for (AuthorityUserPO authorityUserPO : listPo) {
			this.add(authorityUserPO);
			logger.debug("插入第" + (++count) + "条完成");
		}
		return count;
	}
	
	@Override
	public Integer update(AuthorityUserPO authorityUserPo) throws Exception {
		try {
			String userLoginPwd = authorityUserPo.getUserLoginPwd();
			if(userLoginPwd != null && !"".equals(userLoginPwd)){
				authorityUserPo.setUserLoginPwd(PBKDF2SHA1.generateStorngPasswordHash(userLoginPwd));
			}
		} catch (Exception e) {
			logger.debug("加密失败", e);
			throw e;
		}
		authorityUserPo.setUserEditTime(TimeUtil.getNowStr());
		return authorityUserDao.updateByUuid(authorityUserPo);
	}
	
	@Override
	public List<AuthorityUserPO> query(AuthorityUserPO authorityUserPo) {
		authorityUserPo.setUserIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		return authorityUserDao.selectListByCondition(authorityUserPo);
	}
	
	@Override
	public Map<String, Object> pageQuery(AuthorityUserPO userPo,Page page) {
		userPo.setUserIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		//数据
		List<AuthorityUserPO> pageList = authorityUserDao.selectPageListByCondition(userPo,page.getPageNum(), page.getLimit(), page.getOrder(), page.getSort());
		//数量
		Integer count = authorityUserDao.getCountByCondition(userPo);
		Map<String, Object> resutlMap = new HashMap<>();
		resutlMap.put("count", count);
		resutlMap.put("data", pageList);
		logger.debug("分页查询结果:--->" + resutlMap);
		return resutlMap;
	}
	
	@Override
	public Integer getCount(AuthorityUserPO authorityUserPo) {
		authorityUserPo.setUserIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		return authorityUserDao.getCountByCondition(authorityUserPo);
	}
	
	@Override
	public Integer updateBatch(List<AuthorityUserPO> userList) throws Exception {
		Integer count = 0;
		for (AuthorityUserPO authorityUserPO : userList) {
			this.update(authorityUserPO);
			logger.debug("更新完第---" + (++count) + "---条");
		}
		logger.debug("总共更新--" + count + "--条");
		return count;
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityService#delete(java.lang.Object)
	 */
	@Override
	public Integer delete(AuthorityUserPO userPo) {
		//删除与该用户所关联的所有与角色的关联关系
		authorityUserRoleRelationDao.deleteByUser(userPo.getUserUuid());
		//删除用户本身
		return authorityUserDao.delete(userPo);
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityService#deleteBatch(java.util.List)
	 */
	@Override
	public Integer deleteBatch(List<AuthorityUserPO> listPo) {
		Integer count = 0;
		for (AuthorityUserPO authorityUserPO : listPo) {
			this.delete(authorityUserPO);
			logger.debug("删除完第--" + (++count) + "条");
		}
		logger.debug("总共删除--" + count + "条");
		return count;
	}
	
	

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityService#validateRepeat(net.wangxj.authority.po.PO, java.lang.String)
	 */
	@Override
	public String validateRepeat(PO singlePo,PO originPo, String fieldName) throws NoSuchFieldException, SecurityException {
		AuthorityUserPO userPo = (AuthorityUserPO) singlePo;
		AuthorityUserPO originUserPo = (AuthorityUserPO) originPo;
		List<AuthorityUserPO> listUserPo = this.query(userPo);
		if(listUserPo == null || listUserPo.size() == 0 ){
			return null;
		}
		else if(listUserPo.size() == 1 && listUserPo.get(0).getUserUuid().equals(originUserPo.getUserUuid())){
			return null;
		}
		else{
			Field annotatedNotRepeatFiled = AuthorityUserPO.class.getDeclaredField(fieldName);
			NotRepeat notRepeatAnnotation = annotatedNotRepeatFiled.getAnnotation(NotRepeat.class);
			return notRepeatAnnotation.message();
		}
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityUserService#grantRoles(java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public Boolean grantRoles(String userUuid, String platformUuid, List<String> rolesUuidList, String addBy) {
		//先删除该用户在该平台所拥有的所有角色
		AuthorityUserRoleRelationPO userRoleRelationPo = new AuthorityUserRoleRelationPO();
		userRoleRelationPo.setUrUserUuid(userUuid);
		authorityUserRoleRelationDao.deleteBy(userRoleRelationPo, platformUuid);
		//添加新的授权
		Integer count = 0;
		for(String roleUuid : rolesUuidList){
			AuthorityUserRoleRelationPO addUserRoleRelationPo = new AuthorityUserRoleRelationPO();
			addUserRoleRelationPo.setUrAddBy(addBy);
			addUserRoleRelationPo.setUrAddTime(TimeUtil.getNowStr());
			addUserRoleRelationPo.setUrIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
			addUserRoleRelationPo.setUrRoleUuid(roleUuid);
			addUserRoleRelationPo.setUrUserUuid(userUuid);
			addUserRoleRelationPo.setUrUuid(UuidUtil.newGUID());
			authorityUserRoleRelationDao.insert(addUserRoleRelationPo);
			logger.debug("添加第--" + (++count) + "--条授权");
		}
		logger.debug("共为用户" + userUuid + "添加--" + count + "--条授权");
		return rolesUuidList.size() == count.intValue();
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityUserService#roles(java.lang.String)
	 */
	@Override
	public List<AuthorityRolePO> roles(String userUuid) {
		List<AuthorityRolePO> roleList = new ArrayList<>();
		//获取该用户所对应的所有resource uuid列表
		AuthorityUserRoleRelationPO userRolePo = new AuthorityUserRoleRelationPO();
		userRolePo.setUrUserUuid(userUuid);
		List<AuthorityUserRoleRelationPO> userHasRoleList = authorityUserRoleRelationDao.selectListByCondition(userRolePo);
		logger.debug("用户" + userUuid + "拥有的角色:-->" + userHasRoleList);
		for (AuthorityUserRoleRelationPO authorityUserRoleRelationPO : userHasRoleList) {
			String roleUuid = authorityUserRoleRelationPO.getUrRoleUuid();
			AuthorityRolePO rolePo = new AuthorityRolePO();
			rolePo.setRoleUuid(roleUuid);
			List<AuthorityRolePO> roles = authorityRoleDao.selectListByCondition(rolePo);
			if(roles == null || roles.size() == 0){
				continue;
			}
			roleList.add(roles.get(0));
		}
		logger.debug("用户" + userUuid + "拥有的角色列表-->" + roleList);
		return roleList;
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityUserService#search(java.lang.String, net.wangxj.authority.po.Page)
	 */
	@Override
	public Map<String, Object> search(String search, Page page) {
		List<AuthorityUserPO> searchList = authorityUserDao.search(search,page.getPageNum(), page.getLimit(), page.getOrder(), page.getSort());
		Integer count = authorityUserDao.searchCount(search);
		Map<String, Object> resutlMap = new HashMap<>();
		resutlMap.put("count", count);
		resutlMap.put("data", searchList);
		logger.debug("搜索查询结果:--->" + resutlMap);
		return resutlMap;
	}

}
