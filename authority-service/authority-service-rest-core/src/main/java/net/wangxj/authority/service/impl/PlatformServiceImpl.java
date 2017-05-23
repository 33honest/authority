package net.wangxj.authority.service.impl;
import java.lang.reflect.Field;
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
import net.wangxj.authority.dao.PlatformDao;
import net.wangxj.authority.po.AuthorityResourcesPO;
import net.wangxj.authority.po.AuthorityRolePO;
import net.wangxj.authority.po.PO;
import net.wangxj.authority.po.Page;
import net.wangxj.authority.po.PlatformPO;
import net.wangxj.authority.service.PlatformService;
import net.wangxj.util.annotation.NotRepeat;
import net.wangxj.util.string.TimeUtil;
import net.wangxj.util.string.UuidUtil;

import org.springframework.stereotype.Service;


/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:44
 */

@Service("platformService")
public class PlatformServiceImpl implements PlatformService{
	
	private static Logger logger = Logger.getLogger(PlatformServiceImpl.class);
	
	@Resource
	private PlatformDao platformDao;
	@Resource
	private AuthorityResourcesDao authorityResourcesDao;
	@Resource
	private AuthorityRoleDao authorityRoleDao;
	@Resource
	private AuthorityUserRoleRelationDao authorityUserRoleRelationDao;
	@Resource
	private AuthorityRoleResourcesRelationDao authorityRoleResourcesRelationDao;

	/**
	 * 添加
	 * 在添加平台的同时,为该平台添加一级资源
	 */
	@Override
	public Integer add(PlatformPO platformPo) {
		platformPo.setPlatformUuid(UuidUtil.newGUID());
		platformPo.setPlatformAddTime(TimeUtil.getNowStr());
		platformPo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		//添加一级资源
		//一级资源uuid和其父级uuid
		String resourceUuid = UuidUtil.newGUID();
		AuthorityResourcesPO resourcePo = new AuthorityResourcesPO();
		resourcePo.setResourceAddBy(platformPo.getPlatformAddBy());
		resourcePo.setResourceAddTime(TimeUtil.getNowStr());
		resourcePo.setResourceIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		resourcePo.setResourceLevel(1);
		resourcePo.setResourceName(platformPo.getPlatformName());
		resourcePo.setResourceOrder(1);
		resourcePo.setResourceParentUuid(resourceUuid);
		resourcePo.setResourceUuid(resourceUuid);
		resourcePo.setResourcePlatformUuid(platformPo.getPlatformUuid());
		resourcePo.setResourceStatus(DataDictionaryConstant.RESOURCE_STATUS_ACTIVE_VALUE);
		resourcePo.setResourceUrl("");
		logger.debug("为平台--" + platformPo.getPlatformName() + "--添加一级资源--->" + resourcePo);
		Integer count = authorityResourcesDao.insert(resourcePo);
		logger.debug("添加平台开始:---->" + platformPo);
		return platformDao.insert(platformPo);
	}
	
	@Override
	public Integer addBatch(List<PlatformPO> listPo){
		Integer count = 0;
		for (PlatformPO platformPO : listPo) {
			this.add(platformPO);
			count++;
		}
		return count;
	}
	
	@Override
	public Map<String, Object> pageQuery(PlatformPO platformPo, Page page) {
		platformPo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		//数据
		List<PlatformPO> pageList = platformDao.selectPageListByCondition(platformPo, page.getPageNum(), page.getLimit(),page.getOrder(),page.getSort());
		//数量
		Integer count = this.getCount(platformPo);
		logger.debug("分页查询:-->count:" + count + "---->data:"+pageList);
		Map<String, Object> resutlMap = new HashMap<>();
		resutlMap.put("count", count);
		resutlMap.put("data", pageList);
		return resutlMap;
	}


	@Override
	public Integer update(PlatformPO platformPo) {
		platformPo.setPlatformEditTime(TimeUtil.getNowStr());
		return platformDao.updateByUuid(platformPo);
	}

	@Override
	public List<PlatformPO> query(PlatformPO platformPo) {
		platformPo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		return platformDao.selectListByCondition(platformPo);
	}

	@Override
	public Integer updateBatch(List<PlatformPO> platformPoList) {
		Integer count = 0;
		for (PlatformPO platformPO : platformPoList) {
			this.update(platformPO);
			count++;
			logger.debug("更新第--" + count + "--条");
		}
		logger.debug("总共更新--"+ count + "--条");
		return count;
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityService#getCountByCondition(java.lang.Object)
	 */
	@Override
	public Integer getCount(PlatformPO platformPo) {
		platformPo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		return platformDao.getCountByCondition(platformPo);
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityService#delete(java.lang.Object)
	 * 该操作会删除掉1）平台信息 2)平台下的所有角色信息 3)平台下的资源信息 4)平台下所有角色与用户对应的关联信息 5)平台下所有资源与角色对应的关联信息
	 */
	@Override
	public Integer delete(PlatformPO po) {
		//删除用户在该平台下的角色
		authorityUserRoleRelationDao.deleteByPlatform(po.getPlatformUuid());
		//删除该平台下所有角色的资源
		authorityRoleResourcesRelationDao.deleteByPlatform(po.getPlatformUuid());
		//删除该平台下的所有角色
		AuthorityRolePO rolePo = new AuthorityRolePO();
		rolePo.setRoleAddBy(po.getPlatformAddBy());
		authorityRoleDao.delete(rolePo);
		//删除该平台下的所有资源
		AuthorityResourcesPO resourcePo = new AuthorityResourcesPO();
		resourcePo.setResourceAddBy(po.getPlatformAddBy());
		authorityResourcesDao.delete(resourcePo);
		
		return platformDao.delete(po);
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityService#deleteBatch(java.util.List)
	 */
	@Override
	public Integer deleteBatch(List<PlatformPO> listPo) {
		Integer count = 0;
		for (PlatformPO platformPO : listPo) {
			this.delete(platformPO);
			logger.debug("删除第--" + ++count + "--条");
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AbstractAuthorityService#validateRepeat(net.wangxj.authority.po.PO, java.lang.String)
	 */
	@Override
	public String validateRepeat(PO singlePo,PO originPo, String fieldName) throws NoSuchFieldException, SecurityException {
		PlatformPO platformPo = (PlatformPO) singlePo;
		PlatformPO originPlatformPo = (PlatformPO) originPo;
		List<PlatformPO> existListPo = this.query(platformPo);
		if(existListPo == null || existListPo.size() == 0){
			return null;
		}
		else if(existListPo.size() == 1 && existListPo.get(0).getPlatformUuid().equals(originPlatformPo.getPlatformUuid())){
			return null;
		}
		else{
			Field annotatedNotRepeatFiled = PlatformPO.class.getDeclaredField(fieldName);
			NotRepeat notRepeatAnnotation = annotatedNotRepeatFiled.getAnnotation(NotRepeat.class);
			return notRepeatAnnotation.message();
		}
	}
}
