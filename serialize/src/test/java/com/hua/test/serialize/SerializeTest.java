/**
 * 描述: 
 * SerializeTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.serialize;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.entity.HasDefaultConstructor;
import com.hua.entity.NoSerializableObject;
import com.hua.entity.SubHasSuperHas;
import com.hua.entity.SubHasSuperNo;
import com.hua.entity.SubNoSuperHas;
import com.hua.entity.SubNoSuperNo;
import com.hua.test.BaseTest;
import com.hua.util.ClassPathUtil;
import com.hua.util.DateTimeUtil;


/**
 * 描述: 序列化 - 测试
 * 
 * @author qye.zheng
 * SerializeTest
 */
public final class SerializeTest extends BaseTest {

	/**
	 * 
	 * 描述: 读取各种情形的序列化结果
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testExtendsRead() {
		try {
				/*
			 	 1) 父类无序列化，子类无序列化 --> 不能序列化
	
				 2) 父类无序列化，子类有序列化 --> 可以序列化
				 
				 3) 父类有序列化，子类无序列化 --> 可以序列化
				 
				 4) 父类有序列化，子类有序列化 --> 可以序列化
			 */
			// SubHasSuperNo SubNoSuperHas SubHasSuperHas
			
			// 2) 父类无序列化，子类有序列化 --> 可以序列化
			inputStream = ClassPathUtil.getInputStream(baseDir + "SubHasSuperNo_test.txt");
			// 对象输入 - 接口对象
			objectInput = new ObjectInputStream(inputStream);
			
			// 读取对象
			object = objectInput.readObject();
			SubHasSuperNo subHasSuperNo = (SubHasSuperNo) object;
			
			System.out.println(subHasSuperNo.toString());
			
			
			// 3) 父类有序列化，子类无序列化 --> 可以序列化
			inputStream = ClassPathUtil.getInputStream(baseDir + "SubNoSuperHas_test.txt");
			// 对象输入 - 接口对象
			objectInput = new ObjectInputStream(inputStream);
			
			// 读取对象
			object = objectInput.readObject();
			SubNoSuperHas subNoSuperHas = (SubNoSuperHas) object;
			
			System.out.println(subNoSuperHas.toString());
			
			
			// 4) 父类有序列化，子类有序列化 --> 可以序列化
			inputStream = ClassPathUtil.getInputStream(baseDir + "SubHasSuperHas_test.txt");
			// 对象输入 - 接口对象
			objectInput = new ObjectInputStream(inputStream);
			
			// 读取对象
			object = objectInput.readObject();
			SubHasSuperHas subHasSuperHas = (SubHasSuperHas) object;
			
			System.out.println(subHasSuperHas.toString());
			
			
		} catch (Exception e) {
			log.error("testExtendsRead =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 无法序列化
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSubNoSuperNo() {
		try {
			
			/*
			 	 1) 父类无序列化，子类无序列化 --> 不能序列化
	 
				 2) 父类无序列化，子类有序列化 --> 可以序列化
				 
				 3) 父类有序列化，子类无序列化 --> 可以序列化
				 
				 4) 父类有序列化，子类有序列化 --> 可以序列化
			 */
			
			/**
			 不可序列化异常:
			 java.io.NotSerializableException
			 */
			
			//  1) 父类无序列化，子类无序列化 --> 不能序列化
			SubNoSuperNo subNoSuperNo = new SubNoSuperNo();
			subNoSuperNo.setId("123456");
			subNoSuperNo.setName("name-zhagnsan");
			subNoSuperNo.setAmount(918);
			subNoSuperNo.setUsername("username-zhangsan");
			subNoSuperNo.setPassword("123456");
			subNoSuperNo.setLoginIp("192.0.5.224");
			subNoSuperNo.setLoginTime(DateTimeUtil.getTime());
			SubNoSuperNo.setDescription("描述信息");
			
			path = ClassPathUtil.getClassPath(baseDir) + "SubNoSuperNo_test.txt";
			
			// 输出到文件
			outputStream = new FileOutputStream(path);
			// 输出到控制台
			//outputStream = System.out;
			
			// 对象输出 - 接口对象
			objectOutput = new ObjectOutputStream(outputStream);
			
			// 输出 目标对象
			objectOutput.writeObject(subNoSuperNo);
			// 刷新
			objectOutput.flush();

			
		} catch (Exception e) {
			log.error("testSubNoSuperNo =====> ", e);
		} finally {
			// 关闭流
			try
			{
				if (null != objectOutput)
				{
					objectOutput.close();
				}
				if (null != outputStream)
				{
					outputStream.close();
				}
			} catch (IOException e2)
			{

			}
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSubHasSuperNo() {
		try {
			
			/*
			 	 1) 父类无序列化，子类无序列化 --> 不能序列化
	 
				 2) 父类无序列化，子类有序列化 --> 可以序列化
				 
				 3) 父类有序列化，子类无序列化 --> 可以序列化
				 
				 4) 父类有序列化，子类有序列化 --> 可以序列化
			 */
			
			// 2) 父类无序列化，子类有序列化 --> 可以序列化
			SubHasSuperNo subHasSuperNo = new SubHasSuperNo();
			subHasSuperNo.setId("123456");
			subHasSuperNo.setName("name-zhagnsan");
			subHasSuperNo.setAmount(918);
			subHasSuperNo.setUsername("username-zhangsan");
			subHasSuperNo.setPassword("123456");
			subHasSuperNo.setLoginIp("192.0.5.224");
			subHasSuperNo.setLoginTime(DateTimeUtil.getTime());
			/*
			 * 属性中包含有能序列化的对象，设置了之后 会发生异常 
			 * java.io.NotSerializableException: 
			 * com.hua.entity.NoSerializableObject

			 */
			//subHasSuperNo.setNoSerializableObject(new NoSerializableObject());
			SubHasSuperNo.setDescription("描述信息");
			
			path = ClassPathUtil.getClassPath(baseDir) + "SubHasSuperNo_test.txt";
			
			// 输出到文件
			outputStream = new FileOutputStream(path);
			// 输出到控制台
			//outputStream = System.out;
			
			// 对象输出 - 接口对象
			objectOutput = new ObjectOutputStream(outputStream);
			
			// 输出 目标对象
			objectOutput.writeObject(subHasSuperNo);
			// 刷新
			objectOutput.flush();

			
		} catch (Exception e) {
			log.error("testSubHasSuperNo =====> ", e);
		} finally {
			// 关闭流
			try
			{
				if (null != objectOutput)
				{
					objectOutput.close();
				}
				if (null != outputStream)
				{
					outputStream.close();
				}
			} catch (IOException e2)
			{

			}
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSubNoSuperHas() {
		try {
			
			/*
			 	 1) 父类无序列化，子类无序列化 --> 不能序列化
	 
				 2) 父类无序列化，子类有序列化 --> 可以序列化
				 
				 3) 父类有序列化，子类无序列化 --> 可以序列化
				 
				 4) 父类有序列化，子类有序列化 --> 可以序列化
			 */
			
			// 3) 父类有序列化，子类无序列化 --> 可以序列化
			SubNoSuperHas subNoSuperHas = new SubNoSuperHas();
			subNoSuperHas.setId("123456");
			subNoSuperHas.setName("name-zhagnsan");
			subNoSuperHas.setAmount(918);
			subNoSuperHas.setUsername("username-zhangsan");
			subNoSuperHas.setPassword("123456");
			subNoSuperHas.setLoginIp("192.0.5.224");
			subNoSuperHas.setLoginTime(DateTimeUtil.getTime());
			SubHasSuperNo.setDescription("描述信息");
			
			path = ClassPathUtil.getClassPath(baseDir) + "SubNoSuperHas_test.txt";
			
			// 输出到文件
			outputStream = new FileOutputStream(path);
			// 输出到控制台
			//outputStream = System.out;
			
			// 对象输出 - 接口对象
			objectOutput = new ObjectOutputStream(outputStream);
			
			// 输出 目标对象
			objectOutput.writeObject(subNoSuperHas);
			// 刷新
			objectOutput.flush();
			
			
			
			
		} catch (Exception e) {
			log.error("testSubNoSuperHas =====> ", e);
		} finally {
			// 关闭流
			try
			{
				if (null != objectOutput)
				{
					objectOutput.close();
				}
				if (null != outputStream)
				{
					outputStream.close();
				}
			} catch (IOException e2)
			{

			}
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSubHasSuperHas() {
		try {
			
			/*
			 	 1) 父类无序列化，子类无序列化 --> 不能序列化
	 
				 2) 父类无序列化，子类有序列化 --> 可以序列化
				 
				 3) 父类有序列化，子类无序列化 --> 可以序列化
				 
				 4) 父类有序列化，子类有序列化 --> 可以序列化
			 */
			

			// 4) 父类有序列化，子类有序列化 --> 可以序列化
			SubHasSuperHas subHasSuperHas = new SubHasSuperHas();
			subHasSuperHas.setId("123456");
			subHasSuperHas.setName("name-zhagnsan");
			subHasSuperHas.setAmount(918);
			subHasSuperHas.setUsername("username-zhangsan");
			subHasSuperHas.setPassword("123456");
			subHasSuperHas.setLoginIp("192.0.5.224");
			subHasSuperHas.setLoginTime(DateTimeUtil.getTime());
			SubHasSuperNo.setDescription("描述信息");
			
			path = ClassPathUtil.getClassPath(baseDir) + "SubHasSuperHas_test.txt";
			
			// 输出到文件
			outputStream = new FileOutputStream(path);
			// 输出到控制台
			//outputStream = System.out;
			
			// 对象输出 - 接口对象
			objectOutput = new ObjectOutputStream(outputStream);
			
			// 输出 目标对象
			objectOutput.writeObject(subHasSuperHas);
			// 刷新
			objectOutput.flush();			
			
			
		} catch (Exception e) {
			log.error("testExtends =====> ", e);
		} finally {
			// 关闭流
			try
			{
				if (null != objectOutput)
				{
					objectOutput.close();
				}
				if (null != outputStream)
				{
					outputStream.close();
				}
			} catch (IOException e2)
			{

			}
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSerialize() {
		try {
			
			
			
		} catch (Exception e) {
			log.error("testSerialize =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteObject() {
		try {
			
			HasDefaultConstructor hasDefaultConstructor = new HasDefaultConstructor();
			hasDefaultConstructor.setUsername("zhangzijian");
			hasDefaultConstructor.setPassword("1234656");
			hasDefaultConstructor.setLoginTime(DateTimeUtil.getTime());
			hasDefaultConstructor.setLoginIp("192.0.25.2");
			
			path = ClassPathUtil.getClassPath(baseDir) + "login_test.txt";
			
			// 输出到文件
			outputStream = new FileOutputStream(path);
			// 输出到控制台
			//outputStream = System.out;
			
			// 对象输出 - 接口对象
			objectOutput = new ObjectOutputStream(outputStream);
			
			// 输出 目标对象
			objectOutput.writeObject(hasDefaultConstructor);
			// 刷新
			objectOutput.flush();
			
		} catch (Exception e) {
			log.error("testWriteObject=====> ", e);
		} finally {
			// 关闭流
			try
			{
				if (null != objectOutput)
				{
					objectOutput.close();
				}
				if (null != outputStream)
				{
					outputStream.close();
				}
			} catch (IOException e2)
			{

			}
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadObject() {
		try {
			//path = ClassPathUtil.getClassPath(baseDir + "");
			inputStream = ClassPathUtil.getInputStream(baseDir + "login_test.txt");
			// 对象输入 - 接口对象
			objectInput = new ObjectInputStream(inputStream);
			
			// 读取对象
			object = objectInput.readObject();
			HasDefaultConstructor hasDefaultConstructor = (HasDefaultConstructor) object;
			
			System.out.println(hasDefaultConstructor.toString());
			
		} catch (Exception e) {
			log.error("testReadObject =====> ", e);
		} finally {
			// 关闭流
			try
			{
				if (null != objectInput)
				{
					objectInput.close();
				}
				if (null != inputStream)
				{
					inputStream.close();
				}
			} catch (IOException e2)
			{

			}
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			
			HasDefaultConstructor hasDefaultConstructor = new HasDefaultConstructor();
			hasDefaultConstructor.setUsername("zhangzijian");
			hasDefaultConstructor.setPassword("1234656");
			hasDefaultConstructor.setLoginTime(DateTimeUtil.getTime());
			hasDefaultConstructor.setLoginIp("192.0.25.2");
			
			path = ClassPathUtil.getClassPath(baseDir) + "login_test.txt";
			
			// 输出到文件
			outputStream = new FileOutputStream(path);
			// 输出到控制台
			//outputStream = System.out;
			
			// 对象输出 - 接口对象
			objectOutput = new ObjectOutputStream(outputStream);
			
			// 输出 目标对象
			objectOutput.writeObject(hasDefaultConstructor);
			// 刷新
			objectOutput.flush();
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		} finally {
			// 关闭流
			try
			{
				if (null != objectOutput)
				{
					objectOutput.close();
				}
				if (null != outputStream)
				{
					outputStream.close();
				}
			} catch (IOException e2)
			{

			}
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 普通测试方法
	 ,@Test注解 不带参数
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testNormal() {
		System.out.println("testNormal()");
	}
	
	/**
	 * 
	 * 描述: 期望发生异常-测试方法
	 ,@Test注解 异常
	 * @author qye.zheng
	 * 
	 */
	@Test(expected=NullPointerException.class)
	@SuppressWarnings("all")
	public void testException() {
		String str = null;
		System.out.println(str.length());
	}
	
	/**
	 * 
	 * 描述: 超时测试方法
	 ,@Test注解 指定运行时间 (单位 : 毫秒)
	 * @author qye.zheng
	 * 
	 */
	@Test(timeout=3000)
	public void testTimeOut() {
		System.out.println("testTimeOut()");
	}
	
	/**
	 * 
	 * 描述: 测试忽略的方法
	 * @author qye.zheng
	 * 
	 */
	@Ignore("暂时忽略的方法")
	@Test
	public void ignoreMethod() {
		System.out.println("ignoreMethod()");
	}
	
	/**
	 * 
	 * 描述: [所有测试]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@BeforeClass
	public static void beforeClass() {
		System.out.println("beforeClass()");
	}
	
	/**
	 * 
	 * 描述: [所有测试]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println("afterClass()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@Before
	public void beforeMethod() {
		System.out.println("beforeMethod()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@After
	public void afterMethod() {
		System.out.println("afterMethod()");
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
