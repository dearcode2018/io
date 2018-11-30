/**
 * 描述: 
 * BufferTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.nio.buffer;

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
import java.io.RandomAccessFile;
import java.nio.Buffer;
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
 * BufferTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
public final class BufferTest extends BaseTest {

	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testBuffer() {
		try {
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			FileInputStream stream = new FileInputStream(filePath);
			FileChannel channel =  stream.getChannel();
			
			// 分配直接内存
			//ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			
			// 将指定的数组包装为缓冲区对象
			//ByteBuffer buffer = ByteBuffer.wrap(new byte[] {});
			
			// 分配堆内存
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			
			// 创建子缓冲区
			//ByteBuffer slice = buffer.slice();
			
			channel.read(buffer);
			
			/*
			 * 将position设置为0，limit设置为capacity
			 */
			buffer.flip();
			
			/*
			 * 读取数据
			 */
			buffer.get();
			//buffer.get();
			
			// 将position设置为0，可以重读所有Buffer数据
			//buffer.rewind();
			
			/*
			 * 清空缓冲区的数据
			 */
			buffer.clear();
			
/*			while (buffer.hasRemaining())
			{
				System.out.print((char) buffer.get());
			}*/
			
			stream.close();
			System.out.println();
		} catch (Exception e) {
			log.error("testBuffer =====> ", e);
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
	public void testMark() {
		try {
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			FileInputStream stream = new FileInputStream(filePath);
			FileChannel channel =  stream.getChannel();
			
			// 分配直接内存
			//ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			
			// 将指定的数组包装为缓冲区对象
			//ByteBuffer buffer = ByteBuffer.wrap(new byte[] {});
			
			// 分配堆内存
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			
			// 创建子缓冲区
			//ByteBuffer slice = buffer.slice();
			
			channel.read(buffer);
			
			buffer.flip();
			/*
			 * 读取数据
			 */
			buffer.get();
			//buffer.get();
			
			/*
			 * 调用mark方法标记一个当前的position，之后可以调用reset返回到这个postion
			 */
			buffer.mark();
			System.out.println(buffer.position());
			buffer.get();
			System.out.println(buffer.position());
			
			
			// reset
			buffer.reset();
			System.out.println(buffer.position());
			
			
			// 将position设置为0，可以重读所有Buffer数据
			//buffer.rewind();
			
			
			
			/*
			 * 清空缓冲区的数据
			 */
			buffer.clear();
			
/*			while (buffer.hasRemaining())
			{
				System.out.print((char) buffer.get());
			}*/
			
			stream.close();
			System.out.println();
		} catch (Exception e) {
			log.error("testMark =====> ", e);
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
	public void testFlip() {
		try {
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			FileInputStream stream = new FileInputStream(filePath);
			FileChannel channel =  stream.getChannel();
			
			// 分配直接内存
			//ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			
			// 将指定的数组包装为缓冲区对象
			//ByteBuffer buffer = ByteBuffer.wrap(new byte[] {});
			
			// 分配堆内存
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			
			// 创建子缓冲区
			//ByteBuffer slice = buffer.slice();
			
			channel.read(buffer);
			
			/*
			 * 将position设置为0，limit设置为capacity
			 */
			buffer.flip();
			
			while (buffer.hasRemaining())
			{
				System.out.print((char) buffer.get());
			}
			
			/*
			 * 清空缓冲区的数据
			 */
			buffer.clear();
			
/*			while (buffer.hasRemaining())
			{
				System.out.print((char) buffer.get());
			}*/
			
			stream.close();
			System.out.println();
		} catch (Exception e) {
			log.error("testFlip =====> ", e);
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
	public void testRewind() {
		try {
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			FileInputStream stream = new FileInputStream(filePath);
			FileChannel channel =  stream.getChannel();
			
			// 分配直接内存
			//ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			
			// 将指定的数组包装为缓冲区对象
			//ByteBuffer buffer = ByteBuffer.wrap(new byte[] {});
			
			// 分配堆内存
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			
			// 创建子缓冲区
			//ByteBuffer slice = buffer.slice();
			
			channel.read(buffer);
			
			//buffer.flip();
			
			/*
			 * 将position设置为0，可以重读所有Buffer数据
			 * 
			 * 对应flip方法，增加调用setLimit方法
			 */
			buffer.rewind();
			
			while (buffer.hasRemaining())
			{
				System.out.print((char) buffer.get());
			}
			
			/*
			 * 清空缓冲区的数据
			 */
			buffer.clear();
			
/*			while (buffer.hasRemaining())
			{
				System.out.print((char) buffer.get());
			}*/
			
			stream.close();
			System.out.println();
		} catch (Exception e) {
			log.error("testBuffer =====> ", e);
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
	public void testClear() {
		try {
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			FileInputStream stream = new FileInputStream(filePath);
			FileChannel channel =  stream.getChannel();
			
			// 分配直接内存
			//ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			
			// 将指定的数组包装为缓冲区对象
			//ByteBuffer buffer = ByteBuffer.wrap(new byte[] {});
			
			// 分配堆内存
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			
			// 创建子缓冲区
			//ByteBuffer slice = buffer.slice();
			
			channel.read(buffer);
			
			/*
			 * 清空Buffer，position设置为0，limit设置为capacity的值，Buffer当中的数据并未清除
			 */
			buffer.clear();
			
			while (buffer.hasRemaining())
			{
				System.out.print((char) buffer.get());
			}
			
			
/*			while (buffer.hasRemaining())
			{
				System.out.print((char) buffer.get());
			}*/
			
			stream.close();
			System.out.println();
		} catch (Exception e) {
			log.error("testClear =====> ", e);
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
	public void testCompact() {
		try {
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			FileInputStream stream = new FileInputStream(filePath);
			FileChannel channel =  stream.getChannel();
			
			// 分配直接内存
			//ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			
			// 将指定的数组包装为缓冲区对象
			//ByteBuffer buffer = ByteBuffer.wrap(new byte[] {});
			
			// 分配堆内存
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			
			// 创建子缓冲区
			//ByteBuffer slice = buffer.slice();
			
			channel.read(buffer);
			
			/*
			 * 将position设置为0，limit设置为capacity
			 */
			buffer.flip();
			
			/*
			 * 读取数据
			 */
			buffer.get();
			//buffer.get();
			
			/*
			 * 将所有未读数据拷贝到Buffer起始位置，然后将position设置到最后一个未读数据的下一个位置，limit设置为capacity，
			 * Buffer准备好写数据，但不会覆盖未读数据
			 */
			buffer.compact();
			/*
			 * compact之后，没有读取的数据移动到其实位置，position也相应变化，继续写数据不会覆盖未读的数据
			 */
			System.out.println(buffer.position());
			
			stream.close();
			//System.out.println();
		} catch (Exception e) {
			log.error("testClear =====> ", e);
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
	public void testBufferCall() {
		try {
			String filePath = ClassPathUtil.getClassSubpath("/file/txt/letter-char.txt", true);
			FileInputStream stream = new FileInputStream(filePath);
			FileChannel channel =  stream.getChannel();
			
			// 分配直接内存
			//ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			
			// 将指定的数组包装为缓冲区对象
			//ByteBuffer buffer = ByteBuffer.wrap(new byte[] {});
			
			// 分配堆内存
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			output("初始化", buffer);
			
			
			// 创建子缓冲区
			//ByteBuffer slice = buffer.slice();
			
			channel.read(buffer);
			output("调用read", buffer);
			
			buffer.flip();
			output("调用flip", buffer);
			
			while (buffer.hasRemaining())
			{
				System.out.print((char) buffer.get());
			}
			output("调用get", buffer);
			
			/*
			 * 清空缓冲区的数据
			 */
			buffer.clear();
			output("调用clear", buffer);
			
/*			while (buffer.hasRemaining())
			{
				System.out.print((char) buffer.get());
			}*/
			
			stream.close();
			System.out.println();
		} catch (Exception e) {
			log.error("testBufferCall =====> ", e);
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
	public void testSliceBuffer() {
		try {
		
			// 分配直接内存
			//ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			
			// 将指定的数组包装为缓冲区对象
			//ByteBuffer buffer = ByteBuffer.wrap(new byte[] {});
			
			
			/**
			 * 结论: 子缓冲区和主缓冲区数据共享，子缓冲区部分的数据发生变化会提现到主缓冲区
			 */
			
			// 分配堆内存
			ByteBuffer buffer = ByteBuffer.allocate(10);
			for (int i = 0 ; i < buffer.capacity(); i++)
			{
				buffer.put((byte) i);
			}
			
			// 修改缓冲区的position
			buffer.position(3);
			buffer.limit(7);
			// 创建子缓冲区
			ByteBuffer slice = buffer.slice();
			// 3, 4, 5, 6
			System.out.println("Slice capacity = " + slice.capacity());
			for (int i = 0; i < slice.capacity(); i++)
			{
				slice.put((byte) (i + 20));
			}
			
			//buffer.flip();
			buffer.position(0);
			buffer.limit(buffer.capacity());
			
			while (buffer.hasRemaining())
			{
				System.out.print(buffer.get() + " ");
			}
			
			/*
			 * 清空缓冲区的数据
			 */
			buffer.clear();
			
			System.out.println();
		} catch (Exception e) {
			log.error("testSliceBuffer =====> ", e);
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
	public void testReadOnlyBuffer() {
		try {
		
			// 分配直接内存
			//ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			
			// 将指定的数组包装为缓冲区对象
			//ByteBuffer buffer = ByteBuffer.wrap(new byte[] {});
			
			/**
			 * 结论: 只读缓冲区和主缓冲区数据共享，只读缓冲区部分的数据发生变化会提现到主缓冲区
			 * 
			 * 尝试修改只读缓存区会抛ReadOnlyBufferException，只读缓冲区可以保护数据，避免失误修改
			 * 只读缓冲区在传递过程中可以保持不变
			 * 
			 */
			
			// 分配堆内存
			ByteBuffer buffer = ByteBuffer.allocate(10);
			for (int i = 0 ; i < buffer.capacity(); i++)
			{
				buffer.put((byte) i);
			}
			
			// 创建只读缓冲区
			ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
			
			
			// 修改原缓冲区
			for (int i = 0; i < buffer.capacity(); i++)
			{
				// 在指定索引位置放入值
				buffer.put(i, (byte) (2 * i));
			}
			
			//buffer.flip();
			readOnlyBuffer.position(0);
			readOnlyBuffer.limit(buffer.capacity());
			
			while (readOnlyBuffer.hasRemaining())
			{
				System.out.print(readOnlyBuffer.get() + " ");
			}
			
			/*
			 * 清空缓冲区的数据
			 */
			//buffer.clear();
			
			System.out.println();
		} catch (Exception e) {
			log.error("testBuffer =====> ", e);
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
	public void testDirectBuffer() {
		try {
		
			// 分配直接内存
			//ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			
			// 将指定的数组包装为缓冲区对象
			//ByteBuffer buffer = ByteBuffer.wrap(new byte[] {});
			
			/**
			 * 直接缓冲区
			 * 加快IO速度
			 * 
			 * 
			 */
			
			// 分配直接内存
			ByteBuffer buffer = ByteBuffer.allocateDirect(10);
			for (int i = 0 ; i < buffer.capacity(); i++)
			{
				buffer.put((byte) i);
			}
			
			// 修改原缓冲区
			for (int i = 0; i < buffer.capacity(); i++)
			{
				// 在指定索引位置放入值
				buffer.put(i, (byte) (2 * i));
			}
			
			buffer.flip();
			while (buffer.hasRemaining())
			{
				System.out.print(buffer.get() + " ");
			}
			
			/*
			 * 清空缓冲区的数据
			 */
			//buffer.clear();
			
			System.out.println();
		} catch (Exception e) {
			log.error("testDirectBuffer =====> ", e);
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
	public void testMappedBuffer() {
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
			log.error("testMappedBuffer =====> ", e);
		}
	}
	
	/**
	 * 
	 * @description 输出Buffer动态变化步骤
	 * @param step
	 * @param buffer
	 * @author qianye.zheng
	 */
	private static final void output(final String step, final Buffer buffer)
	{
		System.out.println("step: " + step);
		System.out.println("capacity = " + buffer.capacity());
		System.out.println("position = " + buffer.position());
		System.out.println("limit = " + buffer.limit());
		System.out.println();
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
