package net.wangxj.authority.service.rest.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.validation.groups.Default;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import net.wangxj.util.constant.RegexConstant;
import net.wangxj.util.validate.ValidationResult;
import net.wangxj.util.validate.groups.AddValidate;
import net.wangxj.util.validate.groups.DeleteValidate;
import net.wangxj.util.validate.groups.EditValidate;
import net.wangxj.authority.po.AuthorityResourcesPO;
import net.wangxj.authority.po.AuthorityRolePO;
import net.wangxj.authority.po.Page;
import net.wangxj.authority.po.PlatformPO;
import net.wangxj.authority.service.AuthorityResourcesService;
import net.wangxj.authority.service.AuthorityRoleService;
import net.wangxj.authority.service.PlatformService;

/**
 * 
 * @author huoshan
 * created by 2017年4月8日 上午11:10:35
 */
@Path("/platforms")
@Produces({"application/json"})
@Consumes({"application/json"})
public class PlatformRestServiceApi extends AbstractAuthrotiyRestService {

	private static Logger logger = Logger.getLogger(PlatformRestServiceApi.class);
	
	@Autowired
	private PlatformService platformService;
	@Autowired
	private AuthorityRoleService authorityRoleService;
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
	 * @apiDefine platformResponse
	 * @apiSuccess (200) {String} success 是否操作成功
	 * @apiSuccessExample {json}　请求成功响应 : 
	 * 									{
	 *									  "success": true
	 *									}
	 *
	 *@apiError (400) {String} error_message 错误说明
	 *@apiError (400) {Boolean} is_pass　　格式是否正确
	 *@apiErrorExample {json} 错误400响应 : 
	 *									{
	 *									   "error_message": "平台名必须是2-25个汉字",
	 *									   "is_pass": false
	 *									}
	 *@apiError (500) {String} error 错误说明
	 *@apiErrorExample {json} 错误500响应 :
	 *									{
	 *										"error": "服务器内部发生错误
	 *									}
	 */
	
	
	/**
	 * TODO 检查platform_add_user是否存在该用户
	 * 添加平台
	 * @param platformPo
	 * @return {success: true/false]
	 * @throws Exception 
	 * 
	 * apidoc----------->
	 * @api {POST} /platforms/ 添加平台(单个)
	 * @apiExample {curl} curl请求示例:
	 * curl -H "Content-Type:application/json" -H "Accept:application/json" -i -X POST -d '
	 * {"platform_name":"测试",
	 *  "platform_sign":"CE_SHI",
	 *  "platform_domain":"ceshi.com",
	 *  "platform_status":3,
	 *  "platform_add_user":"0cf700bfd72142c498ff7508aa2603c3"}' http://localhost:9000/api/platforms
	 * @apiGroup platforms
	 * @apiParam {String{2..25}} platform_name 平台名 
	 * @apiParam {String} platform_sign 平台标识
	 * @apiParam {String{5..30}} platform_domain 平台域名
	 * @apiParam {number=1(激活),2(注销),3(初始化)} platform_status 平台状态
	 * @apiParam {String} platform_add_user 添加人
	 * @apiParamExample {json} 请求参数示例:
	 * 					{
	 * 					 "platform_name":"测试",
	 * 					 "platform_sign":"CE_SHI",
	 * 					 "platform_domain":"ceshi.com",
	 * 					 "platform_status":3,
	 * 					 "platform_add_user":"0cf700bfd72142c498ff7508aa2603c3"
	 * 					}
	 *@apiUse platformResponse
	 * 
	 *
	 */
	@POST
	public Response add(PlatformPO platformPo) throws Exception{
		//validate
		ValidationResult validateRes;
		validateRes = platformService.validatePoAndNotRepeadField(platformPo, AddValidate.class);
		if(validateRes != null){
			return failValidate(validateRes);
		}
		else{
			//添加
			Map<String,Object> result = new HashMap<>();
			result.put("success",  platformService.add(platformPo) == 1 ? true : false);
			return success(result);
		}
	}

	/**
	 * TODO 检查platform_edit_user是否存在该用户
	 * 修改平台(根据uuid)
	 * @param platformPo
	 * @return
	 * @throws Exception 
	 * apidoc--------------------->
	 * @api {PUT} /platforms/{platform_uuid} 修改平台
	 * @apiExample {curl} curl请求示例:
	 * curl -H "Content-Type:application/json" -H "Accept:application/json" -i -X PUT -d '
	 * {"platform_name":"测试修改",
	 *  "platform_sign":"CE_SHI_XIUGAI",
	 *  "platform_domain":"ceshixiugai.com",
	 *  "platform_status":3,
	 *  "platform_edit_user":"0cf700bfd72142c498ff7508aa2603c3"}' http://localhost:9000/api/platforms/fbe79e23230248078116f407884a7785
	 * @apiGroup platforms
	 * @apiParam {String} platform_uuid 平台uuid
	 * @apiParam {String{2..25}} [platform_name] 平台名 
	 * @apiParam {String} [platform_sign] 平台标识
	 * @apiParam {String{5..30}} [platform_domain] 平台域名
	 * @apiParam {number=1(激活),2(注销),3(初始化)} [platform_status] 平台状态
	 * @apiParam {String} platform_edit_user 编辑人
	 * @apiParamExample {json} 请求参数示例:
	 * 					{
	 * 					 "platform_name":"测试修改",
	 * 					 "platform_sign":"CE_SHI_XIUGAI",
	 * 					 "platform_domain":"ceshixiugai.com",
	 * 					 "platform_status":3,
	 * 					 "platform_edit_user":"0cf700bfd72142c498ff7508aa2603c3"
	 * 					}
	 *@apiUse platformResponse 
	 */
	@PUT
	@Path("/{uuid}")
	public Response update(@PathParam(value = "uuid") String uuid,PlatformPO platformPo) throws Exception{
		platformPo.setPlatformUuid(uuid);
		ValidationResult validateResult = platformService.validatePoAndNotRepeadField(platformPo, EditValidate.class);
		if(validateResult != null){
			return failValidate(validateResult);
		}
		else{
			//update
			Map<String,Object> updateResMap = new HashMap<>();
			updateResMap.put("success", platformService.update(platformPo) == 1 ? true : false);
			return success(updateResMap);
		}
	}
	
	/**
	 * http://localhost:9000/api/platforms?search=权限平台&page_number=2&limit=3&order=asc&sort=platform_uuid
	 * 分页查询
	 * 
	 * apidoc----------------->
	 * @api {GET} /platforms 分页查询
	 * @apiExample {curl} curl请求示例:
	 * curl -X GET 'http://localhost:9000/api/platforms?search=权限平台&page_number=2&limit=3&order=asc&sort=platform_uuid'
	 * @apiGroup platforms
	 * @apiParam {String} [search] 搜索字符串
	 * @apiParam {number} page_number 页码
	 * @apiParam {number}  limit 每页条数
	 * @apiParam {String="desc","asc"} order 排序(正序/反序)
	 * @apiParam {String} sort 排序字段(按该字段排序)
	 * @apiParamExample {json} 请求参数示例:
	 * {
	 *   "search" : "权限平台",
	 *   "page_number":2,
	 *   "limit": 3,
	 *   "order": "asc",
	 *   "sort": "platform_uuid"
	 * }
	 * @apiSuccess (200) {String} data 数据
	 * @apiSuccess (200) {number} count 数据总条数
	 * @apiSuccessExample {json}　请求成功响应 : 
	　*	 {
	　*	  "data": [
	　*	    {
	　*	      "platform_add_time": "2017-05-14 11:00:37",
	　*	      "platform_add_user": "0cf700bfd72142c498ff7508aa2603c3",
	　*	      "platform_domain": "ceshi.com",
	　*	      "platform_name": "测试吧",
	　*	      "platform_sign": "CE_SHIhaa",
	　*	      "platform_status": 3,
	　*	      "platform_uuid": "fa06b693de9241b0a57586283d99d1ec"
	　*	    },
	　*	    {
	　*	      "platform_add_time": "2017-05-07 15:00:13",
	　*	      "platform_add_user": "0cf700bfd72142c498ff7508aa2603c3",
	　*	      "platform_domain": "ceshixiugai.com",
	　*	      "platform_edit_time": "2017-05-19 11:09:17",
	　*	      "platform_edit_user": "0cf700bfd72142c498ff7508aa2603c3",
	　*	      "platform_name": "测试修改",
	　*	      "platform_sign": "CE_SHI_XIUGAI",
	　*	      "platform_status": 3,
	　*	      "platform_uuid": "fbe79e23230248078116f407884a7785"
	　*	    },
	　*	    {
	　*	      "platform_add_time": "2017-01-01 12:12:12",
	　*	      "platform_add_user": "de0c7b2480494fda98db82f7a4707649",
	　*	      "platform_domain": "authority.com",
	　*	      "platform_name": "权限平台",
	　*	      "platform_sign": "authority",
	　*	      "platform_status": 1,
	　*	      "platform_uuid": "fe178fd0073a4edea94e95a46bab15be"
	　*	    }
	　*	  ],
	　*	  "count": 9
	　*	}
	 *
	 *@apiError (400) {String} error_message 错误说明
	 *@apiError (400) {Boolean} is_pass　　格式是否正确
	 *@apiErrorExample {json} 错误400响应 : 
	 *									{
	 *									   "error_message": "page_number非法",
	 *									   "is_pass": false
	 *									}
	 *@apiError (500) {String} error 错误说明
	 *@apiErrorExample {json} 错误500响应 :
	 *									{
	 *										"error": "服务器内部发生错误
	 *									}
	 */ 
	@GET
	public Response pageQuery(@BeanParam Page page,@QueryParam("search")String search){
		ValidationResult validateResult = platformService.validatePo(page, Default.class);
		ValidationResult validateSortRes = platformService.validateSort(PlatformPO.class, page.getSort());
		if(validateResult != null){
			return failValidate(validateResult);
		}
		else if(validateSortRes != null){
			return failValidate(validateSortRes);
		}
		else{
			Map<String , Object>  pageResMap = platformService.search(search, page);
			return success(pageResMap);
		}
	}

	/**
	 * TODO 检查是否存在delete_user该用户
	 * 删除  http://localhost:9000/api/platforms?delete_user=de0c7b2480494fda98db82f7a4707649&uuids=5e669e868cf04483802efeebe1608f9f,51f43adfea7045ff8c76b1433110c864
	 * @param user 删除人
	 * @param uuids 
	 * @return
	 * apidoc------------------------>
	 * @api {DELETE} /platforms 删除(批量)
	 * @apiExample {curl} curl请求示例:
	 * curl -X DELETE 'http://localhost:9000/api/platforms?delete_user=de0c7b2480494fda98db82f7a4707649&uuids=5e669e868cf04483802efeebe1608f9f,51f43adfea7045ff8c76b1433110c864'
	 * @apiGroup platforms
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
	 *@apiError (400) {String} error_message 错误说明
	 *@apiError (400) {Boolean} is_pass　　格式是否正确
	 *@apiErrorExample {json} 错误400响应 : 
	 *									{
	 *									   "error_message": "删除人非法",
	 *									   "is_pass": false
	 *									}
	 *@apiError (500) {String} error 错误说明
	 *@apiErrorExample {json} 错误500响应 :
	 *									{
	 *										"error": "服务器内部发生错误
	 *									}
	 */
	@DELETE
	public Response delete(@QueryParam(value="delete_user")String user , @QueryParam(value="uuids")String uuids) {
		logger.debug("delete_user:" + user + "----uuids:" + uuids);
		List<PlatformPO> listPo = new ArrayList<>();
		for (String uuid : uuids.split(",")) {
			PlatformPO platformPo = new PlatformPO();
			platformPo.setPlatformUuid(uuid);
			platformPo.setPlatformDelBy(user);
			ValidationResult validateResult = platformService.validatePo(platformPo, DeleteValidate.class);
			if(validateResult != null){
				return failValidate(validateResult);
			}
			listPo.add(platformPo);
		}
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("success", platformService.deleteBatch(listPo) > 0 ? true : false);
		return success(resultMap);
	}
	
	/**
	 * TODO 1,检查是否存在platform_uuid该平台 2,检查是否存在role_add_by该用户
	 * 向该平台添加角色
	 * @param platformUuid 平台uuid
	 * @param rolePo	角色信息
	 * @return
	 * @throws Exception
	 * apidoc--------------------->
	 * @api {POST} /platforms/{platform_uuid}/{roles} 添加角色
	 * @apiGroup platforms
	 * @apiExample {curl} curl请求示例:
	 * curl -i -X POST -H "Content-Type:application/json" -H "Accept:application/json" -d '
	 * {"role_name" : "测试角色",
	 *  "role_status" : 2,
	 *  "role_add_by" : "db1d225261cf4a1293e7eb8d4371b667"
	 * }' http://localhost:9000/api/platforms/51f43adfea7045ff8c76b1433110c864/roles
	 * @apiParam {String} platform_uuid 平台uuid
	 * @apiParam {String{2..26}} role_name 角色名
	 * @apiParam {number=1(已添加),2(已激活)} role_status 角色状态
	 * @apiParam {String} role_add_by 添加人
	 * @apiParamExample {json} 请求参数示例:
	 * {"platform_uuid" : "51f43adfea7045ff8c76b1433110c864",
	 *  "role_name" : "测试角色",
	 *  "role_status" : 2,
	 *  "role_add_by" : "db1d225261cf4a1293e7eb8d4371b667"
	 * } 
	 * @apiSuccess (200) {String} success 是否操作成功
	 * @apiSuccessExample {json}　请求成功响应 : 
	 * 									{
	 *									  "success": true
	 *									}
	 *
	 *@apiError (400) {String} error_message 错误说明
	 *@apiError (400) {Boolean} is_pass　　格式是否正确
	 *@apiErrorExample {json} 错误400响应 : 
	 *									{
	 *									   "error_message": "该role_name已存在",
	 *									   "is_pass": false
	 *									}
	 *@apiError (500) {String} error 错误说明
	 *@apiErrorExample {json} 错误500响应 :
	 *									{
	 *										"error": "服务器内部发生错误
	 *									}
	 */
	@POST
	@Path("/{uuid}/roles")
	public Response addRole(@PathParam(value = "uuid")String platformUuid , AuthorityRolePO rolePo) throws Exception{
		rolePo.setRolePlatformUuid(platformUuid);
		ValidationResult validateResult = authorityRoleService.validatePoAndNotRepeadField(rolePo, AddValidate.class);
		if(validateResult != null){
			return failValidate(validateResult);
		}else{
			Map<String,Object> addResultMap = new HashMap<>();
			addResultMap.put("success", authorityRoleService.add(rolePo) == 1 ? true : false);
			logger.debug("添加角色结果:--->" + addResultMap);
			return success(addResultMap);
		}
	}

	/**
	 * TODO 1,检查是否存在platform_uuid该平台 2,检查是否存在resource_add_by该用户
	 * 向平台添加资源
	 * @param platformUuid 平台uuid
	 * @param resourcesPo 资源信息
	 * @return
	 * @throws Exception
	 * apidoc----------------------------->
	 * @api {POST} /platforms/{platform_uuid}/resources 添加资源
	 * @apiExample {curl} curl请求示例:
	 * curl -i -X POST -H "Content-Type:application/json" -H "Accept:application/json" -d '
	 * {"resource_name" : "测试资源",
	 *  "resource_status" : 1,
	 *  "resource_url" : "/ceshi/ziyuan",
	 *  "resource_level" : 2,
	 *  "resource_order" : 1,
	 *  "resource_parent_uuid" : "2d19a2d466d84b12b27689ed2a08589d",
	 *  "resource_css_code" : "",
	 *  "resource_add_by" : "db1d225261cf4a1293e7eb8d4371b667"
	 * }' http://localhost:9000/api/platforms/51f43adfea7045ff8c76b1433110c864/resources
	 * @apiGroup platforms
	 * @apiParam {String} platform_uuid 平台Uuid
	 * @apiParam {String} resource_name 资源名
	 * @apiParam {number=1(已激活),2(已注销),3(未激活)} resource_status 资源状态
	 * @apiParam {String{..128}} resource_url 资源url
	 * @apiParam {number{1..10}} resource_level 资源层级
	 * @apiParam {number{1..100}} resource_order 资源序号
	 * @apiParam {String} resource_parent_uuid 资源父级uuid
	 * @apiParam {String} [resource_css_code] 资源css code
	 * @apiParam {String} resource_add_by 添加人
	 * @apiParamExample {json} 请求参数示例:
	 * {"platform_uuid" : "51f43adfea7045ff8c76b1433110c864",
	 *  "resource_name" : "测试资源",
	 *  "resource_status" : 1,
	 *  "resource_url" : "/ceshi/ziyuan",
	 *  "resource_level" : 2,
	 *  "resource_order" : 1,
	 *  "resource_parent_uuid" : "2d19a2d466d84b12b27689ed2a08589d",
	 *  "resource_css_code" : "",
	 *  "resource_add_by" : "db1d225261cf4a1293e7eb8d4371b667"
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
	 *									   "error_message": "增加人不可为空",
	 *									   "is_pass": false
	 *									}
	 * @apiError (500) {String} error 错误说明
	 * @apiErrorExample {json} 错误500响应 :
	 *									{
	 *										"error": "服务器内部发生错误
	 *									}  
	 * 
	 */
	@POST
	@Path("/{uuid}/resources")
	public Response addResource(@PathParam(value = "uuid")String platformUuid , AuthorityResourcesPO resourcesPo) throws Exception{
		resourcesPo.setResourcePlatformUuid(platformUuid);
		ValidationResult addResourceValidateResult = authorityResourcesService.validatePoAndNotRepeadField(resourcesPo, AddValidate.class);
		if(addResourceValidateResult != null){
			return failValidate(addResourceValidateResult);
		}else{
			Map<String,Object> addResourcesResultMap = new HashMap<>();
			addResourcesResultMap.put("success", authorityResourcesService.add(resourcesPo) == 1 ? true : false);
			return success(addResourcesResultMap);
		}
	}
	
	/**
	 * 该平台下的所有角色
	 * @param platformUuid
	 * @return
	 * apidoc----------------->
	 * @api {GET} /platforms/{platform_uuid}/roles 角色列表
	 * @apiExample {curl} curl请求示例:
	 * curl -X GET 'http://localhost:9000/api/platforms/fe178fd0073a4edea94e95a46bab15be/roles'
	 * @apiGroup platforms
	 * @apiParam {String} platform_uuid 平台uuid
	 * @apiParamExample {json} 参数请求示例:
	 * {
	 * 	"platform_uuid" : "fe178fd0073a4edea94e95a46bab15be"
	 * }
	 * @apiSuccess (200) {String} data 响应数据
	 * @apiSuccessExample {json}　请求成功响应 : 
	 * 	[
	 *	  {
	 *	    "role_add_by": "de0c7b2480494fda98db82f7a4707649",
	 *	    "role_add_time": "2017-02-21 16:45:54",
	 *	    "role_name": "管理员",
	 *	    "role_platform_uuid": "fe178fd0073a4edea94e95a46bab15be",
	 *	    "role_status": 2,
	 *	    "role_uuid": "2be1d2b183f84483a8f9762a3da2a4c9"
	 *	  },
	 *	  {
	 *	    "role_add_by": "db1d225261cf4a1293e7eb8d4371b667",
	 *	    "role_add_time": "2017-05-21 09:42:06",
	 *	    "role_edit_by": "db1d225261cf4a1293e7eb8d4371b667",
	 *	    "role_edit_time": "2017-05-21 11:10:05",
	 *	    "role_name": "测试角色修改",
	 *	    "role_platform_uuid": "fe178fd0073a4edea94e95a46bab15be",
	 *	    "role_status": 1,
	 *	    "role_uuid": "369552346cf94f2483f3b1a7b9ff4cf9"
	 *	  }
	 *	]
	 * @apiErrorExample {json} 错误400响应 : 
	 *									{
	 *									   "error_message": "platform_uuid非法",
	 *									   "is_pass": false
	 *									}
	 * @apiError (500) {String} error 错误说明
	 * @apiErrorExample {json} 错误500响应 :
	 *									{
	 *										"error": "服务器内部发生错误
	 *									}  
	 */
	@Path("/{uuid}/roles")
	@GET
	public Response roles(@PathParam("uuid") String platformUuid){
		ValidationResult validateResult = new ValidationResult();
		if(!Pattern.matches(RegexConstant.UUID_32, platformUuid)){
			validateResult.setErrorMsg("platform_uuid非法");
			return failValidate(validateResult);
		}else{
			AuthorityRolePO rolePo = new AuthorityRolePO();
			rolePo.setRolePlatformUuid(platformUuid);
			Map<String,Object> rolesMap = new HashMap<>();
			rolesMap.put("data", authorityRoleService.query(rolePo));
			return success(rolesMap);
		}
	}
	
	/**
	 * 该平台下的所有资源
	 * @param platformUuid 平台uuid
	 * @return
	 * apidoc----------------------->
	 * @api {GET} /platforms/{platform_uuid}/resources 资源树
	 * @apiExample {curl} curl请求示例:
	 * curl -i -X GET 'http://localhost:9000/api/platforms/fe178fd0073a4edea94e95a46bab15be/resources'
	 * @apiGroup platforms
	 * @apiParam {String} platform_uuid 平台uuid
	 * @apiParamExample {json} 请求参数示例:
	 * {
	 *  "platform_uuid" : "fe178fd0073a4edea94e95a46bab15be"
	 * }
	 * @apiSuccess (200) {String} success 是否操作成功
	 * @apiSuccessExample {json}　请求成功响应 : 
	 *	{
	 *		data": [
	 *		    {
	 *		      "childList": [
	 *			  	 {
	 *			      "resource_add_by": "de0c7b2480494fda98db82f7a4707649",
	 *		          "resource_add_time": "2017-02-14 17:15:37",
	 *		          "resource_level": 2,
	 *		          "resource_name": "平台管理",
	 *		          "resource_order": 1,
	 *		          "resource_parent_uuid": "2d19a2d466d84b12b27689ed2a08589d",
	 *		          "resource_platform_uuid": "fe178fd0073a4edea94e95a46bab15be",
	 *		          "resource_status": 2,
	 *		          "resource_url": "/platform",
	 *		          "resource_uuid": "f2e251236cae4e3eb1bca10535bdbf36"
	 *		        }
	 *		      ],
	 *			  "resource_add_by": "de0c7b2480494fda98db82f7a4707649",
	 *		      "resource_add_time": "2017-02-14 12:05:09",
	 *		      "resource_edit_by": "de0c7b2480494fda98db82f7a4707649",
	 *		      "resource_edit_time": "2017-02-14 17:08:45",
	 *		      "resource_level": 1,
	 *		      "resource_name": "权限平台",
	 *		      "resource_order": 1,
	 *		      "resource_parent_uuid": "#",
	 *		      "resource_platform_uuid": "fe178fd0073a4edea94e95a46bab15be",
	 *		      "resource_status": 2,
	 *		      "resource_url": "/authority",
	 *		      "resource_uuid": "2d19a2d466d84b12b27689ed2a08589d"
	 *		    }
	 *	  	]
	 *	}
	 * @apiError (400) {String} error_message 错误说明
	 * @apiError (400) {Boolean} is_pass　　格式是否正确
	 * @apiErrorExample {json} 错误400响应 : 
	 *									{
	 *									   "error_message": "platform_uuid非法",
	 *									   "is_pass": false
	 *									} 
	 * @apiError (500) {String} error 错误说明
	 * @apiErrorExample {json} 错误500响应 :
	 *									{
	 *										"error": "服务器内部发生错误
	 *									}
	 * 
	 */
	@Path("/{uuid}/resources")
	@GET
	public Response resources(@PathParam("uuid")String platformUuid){
		ValidationResult validateResult = new ValidationResult();
		if(!Pattern.matches(RegexConstant.UUID_32, platformUuid)){
			validateResult.setErrorMsg("platform_uuid非法");
			return failValidate(validateResult);
		}else{
			AuthorityResourcesPO resourcePo = new AuthorityResourcesPO();
			resourcePo.setResourcePlatformUuid(platformUuid);
			Map<String,Object> resourcesMap = new HashMap<>();
			resourcesMap.put("data", authorityResourcesService.queryResourceTreeByPlatform(resourcePo));
			return success(resourcesMap);
		}
	}
	
	/**
	 * 平台信息
	 * @param platformUuid 平台uuid
	 * @return
	 * apidoc--------------------------->
	 * @api {GET} /platforms/{platform_uuid} 平台信息
	 * @apiExample {curl} curl请求示例:
	 * curl -i -X GET 'http://localhost:9000/api/platforms/fe178fd0073a4edea94e95a46bab15be'
	 * @apiGroup platforms
	 * @apiParam {String} platform_uuid 平台uuid
	 * @apiParamExample {json} 请求参数示例:
	 * {
	 *  "platform_uuid" : "fe178fd0073a4edea94e95a46bab15be"
	 * }
	 * @apiSuccess (200) {String} success 是否操作成功
	 * @apiSuccessExample {json}　请求成功响应 : 
	 * {
	 *	  "platform_add_time": "2017-01-01 12:12:12",
	 *	  "platform_add_user": "de0c7b2480494fda98db82f7a4707649",
	 *	  "platform_domain": "authority.com",
	 *	  "platform_name": "权限平台",
	 *	  "platform_sign": "authority",
	 *	  "platform_status": 1,
	 *	  "platform_uuid": "fe178fd0073a4edea94e95a46bab15be"
	 *	}
	 *
	 * @apiError (400) {String} error_message 错误说明
	 * @apiError (400) {Boolean} is_pass　　格式是否正确
	 * @apiErrorExample {json} 错误400响应 : 
	 *									{
	 *									   "error_message": "platform_uuid非法",
	 *									   "is_pass": false
	 *									}
	 * @apiError (500) {String} error 错误说明
	 * @apiErrorExample {json} 错误500响应 :
	 *									{
	 *										"error": "服务器内部发生错误
	 *									}
	 *  
	 */
	@Path("/{uuid}")
	@GET
	public Response query(@PathParam("uuid")String platformUuid){
		ValidationResult validateResult = new ValidationResult();
		if(!Pattern.matches(RegexConstant.UUID_32, platformUuid)){
			validateResult.setErrorMsg("platform_uuid非法");
			return failValidate(validateResult);
		}else{
			PlatformPO platformPo = new PlatformPO();
			platformPo.setPlatformUuid(platformUuid);
			return success(platformService.query(platformPo).get(0));
		}
	}
	
}