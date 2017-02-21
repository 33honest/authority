
package net.wangxj.authority.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:43
 */
public class AuthorityUserRoleRelationPO{
	
    // 主键 	
	private String urUuid;
    // 用户uuid 	
	private String urUserUuid;
    // 角色uuid 	
	private String urRoleUuid;
    // 增加时间 yyyy-MM-dd HH:mm:ss 	
	private String urAddTime;
    // 增加人(用户主键) 	
	private String urAddBy;
    // 删除时间 yyyy-MM-dd HH:mm:ss 	
	private String urDelTime;
    // 删除人(用户主键) 	
	private String urDelBy;
    // 是否已被删除 未：0 已删除:1 	
	private java.lang.Integer urIsDelete;
	//编辑人
	private String urEditBy;
	//编辑时间
	private String urEditTime;
	
	
	public AuthorityUserRoleRelationPO(){
		super();
	}
	
	public AuthorityUserRoleRelationPO(String urUuid, String urUserUuid, String urRoleUuid, String urAddTime,
			String urAddBy, String urDelTime, String urDelBy, Integer urIsDelete, String urEditBy, String urEditTime) {
		super();
		this.urUuid = urUuid;
		this.urUserUuid = urUserUuid;
		this.urRoleUuid = urRoleUuid;
		this.urAddTime = urAddTime;
		this.urAddBy = urAddBy;
		this.urDelTime = urDelTime;
		this.urDelBy = urDelBy;
		this.urIsDelete = urIsDelete;
		this.urEditBy = urEditBy;
		this.urEditTime = urEditTime;
	}



	public void setUrUuid(String value) {
		this.urUuid = value;
	}
	
	public String getUrUuid() {
		return this.urUuid;
	}
	public void setUrUserUuid(String value) {
		this.urUserUuid = value;
	}
	
	public String getUrUserUuid() {
		return this.urUserUuid;
	}
	public void setUrRoleUuid(String value) {
		this.urRoleUuid = value;
	}
	
	public String getUrRoleUuid() {
		return this.urRoleUuid;
	}
	public void setUrAddTime(String value) {
		this.urAddTime = value;
	}
	
	public String getUrAddTime() {
		return this.urAddTime;
	}
	public void setUrAddBy(String value) {
		this.urAddBy = value;
	}
	
	public String getUrAddBy() {
		return this.urAddBy;
	}
	public void setUrDelTime(String value) {
		this.urDelTime = value;
	}
	
	public String getUrDelTime() {
		return this.urDelTime;
	}
	public void setUrDelBy(String value) {
		this.urDelBy = value;
	}
	
	public String getUrDelBy() {
		return this.urDelBy;
	}
	public void setUrIsDelete(java.lang.Integer value) {
		this.urIsDelete = value;
	}
	
	public java.lang.Integer getUrIsDelete() {
		return this.urIsDelete;
	}

	public String getUrEditBy() {
		return urEditBy;
	}

	public void setUrEditBy(String urEditBy) {
		this.urEditBy = urEditBy;
	}

	public String getUrEditTime() {
		return urEditTime;
	}

	public void setUrEditTime(String urEditTime) {
		this.urEditTime = urEditTime;
	}
	
	
}

