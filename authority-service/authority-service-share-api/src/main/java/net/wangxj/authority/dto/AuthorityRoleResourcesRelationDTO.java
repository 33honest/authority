
package net.wangxj.authority.dto;

import java.io.Serializable;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:41
 */

public class AuthorityRoleResourcesRelationDTO implements Serializable{ 
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 2020925455710026039L;
	// 角色uuid 	
	private String rrRoleUuid;
    // 资源uuid 	
	private String rrResourceUuid;
    // 主键 	
	private String rrUuid;
    // 添加时间 yyyy-MM-dd HH:mm:ss 	
	private String rrAddTime;
    // 添加人uuid 	
	private String rrAddBy;
    // 删除时间 yyyy:mm:dd HH:mm:ss 	
	private String rrDelTime;
    // 删除人uuid 	
	private String rrDelBy;
    // 是否已被删除: 0:未 1：已被删除 	
	private java.lang.Integer rrIsDelete;
    // 授予类型:   0： 读，写 1： 读 	
	private java.lang.Integer rrGrantType;
	
	public AuthorityRoleResourcesRelationDTO(){
		super();
	}
	
	public AuthorityRoleResourcesRelationDTO(String rrRoleUuid,String rrResourceUuid,String rrUuid,String rrAddTime,String rrAddBy,String rrDelTime,String rrDelBy,java.lang.Integer rrIsDelete,java.lang.Integer rrGrantType){
			this.rrRoleUuid = rrRoleUuid;
			this.rrResourceUuid = rrResourceUuid;
			this.rrUuid = rrUuid;
			this.rrAddTime = rrAddTime;
			this.rrAddBy = rrAddBy;
			this.rrDelTime = rrDelTime;
			this.rrDelBy = rrDelBy;
			this.rrIsDelete = rrIsDelete;
			this.rrGrantType = rrGrantType;
	}

	public void setRrRoleUuid(String value) {
		this.rrRoleUuid = value;
	}
	
	public String getRrRoleUuid() {
		return this.rrRoleUuid;
	}
	public void setRrResourceUuid(String value) {
		this.rrResourceUuid = value;
	}
	
	public String getRrResourceUuid() {
		return this.rrResourceUuid;
	}
	public void setRrUuid(String value) {
		this.rrUuid = value;
	}
	
	public String getRrUuid() {
		return this.rrUuid;
	}
	public void setRrAddTime(String value) {
		this.rrAddTime = value;
	}
	
	public String getRrAddTime() {
		return this.rrAddTime;
	}
	public void setRrAddBy(String value) {
		this.rrAddBy = value;
	}
	
	public String getRrAddBy() {
		return this.rrAddBy;
	}
	public void setRrDelTime(String value) {
		this.rrDelTime = value;
	}
	
	public String getRrDelTime() {
		return this.rrDelTime;
	}
	public void setRrDelBy(String value) {
		this.rrDelBy = value;
	}
	
	public String getRrDelBy() {
		return this.rrDelBy;
	}
	public void setRrIsDelete(java.lang.Integer value) {
		this.rrIsDelete = value;
	}
	
	public java.lang.Integer getRrIsDelete() {
		return this.rrIsDelete;
	}
	public void setRrGrantType(java.lang.Integer value) {
		this.rrGrantType = value;
	}
	
	public java.lang.Integer getRrGrantType() {
		return this.rrGrantType;
	}
	
	public String toString(){
		return "AuthorityRoleResourcesRelation [rrRoleUuid="+rrRoleUuid+", rrResourceUuid="+rrResourceUuid+", rrUuid="+rrUuid+", rrAddTime="+rrAddTime+", rrAddBy="+rrAddBy+", rrDelTime="+rrDelTime+", rrDelBy="+rrDelBy+", rrIsDelete="+rrIsDelete+", rrGrantType="+rrGrantType+"]";
	}
	
}

