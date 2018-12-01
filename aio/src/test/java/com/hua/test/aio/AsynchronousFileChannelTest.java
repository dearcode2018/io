/**
 * 描述: 
 * AsynchronousFileChannelTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.aio;

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
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.hua.test.BaseTest;
import com.hua.util.FileUtil;
import com.hua.util.ProjectUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * AsynchronousFileChannelTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
public final class AsynchronousFileChannelTest extends BaseTest {

	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testAsynchronousFileChannel() {
		try {
			String first = ProjectUtil.getAbsolutePath("/doc/.bigfile.txt", true);
			Path path = Paths.get(first);
			AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
			ByteBuffer buffer = ByteBuffer.allocateDirect( 5 * 1024 * 1024);
			Future<Integer> future =  fileChannel.read(buffer, 0);
			System.out.println(future.isDone());
			
			System.out.println("继续做其他事情");
			
			System.out.println(future.isDone());
			
			
		} catch (Exception e) {
			log.error("testAsynchronousFileChannel =====> ", e);
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
	public void testFutureRead() {
		try {
			String first = ProjectUtil.getAbsolutePath("/doc/.bigfile.txt", true);
			Path path = Paths.get(first);
			AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
			// 约2G缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(2 * 1000 * 1000 * 1024);
			System.out.println("继续做其他事情");
			Future<Integer> future =  fileChannel.read(buffer, 0);
			System.out.println(future.isDone());
			
			System.out.println("继续做其他事情");
			
			System.out.println(future.isDone());
			
			
		} catch (Exception e) {
			log.error("testFutureRead =====> ", e);
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
	public void testCompletionHandlerRead() {
		try {
			String first = ProjectUtil.getAbsolutePath("/doc/.bigfile.txt", true);
			Path path = Paths.get(first);
			AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
			// 约2G缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(2 * 1000 * 1000 * 1024);
			System.out.println("start");
			fileChannel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer> () {
				/**
				 * @description 
				 * @param result
				 * @param attachment
				 * @author qianye.zheng
				 */
				@Override
				public void completed(Integer result, ByteBuffer attachment)
				{
					System.out.println("result = " + result + " attachment == buffer:" + (attachment == buffer));
				}
				/**
				 * @description 
				 * @param exc
				 * @param attachment
				 * @author qianye.zheng
				 */
				@Override
				public void failed(Throwable exc, ByteBuffer attachment)
				{
					System.out.println("failed");
				}
			});
	
			System.out.println("继续做其他事情");
			
			Thread.sleep(5 * 1000);
			
		} catch (Exception e) {
			log.error("testCompletionHandlerRead =====> ", e);
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
	public void testFutureWrite() {
		try {
			String first = ProjectUtil.getAbsolutePath("/doc/.bigfile.txt", true);
			Path path = Paths.get(first);
			AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
			// 约2G缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(2 * 1000 * 1000 * 1024);
			String first2 = ProjectUtil.getAbsolutePath("/doc/.bigfile2.txt", true);
			// 删除
			FileUtil.deleteFile(first2);
			// 创建
			FileUtil.createFile(first2);
			System.out.println("start");
			
			fileChannel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer> () {
				/**
				 * @description 
				 * @param result
				 * @param attachment
				 * @author qianye.zheng
				 */
				@Override
				public void completed(Integer result, ByteBuffer attachment)
				{
					System.out.println("result = " + result);
					// 写
					Path path2 = Paths.get(first2);
					AsynchronousFileChannel fileChannel2 = null;
					try
					{
						fileChannel2 = AsynchronousFileChannel.open(path2, StandardOpenOption.WRITE);
					} catch (IOException e)
					{
						e.printStackTrace();
					}
					buffer.flip();
					// 约2G缓冲区
					Future<Integer> future =  fileChannel2.write(buffer, 0);
					System.out.println(future.isDone());
				}
				/**
				 * @description 
				 * @param exc
				 * @param attachment
				 * @author qianye.zheng
				 */
				@Override
				public void failed(Throwable exc, ByteBuffer attachment)
				{
					System.out.println("failed");
				}
			});
			while (true)
			{
				System.out.println("do other thing...");
				Thread.sleep(1 * 1000);
			}
			//System.out.println("继续做其他事情");
	
			
		} catch (Exception e) {
			log.error("testFutureWrite =====> ", e);
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
	public void testCompletionHandlerWrite() {
		try {
			String first = ProjectUtil.getAbsolutePath("/doc/.bigfile.txt", true);
			Path path = Paths.get(first);
			AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
			// 约2G缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(2 * 1000 * 1000 * 1024);
			String first2 = ProjectUtil.getAbsolutePath("/doc/.bigfile2.txt", true);
			// 删除
			FileUtil.deleteFile(first2);
			// 创建
			FileUtil.createFile(first2);
			System.out.println("start");
			fileChannel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer> () {
				/**
				 * @description 
				 * @param result
				 * @param attachment
				 * @author qianye.zheng
				 */
				@Override
				public void completed(Integer result, ByteBuffer attachment)
				{
					System.out.println("result = " + result + " attachment == buffer" + (attachment == buffer));
					Path path2 = Paths.get(first2);
					AsynchronousFileChannel fileChannel2 = null;
					try
					{
						fileChannel2 = AsynchronousFileChannel.open(path2, StandardOpenOption.WRITE);
					} catch (IOException e1)
					{
						e1.printStackTrace();
					}
					buffer.flip();
					fileChannel2.write(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer> () {
						/**
						 * @description 
						 * @param result
						 * @param attachment
						 * @author qianye.zheng
						 */
						@Override
						public void completed(Integer result, ByteBuffer attachment)
						{
							System.out.println("finish");
						}
						/**
						 * @description 
						 * @param exc
						 * @param attachment
						 * @author qianye.zheng
						 */
						@Override
						public void failed(Throwable exc, ByteBuffer attachment)
						{
							System.out.println("failed");
						}
					});	
					try
					{
						fileChannel2.force(false);
						fileChannel2.close();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				/**
				 * @description 
				 * @param exc
				 * @param attachment
				 * @author qianye.zheng
				 */
				@Override
				public void failed(Throwable exc, ByteBuffer attachment)
				{
					System.out.println("failed");
				}
			});
			System.out.println("继续做其他事情");
			
			while (true)
			{
				System.out.println("do other thing...");
				Thread.sleep(1 * 1000);
			}
			
		} catch (Exception e) {
			log.error("testCompletionHandlerWrite =====> ", e);
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
	public void testReadBigFile() {
		try {
			String pathStr = ProjectUtil.getAbsolutePath("/doc/.bigfile.txt", true);
			ByteBuffer buffer = ByteBuffer.allocateDirect(512);
			FileInputStream inputStream = new FileInputStream(pathStr);
			FileChannel channel = inputStream.getChannel();
			
			int length = 0;
			int count = 0;
			while (-1 != (length = channel.read(buffer)))
			{
				buffer.clear();
				count++;
			}
			
			/*
			 * 读取文件过程中，一直阻塞在读取环节，以此模拟工作线程
			 * 被IO阻塞的情形.
			 */
			System.out.println("read finish count = " + count);
			
			channel.close();
			inputStream.close();
		} catch (Exception e) {
			log.error("testReadBigFile =====> ", e);
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
	public void buildBigFile() {
		try {
			String pathStr = ProjectUtil.getAbsolutePath("/doc/.bigfile.txt", true);
			ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			//Path path = Paths.get(pathStr);
			// append模式为false
			FileOutputStream outputStream = new FileOutputStream(pathStr, false);
			FileChannel channel = outputStream.getChannel();
			String content = "2746EB2CCC093599808F666E9881AA84";
			String temp = null;
			for (int i =0; i < 50000000; i++)
			{
				temp = content;
				// 加一个换行符
				temp += i + "\n";
				buffer.put(temp.getBytes());
				buffer.flip();
				// 写入文件
				while (buffer.hasRemaining())
				{
					channel.write(buffer);
				}
				// 添加一个换行
				buffer.clear();
			}
			
			channel.close();
			outputStream.close();
			
		} catch (Exception e) {
			log.error("buildBigFile =====> ", e);
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
