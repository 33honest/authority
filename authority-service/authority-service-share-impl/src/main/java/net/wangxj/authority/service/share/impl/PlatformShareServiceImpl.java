
package net.wangxj.authority.service.share.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import net.wangxj.authority.service.share.BaseAbstractAuthorityShareService;
import net.wangxj.authority.service.share.PlatformShareService;
import net.wangxj.util.string.TimeUtil;
import net.wangxj.util.string.UuidUtil;
import net.wangxj.util.validate.groups.AddValidate;
import net.wangxj.util.validate.groups.DeleteValidate;
import net.wangxj.util.validate.groups.EditValidate;
import net.wangxj.authority.Response;
import net.wangxj.authority.constant.DataDictionaryConstant;
import net.wangxj.authority.dto.PlatformDTO;
import net.wangxj.authority.po.AuthorityUserPO;
import net.wangxj.authority.po.PlatformPO;
import net.wangxj.authority.service.AuthorityUserService;
import net.wangxj.authority.service.PlatformService;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:44
 */
public class PlatformShareServiceImpl extends BaseAbstractAuthorityShareService implements PlatformShareService {

	private static Logger logger = Logger.getLogger(PlatformShareServiceImpl.class);
	
	@Resource
	private PlatformService platformService;
	@Resource
	private AuthorityUserService authorityUserService;
	
	@Override
	public Response<Integer> add(PlatformDTO platformDto){
		
		Response<Integer> response = new Response<Integer>();
		PlatformPO platformpo = new PlatformPO();
		//验证
		String validateRes = validateDto(platformDto, platformpo, AddValidate.class);
		if(validateRes != null){
			response.setCode(1L);
			response.setMessage(validateRes);
			return response;
		}
		platformpo.setPlatformUuid(UuidUtil.newGUID());
		platformpo.setPlatformAddTime(TimeUtil.getNowStr());
		platformpo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		Integer uuid = platformService.add(platformpo);
		if(uuid>0){
			logger.info("新增platformDTO成功");
			response.setCode(0L);
			response.setResObject(uuid);
			response.setMessage("添加成功");
		}
		else{
			logger.info("新增platformDTO失败");
		}
		return response;
	}
	
	@Override
	public Response<Integer> addBatch(List<PlatformDTO> listDto){
		
		Response<Integer> response = new Response<Integer>();
		
		if(listDto == null && listDto.size()>0){
			response.setMessage("传入参数不可为空");
			return response;
		}
		
		List<PlatformPO> listPo = new ArrayList<>();
		for(PlatformDTO platformDto : listDto){
			PlatformPO platformPo = new PlatformPO();
			BeanUtils.copyProperties(platformDto,platformPo);
			listPo.add(platformPo);
		}
		Integer count = platformService.addBatch(listPo);
		logger.info("新增listplatformDTO成功");
		response.setCode(0L);
		response.setResObject(count);
		return response;
	}
	
	@Override
	public Response<Integer> modifyByUuid(PlatformDTO platformDto){
		
		Response<Integer> response = new Response<>();
		PlatformPO platformpo = new PlatformPO(); 
		//验证
		String validateRes = validateDto(platformDto, platformpo, EditValidate.class);
		if(validateRes != null){
			response.setCode(1L);
			response.setMessage(validateRes);
			return response;
		}
		platformpo.setPlatformEditTime(TimeUtil.getNowStr());
		Integer count = platformService.modifyByUuid(platformpo);
		if(count > 0){
			logger.info("modifyplatformDTO修改成功");
			response.setCode(0L);
			response.setResObject(count);
			response.setMessage("成功");
		}
		else{
			logger.debug("修改platform失败");
		}
		return response;
	}
	
	@Override
	public Response<PlatformDTO> queryListByCondition(PlatformDTO platformDto,boolean noDelete){
		
		Response<PlatformDTO> response = new Response<>();
		if(platformDto == null){
			response.setMessage("platformDto参数不可为空");
			return response;
		}
		PlatformPO platformPo = new PlatformPO();
		List<PlatformPO> listPo = new ArrayList<>();
		List<PlatformDTO> listDto = new ArrayList<>();
		BeanUtils.copyProperties(platformDto, platformPo);
		if(noDelete){
			platformPo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		}
		listPo = platformService.queryListByCondition(platformPo);
		logger.info("查询queryList成功");
		for (PlatformPO platformPo2 : listPo) {
			PlatformDTO platformDto2 = new PlatformDTO();
			BeanUtils.copyProperties(platformPo2, platformDto2);
			listDto.add(platformDto2);
		}
		response.setCode(0L);
		response.setData(listDto);
		response.setMessage("成功");
		
		return response;
	}
	
	@Override
	public Response<PlatformDTO> queryPageListByCondition(PlatformDTO platformDto, int pageNum, int limit, String order, String sort){
		
		Response<PlatformDTO> response = new Response<>();
		if(platformDto == null){
			response.setMessage("platformDto参数不可为空");
			return response;
		}
		PlatformPO platformPo = new PlatformPO();
		List<PlatformPO> listPo = new ArrayList<>();
		List<PlatformDTO> listDto = new ArrayList<>();
		BeanUtils.copyProperties(platformDto, platformPo);
		platformPo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		listPo = platformService.queryPageListByCondition(platformPo,pageNum,limit,order,sort);
		for (PlatformPO platformPo2 : listPo) {
			AuthorityUserPO userPo = new AuthorityUserPO();
			userPo.setUserUuid(platformPo2.getPlatformAddBy());
			AuthorityUserPO authorityUserPO = authorityUserService.queryListByCondition(userPo).get(0);
			PlatformDTO platformDto2 = new PlatformDTO();
			BeanUtils.copyProperties(platformPo2, platformDto2);
			platformDto2.setPlatformAddByName(authorityUserPO.getUserLoginName());
			listDto.add(platformDto2);
		}
		response.setCode(0L);
		response.setData(listDto);
		response.setMessage("成功");
		
		return response;
	}
	
	@Override
	public Response<Integer> getCountByCondition(PlatformDTO platformDto,boolean noDelete){
		
		Response<Integer> response = new Response<>();
		if(platformDto == null){
			response.setMessage("platformDto参数不可为空");
			return response;
		}
		PlatformPO platformpo = new PlatformPO(); 
		BeanUtils.copyProperties(platformDto, platformpo);
		if(noDelete){
			platformpo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_NO_VALUE);
		}
		Integer count = platformService.getCountByCondition(platformpo);
		logger.info("count查询成功");
		
		response.setCode(0L);
		response.setResObject(count);
		response.setMessage("成功");
			
		return response;
	}

	@Override
	public Response<Integer> deleteByUuid(PlatformDTO platformDto) {
		Response<Integer> response = new Response<>();
		PlatformPO platformpo = new PlatformPO(); 
		//验证
		String validateRes = validateDto(platformDto, platformpo, DeleteValidate.class);
		if(validateRes != null){
			response.setCode(1L);
			response.setMessage(validateRes);
			return response;
		}
		platformpo.setPlatformDelTime(TimeUtil.getNowStr());
		platformpo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_YES_VALUE);
		Integer count = platformService.modifyByUuid(platformpo);
		if(count > 0){
			logger.info("deleteplatformDTO修改成功");
			response.setCode(0L);
			response.setResObject(count);
			response.setMessage("成功");
		}
		else{
			logger.debug("删除platform失败");
		}
		return response;
	}

	@Override
	public Response<Integer> deleteByBatch(List<PlatformDTO> platformList) {
		Response<Integer> response = new Response<>();
		List<PlatformPO> platformPoList = new ArrayList<>();
		//验证
		for (PlatformDTO platformDto : platformList) {
			PlatformPO platformPo = new PlatformPO();
			String validateRes = validateDto(platformDto, platformPo, DeleteValidate.class);
			if(validateRes != null){
				response.setCode(1L);
				response.setMessage(validateRes);
				return response;
			}
			platformPo.setPlatformDelTime(TimeUtil.getNowStr());
			platformPo.setPlatformIsDelete(DataDictionaryConstant.ISDELETE_YES_VALUE);
			platformPoList.add(platformPo);
		}
		Integer count = platformService.modifyByBatch(platformPoList);
		if(count == platformList.size()){
			logger.debug("批量删除platform成功");
			response.setCode(0L);
			response.setResObject(count);
			response.setMessage("成功");
		}
		else{
			logger.debug("删除platform失败");
		}
		return response;
	}
	
}