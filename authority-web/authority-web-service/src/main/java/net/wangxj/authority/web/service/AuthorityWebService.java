//package net.wangxj.authority.web.service;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//
//import net.wangxj.authority.jersey.HttpConnectPool;
//
//
//public interface AuthorityWebService<T> {
//	
//	/**
//	 * 分页查询
//	 * @param jsonStr
//	 * @return
//	 */
//	public String getPageList(String jsonStr);
//
//	/**
//	 * 增加
//	 * @param dto
//	 * @return
//	 */
//	public String add(T dto);
//	/**
//	 * 检测某字段是否重复
//	 * @param dto
//	 * @return
//	 */
//	public String isRepeatField(T dto);
//	/**
//	 * 编辑
//	 * @param dto
//	 * @return
//	 */
//	public String edit(T dto);
//	/**
//	 *　删除
//	 * @param dto
//	 * @return
//	 */
//	public String delete(T dto);
//
//	/**
//	 * 批量删除
//	 * @param uuidList
//	 * @return
//	 */
//	public String deleteBatch(List<String> uuidList);
//	
//	
//}
