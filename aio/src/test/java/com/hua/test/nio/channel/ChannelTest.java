/**
 * 描述: 
 * ChannelTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.nio.channel;

//静态导入
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.hua.test.BaseTest;
import com.hua.util.ClassPathUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * ChannelTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
public final class ChannelTest extends BaseTest {

	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testChannel() {
		try {
			// 分配直接内存
			//ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			
			// 将指定的数组包装为缓冲区对象
			//ByteBuffer buffer = ByteBuffer.wrap(new byte[] {});
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
			FileChannel channel =  raf.getChannel();
		
			/**
			 *  内存映射文件IO
			 *  比常规基于流和通道的IO快的多，
			 *  实际读取或写入的部分才会映射到内存中
			 * 
			 */
			// 
			int start = 0;
			int size = 1024;
			MappedByteBuffer buffer =channel.map(FileChannel.MapMode.READ_WRITE, start, size);
			
			//buffer.flip();
			while (buffer.hasRemaining())
			{
				System.out.print(buffer.get() + " ");
			}
			/*
			 * 清空缓冲区的数据
			 */
			//buffer.clear();
			raf.close();
			System.out.println();
			
		} catch (Exception e) {
			log.error("testChannel =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testTransferFrom() {
		try {
			// 分配直接内存
			//ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			
			// 将指定的数组包装为缓冲区对象
			//ByteBuffer buffer = ByteBuffer.wrap(new byte[] {});
			
			String fromPath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			RandomAccessFile fromFile = new RandomAccessFile(fromPath, "rw");
			FileChannel fromChannel =  fromFile.getChannel();
			
			String toPath = ClassPathUtil.getClassSubpath("/file/txt/letter-char-to1.txt", true);
			RandomAccessFile toFile = new RandomAccessFile(toPath, "rw");
			FileChannel toChannel =  toFile.getChannel();
			
			/*
			 * 传输
			 * transferFrom将指定来源通道数据拷贝到当前通道
			 */
			long position = 0;
			long size = fromChannel.size();
			toChannel.transferFrom(fromChannel, position, size);
			
			toChannel.close();
			fromChannel.close();
			
			toFile.close();
			fromFile.close();
			
		} catch (Exception e) {
			log.error("testTransferFrom =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testTransferTo() {
		try {
			String fromPath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			RandomAccessFile fromFile = new RandomAccessFile(fromPath, "rw");
			FileChannel fromChannel =  fromFile.getChannel();
			
			String toPath = ClassPathUtil.getClassSubpath("/file/txt/letter-char-to2.txt", true);
			RandomAccessFile toFile = new RandomAccessFile(toPath, "rw");
			FileChannel toChannel =  toFile.getChannel();
			
			/*
			 * 传输
			 * transferTo将当前通道指定范围的数据传送到目标通道
			 */
			long position = 0;
			long size = fromChannel.size();
			fromChannel.transferTo(position, size, toChannel);
			
			toChannel.close();
			fromChannel.close();
			
			toFile.close();
			fromFile.close();
			
		} catch (Exception e) {
			log.error("testTransferTo =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testScatter() {
		try {
			/*
			 * scatter: 分散，从channel读取的数据分别写到多个Buffer
			 */
			ByteBuffer header = ByteBuffer.allocate(10);
			ByteBuffer body = ByteBuffer.allocateDirect(1024);
			ByteBuffer[] bufferArray = {header, body};
			
			//
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			FileInputStream stream = new FileInputStream(filePath);
			FileChannel channel =  stream.getChannel();
			// 从通道读取分散到多个缓冲区
			channel.read(bufferArray);
			
			stream.close();
			
		} catch (Exception e) {
			log.error("testScatter =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testGather() {
		try {
			/*
			 * gather: 聚合，将多个缓冲区的数据写入一个channel
			 */
			ByteBuffer header = ByteBuffer.allocate(10);
			ByteBuffer body = ByteBuffer.allocateDirect(1024);
			ByteBuffer[] bufferArray = {header, body};
			
			//
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			FileInputStream stream = new FileInputStream(filePath);
			FileChannel channel =  stream.getChannel();
			// 从通道读取分散到多个缓冲区
			channel.write(bufferArray);
			
			stream.close();
			
			
			
		} catch (Exception e) {
			log.error("testGather =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("testTemp")
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
	@DisplayName("testCommon")
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
	@DisplayName("testSimple")
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
	@DisplayName("testBase")
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("beforeMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@BeforeEach
	public void beforeMethod() {
		System.out.println("beforeMethod()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("afterMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@AfterEach
	public void afterMethod() {
		System.out.println("afterMethod()");
	}
	
	/**
	 * 
	 * 描述: 测试忽略的方法
	 * @author qye.zheng
	 * 
	 */
	@Disabled
	@DisplayName("ignoreMethod")
	@Test
	public void ignoreMethod() {
		System.out.println("ignoreMethod()");
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("noUse")
	@Disabled("解决ide静态导入消除问题 ")
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
		assertArrayEquals(expecteds, actuals, message);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(true, message);
		assertTrue(true, message);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(expecteds, actuals, message);
		assertNotSame(expecteds, actuals, message);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(actuals, message);
		assertNotNull(actuals, message);
		
		fail();
		fail("Not yet implemented");
		
		dynamicTest(null, null);
		
		assumeFalse(false);
		assumeTrue(true);
		assumingThat(true, null);
	}

}
