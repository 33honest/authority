package net.wangxj.authority.po;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.ws.rs.QueryParam;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

import net.wangxj.util.annotation.NotRepeat;
import net.wangxj.util.constant.RegexConstant;
import net.wangxj.util.validate.groups.AddValidate;
import net.wangxj.util.validate.groups.DeleteValidate;
import net.wangxj.util.validate.groups.EditValidate;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:38
 */
@JSONType
public class AuthorityResourcesPO extends PO implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1334421736357983765L;
	// 主键
	@Null(message = "无法识别resource_uuid,请严格按照API文档掉用" , groups = {AddValidate.class})
	@NotBlank(message = "资源uuid为必填值" , groups = {EditValidate.class,DeleteValidate.class})
	@Pattern(regexp=RegexConstant.UUID_32, message="uuid非法", groups={EditValidate.class, DeleteValidate.class})
	@JSONField(name = "resource_uuid")
	@QueryParam("resource_uuid")
	private String resourceUuid;
    // 资源名称 	
	@Pattern(regexp=RegexConstant.MORETHAN_TWO_CHINESECHAR, message="角色名必须是2-25个汉字", groups={AddValidate.class, EditValidate.class})
//	@NotBlank(message="角色名不可为空", groups={AddValidate.class})
	@NotRepeat(message = "该resource_name已存在")
	@JSONField(name = "resource_name")
	@QueryParam("resource_name")
	private String resourceName;
    // 资源所在平台uuid 
	@Pattern(regexp=RegexConstant.UUID_32, message="资源平台非法", groups={AddValidate.class, EditValidate.class})
	@NotBlank(message="资源平台不可为空", groups={AddValidate.class})
	@JSONField(name = "resource_platform_uuid")
	@QueryParam("resource_platform_uuid")
	private String resourcePlatformUuid;
    // 资源状态: 未激活：3 已激活：1 已注销：2
	@Max(value=3, message="资源状态非法", groups={AddValidate.class, EditValidate.class})
	@Min(value=1, message="资源状态非法", groups={AddValidate.class, EditValidate.class})
	@NotNull(message="资源状态不可为空", groups={AddValidate.class})
	@JSONField(name = "resource_status")
	private java.lang.Integer resourceStatus;
    // 资源链接 	
	@Pattern(regexp=RegexConstant.RESOURCE_HREF, message="资源url不符合格式", groups={AddValidate.class,EditValidate.class})
	@Length(max=128, message="资源url最长128个字符", groups={AddValidate.class, EditValidate.class})
	@NotBlank(message="资源url不可为空", groups={AddValidate.class})
	@NotRepeat(message = "该resource_url已存在")
	@JSONField(name = "resource_url")
	@QueryParam("resource_url")
	private String resourceUrl;
    // 资源层级： 资源分为: 1:系统 ，2:菜单3：菜单下资源。。。 	
	@Max(value=10, message="资源层级非法", groups={AddValidate.class, EditValidate.class})
	@Min(value=1, message="资源层级非法", groups={AddValidate.class, EditValidate.class})
	@NotNull(message="资源层级不可为空", groups={AddValidate.class})
	@JSONField(name = "resource_level")
	private java.lang.Integer resourceLevel;
    // 资源在所在层级的序号编码 	
	@Max(value=100, message="资源序号非法", groups={AddValidate.class,EditValidate.class})
	@Min(value=1, message="资源序号非法", groups={AddValidate.class, EditValidate.class})
	@NotNull(message="资源序号不可为空", groups={AddValidate.class})
	@JSONField(name = "resource_order")
	private java.lang.Integer resourceOrder;
    // 资源父级id 	
	@Pattern(regexp=RegexConstant.UUID_32, message="资源父级uuid非法", groups={AddValidate.class,EditValidate.class})
	@NotBlank(message="资源父级uuid不可为空", groups={AddValidate.class})
	@JSONField(name = "resource_parent_uuid")
	private String resourceParentUuid;
    // 资源css code备用
	@JSONField(name = "resource_css_code")
	private String resourceCssCode;
    // 资源增加时间 yyyy-MM-dd HH:mm:ss
	@Null(message = "无法识别resource_add_time,请严格按照API文档调用" , groups = {AddValidate.class,EditValidate.class})
	@JSONField(name = "resource_add_time")
	private String resourceAddTime;
    // 资源增加人uuid 	
	@Pattern(regexp=RegexConstant.UUID_32, message="增加人uuid非法", groups={AddValidate.class})
	@NotBlank(message="增加人不可为空", groups={AddValidate.class})
	@Null(message = "无法识别resource_add_by,请严格按照API文档调用" , groups={EditValidate.class})
	@JSONField(name = "resource_add_by")
	private String resourceAddBy;
    // 删除时间 yyyy-MM-dd HH:mm:ss 	
	@JSONField(deserialize = false , serialize = false)
	private String resourceDelTime;
    // 删除人uuid 	
	@Pattern(regexp=RegexConstant.UUID_32, message="删除人uuid非法", groups={DeleteValidate.class})
	@NotBlank(message="删除人不可为空", groups={DeleteValidate.class})
	@Null(message = "无法是被resource_delete_by,请严格按照啊API文旦掉用" , groups = {AddValidate.class, EditValidate.class})
	@JSONField(name = "resource_delete_by")
	private String resourceDelBy;
    // 2:未 1：已删除 	
	@JSONField(serialize = false , deserialize = false)
	private java.lang.Integer resourceIsDelete;
	//编辑人
	@Pattern(regexp=RegexConstant.UUID_32, message="编辑人uuid非法", groups={EditValidate.class})
	@NotBlank(message="编辑任不可为空", groups={EditValidate.class})
	@Null(message = "无法识别resource_edit_by,请严格按照API文档调用" , groups = {AddValidate.class})
	@JSONField(name = "resource_edit_by")
	private String resourceEditBy;
	//编辑时间
	@Null(message = "无法识别resource_edit_time ,请严格按照API文档调用" , groups = {AddValidate.class,EditValidate.class})
	@JSONField(name = "resource_edit_time")
	private String resourceEditTime;
	
	private List<AuthorityResourcesPO> childList;
	
	public AuthorityResourcesPO(){
		super();
	}

	public AuthorityResourcesPO(String resourceUuid, String resourceName, String resourcePlatformUuid,
			Integer resourceStatus, String resourceUrl, Integer resourceLevel, Integer resourceOrder,
			String resourceParentUuid, String resourceCssCode, String resourceAddTime, String resourceAddBy,
			String resourceDelTime, String resourceDelBy, Integer resourceIsDelete, String resourceEditBy,
			String resourceEditTime, List<AuthorityResourcesPO> childList) {
		super();
		this.resourceUuid = resourceUuid;
		this.resourceName = resourceName;
		this.resourcePlatformUuid = resourcePlatformUuid;
		this.resourceStatus = resourceStatus;
		this.resourceUrl = resourceUrl;
		this.resourceLevel = resourceLevel;
		this.resourceOrder = resourceOrder;
		this.resourceParentUuid = resourceParentUuid;
		this.resourceCssCode = resourceCssCode;
		this.resourceAddTime = resourceAddTime;
		this.resourceAddBy = resourceAddBy;
		this.resourceDelTime = resourceDelTime;
		this.resourceDelBy = resourceDelBy;
		this.resourceIsDelete = resourceIsDelete;
		this.resourceEditBy = resourceEditBy;
		this.resourceEditTime = resourceEditTime;
		this.childList = childList;
	}

	public String getResourceUuid() {
		return resourceUuid;
	}

	public void setResourceUuid(String resourceUuid) {
		this.resourceUuid = resourceUuid;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourcePlatformUuid() {
		return resourcePlatformUuid;
	}

	public void setResourcePlatformUuid(String resourcePlatformUuid) {
		this.resourcePlatformUuid = resourcePlatformUuid;
	}

	public java.lang.Integer getResourceStatus() {
		return resourceStatus;
	}

	public void setResourceStatus(java.lang.Integer resourceStatus) {
		this.resourceStatus = resourceStatus;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public java.lang.Integer getResourceLevel() {
		return resourceLevel;
	}

	public void setResourceLevel(java.lang.Integer resourceLevel) {
		this.resourceLevel = resourceLevel;
	}

	public java.lang.Integer getResourceOrder() {
		return resourceOrder;
	}

	public void setResourceOrder(java.lang.Integer resourceOrder) {
		this.resourceOrder = resourceOrder;
	}

	public String getResourceParentUuid() {
		return resourceParentUuid;
	}

	public void setResourceParentUuid(String resourceParentUuid) {
		this.resourceParentUuid = resourceParentUuid;
	}

	public String getResourceCssCode() {
		return resourceCssCode;
	}

	public void setResourceCssCode(String resourceCssCode) {
		this.resourceCssCode = resourceCssCode;
	}

	public String getResourceAddTime() {
		return resourceAddTime;
	}

	public void setResourceAddTime(String resourceAddTime) {
		this.resourceAddTime = resourceAddTime;
	}

	public String getResourceAddBy() {
		return resourceAddBy;
	}

	public void setResourceAddBy(String resourceAddBy) {
		this.resourceAddBy = resourceAddBy;
	}

	public String getResourceDelTime() {
		return resourceDelTime;
	}

	public void setResourceDelTime(String resourceDelTime) {
		this.resourceDelTime = resourceDelTime;
	}

	public String getResourceDelBy() {
		return resourceDelBy;
	}

	public void setResourceDelBy(String resourceDelBy) {
		this.resourceDelBy = resourceDelBy;
	}

	public java.lang.Integer getResourceIsDelete() {
		return resourceIsDelete;
	}

	public void setResourceIsDelete(java.lang.Integer resourceIsDelete) {
		this.resourceIsDelete = resourceIsDelete;
	}

	public String getResourceEditBy() {
		return resourceEditBy;
	}

	public void setResourceEditBy(String resourceEditBy) {
		this.resourceEditBy = resourceEditBy;
	}

	public String getResourceEditTime() {
		return resourceEditTime;
	}

	public void setResourceEditTime(String resourceEditTime) {
		this.resourceEditTime = resourceEditTime;
	}

	public List<AuthorityResourcesPO> getChildList() {
		return childList;
	}

	public void setChildList(List<AuthorityResourcesPO> childList) {
		this.childList = childList;
	}

	@Override
	public String toString() {
		return "AuthorityResourcesPO [resourceUuid=" + resourceUuid + ", resourceName=" + resourceName
				+ ", resourcePlatformUuid=" + resourcePlatformUuid + ", resourceStatus=" + resourceStatus
				+ ", resourceUrl=" + resourceUrl + ", resourceLevel=" + resourceLevel + ", resourceOrder="
				+ resourceOrder + ", resourceParentUuid=" + resourceParentUuid + ", resourceCssCode=" + resourceCssCode
				+ ", resourceAddTime=" + resourceAddTime + ", resourceAddBy=" + resourceAddBy + ", resourceDelTime="
				+ resourceDelTime + ", resourceDelBy=" + resourceDelBy + ", resourceIsDelete=" + resourceIsDelete
				+ ", resourceEditBy=" + resourceEditBy + ", resourceEditTime=" + resourceEditTime + ", childList="
				+ childList + "]";
	}
	
}

