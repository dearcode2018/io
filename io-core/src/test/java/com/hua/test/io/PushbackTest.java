/**
 * 描述: 
 * PushbackTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.io;

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

import java.io.PushbackInputStream;
import java.io.PushbackReader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.constant.Constant;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * PushbackTest
 */
public final class PushbackTest extends BaseTest {

	/*
	 
	 退回输入流
	 调用 unread()方法，系统会将指定数组推回 pushback 缓冲区，
	 而每次调用read方法时，先从pushback缓冲区读取数据，
	 只有完全读取了pushback缓冲区的数据，但是还没有装满
	 read所需的数据时，才会从原输入流中读取.
	 
	 推回缓冲区 默认大小是1个字符或1个字节
	 
	 */
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPushbackInputStream() {
		try {
			/*
			 * 
			 */
			byteArray = new byte[16];
			pushbackInputStream = new PushbackInputStream(inputStream);
			
			actualCount = pushbackInputStream.read(byteArray);
			
			String str = new String(byteArray, 0, actualCount, Constant.CHART_SET_UTF_8);
			
			log.info("testPushbackInputStream =====> str = " + str);
			
			System.out.println("====================");
			
			pushbackInputStream.unread("p".getBytes(Constant.CHART_SET_UTF_8));
			
			actualCount = pushbackInputStream.read(byteArray);
			
			str = new String(byteArray, 0, actualCount, Constant.CHART_SET_UTF_8);
			
			log.info("testPushbackInputStream =====> str = " + str);
			
		} catch (Exception e) {
			log.error("testPushbackInputStream =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPushbackReader() {
		try {
			pushbackReader = new PushbackReader(inputStreamReader, 2);
			// 
			char ch = (char) pushbackReader.read();
			log.info("testPushbackReader =====> ch = " + ch);
			
			/*
			 将数据放入 推回缓冲区(数量受缓冲区大小限制，超出会抛异常
			  java.io.IOException: Pushback buffer overflow)
			 */
			pushbackReader.unread(99);
			pushbackReader.unread(100);
			
			// 到 推回缓冲区 读取数据
			ch = (char) pushbackReader.read();
			log.info("testPushbackReader =====> ch = " + ch);
			
			ch = (char) pushbackReader.read();
			log.info("testPushbackReader =====> ch = " + ch);
			
			// 推回缓冲区数据读取完，到原始输入流中读取(继续往下读取)
			ch = (char) pushbackReader.read();
			log.info("testPushbackReader =====> ch = " + ch);
			
		} catch (Exception e) {
			log.error("testPushbackReader =====> ", e);
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
