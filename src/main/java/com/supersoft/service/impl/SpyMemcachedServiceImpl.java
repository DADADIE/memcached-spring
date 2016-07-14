package com.supersoft.service.impl;

import com.supersoft.service.MemcachedService;
import net.spy.memcached.MemcachedClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * memcached实现类
 * @author chendongzhi
 *
 */
@Service("spyMemcachedService")
public class SpyMemcachedServiceImpl implements MemcachedService {

	@Resource
	private MemcachedClient spyMemcachedClient;
	private static final int  EXPIREDTIME=60*60*24;
	
	/**
	 * 获取session中保存的用户
	 * @param sessionId
	 * @return
	 */
	@Override
	public Object getSession(String sessionId) {
		Object session=spyMemcachedClient.get("session_"+sessionId);
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
		Object session=spyMemcachedClient.get("session_"+sessionId);
		if(session!=null){
			spyMemcachedClient.delete("session_"+sessionId);
		}
		spyMemcachedClient.add("session_"+sessionId,EXPIREDTIME,obj);
	}
	/**
	 * 删除用户session
	 * @param sessionId
	 */
	@Override
	public void deleteSession(String sessionId) {
		Object session=spyMemcachedClient.get("session_"+sessionId);
		if(session!=null){
			spyMemcachedClient.delete("session_"+sessionId);
		}
	}
}
