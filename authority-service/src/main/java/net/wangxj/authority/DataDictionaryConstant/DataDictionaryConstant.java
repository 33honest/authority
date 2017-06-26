package net.wangxj.authority.DataDictionaryConstant;

/**
 * @author huoshan
 * created by 2017年5月5日 上午9:07:50
 * 
 */
import java.util.LinkedHashMap;

/**
 * 数据字典常量类，放在内存中
 * @author huoshan
 *
 */
public class DataDictionaryConstant {
	
	/*********   平台状态开始   ********/
	public static String PLATFORM_STATUS_INIT_KEY = "初始化";
	public static Integer PLATFORM_STATUS_INIT_VALUE = 3;
	
	public static String PLATFORM_STATUS_ACTIVE_KEY = "激活";
	public static Integer PLATFORM_STATUS_ACTIVE_VALUE = 1;
	
	public static String PLATFORM_STATUS_CANCEL_KEY = "注销";
	public static Integer PLATFORM_STATUS_CANCEL_VALUE = 2;
	
	public static LinkedHashMap<String, Integer> platformStatusKeyValueMap = new LinkedHashMap<>();
	public static LinkedHashMap<Integer, String> platformStatusValueKeyMap = new LinkedHashMap<>();
	
	static{
		platformStatusKeyValueMap.put(PLATFORM_STATUS_INIT_KEY, PLATFORM_STATUS_INIT_VALUE);
		platformStatusKeyValueMap.put(PLATFORM_STATUS_ACTIVE_KEY, PLATFORM_STATUS_ACTIVE_VALUE);
		platformStatusKeyValueMap.put(PLATFORM_STATUS_CANCEL_KEY, PLATFORM_STATUS_CANCEL_VALUE);
		
		platformStatusValueKeyMap.put(PLATFORM_STATUS_INIT_VALUE, PLATFORM_STATUS_INIT_KEY);
		platformStatusValueKeyMap.put(PLATFORM_STATUS_ACTIVE_VALUE, PLATFORM_STATUS_ACTIVE_KEY);
		platformStatusValueKeyMap.put(PLATFORM_STATUS_CANCEL_VALUE, PLATFORM_STATUS_CANCEL_KEY);
	}
	
	public static Integer getPlatformStatusValue(String key){
		return platformStatusKeyValueMap.get(key);
	}
	public static String getPlatformStatusKey(Integer value){
		return platformStatusValueKeyMap.get(value);
	}
	
	/*********   平台状态结束   ********/
	
	/*********    是否删除开始　　　********/
	public static String ISDELETE_YES_KEY = "已删除";
	public static Integer ISDELETE_YES_VALUE = 1;
	
	public static String ISDELETE_NO_KEY = "未删除";
	public static Integer ISDELETE_NO_VALUE = 2;
	
	public static LinkedHashMap<String, Integer> isDeleteKeyValueMap = new LinkedHashMap<>();
	public static LinkedHashMap<Integer, String> isDeleteValueKeyMap = new LinkedHashMap<>();
	
	static{
		isDeleteKeyValueMap.put(ISDELETE_YES_KEY, ISDELETE_YES_VALUE);
		isDeleteKeyValueMap.put(ISDELETE_NO_KEY, ISDELETE_NO_VALUE);
		
		isDeleteValueKeyMap.put(ISDELETE_YES_VALUE, ISDELETE_YES_KEY);
		isDeleteValueKeyMap.put(ISDELETE_NO_VALUE, ISDELETE_NO_KEY);
	}
	
	public static Integer getDeleteValue(String key){
		return isDeleteKeyValueMap.get(key);
	}
	public static String getDeleteKey(Integer value){
		return isDeleteValueKeyMap.get(value);
	}
	
	/*********    是否删除结束　　　********/
	
	/*********    用户状态开始　　　********/
	public static String USER_STATUS_NOACTIVE_KEY = "未激活";
	public static Integer USER_STATUS_NOACTIVE_VALUE = 1;
	
	public static String USER_STATUS_YESACTIVE_KEY = "已激活";
	public static Integer USER_STATUS_YESACTIVE_VALUE = 2;
	
	public static String USER_STATUS_LOCK_KEY = "已锁定";
	public static Integer USER_STATUS_LOCK_VALUE = 3;
	
	public static LinkedHashMap<String, Integer> userStatusKeyValueMap = new LinkedHashMap<>();
	public static LinkedHashMap<Integer, String> userStatusValueKeyMap = new LinkedHashMap<>();
	
	static{
		userStatusKeyValueMap.put(USER_STATUS_NOACTIVE_KEY, USER_STATUS_NOACTIVE_VALUE);
		userStatusKeyValueMap.put(USER_STATUS_YESACTIVE_KEY, USER_STATUS_YESACTIVE_VALUE);
		userStatusKeyValueMap.put(USER_STATUS_LOCK_KEY, USER_STATUS_LOCK_VALUE);
		
		userStatusValueKeyMap.put(USER_STATUS_NOACTIVE_VALUE, USER_STATUS_NOACTIVE_KEY);
		userStatusValueKeyMap.put(USER_STATUS_YESACTIVE_VALUE, USER_STATUS_YESACTIVE_KEY);
		userStatusValueKeyMap.put(USER_STATUS_LOCK_VALUE, USER_STATUS_LOCK_KEY);
	}
	
	public static String getUserStatuKey(Integer value){
		return userStatusValueKeyMap.get(value);
	}
	
	public static Integer getUserStatusValue(String key){
		return userStatusKeyValueMap.get(key);
	}
	/*********    用户状态结束　　　********/
	
	/*********    用户类型开始　　　********/
	public static String USER_TYPE_INNER_KEY = "内部用户";
	public static Integer USER_TYPE_INNER_VALUE = 1;
	
	public static String USER_TYPE_OUTER_KEY = "外部用户";
	public static Integer USER_TYPE_OUTER_VALUE = 2;
	
	public static LinkedHashMap<String, Integer> userTypeKeyValueMap = new LinkedHashMap<>();
	public static LinkedHashMap<Integer, String> userTypeValueKeyMap = new LinkedHashMap<>();
	
	static{
		userTypeKeyValueMap.put(USER_TYPE_INNER_KEY, USER_TYPE_INNER_VALUE);
		userTypeKeyValueMap.put(USER_TYPE_OUTER_KEY, USER_TYPE_OUTER_VALUE);
		
		userTypeValueKeyMap.put(USER_TYPE_INNER_VALUE, USER_TYPE_INNER_KEY);
		userTypeValueKeyMap.put(USER_TYPE_OUTER_VALUE, USER_TYPE_OUTER_KEY);
	}
	
	public static String getUserTypeKey(Integer value){
		return userTypeValueKeyMap.get(value);
	}
	public static Integer getUserTypeValue(String key){
		return userTypeKeyValueMap.get(key);
	}
	
	/*********    用户类型结束　　　********/
	
	/*********    添加用户类型开始　　　********/
	
	public static String USER_ADDTYPE_INNTER_KEY = "内部添加";
	public static Integer USER_ADDTYPE_INNER_VALUE = 1;
	
	public static String USER_ADDTYPE_SELFREGIST_KEY = "自己注册";
	public static Integer USER_ADDTYPE_SELFREGIST_VALUE = 2;
	
	public static LinkedHashMap<String, Integer> userADDTypeKeyValueMap = new LinkedHashMap<>();
	public static LinkedHashMap<Integer, String> userADDTypeValueKeyMap = new LinkedHashMap<>();
	
	static{
		userADDTypeKeyValueMap.put(USER_ADDTYPE_INNTER_KEY, USER_ADDTYPE_INNER_VALUE);
		userADDTypeKeyValueMap.put(USER_ADDTYPE_SELFREGIST_KEY, USER_ADDTYPE_SELFREGIST_VALUE);
		
		userADDTypeValueKeyMap.put(USER_ADDTYPE_INNER_VALUE, USER_ADDTYPE_INNTER_KEY);
		userADDTypeValueKeyMap.put(USER_ADDTYPE_SELFREGIST_VALUE, USER_ADDTYPE_SELFREGIST_KEY);
	}
	
	public static String getUserADDTypeKey(Integer value){
		return userADDTypeValueKeyMap.get(value);
	}
	
	public static Integer getUserADDTypeValue(String key){
		return userADDTypeKeyValueMap.get(key);
	}
	/*********    添加用户类型结束　　　********/
	
	/********     角色状态开始			*********/
	public static String ROLE_ADDED_KEY = "已添加";
	public static Integer ROLE_ADDED_VALUE = 1;
	
	public static String ROLE_ACTIVED_KEY = "已激活";
	public static Integer ROLE_ACTIVED_VALUE = 2;
	
	public static LinkedHashMap<String, Integer> roleStatusKeyValueMap = new LinkedHashMap<>();
	public static LinkedHashMap<Integer, String> roleStatusValueKeyMap = new LinkedHashMap<>();
	
	static{
		roleStatusKeyValueMap.put(ROLE_ADDED_KEY, ROLE_ADDED_VALUE);
		roleStatusKeyValueMap.put(ROLE_ACTIVED_KEY, ROLE_ACTIVED_VALUE);
		
		roleStatusValueKeyMap.put(ROLE_ADDED_VALUE,ROLE_ADDED_KEY);
		roleStatusValueKeyMap.put(ROLE_ACTIVED_VALUE, ROLE_ACTIVED_KEY);
	}
	
	public static String getRoleStatusKey(Integer value){
		return roleStatusValueKeyMap.get(value);
	}
	
	public static Integer getRoleStatusValue(String key){
		return roleStatusKeyValueMap.get(key);
	}
	
	/********     角色状态结束			*********/
	
	/***********   资源状态开始  ***************************/
	
	public static String RESOURCE_STATUS_NOACTIVE_KEY = "未激活";
	public static Integer RESOURCE_STATUS_NOACTIVE_VALUE = 3;
	
	public static String RESOURCE_STATUS_ACTIVE_KEY = "已激活";
	public static Integer RESOURCE_STATUS_ACTIVE_VALUE = 1;
	
	public static String RESOURCE_STATUS_CANCELATION_KEY = "已注销";
	public static Integer RESOURCE_STATUS_CANCELATION_VALUE = 2;
	
	public static LinkedHashMap<String, Integer> resourceStatusKeyValueMap = new LinkedHashMap<>();
	public static LinkedHashMap<Integer, String> resourceStatusValueKeyMap = new LinkedHashMap<>();
	
	static{
		resourceStatusKeyValueMap.put(RESOURCE_STATUS_NOACTIVE_KEY, RESOURCE_STATUS_NOACTIVE_VALUE);
		resourceStatusKeyValueMap.put(RESOURCE_STATUS_ACTIVE_KEY, RESOURCE_STATUS_ACTIVE_VALUE);
		resourceStatusKeyValueMap.put(RESOURCE_STATUS_CANCELATION_KEY, RESOURCE_STATUS_CANCELATION_VALUE);
		
		resourceStatusValueKeyMap.put(RESOURCE_STATUS_NOACTIVE_VALUE, RESOURCE_STATUS_NOACTIVE_KEY);
		resourceStatusValueKeyMap.put(RESOURCE_STATUS_ACTIVE_VALUE, RESOURCE_STATUS_ACTIVE_KEY);
		resourceStatusValueKeyMap.put(RESOURCE_STATUS_CANCELATION_VALUE, RESOURCE_STATUS_CANCELATION_KEY);
	}
	
	public static String getResourceStatusKey(Integer value){
		return resourceStatusValueKeyMap.get(value);
	}
	
	public static Integer getResourceStatusValue(String key){
		return resourceStatusKeyValueMap.get(key);
	}
	
	/***********   资源状态结束   **************************/
	
	/***********   资源授予类型开始   **************************/
	public static String GRANTTYPE_READ_KEY = "读";
	public static Integer GRANTTYPE_READ_VALUE = 1;
	
	public static String GRANTTYPE_WRITEREAD_KEY = "读&写";
	public static Integer GRANTTYPE_WRITEREAD_VALUE = 2;
	
	public static LinkedHashMap<String, Integer> grantTypeKeyValueMap = new LinkedHashMap<>();
	public static LinkedHashMap<Integer, String> grantTypeValueKeyMap = new LinkedHashMap<>();
	
	static{
		grantTypeKeyValueMap.put(GRANTTYPE_READ_KEY, GRANTTYPE_READ_VALUE);
		grantTypeKeyValueMap.put(GRANTTYPE_WRITEREAD_KEY, GRANTTYPE_WRITEREAD_VALUE);
		
		grantTypeValueKeyMap.put(GRANTTYPE_READ_VALUE, GRANTTYPE_READ_KEY);
		grantTypeValueKeyMap.put(GRANTTYPE_WRITEREAD_VALUE, GRANTTYPE_WRITEREAD_KEY);
	}
	
	public String getGrantTypeKey(Integer value){
		return grantTypeValueKeyMap.get(value);
	}
	
	public Integer getGrantTypeValue(String key){
		return grantTypeKeyValueMap.get(key);
	}
	
	/***********   资源授予类型结束   **************************/
}

