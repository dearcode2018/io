/**
 * package-info.java
 * @author  qye.zheng
 * 	version 1.0
 */
/**
 * package-info
 * 描述: 实现 java.io.Externalizable 接口，自定义序列化/反序列化
 * @author  qye.zheng
 */
package com.hua.entity.ext;

/*

实现 java.io.Externalizable的对象，在反序列化的时候，
调用类的默认构造方法，因此，要求必须提供一个默认
构造方法，否则 java.io.InvalidException: no valid constructor

 
 
 */
