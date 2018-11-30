/**
 * 描述: 
 * FileChannelTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.nio.channel;

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

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.hua.util.ClassPathUtil;
import com.hua.util.ProjectUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * FileChannelTest
 */
public final class FileChannelTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testFileChannel() {
		try {
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/小说_文本_01.txt", true);
			RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
			log.info("testFileChannel =====> filePath = " + filePath);
			// 文件通道
			FileChannel fChannel = raf.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(48);		
			
			/*
			 * 截取文件: 使用FileChannel.truncate(long)截取一个文件，截取文件时，文件中
			 * 指定长度后面的部分将被删除.
			 */
			/*
			 * force(boolean) 方法: 强制将通道里尚未写入磁盘的数据强制写入磁盘.
			 * 布尔值表示是否同时将文件元数据(权限等信息)写到磁盘上.
			 */
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
	@Test
	public void testReadFile() {
		try {
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/小说_文本_01.txt", true);
			RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
			log.info("testReadFile =====> filePath = " + filePath);
			// 文件通道
			FileChannel fChannel = raf.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(48);
			// 读入缓冲区，一次读取指定字节数
			int bytesRead = fChannel.read(buffer);
			
			while (-1 != bytesRead)
			{
				// 
				log.info("testReadFile =====> read " + bytesRead);
				// 翻转 (模式切换，写模式 --> 读模式的切换)
				buffer.flip();
				/*while (buffer.hasRemaining())
				{
					log.info("testReadFile =====> char = " + (char) buffer.get());
				}*/
				// clear方法清除掉缓冲区所有数据，包括未读数据
				// 清理缓存区 (模式切换，读模式 --> 写模式)
				buffer.clear();// 或调用 buffer.compact();
				// 适用: Buffer中仍有未读的数据，且后续还需要这些数据.
				// buffer.compact(); 
				// 写入缓冲区
				bytesRead = fChannel.read(buffer);
			}
			// 关闭通道
			fChannel.close();
			// 关闭
			raf.close();
		} catch (Exception e) {
			log.error("testReadFile =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 通道之间的数据传输
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTransferFrom() {
		try {
			String fromPath = ClassPathUtil.getClassSubpath("/file/txt/fromFile_01.txt", true);
			String toPath = ProjectUtil.getAbsolutePath("/doc/toFile_01.txt", true);
			RandomAccessFile fRaf = new RandomAccessFile(fromPath, "rw");
			// 文件通道
			FileChannel fChannel = fRaf.getChannel();
			
			RandomAccessFile tRaf = new RandomAccessFile(toPath, "rw");
			FileChannel tChannel = tRaf.getChannel();
			
			// 通道之间的数据传输
			// position 的值会影响到 transferFrom的执行效果.
			long position = 0;
			long count = fChannel.size();
			/**
			 */
			// 跨通道的数据传输
			tChannel.transferFrom(fChannel, position, count);
			// transferTo 将当前通道的数据传输到另外一个通道
			//fChannel.transferTo(position, count, tChannel);
			
			tRaf.close();
			fRaf.close();
			
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
