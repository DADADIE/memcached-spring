package com.supersoft.service;

public interface MemcachedService {

		/**
		 * 获取session中保存的用户
		 * @param sessionId
		 * @return
		 */
		public Object getSession(String sessionId);
		/**
		 * 设置用户session
		 * @param sessionId
		 * @param obj
		 */
		public void setSession(String sessionId, Object obj);
		/**
		 * 删除用户session
		 * @param sessionId
		 */
		public void deleteSession(String sessionId);
}
