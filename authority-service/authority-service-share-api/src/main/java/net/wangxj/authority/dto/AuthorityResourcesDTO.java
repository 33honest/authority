
package net.wangxj.authority.dto;

import java.io.Serializable;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:38
 */

public class AuthorityResourcesDTO implements Serializable{ 
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6932642899780126846L;
	// 主键 	
	private String resourceUuid;
    // 资源名称 	
	private String resourceName;
    // 资源所在平台uuid 	
	private String resourcePlatformUuid;
    // 资源状态: 未激活：0 已激活：1 已注销：2 	
	private java.lang.Integer resourceStatus;
    // 资源链接 	
	private String resourceUrl;
    // 资源层级： 资源分为: 1:系统 ，2:菜单3：菜单下资源。。。 	
	private java.lang.Integer resourceLevel;
    // 资源在所在层级的序号编码 	
	private java.lang.Integer resourceOrder;
    // 资源父级id 	
	private String resourceParentUuid;
    // 资源css code备用 	
	private String resourceCssCode;
    // 资源增加时间 yyyy-MM-dd HH:mm:ss 	
	private String resourceAddTime;
    // 资源增加人uuid 	
	private String resourceAddBy;
    // 删除时间 yyyy-MM-dd HH:mm:ss 	
	private String resourceDelTime;
    // 删除人uuid 	
	private String resourceDelBy;
    // 0:未 1：已删除 	
	private java.lang.Integer resourceIsDelete;
	
	public AuthorityResourcesDTO(){
		super();
	}
	
	public AuthorityResourcesDTO(String resourceUuid,String resourceName,String resourcePlatformUuid,java.lang.Integer resourceStatus,String resourceUrl,java.lang.Integer resourceLevel,java.lang.Integer resourceOrder,String resourceParentUuid,String resourceCssCode,String resourceAddTime,String resourceAddBy,String resourceDelTime,String resourceDelBy,java.lang.Integer resourceIsDelete){
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
	}

	public void setResourceUuid(String value) {
		this.resourceUuid = value;
	}
	
	public String getResourceUuid() {
		return this.resourceUuid;
	}
	public void setResourceName(String value) {
		this.resourceName = value;
	}
	
	public String getResourceName() {
		return this.resourceName;
	}
	public void setResourcePlatformUuid(String value) {
		this.resourcePlatformUuid = value;
	}
	
	public String getResourcePlatformUuid() {
		return this.resourcePlatformUuid;
	}
	public void setResourceStatus(java.lang.Integer value) {
		this.resourceStatus = value;
	}
	
	public java.lang.Integer getResourceStatus() {
		return this.resourceStatus;
	}
	public void setResourceUrl(String value) {
		this.resourceUrl = value;
	}
	
	public String getResourceUrl() {
		return this.resourceUrl;
	}
	public void setResourceLevel(java.lang.Integer value) {
		this.resourceLevel = value;
	}
	
	public java.lang.Integer getResourceLevel() {
		return this.resourceLevel;
	}
	public void setResourceOrder(java.lang.Integer value) {
		this.resourceOrder = value;
	}
	
	public java.lang.Integer getResourceOrder() {
		return this.resourceOrder;
	}
	public void setResourceParentUuid(String value) {
		this.resourceParentUuid = value;
	}
	
	public String getResourceParentUuid() {
		return this.resourceParentUuid;
	}
	public void setResourceCssCode(String value) {
		this.resourceCssCode = value;
	}
	
	public String getResourceCssCode() {
		return this.resourceCssCode;
	}
	public void setResourceAddTime(String value) {
		this.resourceAddTime = value;
	}
	
	public String getResourceAddTime() {
		return this.resourceAddTime;
	}
	public void setResourceAddBy(String value) {
		this.resourceAddBy = value;
	}
	
	public String getResourceAddBy() {
		return this.resourceAddBy;
	}
	public void setResourceDelTime(String value) {
		this.resourceDelTime = value;
	}
	
	public String getResourceDelTime() {
		return this.resourceDelTime;
	}
	public void setResourceDelBy(String value) {
		this.resourceDelBy = value;
	}
	
	public String getResourceDelBy() {
		return this.resourceDelBy;
	}
	public void setResourceIsDelete(java.lang.Integer value) {
		this.resourceIsDelete = value;
	}
	
	public java.lang.Integer getResourceIsDelete() {
		return this.resourceIsDelete;
	}
	
	public String toString(){
		return "AuthorityResources [resourceUuid="+resourceUuid+", resourceName="+resourceName+", resourcePlatformUuid="+resourcePlatformUuid+", resourceStatus="+resourceStatus+", resourceUrl="+resourceUrl+", resourceLevel="+resourceLevel+", resourceOrder="+resourceOrder+", resourceParentUuid="+resourceParentUuid+", resourceCssCode="+resourceCssCode+", resourceAddTime="+resourceAddTime+", resourceAddBy="+resourceAddBy+", resourceDelTime="+resourceDelTime+", resourceDelBy="+resourceDelBy+", resourceIsDelete="+resourceIsDelete+"]";
	}
	
}

