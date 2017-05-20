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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.wangxj.util.validate.ValidationResult;
import net.wangxj.util.validate.groups.AddValidate;
import net.wangxj.util.validate.groups.DeleteValidate;
import net.wangxj.util.validate.groups.EditValidate;
import net.wangxj.authority.po.Page;
import net.wangxj.authority.po.PlatformPO;
import net.wangxj.authority.service.PlatformService;

/**
 * 
 * @author huoshan
 * created by 2017年4月8日 上午11:10:35
 * 
 * 
 *
 */
@Path("/platforms")
public class PlatformRestServiceApi extends AbstractAuthrotiyRestService {

	private static Logger logger = Logger.getLogger(PlatformRestServiceApi.class);
	
	@Autowired
	private PlatformService platformService;
	
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
	@Produces({"application/json"})
	@Consumes({"application/json"})
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
	@Produces({"application/json"})
	@Consumes({"application/json"})
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
	 * http://localhost:9000/api/platforms?page_number=2&limit=3&order=asc&sort=platform_uuid
	 * 分页查询
	 * 
	 * apidoc----------------->
	 * @api {GET} /platforms 分页查询
	 * @apiExample {curl} curl请求示例:
	 * curl -X GET 'http://localhost:9000/api/platforms?page_number=2&limit=3&order=asc&sort=platform_uuid'
	 * @apiGroup platforms
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
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response pageQuery(@BeanParam Page page){
		ValidationResult validateResult = platformService.validatePo(page, Default.class);
		ValidationResult validateSortRes = platformService.validateSort(PlatformPO.class, page.getSort());
		if(validateResult != null){
			return failValidate(validateResult);
		}
		else if(validateSortRes != null){
			return failValidate(validateSortRes);
		}
		else{
			Map<String , Object>  pageResMap = platformService.pageQuery(new PlatformPO(), page);
			return success(pageResMap);
		}
	}

	/**
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
	@Produces({"application/json"})
	@Consumes({"application/json"})
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


	
}