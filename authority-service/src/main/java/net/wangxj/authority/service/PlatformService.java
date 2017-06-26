

package net.wangxj.authority.service;

import java.util.Map;

import net.wangxj.authority.po.Page;
import net.wangxj.authority.po.PlatformPO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:44
 */

public interface PlatformService extends AuthorityService<PlatformPO>{

	/**
	 * 搜索分页查询
	 * @param search
	 * @param page
	 * @return
	 */
	Map<String, Object> search(String search, Page page); 
}
