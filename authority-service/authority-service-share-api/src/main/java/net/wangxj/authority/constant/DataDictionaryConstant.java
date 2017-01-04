package net.wangxj.authority.constant;

import java.util.LinkedHashMap;

/**
 * 数据字典常量类，放在内存中
 * @author huoshan
 *
 */
public class DataDictionaryConstant {
	
	/*********   平台状态开始   ********/
	public static String PLATFORM_STATUS_INIT_KEY = "初始化";
	public static Integer PLATFORM_STATUS_INIT_VALUE = 0;
	
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
	public static Integer ISDELETE_NO_VALUE = 0;
	
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
}
