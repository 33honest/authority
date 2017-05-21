package net.wangxj.authority.service.rest.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.groups.Default;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import net.wangxj.util.validate.ValidationResult;
import net.wangxj.util.validate.groups.DeleteValidate;
import net.wangxj.util.validate.groups.EditValidate;
import net.wangxj.authority.po.AuthorityRolePO;
import net.wangxj.authority.po.Page;
import net.wangxj.authority.service.AuthorityRoleService;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:40
 */
@Path("/roles")
@Produces(value = "application/json")
@Consumes(value = "application/json")
public class AuthorityRoleRestServiceApi extends AbstractAuthrotiyRestService{

	private static Logger logger = Logger.getLogger(AuthorityRoleRestServiceApi.class);
	
	@Autowired
	private AuthorityRoleService authorityRoleService;
	
	/**
	 * @apiDefine 200 成功 200
	 */
	/**
	 * @apiDefine 400 错误 400
	 */
	/**
	 * @apiDefine 500 错误 500
	 */
	/**
	 * @apiDefine roleSuccessResponse
	 * @apiSuccess (200) {String} success 是否操作成功
	 * @apiSuccessExample {json}　请求成功响应 : 
	 * 									{
	 *									  "success": true
	 *									}
	 */
	/**
	 * @apiDefine role400Response
	 *@apiError (400) {String} error_message 错误说明
	 *@apiError (400) {Boolean} is_pass　　格式是否正确
	 *@apiErrorExample {json} 错误400响应 : 
	 *									{
	 *									   "error_message": "该role_name已存在",
	 *									   "is_pass": false
	 *									}
	 */
	/**
	 * @apiDefine role500Response
	 *@apiError (500) {String} error 错误说明
	 *@apiErrorExample {json} 错误500响应 :
	 *									{
	 *										"error": "服务器内部发生错误
	 *									}
	 */
	
	
	/**
	 * 更新角色信息
	 * @param roleUuid
	 * @param rolePo
	 * @return
	 * @throws Exception
	 * apidoc------------------->
	 * @api {PUT} /roles/{role_uuid} 修改角色信息
	 * @apiExample {curl} curl请求示例:
	 * curl -i -X PUT -H "Content-Type:application/json" -H "Accept:application/json" -d '
	 * {"role_name" : "测试角色修改",
	 *  "role_status" : 1,
	 *  "role_platform_uuid" : "8542539ffc0a4f4e8d08fdfa80924af5",
	 *  "role_edit_by" : "db1d225261cf4a1293e7eb8d4371b667"
	 * }' http://localhost:9000/api/roles/369552346cf94f2483f3b1a7b9ff4cf9
	 * @apiGroup roles
	 * @apiParam {String} role_uuid 角色uuid
	 * @apiParam {String{2..16}} [role_name] 角色名
	 * @apiParam {number=1(已添加),2(已激活)} [role_status]
	 * @apiParam {String} [role_platform_uuid] 平台uuid
	 * @apiParamExample {json} 请求参数s示例:
	 * {"role_uuid" : "369552346cf94f2483f3b1a7b9ff4cf9",
	 *  "role_name" : "测试角色修改",
	 *  "role_status" : 1,
	 *  "role_platform_uuid" : "8542539ffc0a4f4e8d08fdfa80924af5",
	 *  "role_edit_by" : "db1d225261cf4a1293e7eb8d4371b667"
	 * } 
	 * @apiUse roleSuccessResponse
	 * @apiUse role400Response
	 * @apiUse role500Response
	 */
	@PUT
	@Path("/{uuid}")
	public Response update(@PathParam(value = "uuid")String roleUuid,AuthorityRolePO rolePo) throws Exception{
		rolePo.setRoleUuid(roleUuid);
		ValidationResult editValidateResult = authorityRoleService.validatePoAndNotRepeadField(rolePo, EditValidate.class);
		logger.debug("验证结果:-->" + editValidateResult);
		if(editValidateResult != null){
			return failValidate(editValidateResult);
		}else{
			Map<String, Object> editResultMap = new HashMap<>();
			editResultMap.put("success", authorityRoleService.update(rolePo) == 1 ? true : false);
			logger.debug("添加结果:-->" + editResultMap);
			return success(editResultMap);
		}
	}
	
	/**
	 * 分页查询
	 * @param page
	 * @return
	 * apidoc------------------------->
	 * @api {GET} /roles 分页查询
	 * @apiExample {curl} curl请求示例:
	 * curl -X GET 'http://localhost:9000/api/roles?page_number=1&limit=3&order=asc&sort=role_uuid'
	 * @apiGroup roles
	 * @apiParam {number} page_number 页码
	 * @apiParam {number}  limit 每页条数
	 * @apiParam {String="desc","asc"} order 排序(正序/反序)
	 * @apiParam {String} sort 排序字段(按该字段排序)
	 * @apiParamExample {json} 请求参数示例:
	 * {
	 *   "page_number":2,
	 *   "limit": 3,
	 *   "order": "asc",
	 *   "sort": "role_uuid"
	 * }
	 * @apiSuccess (200) {String} data 数据
	 * @apiSuccess (200) {number} count 数据总条数
	 * @apiSuccessExample {json}　请求成功响应 :
	 * {
	 *	  "data": [
	 *	    {
	 *	      "role_add_by": "de0c7b2480494fda98db82f7a4707649",
	 *	      "role_add_time": "2017-02-21 16:45:54",
	 *	      "role_name": "管理员",
	 *	      "role_platform_uuid": "fe178fd0073a4edea94e95a46bab15be",
	 *	      "role_status": 2,
	 *	      "role_uuid": "2be1d2b183f84483a8f9762a3da2a4c9"
	 *	    }
	 *	  ],
	 *	  "count": 1
	 *	}
	 *@apiError (400) {String} error_message 错误说明
	 *@apiError (400) {Boolean} is_pass　　格式是否正确
	 *@apiErrorExample {json} 错误400响应 : 
	 *									{
	 *									   "error_message": "page_number非法",
	 *									   "is_pass": false
	 *									}
	 * @apiUse role500Response
	 */
	@GET
	public Response pageQuery(@BeanParam Page page){
		ValidationResult validatePageResult = authorityRoleService.validatePo(page, Default.class);
		ValidationResult validateSortResult = authorityRoleService.validateSort(AuthorityRolePO.class, page.getSort());
		logger.debug("验证结果:-->" + validatePageResult + "-->" + validateSortResult);
		if(validatePageResult != null){
			return failValidate(validatePageResult);
		}else if(validateSortResult != null){
			return failValidate(validateSortResult);
		}else{
			return success(authorityRoleService.pageQuery(new AuthorityRolePO(), page));
		}
	}
	
	/**
	 * 删除(批量)
	 * @param deleteUser
	 * @param uuids
	 * @return
	 * apidoc----------------------->
	 * @api {DELETE} /roles 删除(批量)
	 * @apiExample {curl} curl请求示例:
	 * curl -i -X DELETE 'http://localhost:9000/api/roles?delete_user=db1d225261cf4a1293e7eb8d4371b667&uuids=2be1d2b183f84483a8f9762a3da2a4c9,369552346cf94f2483f3b1a7b9ff4cf9'
	 * @apiGroup roles
	 * @apiParam {String} delete_user　删除人
	 * @apiParam {String} uuids 角色uuid集合(以,分割)
	 * @apiParamExample {json} 请求参数示例:
	 * {
	 *  "delete_user" : "db1d225261cf4a1293e7eb8d4371b667",
	 * 　"uuids" : "2be1d2b183f84483a8f9762a3da2a4c9,51f43adfea7045ff8c76b1433110c864"
	 * }
	 * @apiUse roleSuccessResponse
	 * @apiError (400) {String} error_message 错误说明
	 * @apiError (400) {Boolean} is_pass　　格式是否正确
	 * @apiErrorExample {json} 错误400响应 : 
	 *									{
	 *									   "error_message": "删除人非法",
	 *									   "is_pass": false
	 *									} 
	 * @apiUse role500Response
	 */
	@DELETE
	public Response delete(@QueryParam(value = "delete_user")String deleteUser ,@QueryParam(value = "uuids") String uuids){
		logger.debug("查询参数:-->" + deleteUser + "--->" + uuids);
		List<AuthorityRolePO> roleListPo = new ArrayList<>();
		for (String uuid : uuids.split(",")) {
			AuthorityRolePO rolePo = new AuthorityRolePO();
			rolePo.setRoleDelBy(deleteUser);
			rolePo.setRoleUuid(uuid);
			ValidationResult deleteValidateResult = authorityRoleService.validatePo(rolePo, DeleteValidate.class);
			logger.debug("验证-->" + deleteValidateResult);
			if(deleteValidateResult != null){
				return failValidate(deleteValidateResult);
			}
			roleListPo.add(rolePo);
		}
		Map<String,Object> delteResultMap = new HashMap<>();
		delteResultMap.put("success",  authorityRoleService.deleteBatch(roleListPo) > 0 ? true : false);
		logger.debug("删除操作结果:---->" + delteResultMap);
		return success(delteResultMap);
	}

}