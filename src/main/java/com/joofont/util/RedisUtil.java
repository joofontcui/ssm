package com.joofont.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RedisUtil {

	private static final String CACHE_KEY_PREFIX_PHONE_NUMBER = "SMS_PHONE_NUMBER_";
	private static final String CACHE_KEY_PREFIX_SCHEDULE = "SCHEDULE_";

	/**
	 * 写
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean set(RedisTemplate redisTemplate, String key, String value, long expireTimeSecond) {
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTimeSecond, TimeUnit.SECONDS);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 读，以String方式存放的值
	 * 
	 * @param key
	 * @param
	 * @return
	 */
	public static Object get(RedisTemplate redisTemplate, String key) {
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		return operations.get(key);
	}

	/**
	 * 生成接收短信的手机号在redis上的key，用于判断是否频繁发送短信
	 * 
	 * @Description:
	 * @param: @return： @throws：
	 * @author: Jordan @date: 27 Apr 2018 09:53:55
	 */
	public static String getCacheKeySmsPhoneNumber(String phoneNumber) {
		return CACHE_KEY_PREFIX_PHONE_NUMBER + phoneNumber;
	}

	/**
	 * 生成调度任务在redis上的key，用于判断任务是否已在运行
	 * 
	 * @Description:
	 * @param: @return： @throws：
	 * @author: Jordan @date: 27 Apr 2018 09:54:31
	 */
	public static String getCacheKeySchedule(String scheduleKey) {
		return CACHE_KEY_PREFIX_SCHEDULE + scheduleKey;
	}

//	/**
//	 * 把map转换成json存放。永久不过期。
//	 *
//	 * @Description:
//	 * @param: @return： @throws：
//	 * @author: Jordan @date: 17 May 2018 17:20:43
//	 */
//	public static boolean setMapList(RedisTemplate redisTemplate, String key, List<Map<String,Object>> mapList) {
//		String valueJson = JsonUtil.toJson(mapList);
//		return set(redisTemplate, key, valueJson,60*60*24*365);	//一年
//	}
//
//	/**
//	 * 把value存储为json格式的值取出来，转换成map
//	 *
//	 * @Description:
//	 * @param: @return： @throws：
//	 * @author: Jordan @date: 17 May 2018 17:21:10
//	 */
//	public static List<Map> getMapList(RedisTemplate redisTemplate, String key) {
//		Object valueObj = get(redisTemplate, key);
//		if (valueObj != null && valueObj instanceof String) {
//			return JsonUtil.json2List((String) valueObj,new TypeToken<List<Map>>() {});
//		}
//		return null;
//	}
	
	/**
	 * 删除
	 * 
	 * @Description:
	 * @param: @return： @throws：
	 * @author: Jordan @date: 17 May 2018 17:21:10
	 */
	public static boolean delete(RedisTemplate redisTemplate, String key) {
		if (redisTemplate.hasKey(key)) {
			redisTemplate.delete(key);
		}
		return true;
	}
	
	/**
	 * 读,以hash方式存放的值
	 * 
	 * @param key
	 * @param
	 * @return
	 */
	public static Map getByHash(RedisTemplate redisTemplate, String key) {
		Map entries = redisTemplate.opsForHash().entries(key);
        return entries;
	}
}
