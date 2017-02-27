
package net.wangxj.authority.service.share;

import net.wangxj.authority.Response;
import java.util.List;
import net.wangxj.authority.dto.AuthorityUserRoleRelationDTO;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:43
 */

public interface AuthorityUserRoleRelationShareService{

	/**
	 * 添加AuthorityUserRoleRelationDTO
	 * @return	新增主键
	 */
	public Response<Integer> add(AuthorityUserRoleRelationDTO authorityUserRoleRelationDto);
	
	/**
	 * 批量添加
	 * @return 成功添加的条数
	 */
	public Response<Integer> addBatch(List<AuthorityUserRoleRelationDTO> listDto, String platformUuid);
	
	/**
	 * 根据主键ID修改
	 * @return 新增条数: 默认1
	 */
	public Response<Integer> modifyByUuid(AuthorityUserRoleRelationDTO authorityUserRoleRelationDto);
	
	/**
	 * 条件查询
	 */
	public Response<AuthorityUserRoleRelationDTO> queryListByCondition(AuthorityUserRoleRelationDTO authorityUserRoleRelationDto);
	
	/**
	 * 条件分页查询
	 * @param pageNum: 页码
	 * @param limit:   每页条数
	 */
	public Response<AuthorityUserRoleRelationDTO> queryPageListByCondition(AuthorityUserRoleRelationDTO authorityUserRoleRelationDto, int pageNum, int limit);
	
	/**
	 * 条件数量查询
	 * @return
	 */
	public Response<Integer> getCountByCondition(AuthorityUserRoleRelationDTO authorityUserRoleRelationDto);
	

}