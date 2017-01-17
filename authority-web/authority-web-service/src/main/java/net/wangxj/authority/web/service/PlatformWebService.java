package net.wangxj.authority.web.service;

import java.util.List;

import net.wangxj.authority.dto.PlatformDTO;

public interface PlatformWebService {

	/**
	 * 分页查询
	 * @param jsonStr
	 * @return
	 */
	public String getPlatformList(String jsonStr);

	/**
	 * 增加平台
	 * @param platformDto
	 * @return
	 */
	public String add(PlatformDTO platformDto);
	/**
	 * 检测某字段是否重复
	 * @param platformDto
	 * @return
	 */
	public String isRepeatField(PlatformDTO platformDto);
	/**
	 * 编辑平台
	 * @param platformDto
	 * @return
	 */
	public String edit(PlatformDTO platformDto);
	/**
	 *　删除
	 * @param platformDto
	 * @return
	 */
	public String delete(PlatformDTO platformDto);

	/**
	 * 批量删除
	 * @param uuidList
	 * @return
	 */
	public String deleteBatch(List<String> uuidList);

}
