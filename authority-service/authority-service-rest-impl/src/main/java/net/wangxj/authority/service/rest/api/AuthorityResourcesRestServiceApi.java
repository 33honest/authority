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
import net.wangxj.authority.po.AuthorityResourcesPO;
import net.wangxj.authority.po.Page;
import net.wangxj.authority.service.AuthorityResourcesService;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:38
 */
@Path("/resources")
@Produces("application/json")
@Consumes("application/json")
public class AuthorityResourcesRestServiceApi extends AbstractAuthrotiyRestService{

	private static Logger logger = Logger.getLogger(AuthorityResourcesRestServiceApi.class);
	
	@Autowired
	private AuthorityResourcesService authorityResourcesService;
	
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
	 * @apiDefine resourceSuccessResponse
	 * @apiSuccess (200) {String} success 是否操作成功
	 * @apiSuccessExample {json}　请求成功响应 : 
	 * 									{
	 *									  "success": true
	 *									}
	 */
	/**
	 *@apiDefine resource400Response
	 *@apiError (400) {String} error_message 错误说明
	 *@apiError (400) {Boolean} is_pass　　格式是否正确
	 *@apiErrorExample {json} 错误400响应 : 
	 *									{
	 *									   "error_message": "角色名必须是2-25个汉字",
	 *									   "is_pass": false
	 *									}
	 */
	/**
	 *@apiDefine resource500Response
	 *@apiError (500) {String} error 错误说明
	 *@apiErrorExample {json} 错误500响应 :
	 *									{
	 *										"error": "服务器内部发生错误
	 *									}
	 */
	
	
	/**
	 * TODO 检查resource_parent_uuid,resource_platform_uuid,resource_edit_by是否存在,
	 * 更新资源
	 * @param resourceUuid 资源uuid
	 * @param resourcePo 资源信息
	 * @return
	 * @throws Exception
	 * apidoc----------------------------->
	 * @api {PUT} /resources/{resource_uuid} 更新资源
	 * @apiExample {curl} curl请求示例:
	 * curl -i -X PUT -H "Content-Type:application/json" -H "Accept:application/json" -d '
	 * {"resource_name" : "测试资源修改",
	 *  "resource_status" : 1,
	 *  "resource_url" : "/ceshi/ziyuan",
	 *  "resource_level" : 2,
	 *  "resource_order" : 1,
	 *  "resource_parent_uuid" : "2d19a2d466d84b12b27689ed2a08589d",
	 *  "resource_css_code" : "",
	 *  "resource_edit_by" : "db1d225261cf4a1293e7eb8d4371b667",
	 *  "resource_platform_uuid" : "51f43adfea7045ff8c76b1433110c864"
	 * }' http://localhost:9000/api/resources/00598e96def14315aa03255cb517029a
	 * @apiGroup resources
	 * @apiParam {String} resource_uuid 资源Uuid
	 * @apiParam {String} [resource_name] 资源名
	 * @apiParam {number=1(已激活),2(已注销),3(未激活)} [resource_status] 资源状态
	 * @apiParam {String{..128}} [resource_url] 资源url
	 * @apiParam {number{1..10}} [resource_level] 资源层级
	 * @apiParam {number{1..100}} [resource_order] 资源序号
	 * @apiParam {String} [resource_parent_uuid] 资源父级uuid
	 * @apiParam {String} [resource_css_code] 资源css code
	 * @apiParam {String} resource_edit_by 修改人
	 * @apiParam {String} resource_platform_uuid 平台uuid
	 * @apiParamExample {json} 请求参数示例:
	 * {"resource_name" : "测试资源修改",
	 *  "resource_status" : 1,
	 *  "resource_url" : "/ceshi/ziyuan",
	 *  "resource_level" : 2,
	 *  "resource_order" : 1,
	 *  "resource_parent_uuid" : "2d19a2d466d84b12b27689ed2a08589d",
	 *  "resource_css_code" : "",
	 *  "resource_edit_by" : "db1d225261cf4a1293e7eb8d4371b667",
	 *  "resource_uuid" : "00598e96def14315aa03255cb517029a"
	 * }
	 * @apiUse resourceSuccessResponse
	 * @apiUse resource400Response
	 * @apiUse resource500Response
	 */
	@PUT
	@Path("{uuid}")
	public Response update(@PathParam("uuid")String resourceUuid , AuthorityResourcesPO resourcePo) throws Exception{
		resourcePo.setResourceUuid(resourceUuid);
		ValidationResult updateValidateResult = authorityResourcesService.validatePoAndNotRepeadField(resourcePo, EditValidate.class);
		if(updateValidateResult != null){
			return failValidate(updateValidateResult);
		}else{
			Map<String , Object> updateResultMap = new HashMap<>();
			updateResultMap.put("success", authorityResourcesService.update(resourcePo) == 1 ? true : false);
			return success(updateResultMap);
		}
	}

	/**
	 * 分页查询
	 * @param page
	 * @return
	 * apidoc----------------------->
	 * @api {GET} /resources 分页查询
	 * @apiExample {curl} curl请求示例:
	 * curl -X GET 'http://localhost:9000/api/resources?page_number=2&limit=3&order=asc&sort=resource_uuid'
	 * @apiGroup resources
	 * @apiParam {number} page_number 页码
	 * @apiParam {number}  limit 每页条数
	 * @apiParam {String="desc","asc"} order 排序(正序/反序)
	 * @apiParam {String} sort 排序字段(按该字段排序)
	 * @apiParamExample {json} 请求参数示例:
	 * {
	 *   "page_number":2,
	 *   "limit": 3,
	 *   "order": "asc",
	 *   "sort": "platform_uuid"
	 * }
	 * @apiSuccess (200) {String} data 数据
	 * @apiSuccess (200) {number} count 数据总条数
	 * @apiSuccessExample {json}　请求成功响应 : 
	 * {
	 *	  "data": [
	 *	    {
	 *	      "resource_add_by": "de0c7b2480494fda98db82f7a4707649",
	 *	      "resource_add_time": "2017-02-21 16:32:35",
	 *	      "resource_level": 3,
	 *	      "resource_name": "资源列表",
	 *	      "resource_order": 1,
	 *	      "resource_parent_uuid": "74d8acecef6c4404b863ffceb892a418",
	 *	      "resource_platform_uuid": "fe178fd0073a4edea94e95a46bab15be",
	 *	      "resource_status": 2,
	 *	      "resource_url": "/resource/list",
	 *	      "resource_uuid": "1dead8c5c0034355ad27b86c3f0d262f"
	 *	    },
	 *	    {
	 *	      "resource_add_by": "de0c7b2480494fda98db82f7a4707649",
	 *	      "resource_add_time": "2017-02-21 15:55:11",
	 *	      "resource_level": 3,
	 *	      "resource_name": "编辑平台",
	 *	      "resource_order": 3,
	 *	      "resource_parent_uuid": "f2e251236cae4e3eb1bca10535bdbf36",
	 *	      "resource_platform_uuid": "fe178fd0073a4edea94e95a46bab15be",
	 *	      "resource_status": 2,
	 *	      "resource_url": "/platform/edit",
	 *	      "resource_uuid": "21824222d2f54cbcb21cddeb9b9131b8"
	 *	    },
	 *	    {
	 *	      "resource_add_by": "de0c7b2480494fda98db82f7a4707649",
	 *        "resource_add_time": "2017-02-21 16:04:09",
	 *	      "resource_level": 3,
	 *	      "resource_name": "初始化角色表单下拉列表",
	 *	      "resource_order": 6,
	 *	      "resource_parent_uuid": "08fd65930c1446c588b73ffca084ea70",
	 *	      "resource_platform_uuid": "fe178fd0073a4edea94e95a46bab15be",
	 *	      "resource_status": 2,
	 *	      "resource_url": "/role/getStatusList",
	 *	      "resource_uuid": "2a588a35adc0403ba6ca47b6acd79b99"
	 *	    }
	 *	  ],
	 *	  "count": 34
	 *	}
	 * @apiError (400) {String} error_message 错误说明
	 * @apiError (400) {Boolean} is_pass　　格式是否正确
	 * @apiErrorExample {json} 错误400响应 : 
	 *									{
	 *									   "error_message": "page_number非法",
	 *									   "is_pass": false
	 *									}
	 * @apiError (500) {String} error 错误说明
	 * @apiErrorExample {json} 错误500响应 :
	 *									{
	 *										"error": "服务器内部发生错误
	 *									}
	 *
	 */
	@GET
	public Response pageQuery(@BeanParam Page page){
		ValidationResult pageValidateResult = authorityResourcesService.validatePo(page, Default.class);
		ValidationResult validateSortResult = authorityResourcesService.validateSort(AuthorityResourcesPO.class, page.getSort());
		if(pageValidateResult != null){
			return failValidate(pageValidateResult);
		}else if(validateSortResult != null){
			return failValidate(validateSortResult);
		}else{
			return success(authorityResourcesService.pageQuery(new AuthorityResourcesPO(), page));
		}
	}
	
	/**
	 * TODO 检查delete_user是否存在
	 * 删除资源(批量)
	 * @param deleteUser
	 * @param uuids
	 * @return
	 * apidoc--------------------------->
	 * @api {DELETE} /resources 删除(批量)
	 * @apiExample {curl} curl请求示例:
	 * curl -X DELETE 'http://localhost:9000/api/resources?delete_user=de0c7b2480494fda98db82f7a4707649&uuids=00598e96def14315aa03255cb517029a,3b453ee2d69a4543a14ee2f15cfef74c'
	 * @apiGroup resources
	 * @apiParam {String} delete_user　删除人
	 * @apiParam {String} uuids 平台uuid集合(以,分割)
	 * @apiParamExample {json} 请求参数示例:
	 * {
	 *  "delete_user" : "de0c7b2480494fda98db82f7a4707649",
	 * 　"uuids" : "5e669e868cf04483802efeebe1608f9f,51f43adfea7045ff8c76b1433110c864"
	 * }
	 * @apiSuccess (200) {String} success 是否操作成功
	 * @apiSuccessExample {json}　请求成功响应 : 
	 * 									{
	 *									  "success": true
	 *									}
	 *
	 * @apiError (400) {String} error_message 错误说明
	 * @apiError (400) {Boolean} is_pass　　格式是否正确
	 * @apiErrorExample {json} 错误400响应 : 
	 *									{
	 *									   "error_message": "删除人非法",
	 *									   "is_pass": false
	 *									}
	 * @apiError (500) {String} error 错误说明
	 * @apiErrorExample {json} 错误500响应 :
	 *									{
	 *										"error": "服务器内部发生错误
	 *									}
	 */
	@DELETE
	public Response delete(@QueryParam(value = "delete_user")String deleteUser , @QueryParam(value = "uuids")String uuids){
		logger.debug("删除:delete_user--->" + deleteUser + "--->uuids---->" + uuids);
		List<AuthorityResourcesPO> resourceListPo = new ArrayList<>();
		for (String uuid : uuids.split(",")) {
			AuthorityResourcesPO resourcePo = new AuthorityResourcesPO();
			resourcePo.setResourceDelBy(deleteUser);
			resourcePo.setResourceUuid(uuid);
			ValidationResult deleteValidateResult = authorityResourcesService.validatePo(resourcePo, DeleteValidate.class);
			if(deleteValidateResult != null){
				return failValidate(deleteValidateResult);
			}
			resourceListPo.add(resourcePo);
		}
		Map<String , Object> deleteResultMap = new HashMap<>();
		deleteResultMap.put("success", authorityResourcesService.deleteBatch(resourceListPo) > 0 ? true : false);
		return success(deleteResultMap);
	}

}