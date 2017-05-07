//
//package net.wangxj.authority.dto;
//
//import java.io.Serializable;
//
//import net.wangxj.authority.constant.DataDictionaryConstant;
//
///**
// * created by	: wangxj
// * created time	: 2016-12-26 18:06:38
// */
//
//public class AuthorityResourcesDTO implements DTO,Serializable{ 
//	
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = -6932642899780126846L;
//	// 主键 	
//	private String resourceUuid;
//    // 资源名称 	
//	private String resourceName;
//    // 资源所在平台uuid 	
//	private String resourcePlatformUuid;
//	//资源所属平台名
//	private String resourcePlatformName;
//    // 资源状态: 未激活：3 已激活：1 已注销：2 	
//	private java.lang.Integer resourceStatus;
//	//资源状态名
//	private String resourceStatusName;
//    // 资源链接 	
//	private String resourceUrl;
//    // 资源层级： 资源分为: 1:系统 ，2:菜单3：菜单下资源。。。 	
//	private java.lang.Integer resourceLevel;
//    // 资源在所在层级的序号编码 	
//	private java.lang.Integer resourceOrder;
//    // 资源父级id 	
//	private String resourceParentUuid;
//	//资源的父级资源名
//	private String resourceParentName;
//    // 资源css code备用 	
//	private String resourceCssCode;
//    // 资源增加时间 yyyy-MM-dd HH:mm:ss 	
//	private String resourceAddTime;
//    // 资源增加人uuid 	
//	private String resourceAddBy;
//	//资源增加人名
//	private String resourceAddByName;
//    // 删除时间 yyyy-MM-dd HH:mm:ss 	
//	private String resourceDelTime;
//    // 删除人uuid 	
//	private String resourceDelBy;
//	//资源删除人名
//	private String resourceDelByName;
//    // 2:未 1：已删除 	
//	private java.lang.Integer resourceIsDelete;
//	//是否删除名
//	private String resourceIsDeleteName;
//	//编辑人
//	private String resourceEditBy;
//	//资源编辑人名
//	private String resourceEditByName;
//	//编辑时间
//	private String resourceEditTime;
//	
//	public AuthorityResourcesDTO(){
//		super();
//	}
//
//	public AuthorityResourcesDTO(String resourceUuid, String resourceName, String resourcePlatformUuid,
//			String resourcePlatformName, Integer resourceStatus, String resourceStatusName, String resourceUrl,
//			Integer resourceLevel, Integer resourceOrder, String resourceParentUuid, String resourceParentName,
//			String resourceCssCode, String resourceAddTime, String resourceAddBy, String resourceAddByName,
//			String resourceDelTime, String resourceDelBy, String resourceDelByName, Integer resourceIsDelete,
//			String resourceIsDeleteName, String resourceEditBy, String resourceEditByName, String resourceEditTime) {
//		super();
//		this.resourceUuid = resourceUuid;
//		this.resourceName = resourceName;
//		this.resourcePlatformUuid = resourcePlatformUuid;
//		this.resourcePlatformName = resourcePlatformName;
//		this.resourceStatus = resourceStatus;
//		this.resourceStatusName = resourceStatusName;
//		this.resourceUrl = resourceUrl;
//		this.resourceLevel = resourceLevel;
//		this.resourceOrder = resourceOrder;
//		this.resourceParentUuid = resourceParentUuid;
//		this.resourceParentName = resourceParentName;
//		this.resourceCssCode = resourceCssCode;
//		this.resourceAddTime = resourceAddTime;
//		this.resourceAddBy = resourceAddBy;
//		this.resourceAddByName = resourceAddByName;
//		this.resourceDelTime = resourceDelTime;
//		this.resourceDelBy = resourceDelBy;
//		this.resourceDelByName = resourceDelByName;
//		this.resourceIsDelete = resourceIsDelete;
//		this.resourceIsDeleteName = resourceIsDeleteName;
//		this.resourceEditBy = resourceEditBy;
//		this.resourceEditByName = resourceEditByName;
//		this.resourceEditTime = resourceEditTime;
//	}
//
//
//
//	public String getResourceUuid() {
//		return resourceUuid;
//	}
//
//	public void setResourceUuid(String resourceUuid) {
//		this.resourceUuid = resourceUuid;
//	}
//
//	public String getResourceName() {
//		return resourceName;
//	}
//
//	public void setResourceName(String resourceName) {
//		this.resourceName = resourceName;
//	}
//
//	public String getResourcePlatformUuid() {
//		return resourcePlatformUuid;
//	}
//
//	public void setResourcePlatformUuid(String resourcePlatformUuid) {
//		this.resourcePlatformUuid = resourcePlatformUuid;
//	}
//
//	public String getResourcePlatformName() {
//		return resourcePlatformName;
//	}
//
//	public void setResourcePlatformName(String resourcePlatformName) {
//		this.resourcePlatformName = resourcePlatformName;
//	}
//
//	public java.lang.Integer getResourceStatus() {
//		return resourceStatus;
//	}
//
//	public void setResourceStatus(java.lang.Integer resourceStatus) {
//		this.resourceStatus = resourceStatus;
//	}
//
//	public String getResourceStatusName() {
//		return DataDictionaryConstant.getResourceStatusKey(this.resourceStatus);
//	}
//
//	public void setResourceStatusName(String resourceStatusName) {
//		this.resourceStatusName = resourceStatusName;
//	}
//
//	public String getResourceUrl() {
//		return resourceUrl;
//	}
//
//	public void setResourceUrl(String resourceUrl) {
//		this.resourceUrl = resourceUrl;
//	}
//
//	public java.lang.Integer getResourceLevel() {
//		return resourceLevel;
//	}
//
//	public void setResourceLevel(java.lang.Integer resourceLevel) {
//		this.resourceLevel = resourceLevel;
//	}
//
//	public java.lang.Integer getResourceOrder() {
//		return resourceOrder;
//	}
//
//	public void setResourceOrder(java.lang.Integer resourceOrder) {
//		this.resourceOrder = resourceOrder;
//	}
//
//	public String getResourceParentUuid() {
//		return resourceParentUuid;
//	}
//
//	public void setResourceParentUuid(String resourceParentUuid) {
//		this.resourceParentUuid = resourceParentUuid;
//	}
//
//	public String getResourceParentName() {
//		return resourceParentName;
//	}
//
//	public void setResourceParentName(String resourceParentName) {
//		this.resourceParentName = resourceParentName;
//	}
//
//	public String getResourceCssCode() {
//		return resourceCssCode;
//	}
//
//	public void setResourceCssCode(String resourceCssCode) {
//		this.resourceCssCode = resourceCssCode;
//	}
//
//	public String getResourceAddTime() {
//		return resourceAddTime;
//	}
//
//	public void setResourceAddTime(String resourceAddTime) {
//		this.resourceAddTime = resourceAddTime;
//	}
//
//	public String getResourceAddBy() {
//		return resourceAddBy;
//	}
//
//	public void setResourceAddBy(String resourceAddBy) {
//		this.resourceAddBy = resourceAddBy;
//	}
//
//	public String getResourceAddByName() {
//		return resourceAddByName;
//	}
//
//	public void setResourceAddByName(String resourceAddByName) {
//		this.resourceAddByName = resourceAddByName;
//	}
//
//	public String getResourceDelTime() {
//		return resourceDelTime;
//	}
//
//	public void setResourceDelTime(String resourceDelTime) {
//		this.resourceDelTime = resourceDelTime;
//	}
//
//	public String getResourceDelBy() {
//		return resourceDelBy;
//	}
//
//	public void setResourceDelBy(String resourceDelBy) {
//		this.resourceDelBy = resourceDelBy;
//	}
//
//	public String getResourceDelByName() {
//		return resourceDelByName;
//	}
//
//	public void setResourceDelByName(String resourceDelByName) {
//		this.resourceDelByName = resourceDelByName;
//	}
//
//	public java.lang.Integer getResourceIsDelete() {
//		return resourceIsDelete;
//	}
//
//	public void setResourceIsDelete(java.lang.Integer resourceIsDelete) {
//		this.resourceIsDelete = resourceIsDelete;
//	}
//
//	public String getResourceEditBy() {
//		return resourceEditBy;
//	}
//
//	public void setResourceEditBy(String resourceEditBy) {
//		this.resourceEditBy = resourceEditBy;
//	}
//
//	public String getResourceEditByName() {
//		return resourceEditByName;
//	}
//
//	public void setResourceEditByName(String resourceEditByName) {
//		this.resourceEditByName = resourceEditByName;
//	}
//
//	public String getResourceEditTime() {
//		return resourceEditTime;
//	}
//
//	public void setResourceEditTime(String resourceEditTime) {
//		this.resourceEditTime = resourceEditTime;
//	}
//	
//	public String getResourceIsDeleteName() {
//		return DataDictionaryConstant.getDeleteKey(this.resourceIsDelete);
//	}
//
//	public void setResourceIsDeleteName(String resourceIsDeleteName) {
//		this.resourceIsDeleteName = resourceIsDeleteName;
//	}
//
//
//	@Override
//	public String toString() {
//		return "AuthorityResourcesDTO [resourceUuid=" + resourceUuid + ", resourceName=" + resourceName
//				+ ", resourcePlatformUuid=" + resourcePlatformUuid + ", resourcePlatformName=" + resourcePlatformName
//				+ ", resourceStatus=" + resourceStatus + ", resourceStatusName=" + resourceStatusName + ", resourceUrl="
//				+ resourceUrl + ", resourceLevel=" + resourceLevel + ", resourceOrder=" + resourceOrder
//				+ ", resourceParentUuid=" + resourceParentUuid + ", resourceParentName=" + resourceParentName
//				+ ", resourceCssCode=" + resourceCssCode + ", resourceAddTime=" + resourceAddTime + ", resourceAddBy="
//				+ resourceAddBy + ", resourceAddByName=" + resourceAddByName + ", resourceDelTime=" + resourceDelTime
//				+ ", resourceDelBy=" + resourceDelBy + ", resourceDelByName=" + resourceDelByName
//				+ ", resourceIsDelete=" + resourceIsDelete + ", resourceIsDeleteName=" + resourceIsDeleteName
//				+ ", resourceEditBy=" + resourceEditBy + ", resourceEditByName=" + resourceEditByName
//				+ ", resourceEditTime=" + resourceEditTime + "]";
//	}
//
//}
//
