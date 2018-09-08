/**
 * 描述: 
 * BufferedTest.java
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

import java.io.BufferedInputStream;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.constant.Constant;
import com.hua.test.BaseTest;
import com.hua.util.StringUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * BufferedTest
 */
public final class BufferedTest extends BaseTest {

	/*
	 Buffered 使用了字节、字符缓存的方式来操作输入/输出流，
	 输出流覆盖了flush()，此时调用flush 可以起到真正的提交
	 缓存的作用
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBuffered() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBuffered =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBufferedInputStream() {
		try {
			
			/*
			 先执行标识，然后再执行重置，不然抛异常
			 java.io.IOException: Resetting to invalid mark
			 */
			
			log.info("testBufferedInputStream =====> " + bufferedInputStream.available());
			
			// Buffered 的方式支持 标识
			log.info("testBufferedInputStream =====> " + bufferedInputStream.markSupported());
			
			//
			log.info("testBufferedInputStream =====> " + bufferedInputStream.read());
			/*
			 先标识 标识最小有效标识范围，超出此buffer的缓存范围或者此范围，
			 则调用reset 方法会抛异常，无法重置
			 可以通过设置buffer的缓存大小来进行测试该功能
			 bufferedInputStream = new BufferedInputStream(inputStream, 2);
			 
			1)  如果BufferedInputStream类的缓冲区大小大于readlimit，在mark以后只有读取超过缓冲区大小的数据，mark标记才会失效。
			 
			 2) 如果readlimit大于BufferedInputStream类缓冲区的大小，缓冲区会被扩大，那mark后最多就可以读readlimit字节。
			 
			 */
			bufferedInputStream.mark(1);
			log.info("testBufferedInputStream =====> " + bufferedInputStream.read());
			log.info("testBufferedInputStream =====> " + bufferedInputStream.read());
			log.info("testBufferedInputStream =====> " + bufferedInputStream.read());
			// 重置
			bufferedInputStream.reset();
		} catch (Exception e) {
			log.error("testBufferedInputStream =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBufferedInputStreamMark() {
		try {
			//
			log.info("testBufferedInputStreamMark =====> " + bufferedInputStream.read());
			/*
			 先标识 标识最小有效标识范围，超出此buffer的缓存范围或者此范围，
			 则调用reset 方法会抛异常，无法重置
			 可以通过设置buffer的缓存大小来进行测试该功能
			 bufferedInputStream = new BufferedInputStream(inputStream, 2);
			 buffer默认的缓存大小是8192个byte
			 因此，在使用默认的buffer缓存大小的情况下，一般无需太关注markLimit
			 的大小，可以随意设置一个值即可.
			 */
			int markLimt = 1;
			bufferedInputStream.mark(markLimt);
			// 标识此处，然后后面某个地方来进行重置，然后再读取，值和此时的值相同
			log.info("testBufferedInputStreamMark =====> markPositionValue =  " + bufferedInputStream.read());
			log.info("testBufferedInputStreamMark =====> " + bufferedInputStream.read());
			log.info("testBufferedInputStreamMark =====> " + bufferedInputStream.read());
			// 重置
			bufferedInputStream.reset();
			// 重置之后再次读取
			log.info("testBufferedInputStreamMark =====> resetMarkPositionValue = " + bufferedInputStream.read());
		} catch (Exception e) {
			log.error("testBufferedInputStreamMark =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBufferedOutputStream() {
		try {
			
			int codePoint = 97;
			bufferedOutputStream.write(codePoint);
			
			byteArray = "abncd".getBytes(Constant.CHART_SET_UTF_8);
			bufferedOutputStream.write(byteArray);
			
			/*
			 含有中文字符的时候，拆分为字节格式，
			 则可能会导致读取的时候，由于offset 和len参数
			 问题而导致字节读取不完全，从而导致出现乱码
			 */
			
			int offset = 1;
			int len = 3;
			
			bufferedOutputStream.write(byteArray, offset, len);
			
			bufferedOutputStream.flush();
			
		} catch (Exception e) {
			log.error("testBufferedOutputStream =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBufferedReader() {
		try {
			
			log.info("testBufferedReader =====> "+ bufferedReader.read());
			
			/*
			 说明: 
			 */
			StringBuilder builder = StringUtil.getBuilder();
			bufferedReader.mark(56678);
			while (Constant.NEGATIVE_ONE != (actualCount = bufferedReader.read(charArray)))
			{
				builder.append(charArray, 0, actualCount);
			}
			System.out.println(builder.toString());
			
			System.out.println("========================");
			
			charArray = new char[25];
			bufferedReader.reset();
			int offset = 1;
			int len = 3;
			builder = StringUtil.getBuilder();
			while (Constant.NEGATIVE_ONE != (actualCount = bufferedReader.read(charArray, offset, len)))
			{
				builder.append(charArray, offset, actualCount);
			}
			System.out.println(builder.toString());
			
		} catch (Exception e) {
			log.error("testBufferedReader =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBufferedWriter() {
		try {
			
			writer.write("abc中");
			
			writer.flush();
			
		} catch (Exception e) {
			log.error("testBufferedWriter =====> ", e);
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
