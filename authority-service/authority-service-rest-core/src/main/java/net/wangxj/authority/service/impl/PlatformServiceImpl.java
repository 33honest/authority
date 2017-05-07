

package net.wangxj.authority.service.impl;


import java.util.List;


import javax.annotation.Resource;
import org.apache.log4j.Logger;

import net.wangxj.authority.DataDictionaryConstant.DataDictionaryConstant;
import net.wangxj.authority.dao.PlatformDao;
import net.wangxj.authority.po.PlatformPO;
import net.wangxj.authority.service.PlatformService;
import net.wangxj.util.string.StringUtil;
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
		return platformDao.insert(platformPo);
	}
	
	@Override
	public Integer addBatch(List<PlatformPO> listPo){
		return platformDao.insertBatch(listPo);
	}
	
	@Override
	public List<PlatformPO> queryPageListByCondition(PlatformPO platformPo, int pageNum, int limit,String order,String sort) {
		platformPo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		sort = StringUtil.getNumpReverse(sort);
		return platformDao.selectPageListByCondition(platformPo, pageNum, limit,order,sort);
	}


	@Override
	public Integer modifyByUuid(PlatformPO platformPo) {
		platformPo.setPlatformEditTime(TimeUtil.getNowStr());
		return platformDao.updateByUuid(platformPo);
	}

	@Override
	public List<PlatformPO> queryListByCondition(PlatformPO platformPo) {
		platformPo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		return platformDao.selectListByCondition(platformPo);
	}

	@Override
	public Integer modifyByBatch(List<PlatformPO> platformPoList) {
		
		return platformDao.modifyByBatch(platformPoList);
	}

	/* (non-Javadoc)
	 * @see net.wangxj.authority.service.AuthorityService#getCountByCondition(java.lang.Object)
	 */
	@Override
	public Integer getCountByCondition(PlatformPO platformPo) {
		return platformDao.getCountByCondition(platformPo);
	}
}
