package com.supersoft.service.impl;

import com.supersoft.service.MemcachedService;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeoutException;

/**
 * memcached实现类
 * @author chendongzhi
 *
 */
@Service("xMemcachedService")
public class XMemcachedServiceImpl implements MemcachedService {

	@Resource
	private MemcachedClient xMemcachedClient;
	private static final int  EXPIREDTIME=60*60*24;
	
	/**
	 * 获取session中保存的用户
	 * @param sessionId
	 * @return
	 */
	@Override
	public Object getSession(String sessionId) {
		Object session= null;
		try {
			session = xMemcachedClient.get("session_"+sessionId);
		}catch (Exception e){
			e.printStackTrace();
		}
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
		try {
			Object session=xMemcachedClient.get("session_"+sessionId);
			if(session!=null){
				xMemcachedClient.delete("session_"+sessionId);
			}
			xMemcachedClient.add("session_"+sessionId,EXPIREDTIME,obj);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 删除用户session
	 * @param sessionId
	 */
	@Override
	public void deleteSession(String sessionId) {
		try {
			Object session=xMemcachedClient.get("session_"+sessionId);
			if(session!=null){
				xMemcachedClient.delete("session_"+sessionId);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
