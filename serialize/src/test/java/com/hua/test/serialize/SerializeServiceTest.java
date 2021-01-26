/**
 * 描述: 
 * SerializeServiceTest.java
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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.entity.HasDefaultConstructor;
import com.hua.entity.ext.ExternalEntity;
import com.hua.service.SerializeService;
import com.hua.service.impl.SerializeServiceImpl;
import com.hua.test.BaseTest;
import com.hua.util.ClassPathUtil;
import com.hua.util.DateTimeUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * SerializeServiceTest
 */
public final class SerializeServiceTest extends BaseTest {
	
	/**
	 * 
	 * 描述: 实现 java.io.Externalizable的方式
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testExternalWrite() {
		try {
			SerializeService<ExternalEntity> serializeService =
					new SerializeServiceImpl<ExternalEntity>();
			path = ClassPathUtil.getClassPath(baseDir) + "ExternalEntity_test.txt";
			
			// 输出到文件
			outputStream = new FileOutputStream(path);
			ExternalEntity externalEntity = new ExternalEntity();
			externalEntity.setUsername("username-zhangsan");
			externalEntity.setPassword("123456");
			externalEntity.setLoginIp("192.0.5.224");
			
			// 
			serializeService.writeObject(externalEntity, outputStream);
		} catch (Exception e) {
			log.error("testExternalWrite =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 实现 java.io.Externalizable的方式
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testExternalRead() {
		try {
			SerializeService<ExternalEntity> serializeService =
					new SerializeServiceImpl<ExternalEntity>();
			path = ClassPathUtil.getClassPath(baseDir) + "ExternalEntity_test.txt";
			
			inputStream = ClassPathUtil.getInputStream(baseDir + "ExternalEntity_test.txt");
			
			ExternalEntity externalEntity = serializeService.readObject(inputStream);
			System.out.println(externalEntity.toString());
		} catch (Exception e) {
			log.error("testExternalRead =====> ", e);
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
			SerializeService<HasDefaultConstructor> serializeService =
					new SerializeServiceImpl<HasDefaultConstructor>();
			HasDefaultConstructor hasDefaultConstructor = new HasDefaultConstructor();
			hasDefaultConstructor.setUsername("zhangzijian");
			hasDefaultConstructor.setPassword("1234656");
			hasDefaultConstructor.setLoginTime(DateTimeUtil.getTime());
			hasDefaultConstructor.setLoginIp("192.0.25.2");
			
			path = ClassPathUtil.getClassPath(baseDir) + "HasDefaultConstructor_test.txt";
			
			// 输出到文件
			outputStream = new FileOutputStream(path);
			
			serializeService.writeObject(hasDefaultConstructor, outputStream);
			
		} catch (Exception e) {
			log.error("testWriteObject =====> ", e);
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
			SerializeService<HasDefaultConstructor> serializeService =
					new SerializeServiceImpl<HasDefaultConstructor>();
			HasDefaultConstructor hasDefaultConstructor = new HasDefaultConstructor();
			hasDefaultConstructor.setUsername("zhangzijian");
			hasDefaultConstructor.setPassword("1234656");
			hasDefaultConstructor.setLoginTime(DateTimeUtil.getTime());
			hasDefaultConstructor.setLoginIp("192.0.25.2");
			
			path = ClassPathUtil.getClassPath(baseDir) + "HasDefaultConstructor_test.txt";
			
			inputStream = ClassPathUtil.getInputStream(baseDir + "HasDefaultConstructor_test.txt");
			
			hasDefaultConstructor = (HasDefaultConstructor) serializeService.readObject(inputStream);
			
			System.out.println(hasDefaultConstructor.toString());
		} catch (Exception e) {
			log.error("testReadObject =====> ", e);
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
			

			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
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
			Object myObj = new String[]{"one", "two", "three"};{
				        for (String s : (String[])myObj) System.out.print(s + ".");
				   }
			
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
