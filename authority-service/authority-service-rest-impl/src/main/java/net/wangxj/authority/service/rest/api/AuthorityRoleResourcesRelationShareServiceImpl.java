//
//package net.wangxj.authority.service.rest.api;
//
//import java.util.ArrayList;
//import java.util.List;
//import javax.annotation.Resource;
//import org.apache.log4j.Logger;
//import org.springframework.beans.BeanUtils;
//
//import net.wangxj.util.string.TimeUtil;
//import net.wangxj.util.string.UuidUtil;
//import net.wangxj.authority.Response;
//import net.wangxj.authority.constant.DataDictionaryConstant;
//import net.wangxj.authority.dto.AuthorityRoleResourcesRelationDTO;
//import net.wangxj.authority.po.AuthorityRoleResourcesRelationPO;
//import net.wangxj.authority.service.AuthorityRoleResourcesRelationService;
//import net.wangxj.authority.service.rest.AuthorityRoleResourcesRelationShareService;
//
///**
// * created by	: wangxj
// * created time	: 2016-12-26 18:06:41
// */
//public class AuthorityRoleResourcesRelationShareServiceImpl implements AuthorityRoleResourcesRelationShareService{
//
//	private static Logger logger = Logger.getLogger(AuthorityRoleResourcesRelationShareServiceImpl.class);
//	
//	@Resource
//	private AuthorityRoleResourcesRelationService authorityRoleResourcesRelationService;
//	
//	@Override
//	public Response<Integer> add(AuthorityRoleResourcesRelationDTO authorityRoleResourcesRelationDto){
//		
//		Response<Integer> response = new Response<Integer>();
//		
//		if(authorityRoleResourcesRelationDto == null){
//			response.setMessage("传入参数不可为空");
//			return response;
//		}
//		
//		AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationpo = new AuthorityRoleResourcesRelationPO(); 
//		BeanUtils.copyProperties(authorityRoleResourcesRelationDto, authorityRoleResourcesRelationpo);
//		
//		Integer uuid = authorityRoleResourcesRelationService.add(authorityRoleResourcesRelationpo);
//		logger.info("新增authorityRoleResourcesRelationDTO成功");
//		response.setCode(0L);
//		response.setResObject(uuid);
//		return response;
//	}
//	
//	@Override
//	public Response<Integer> addBatch(List<AuthorityRoleResourcesRelationDTO> listDto){
//		
//		Response<Integer> response = new Response<Integer>();
//		
//		if(listDto == null && listDto.size()>0){
//			response.setMessage("传入参数不可为空");
//			return response;
//		}
//		
//		List<AuthorityRoleResourcesRelationPO> listPo = new ArrayList<>();
//		for(AuthorityRoleResourcesRelationDTO authorityRoleResourcesRelationDto : listDto){
//			AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo = new AuthorityRoleResourcesRelationPO();
//			BeanUtils.copyProperties(authorityRoleResourcesRelationDto,authorityRoleResourcesRelationPo);
//			
//			authorityRoleResourcesRelationPo.setRrAddTime(TimeUtil.getNowStr());
//			authorityRoleResourcesRelationPo.setRrIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
//			authorityRoleResourcesRelationPo.setRrGrantType(DataDictionaryConstant.GRANTTYPE_WRITEREAD_VALUE);
//			authorityRoleResourcesRelationPo.setRrUuid(UuidUtil.newGUID());
//			
//			listPo.add(authorityRoleResourcesRelationPo);
//		}
//		Integer count = authorityRoleResourcesRelationService.addBatch(listPo);
//		logger.info("新增listauthorityRoleResourcesRelationDTO成功");
//		response.setCode(0L);
//		response.setResObject(count);
//		return response;
//	}
//	
//	@Override
//	public Response<Integer> modifyByUuid(AuthorityRoleResourcesRelationDTO authorityRoleResourcesRelationDto){
//		
//		Response<Integer> response = new Response<>();
//		if(authorityRoleResourcesRelationDto == null){
//			response.setMessage("authorityRoleResourcesRelationDto参数不可为空");
//			return response;
//		}
//		AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationpo = new AuthorityRoleResourcesRelationPO(); 
//		BeanUtils.copyProperties(authorityRoleResourcesRelationDto, authorityRoleResourcesRelationpo);
//		
//		Integer count = authorityRoleResourcesRelationService.modifyByUuid(authorityRoleResourcesRelationpo);
//		logger.info("modifyauthorityRoleResourcesRelationDTO修改成功");
//			
//		response.setCode(0L);
//		response.setResObject(count);
//		response.setMessage("成功");
//			
//		return response;
//	}
//	
//	@Override
//	public Response<AuthorityRoleResourcesRelationDTO> queryListByCondition(AuthorityRoleResourcesRelationDTO authorityRoleResourcesRelationDto){
//		
//		Response<AuthorityRoleResourcesRelationDTO> response = new Response<>();
//		if(authorityRoleResourcesRelationDto == null){
//			response.setMessage("authorityRoleResourcesRelationDto参数不可为空");
//			return response;
//		}
//		AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo = new AuthorityRoleResourcesRelationPO();
//		List<AuthorityRoleResourcesRelationPO> listPo = new ArrayList<>();
//		List<AuthorityRoleResourcesRelationDTO> listDto = new ArrayList<>();
//		BeanUtils.copyProperties(authorityRoleResourcesRelationDto, authorityRoleResourcesRelationPo);
//		
//		listPo = authorityRoleResourcesRelationService.queryListByCondition(authorityRoleResourcesRelationPo);
//		logger.info("查询queryList成功");
//		for (AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo2 : listPo) {
//			AuthorityRoleResourcesRelationDTO authorityRoleResourcesRelationDto2 = new AuthorityRoleResourcesRelationDTO();
//			BeanUtils.copyProperties(authorityRoleResourcesRelationPo2, authorityRoleResourcesRelationDto2);
//			listDto.add(authorityRoleResourcesRelationDto2);
//		}
//		response.setCode(0L);
//		response.setData(listDto);
//		response.setMessage("成功");
//		
//		return response;
//	}
//	
//	@Override
//	public Response<AuthorityRoleResourcesRelationDTO> queryPageListByCondition(AuthorityRoleResourcesRelationDTO authorityRoleResourcesRelationDto, int pageNum, int limit){
//		
//		Response<AuthorityRoleResourcesRelationDTO> response = new Response<>();
//		if(authorityRoleResourcesRelationDto == null){
//			response.setMessage("authorityRoleResourcesRelationDto参数不可为空");
//			return response;
//		}
//		AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo = new AuthorityRoleResourcesRelationPO();
//		List<AuthorityRoleResourcesRelationPO> listPo = new ArrayList<>();
//		List<AuthorityRoleResourcesRelationDTO> listDto = new ArrayList<>();
//		BeanUtils.copyProperties(authorityRoleResourcesRelationDto, authorityRoleResourcesRelationPo);
//		
//		listPo = authorityRoleResourcesRelationService.queryPageListByCondition(authorityRoleResourcesRelationPo,pageNum,limit);
//		for (AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationPo2 : listPo) {
//			AuthorityRoleResourcesRelationDTO authorityRoleResourcesRelationDto2 = new AuthorityRoleResourcesRelationDTO();
//			BeanUtils.copyProperties(authorityRoleResourcesRelationPo2, authorityRoleResourcesRelationDto2);
//			listDto.add(authorityRoleResourcesRelationDto2);
//		}
//		response.setCode(0L);
//		response.setData(listDto);
//		response.setMessage("成功");
//		
//		return response;
//	}
//	
//	@Override
//	public Response<Integer> getCountByCondition(AuthorityRoleResourcesRelationDTO authorityRoleResourcesRelationDto){
//		
//		Response<Integer> response = new Response<>();
//		if(authorityRoleResourcesRelationDto == null){
//			response.setMessage("authorityRoleResourcesRelationDto参数不可为空");
//			return response;
//		}
//		AuthorityRoleResourcesRelationPO authorityRoleResourcesRelationpo = new AuthorityRoleResourcesRelationPO(); 
//		BeanUtils.copyProperties(authorityRoleResourcesRelationDto, authorityRoleResourcesRelationpo);
//		
//		Integer count = authorityRoleResourcesRelationService.getCountByCondition(authorityRoleResourcesRelationpo);
//		logger.info("count查询成功");
//		
//		response.setCode(0L);
//		response.setResObject(count);
//		response.setMessage("成功");
//			
//		return response;
//	}
//	
//
//}