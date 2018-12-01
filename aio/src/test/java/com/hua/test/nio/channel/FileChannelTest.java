/**
 * 描述: 
 * FileChannelTest.java
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
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

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
 * FileChannelTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
public final class FileChannelTest extends BaseTest {

	
	/**
	 * FileChannel运行在阻塞模式下
	 * 
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testFileChannel() {
		try {
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			FileInputStream stream = new FileInputStream(filePath);
			FileChannel channel =  stream.getChannel();
			//CharBuffer buffer = CharBuffer.allocate(1024);
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			//ByteBuffer buffer = ByteBuffer.allocate(10);
			channel.read(buffer);
			buffer.flip();
			// 底层数组的大小
			System.out.println(buffer.array().length);
			System.out.println("capacity = " + buffer.capacity() + ", position = " + buffer.position() + ", limit = " + buffer.limit());
			while (buffer.hasRemaining())
			{
				System.out.print((char) buffer.get());
			}
			System.out.println();
			stream.close();
			
			System.out.println("capacity = " + buffer.capacity() + ", position = " + buffer.position() + ", limit = " + buffer.limit());
			
		} catch (Exception e) {
			log.error("testFileChannel =====> ", e);
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
	public void testForce() {
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
			ByteBuffer buffer = ByteBuffer.allocateDirect(64);
			
			buffer.putChar('F');
			buffer.flip();
			channel.write(buffer);
			
			/*
			 * force 将数据即时写到磁盘上，true表示同时将
			 * 文件元数据(权限信息等)写到磁盘上
			 */
			channel.force(true);
			
			channel.close();
			raf.close();
			
		} catch (Exception e) {
			log.error("testForce =====> ", e);
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
	public void testTruncate() {
		try {
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			FileInputStream stream = new FileInputStream(filePath);
			FileChannel channel =  stream.getChannel();
			//CharBuffer buffer = CharBuffer.allocate(1024);
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			//ByteBuffer buffer = ByteBuffer.allocate(10);
			
			// 设置渠道的当前位置
			//channel.position(30);
			
			channel = channel.truncate(2);
			channel.force(false);
			
			channel.read(buffer);
			buffer.flip();
			
			while (buffer.hasRemaining())
			{
				System.out.print((char) buffer.get());
			}
			System.out.println();
			
			channel.close();
			stream.close();
		} catch (Exception e) {
			log.error("testTruncate =====> ", e);
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
	public void testPosition() {
		try {
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			FileInputStream stream = new FileInputStream(filePath);
			FileChannel channel =  stream.getChannel();
			//CharBuffer buffer = CharBuffer.allocate(1024);
			ByteBuffer buffer = ByteBuffer.allocate(30);
			//ByteBuffer buffer = ByteBuffer.allocate(10);
			
			// 设置渠道的当前位置
			channel.position(30);
			
			channel.read(buffer);
			buffer.flip();
			
			
			while (buffer.hasRemaining())
			{
				System.out.print((char) buffer.get());
			}
			System.out.println();
			
			channel.close();
			stream.close();
		} catch (Exception e) {
			log.error("testPosition =====> ", e);
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
	public void testRead() {
		try {
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			FileInputStream stream = new FileInputStream(filePath);
			FileChannel channel =  stream.getChannel();
			//CharBuffer buffer = CharBuffer.allocate(1024);
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			//ByteBuffer buffer = ByteBuffer.allocate(10);
			
			/*
			 * read方法: 返回值表示有多少字节读到了Buffer中，-1表示到了文件末尾
			 */
			int bytesRead = channel.read(buffer);
			while (-1 != bytesRead)
			{
				buffer.flip();
				// 遍历缓冲区的数据
				while (buffer.hasRemaining())
				{
					System.out.print(buffer.get() + " ");
				}
				System.out.println();
				/*
				 * 清空缓冲区的数据
				 */
				buffer.clear();
				bytesRead = channel.read(buffer);
			}			
			
			
			
			buffer.flip();
			// 底层数组的大小
			System.out.println(buffer.array().length);
			System.out.println("capacity = " + buffer.capacity() + ", position = " + buffer.position() + ", limit = " + buffer.limit());
			while (buffer.hasRemaining())
			{
				System.out.print((char) buffer.get());
			}
			System.out.println();
			
			channel.close();
			stream.close();
			
			System.out.println("capacity = " + buffer.capacity() + ", position = " + buffer.position() + ", limit = " + buffer.limit());
			
		} catch (Exception e) {
			log.error("testRead =====> ", e);
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
	public void testWrite() {
		try {
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char-write.txt", true);
			RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
			FileChannel channel =  raf.getChannel();
			//CharBuffer buffer = CharBuffer.allocate(1024);
			int start = 0;
			int size = 1024;
			
			MappedByteBuffer buffer =channel.map(FileChannel.MapMode.READ_WRITE, start, size);
			ByteBuffer buffer2 = ByteBuffer.allocate(1024);
			// 将数据放入缓冲区
			//buffer2.putChar('a');
			//buffer2.putChar('c');
			//buffer2.putChar('e');
			buffer2.put((byte) 1);
			buffer2.put((byte) 2);
			
			buffer2.flip();
			/*
			 * 写入通道
			 * channel.write无法保证一次能向通道写入多少字节，因此需要重复调用write方法，直到Buffer中没有
			 * 尚未写入通道的字节
			 */
			while (buffer2.hasRemaining())
			{
				channel.write(buffer2);
				//System.out.println(channel.write(buffer));
			}
			//System.out.println(channel.size());
			buffer.flip();
			//buffer.clear();
			int bytesRead = channel.read(buffer);
			while (-1 != bytesRead)
			{
				buffer.flip();
				// 遍历缓冲区的数据
				while (buffer.hasRemaining())
				{
					System.out.print(buffer.get() + " ");
				}
				System.out.println();
				/*
				 * 清空缓冲区的数据
				 */
				buffer.clear();
				bytesRead = channel.read(buffer);
			}	
			channel.close();
			raf.close();
		} catch (Exception e) {
			log.error("testWrite =====> ", e);
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
	public void testReadChannel() {
		try {
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			FileInputStream stream = new FileInputStream(filePath);
			FileChannel channel =  stream.getChannel();
			//CharBuffer buffer = CharBuffer.allocate(1024);
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			//ByteBuffer buffer = ByteBuffer.allocate(10);
			channel.read(buffer);
			buffer.flip();
			// 底层数组的大小
			System.out.println(buffer.array().length);
			System.out.println("capacity = " + buffer.capacity() + ", position = " + buffer.position() + ", limit = " + buffer.limit());
			while (buffer.hasRemaining())
			{
				System.out.print((char) buffer.get());
			}
			System.out.println();
			stream.close();
			
			System.out.println("capacity = " + buffer.capacity() + ", position = " + buffer.position() + ", limit = " + buffer.limit());
			
		} catch (Exception e) {
			log.error("testReadChannel =====> ", e);
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
	public void testWriteChannel() {
		try {
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char-write.txt", true);
			FileOutputStream stream = new FileOutputStream(filePath);
			// 追加模式
			//FileOutputStream stream = new FileOutputStream(filePath, true);
			FileChannel channel =  stream.getChannel();
			//CharBuffer buffer = CharBuffer.allocate(1024);
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			// 写入字符
			buffer.put("Writing char".getBytes());
			buffer.flip();
			
			channel.write(buffer);
			stream.flush();
			stream.close();
			
			
		} catch (Exception e) {
			log.error("testWriteChannel =====> ", e);
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
	public void testRandomAccessFile() {
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
			log.error("testRandomAccessFile =====> ", e);
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
	public void testRandomAccessFile2() {
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
			//int start = 0;
			//int size = 1024;
			//MappedByteBuffer buffer =channel.map(FileChannel.MapMode.READ_WRITE, start, size);
			ByteBuffer buffer = ByteBuffer.allocateDirect(8);
			int bytesRead = channel.read(buffer);
			while (-1 != bytesRead)
			{
				buffer.flip();
				// 遍历缓冲区的数据
				while (buffer.hasRemaining())
				{
					System.out.print(buffer.get() + " ");
				}
				System.out.println();
				/*
				 * 清空缓冲区的数据
				 */
				buffer.clear();
				bytesRead = channel.read(buffer);
			}
			buffer.clear();
			raf.close();
			System.out.println();
			
		} catch (Exception e) {
			log.error("testRandomAccessFile2 =====> ", e);
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
