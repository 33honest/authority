package net.wangxj.authority.service.impl;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.log4j.Logger;

import net.wangxj.authority.DataDictionaryConstant.DataDictionaryConstant;
import net.wangxj.authority.dao.PlatformDao;
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

	/**
	 * 添加
	 */
	@Override
	public Integer add(PlatformPO platformPo) {
		platformPo.setPlatformUuid(UuidUtil.newGUID());
		platformPo.setPlatformAddTime(TimeUtil.getNowStr());
		platformPo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
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
	 */
	@Override
	public Integer delete(PlatformPO po) {
		po.setPlatformDelTime(TimeUtil.getNowStr());
		po.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_YES_VALUE);
		logger.debug("开始删除---->" + po);
		return platformDao.updateByUuid(po);
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
