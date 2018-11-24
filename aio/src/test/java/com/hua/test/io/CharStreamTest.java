/**
 * 描述: 
 * CharStreamTest.java
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

import java.io.Reader;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.constant.Constant;
import com.hua.test.BaseTest;


/**
 * 描述: 2个字节的字符流测试
 * 
 * @author qye.zheng
 * CharStreamTest
 */
public final class CharStreamTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReader() {
		try {
			
			/*
			 
			 */
			// 是否支持标记
			log.info("testReader =====> 是否支持标记 = " + reader.markSupported());
			
			// 只是输出对象信息，没有输出流的内容
			//System.out.println(reader.toString());
			
			
			/*
			 读取单个字符，返回int值 
			 unicode中一个字符 16位，值范围 [0, 65535]
			 */
			log.info("testReader =====> " + reader.read());
			
			// 跳转，有些不支持负数的方式
			//reader.skip(-1L);
			
		} catch (Exception e) {
			log.error("testReader =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadNoParam() {
		try {
			/*
			 读取单个字符，返回int值 
			 unicode中一个字符 16位，值范围 [0, 65535]
			 */
			int in = reader.read();
			log.info("testReadNoParam =====> in = " + in);
			
		} catch (Exception e) {
			log.error("testReadNoParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadCharArray() {
		try {
			char[] temp = null;
			while (Constant.NEGATIVE_ONE != (actualCount = reader.read(charArray)))
			{
				// 输出实际读取到的字符数组
				temp = Arrays.copyOf(charArray, actualCount);
				// 输出字符数组
				result = Arrays.toString(temp);
				System.out.println(result);
			}
			
		} catch (Exception e) {
			log.error("testReadCharArray =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadCharBuffer() {
		try {
			/*
			 读取构造为java.nio.CharBuffer对象
			 使用情况暂不明确!!!
			 */
			while (Constant.NEGATIVE_ONE != (actualCount = reader.read(charBuffer)))
			{
				
				System.out.println(charBuffer.length());
				
			}
			
			
		} catch (Exception e) {
			log.error("testReadCharBuffer =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadThreeParam() {
		try {
			char[] temp = null;
			int offset = 2;
			int len = 20;
			int from = offset;
			// 不包括
			int to = len + offset;
			while (Constant.NEGATIVE_ONE != (actualCount = 
					reader.read(charArray, offset, len)))
			{
				temp = Arrays.copyOfRange(charArray, from, to);
				result = Arrays.toString(temp);
				System.out.println(result);
			}
			
		} catch (Exception e) {
			log.error("testReadThreeParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriter() {
		try {
			
			
		} catch (Exception e) {
			log.error("testWriter =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriterAppend() {
		try {
			
			/*
		 		writer.append() 方法中调用了相应的write方法
		 		来输出目标
			 */
			writer.append('a');
			writer.append(", hello");
			writer.append("，中文世界");
			
			/*
			  提交输出器缓存的内容
			 */
			writer.flush();
			
		} catch (Exception e) {
			log.error("testWriterAppend =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteInt() {
		try {
			/*
			  int 字符码 [0, 65535]
			  
			 */
			// 获取指定unicode字符的编码(编码点) [0, 65535]
			int cP = "中".codePointAt(0);
			int codePoint = 97;
			writer.write(codePoint);
			writer.write(cP);
			/*
			  提交输出器缓存的内容
			 */
			writer.flush();
			
		} catch (Exception e) {
			log.error("testWriteInt =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteCharArrayOne() {
		try {
			charArray = new char[] {'d', 'a', 'n', 'f', '中'};
			writer.write(charArray);
			
			/*
			  提交输出器缓存的内容
			 */
			writer.flush();
			
		} catch (Exception e) {
			log.error("testWriteCharArrayOne =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteCharArrayTwo() {
		try {
			charArray = new char[] {'d', 'a', 'n', 'f', '中'};
			int offset = 1;
			int len = 3;
			writer.write(charArray, offset, len);
			
			/*
			  提交输出器缓存的内容
			 */
			writer.flush();
			
		} catch (Exception e) {
			log.error("testWriteCharArrayTwo =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteStringOne() {
		try {
			String str = "中-hakie,.;";
			
			writer.write(str);
			
			/*
			  提交输出器缓存的内容
			 */
			writer.flush();
		} catch (Exception e) {
			log.error("testWriteStringOne =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteStringTwo() {
		try {
			String str = "中-hakie,.;";
			int offset = 1;
			int len = 3;
			writer.write(str, offset, len);
			
			/*
			  提交输出器缓存的内容
			 */
			writer.flush();
		} catch (Exception e) {
			log.error("testWriteStringTwo =====> ", e);
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
			Reader r = null;
			
			String str = "中";
			
			
			log.info("testTemp =====> " + str.codePointAt(0));
			
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
