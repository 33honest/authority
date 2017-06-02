package net.wangxj.authority.web.dto;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:38
 */
@JSONType
public class AuthorityResourcesDTO extends DTO implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1334421736357983765L;
	// 主键
	@JSONField(name = "resource_uuid")
	private String resourceUuid;
    // 资源名称 	
	@JSONField(name = "resource_name")
	private String resourceName;
    // 资源所在平台uuid 
	@JSONField(name = "resource_platform_uuid")
	private String resourcePlatformUuid;
    // 资源状态: 未激活：3 已激活：1 已注销：2
	@JSONField(name = "resource_status")
	private java.lang.Integer resourceStatus;
    // 资源链接 	
	@JSONField(name = "resource_url")
	private String resourceUrl;
    // 资源层级： 资源分为: 1:系统 ，2:菜单3：菜单下资源。。。 	
	@JSONField(name = "resource_level")
	private java.lang.Integer resourceLevel;
    // 资源在所在层级的序号编码 	
	@JSONField(name = "resource_order")
	private java.lang.Integer resourceOrder;
    // 资源父级id 	
	@JSONField(name = "resource_parent_uuid")
	private String resourceParentUuid;
    // 资源css code备用
	@JSONField(name = "resource_css_code")
	private String resourceCssCode;
    // 资源增加时间 yyyy-MM-dd HH:mm:ss
	@JSONField(name = "resource_add_time")
	private String resourceAddTime;
    // 资源增加人uuid 	
	@JSONField(name = "resource_add_by")
	private String resourceAddBy;
    // 删除人uuid 	
	@JSONField(name = "resource_delete_by")
	private String resourceDelBy;
	//编辑人
	@JSONField(name = "resource_edit_by")
	private String resourceEditBy;
	//编辑时间
	@JSONField(name = "resource_edit_time")
	private String resourceEditTime;
	@JSONField(name = "childList")
	private List<AuthorityResourcesDTO> childList;
	
	

	public AuthorityResourcesDTO() {
		super();
	}



	public AuthorityResourcesDTO(String resourceUuid, String resourceName, String resourcePlatformUuid,
			Integer resourceStatus, String resourceUrl, Integer resourceLevel, Integer resourceOrder,
			String resourceParentUuid, String resourceCssCode, String resourceAddTime, String resourceAddBy,
			String resourceDelBy, String resourceEditBy, String resourceEditTime,
			List<AuthorityResourcesDTO> childList) {
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
		this.resourceDelBy = resourceDelBy;
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



	public String getResourceDelBy() {
		return resourceDelBy;
	}



	public void setResourceDelBy(String resourceDelBy) {
		this.resourceDelBy = resourceDelBy;
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



	public List<AuthorityResourcesDTO> getChildList() {
		return childList;
	}



	public void setChildList(List<AuthorityResourcesDTO> childList) {
		this.childList = childList;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((resourceUuid == null) ? 0 : resourceUuid.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorityResourcesDTO other = (AuthorityResourcesDTO) obj;
		if (resourceUuid == null) {
			if (other.resourceUuid != null)
				return false;
		} else if (!resourceUuid.equals(other.resourceUuid))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "AuthorityResourcesDTO [resourceUuid=" + resourceUuid + ", resourceName=" + resourceName
				+ ", resourcePlatformUuid=" + resourcePlatformUuid + ", resourceStatus=" + resourceStatus
				+ ", resourceUrl=" + resourceUrl + ", resourceLevel=" + resourceLevel + ", resourceOrder="
				+ resourceOrder + ", resourceParentUuid=" + resourceParentUuid + ", resourceCssCode=" + resourceCssCode
				+ ", resourceAddTime=" + resourceAddTime + ", resourceAddBy=" + resourceAddBy + ", resourceDelBy="
				+ resourceDelBy + ", resourceEditBy=" + resourceEditBy + ", resourceEditTime=" + resourceEditTime
				+ ", childList=" + childList + "]";
	}

	
	
}

