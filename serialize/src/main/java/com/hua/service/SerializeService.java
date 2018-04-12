/**
 * SerializeService.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.service;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * SerializeService
 * 描述: 序列化 - 服务
 * @author  qye.zheng
 */
public interface SerializeService<T> extends CoreService
{

	/**
	 * 
	 * 描述: 将对象序列化输出 
	 * @author  qye.zheng
	 * @param target
	 * @param outputStream
	 * @return
	 */
	public boolean writeObject(final T target, final OutputStream outputStream);
	
	/**
	 * 
	 * 描述: 读取序列化对象
	 * @author  qye.zheng
	 * @param clazz
	 * @param inputStream
	 * @return
	 */
	public T readObject(final InputStream inputStream);
	
}
