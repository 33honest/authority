package net.wangxj.authority.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.log4j.Logger;

import net.wangxj.authority.DataDictionaryConstant.DataDictionaryConstant;
import net.wangxj.authority.dao.AuthorityResourcesDao;
import net.wangxj.authority.po.AuthorityResourcesPO;
import net.wangxj.authority.po.PO;
import net.wangxj.authority.po.Page;
import net.wangxj.authority.service.AuthorityResourcesService;
import net.wangxj.util.annotation.NotRepeat;
import net.wangxj.util.string.TimeUtil;
import net.wangxj.util.string.UuidUtil;

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
		authorityResourcesPo.setResourceUuid(UuidUtil.newGUID());
		authorityResourcesPo.setResourceAddTime(TimeUtil.getNowStr());
		authorityResourcesPo.setResourceIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		logger.debug("添加资源:-->" + authorityResourcesPo);
		return authorityResourcesDao.insert(authorityResourcesPo);
	}
	
	@Override
	public Integer addBatch(List<AuthorityResourcesPO> listPo){
		Integer count = 0;
		for (AuthorityResourcesPO authorityResourcesPO : listPo) {
			this.add(authorityResourcesPO);
			logger.debug("添加第--" + (++count) + "--个");
		}
		logger.debug("总共添加--" + count + "个");
		return count;
	}
	
	@Override
	public Integer update(AuthorityResourcesPO authorityResourcesPo) {
		authorityResourcesPo.setResourceEditTime(TimeUtil.getNowStr());
		return authorityResourcesDao.updateByUuid(authorityResourcesPo);
	}
	
	@Override
	public List<AuthorityResourcesPO> query(AuthorityResourcesPO authorityResourcesPo) {
		authorityResourcesPo.setResourceIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		return authorityResourcesDao.selectListByCondition(authorityResourcesPo);
	}
	
	@Override
	public Map<String , Object> pageQuery(AuthorityResourcesPO authorityResourcesPo, Page page) {
		authorityResourcesPo.setResourceIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		List<AuthorityResourcesPO> pageResourceList = authorityResourcesDao.selectPageListByCondition(authorityResourcesPo, 
				page.getPageNum(), page.getLimit(), page.getOrder(), page.getSort());
		Integer count = authorityResourcesDao.getCountByCondition(authorityResourcesPo);
		Map<String , Object> pageQueryResultMap = new HashMap<>();
		pageQueryResultMap.put("data", pageResourceList);
		pageQueryResultMap.put("count", count);
		return pageQueryResultMap;
	}
	

	@Override
	public Integer getCount(AuthorityResourcesPO authorityResourcesPo) {
		authorityResourcesPo.setResourceIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		return authorityResourcesDao.getCountByCondition(authorityResourcesPo);
	}
	
	@Override
	public Integer updateBatch(List<AuthorityResourcesPO> resourceList){
		Integer count = 0;
		for (AuthorityResourcesPO authorityResourcesPO : resourceList) {
			this.update(authorityResourcesPO);
			logger.debug("更新第--" + (++count) + "--个");
		}
		logger.debug("总共更新--" + count + "--个");
		return count;
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityService#delete(java.lang.Object)
	 */
	@Override
	public Integer delete(AuthorityResourcesPO resourcePo) {
		resourcePo.setResourceDelTime(TimeUtil.getNowStr());
		resourcePo.setResourceIsDelete(DataDictionaryConstant.ISDELETE_YES_VALUE);
		return authorityResourcesDao.updateByUuid(resourcePo);
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityService#deleteBatch(java.util.List)
	 */
	@Override
	public Integer deleteBatch(List<AuthorityResourcesPO> listPo) {
		Integer count = 0;
		for (AuthorityResourcesPO authorityResourcesPO : listPo) {
			this.delete(authorityResourcesPO);
			logger.debug("删除第--" + (++count) + "个");
		}
		logger.debug("总共删除--" + count + "-- 个");
		return count;
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityService#validateRepeat(net.wangxj.authority.po.PO, net.wangxj.authority.po.PO, java.lang.String)
	 */
	@Override
	public String validateRepeat(PO single, PO originPo, String fieldName)
			throws NoSuchFieldException, SecurityException {
		AuthorityResourcesPO singleResourcePo = (AuthorityResourcesPO) single;
		AuthorityResourcesPO originResourcePo = (AuthorityResourcesPO) originPo;
		//在同一平台下不重复
		singleResourcePo.setResourcePlatformUuid(originResourcePo.getResourcePlatformUuid());
		List<AuthorityResourcesPO> singleFieldQueryResult = authorityResourcesDao.selectListByCondition(singleResourcePo);
		if(singleFieldQueryResult == null || singleFieldQueryResult.size() == 0){
			return null;
		}else if(singleFieldQueryResult.size() == 1 && singleFieldQueryResult.get(0).getResourceUuid().equals(originResourcePo.getResourceUuid())){
			return null;
		}else{
			Field annotatedNotRepeatFiled = AuthorityResourcesPO.class.getDeclaredField(fieldName);
			NotRepeat notRepeatAnnotation = annotatedNotRepeatFiled.getAnnotation(NotRepeat.class);
			return notRepeatAnnotation.message();
		}
	}

}
