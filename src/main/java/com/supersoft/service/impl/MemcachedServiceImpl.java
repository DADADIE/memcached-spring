package com.supersoft.service.impl;

import java.util.Date;

import com.supersoft.service.MemcachedService;
import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;

import javax.annotation.Resource;

/**
 * memcached实现类
 * @author chendongzhi
 *
 */
@Service("memcachedService")
public class MemcachedServiceImpl implements MemcachedService {

	@Resource
	private MemCachedClient cachedClient;
	private static final int  EXPIREDTIME=60*60*24;
	
	/**
	 * 获取session中保存的用户
	 * @param sessionId
	 * @return
	 */
	@Override
	public Object getSession(String sessionId) {
		Object session=cachedClient.get("session_"+sessionId);
		if(session!=null){
			return session;
		}
		return null;
	}
	/**
	 * 设置用户session
	 * @param sessionId
	 * @param obj
	 */
	@Override
	public void setSession(String sessionId, Object obj) {
		Object session=cachedClient.get("session_"+sessionId);
		if(session!=null){
			cachedClient.delete("session_"+sessionId);
		}
		cachedClient.add("session_"+sessionId, obj,new Date(EXPIREDTIME));
	}
	/**
	 * 删除用户session
	 * @param sessionId
	 */
	@Override
	public void deleteSession(String sessionId) {
		Object session=cachedClient.get("session_"+sessionId);
		if(session!=null){
			cachedClient.delete("session_"+sessionId);
		}
	}
}
