

package net.wangxj.authority.po;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import net.wangxj.util.constant.RegexConstant;
import net.wangxj.util.validate.Severity.Error;
import net.wangxj.util.validate.Severity.Info;
import net.wangxj.util.validate.groups.AddValidate;
import net.wangxj.util.validate.groups.DeleteValidate;
import net.wangxj.util.validate.groups.EditValidate;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:38
 */
public class AuthorityResourcesPO implements PO{
	
    // 主键 	
	@Pattern(regexp=RegexConstant.UUID_32, message="uuid非法", groups={EditValidate.class, DeleteValidate.class}, payload={Error.class})
	private String resourceUuid;
    // 资源名称 	
	@Pattern(regexp=RegexConstant.MORETHAN_TWO_CHINESECHAR, message="角色名必须是2-25个汉字", groups={AddValidate.class, EditValidate.class}, payload={Info.class})
	@NotBlank(message="角色名不可为空", groups={AddValidate.class}, payload={Info.class})
	private String resourceName;
    // 资源所在平台uuid 
	@Pattern(regexp=RegexConstant.UUID_32, message="资源平台非法", groups={AddValidate.class, EditValidate.class}, payload={Info.class})
	@NotBlank(message="资源平台不可为空", groups={AddValidate.class, EditValidate.class}, payload={Info.class})
	private String resourcePlatformUuid;
    // 资源状态: 未激活：3 已激活：1 已注销：2
	@Max(value=3, message="资源状态非法", groups={AddValidate.class, EditValidate.class}, payload={Info.class})
	@Min(value=1, message="资源状态非法", groups={AddValidate.class, EditValidate.class}, payload={Info.class})
	@NotNull(message="资源状态不可为空", groups={AddValidate.class}, payload={Info.class})
	private java.lang.Integer resourceStatus;
    // 资源链接 	
	@Pattern(regexp=RegexConstant.RESOURCE_HREF, message="资源url不符合格式", groups={AddValidate.class,EditValidate.class}, payload={Info.class})
	@Length(max=128, message="资源url最长128个字符", groups={AddValidate.class, EditValidate.class}, payload={Info.class})
	@NotBlank(message="资源url不可为空", groups={AddValidate.class}, payload={Info.class})
	private String resourceUrl;
    // 资源层级： 资源分为: 1:系统 ，2:菜单3：菜单下资源。。。 	
	@Max(value=10, message="资源层级非法", groups={AddValidate.class, EditValidate.class}, payload={Info.class})
	@Min(value=1, message="资源层级非法", groups={AddValidate.class, EditValidate.class}, payload={Info.class})
	@NotNull(message="资源层级不可为空", groups={AddValidate.class}, payload={Info.class})
	private java.lang.Integer resourceLevel;
    // 资源在所在层级的序号编码 	
	@Max(value=100, message="资源序号非法", groups={AddValidate.class,EditValidate.class}, payload={Info.class})
	@Min(value=1, message="资源序号非法", groups={AddValidate.class, EditValidate.class}, payload={Info.class})
	@NotNull(message="资源序号不可为空", groups={AddValidate.class}, payload={Info.class})
	private java.lang.Integer resourceOrder;
    // 资源父级id 	
	@Pattern(regexp=RegexConstant.UUID_32, message="资源父级uuid非法", groups={AddValidate.class,EditValidate.class}, payload={Info.class})
	@NotBlank(message="资源父级uuid不可为空", groups={AddValidate.class}, payload={Info.class})
	private String resourceParentUuid;
    // 资源css code备用 	
	private String resourceCssCode;
    // 资源增加时间 yyyy-MM-dd HH:mm:ss 	
	private String resourceAddTime;
    // 资源增加人uuid 	
	@Pattern(regexp=RegexConstant.UUID_32, message="增加人uuid非法", groups={AddValidate.class}, payload={Info.class})
	@NotBlank(message="增加人不可为空", groups={AddValidate.class}, payload={Error.class})
	private String resourceAddBy;
    // 删除时间 yyyy-MM-dd HH:mm:ss 	
	private String resourceDelTime;
    // 删除人uuid 	
	@Pattern(regexp=RegexConstant.UUID_32, message="删除人uuid非法", groups={DeleteValidate.class}, payload={Error.class})
	@NotBlank(message="删除人不可为空", groups={DeleteValidate.class}, payload={Error.class})
	private String resourceDelBy;
    // 2:未 1：已删除 	
	private java.lang.Integer resourceIsDelete;
	//编辑人
	@Pattern(regexp=RegexConstant.UUID_32, message="编辑人uuid非法", groups={EditValidate.class}, payload={Error.class})
	@NotBlank(message="编辑任不可为空", groups={EditValidate.class}, payload={Error.class})
	private String resourceEditBy;
	//编辑时间
	private String resourceEditTime;
	
	public AuthorityResourcesPO(){
		super();
	}

	public AuthorityResourcesPO(String resourceUuid, String resourceName, String resourcePlatformUuid,
			Integer resourceStatus, String resourceUrl, Integer resourceLevel, Integer resourceOrder,
			String resourceParentUuid, String resourceCssCode, String resourceAddTime, String resourceAddBy,
			String resourceDelTime, String resourceDelBy, Integer resourceIsDelete, String resourceEditBy,
			String resourceEditTime) {
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

	@Override
	public String toString() {
		return "AuthorityResourcesPO [resourceUuid=" + resourceUuid + ", resourceName=" + resourceName
				+ ", resourcePlatformUuid=" + resourcePlatformUuid + ", resourceStatus=" + resourceStatus
				+ ", resourceUrl=" + resourceUrl + ", resourceLevel=" + resourceLevel + ", resourceOrder="
				+ resourceOrder + ", resourceParentUuid=" + resourceParentUuid + ", resourceCssCode=" + resourceCssCode
				+ ", resourceAddTime=" + resourceAddTime + ", resourceAddBy=" + resourceAddBy + ", resourceDelTime="
				+ resourceDelTime + ", resourceDelBy=" + resourceDelBy + ", resourceIsDelete=" + resourceIsDelete
				+ ", resourceEditBy=" + resourceEditBy + ", resourceEditTime=" + resourceEditTime + "]";
	}
	
	
	
}

