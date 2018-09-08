/**
 * 描述: 
 * ByteStreamTest.java
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
 * 描述: 一个字节的字节流测试
 * 
 * @author qye.zheng
 * ByteStreamTest
 */
public final class ByteStreamTest extends BaseTest {

	/**
	 
	 文件存储:以UTF-8编码存储
	 存储英文字符用1个字节，汉字用3个字节存储.
	 
	 unicode编码使用2个字节存储汉字
	 
	 
	 */
	
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
			每次读取一个字节
	  		读取字节，然后将8为字节，扩展为32位int，
			所以读取后的字节值是 [0, 255] 范围
			read() 方法值int，若强转为8位字节，字面值
			就会发生变化
			 */
			int in = 0;
			while (Constant.NEGATIVE_ONE != (in = inputStream.read()))
			{
				bt = (byte) in;
				System.out.println("32位int值 = " + in + ", " + "8位byte值 = " + bt);
			}
			
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
	public void testReadOneParam() {
		try {
			byte[] temp = null;
			// 读取多个字节
			while (Constant.NEGATIVE_ONE != (actualCount = inputStream.read(byteArray)))
			{
				System.out.println(actualCount);
				// 拷贝实际读取的字节，然后输出
				temp = Arrays.copyOf(byteArray, actualCount);
				// 输出实际读取到的字节
				System.out.println(Arrays.toString(temp));
			}
			
		} catch (Exception e) {
			log.error("testReadOneParam =====> ", e);
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
			byte[] temp = null;
			offset = 2;
			// 每次循环读取的字节数
			int len = 200;
			// 往回跳转，继续用其他方式读取流
			while (Constant.NEGATIVE_ONE != (actualCount = inputStream.read(byteArray, offset, len)))
			{
				System.out.println(actualCount);
				// 拷贝实际读取的字节，然后输出
				temp = Arrays.copyOf(byteArray, actualCount);
				// 输出实际读取到的字节
				System.out.println(Arrays.toString(temp));
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
	public void testWriteInt() {
		try {
			// [0, 255]
			int in = 0;
			/*
			 
			 虽然参数的是int 32位 4个字节，但是write方法实际
			 是将int 转成 byte然后再输出到目的地
			 
			 */
			outputStream.write(in);
			
			/*
			 在循环体外面，输出结束后，进行最后的刷新，
			 确保目标数据完整输出，而不是没有输出或者
			 只是部分输出.
			 */
			outputStream.flush();			
			
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
	public void testWriteByteArray() {
		try {
			byte[] temp = null;
			// 边读边写
			while (Constant.NEGATIVE_ONE != (actualCount = inputStream.read(byteArray)))
			{
				// 只是为了测试 write 字节数组 方法才这样写
				temp = Arrays.copyOf(byteArray, actualCount);
				/*
				 注意最后一次buffer从输入流中读取到的数据长度
				 会 <= buffer数组长度。所以write应该注意
				 */
				outputStream.write(temp);
			}
			
			/*
			 在循环体外面，输出结束后，进行最后的刷新，
			 确保目标数据完整输出，而不是没有输出或者
			 只是部分输出.
			 */
			outputStream.flush();			
						
			
		} catch (Exception e) {
			log.error("testWriteByteArray =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteThreeParam() {
		try {
			// byteArray 存放读取数据的开始小标
			offset = 2;
			// 每次循环读取的字节数
			int len = 200;
			
			// 边读边写
			while (Constant.NEGATIVE_ONE != (actualCount = inputStream.read(byteArray, offset, len)))
			{
				/*
				 注意最后一次buffer从输入流中读取到的数据长度
				 会 <= buffer数组长度。所以write应该注意
				 */
				outputStream.write(byteArray, offset, actualCount);
			}
			
			/*
			 在循环体外面，输出结束后，进行最后的刷新，
			 确保目标数据完整输出，而不是没有输出或者
			 只是部分输出.
			 */
			outputStream.flush();			
						
			
		} catch (Exception e) {
			log.error("testWriteThreeParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testInputStream() {
		try {
			log.info("testInputStream =====> size(byte number) = " + inputStream.available());
			
			/*
			  读取字节，然后将8为字节，扩展为32位int，
			  所以读取后的字节值是 [0, 255] 范围
			  read() 方法值int，若强转为8位字节，字面值
			  就会发生变化
			 */
			
			// 读取单个字节，不标记位置
			log.info("testInputStream =====> " + inputStream.read());
			log.info("testInputStream =====> " + inputStream.read());
			// 跳读到最后，负数可以往前跳转
			inputStream.skip(-2L);
			//inputStream.skip(842L);
			log.info("testInputStream =====> " + inputStream.read());
			// 输入流对象是否持标识功能
			log.info("testInputStream =====> supportMark = " + inputStream.markSupported());
			
		} catch (Exception e) {
			log.error("testInputStream =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 字节流 - 读取字节
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadByte() {
		try {
			log.info("testReadByte =====> size(byte number) = " + inputStream.available());
			// 流数据大小(字节数)，一会可以往回跳
			size = inputStream.available();
			byte[] temp = null;
			// 读取多个字节
			while (Constant.NEGATIVE_ONE != (actualCount = inputStream.read(byteArray)))
			{
				System.out.println(actualCount);
				// 拷贝实际读取的字节，然后输出
				temp = Arrays.copyOf(byteArray, actualCount);
				// 输出实际读取到的字节
				System.out.println(Arrays.toString(temp));
			}
			System.out.println("========================");
			// 往回跳转，返回流的最开始字节位置 (注意用负数进行回跳)
			inputStream.skip(-size);
			offset = 2;
			// 每次循环读取的字节数
			int len = 200;
			// 往回跳转，继续用其他方式读取流
			while (Constant.NEGATIVE_ONE != (actualCount = inputStream.read(byteArray, offset, len)))
			{
				System.out.println(actualCount);
				// 拷贝实际读取的字节，然后输出
				temp = Arrays.copyOf(byteArray, actualCount);
				// 输出实际读取到的字节
				System.out.println(Arrays.toString(temp));
			}
			
		} catch (Exception e) {
			log.error("testReadByte =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testOutputStream() {
		try {
			// 流数据大小(字节数)，一会可以往回跳
			size = inputStream.available();
			// 输出1个字节
			bt = (byte) inputStream.read();
			log.info("testOutputStream =====> bt = " + bt);
			outputStream.write(bt);
			
			bt = (byte) inputStream.read();
			log.info("testOutputStream =====> bt = " + bt);
			outputStream.write(bt);
			
			// 前面读取了2个字节，往前跳
			inputStream.skip(-2L);
			// 边读边写
			while (Constant.NEGATIVE_ONE != (actualCount = inputStream.read(byteArray)))
			{
				outputStream.write(byteArray);
			}
			
			// 往回跳转，返回流的最开始字节位置 (注意用负数进行回跳)
			inputStream.skip(-size);
			
			offset = 2;
			// 每次循环读取的字节数
			int len = 200;
			
			// 边读边写
			while (Constant.NEGATIVE_ONE != (actualCount = inputStream.read(byteArray, offset, len)))
			{
				outputStream.write(byteArray, offset, actualCount);
			}
			
			/*
			 在循环体外面，输出结束后，进行最后的刷新，
			 确保目标数据完整输出，而不是没有输出或者
			 只是部分输出.
			 */
			outputStream.flush();
			
		} catch (Exception e) {
			log.error("testOutputStream =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteByte() {
		try {
			// 存储英文字符用1个字节，中文用3个字节存储.
			byte[] b = new byte[4];
			inputStream.read(b);
			outputStream.write(b);
			
			outputStream.flush();
		} catch (Exception e) {
			log.error("testWriteByte =====> ", e);
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
