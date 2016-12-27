

package net.wangxj.authority.service.impl;


import java.util.List;


import javax.annotation.Resource;
import org.apache.log4j.Logger;
import net.wangxj.authority.dao.PlatformDao;
import net.wangxj.authority.po.PlatformPO;
import net.wangxj.authority.service.PlatformService;
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
	
	@Override
	public Integer add(PlatformPO platformPo) {
		
		return platformDao.insert(platformPo);
	}
	
	@Override
	public Integer addBatch(List<PlatformPO> listPo){
		return platformDao.insertBatch(listPo);
	}
	
	@Override
	public List<PlatformPO> queryPageListByCondition(PlatformPO platformPo, int pageNum, int limit) {
		
		return platformDao.selectPageListByCondition(platformPo, pageNum, limit);
	}


	@Override
	public Integer modifyByUuid(PlatformPO platformPo) {
		
		return platformDao.updateByUuid(platformPo);
	}

	@Override
	public List<PlatformPO> queryListByCondition(PlatformPO platformPo) {
		
		return platformDao.selectListByCondition(platformPo);
	}

	@Override
	public Integer getCountByCondition(PlatformPO platformPo) {
		
		return platformDao.getCountByCondition(platformPo);
	}

}
