/**
 * 描述: 
 * SelectorTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.nio.selector;

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

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * SelectorTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
public final class SelectorTest extends BaseTest {

	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testSelector() {
		try {
			// 打开Selector
			Selector selector = Selector.open();
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8087);
			serverSocketChannel.bind(socketAddress);
			/*
			 * 设置为非阻塞，调用accept()方法不会阻塞
			 * 返回一个空Channel对象
			 * 注册到Selector
			 * 文件通道是阻塞的，可以选择套接字或其他通道
			 */
			//serverSocketChannel.configureBlocking(false);
			SocketChannel channel = serverSocketChannel.accept();
			/*
			 * SelectionKey.OP_READ 指定监听事件类型
			 * 对多个监听事件感兴趣，可以用 位或 操作符将常量连接起来
			 */
			int interestSet = SelectionKey.OP_ACCEPT | SelectionKey.OP_READ 
					| SelectionKey.OP_CONNECT;
			SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_WRITE);
			// 带附加对象
			//SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ, new Object());
			interestSet = selectionKey.interestOps();
			// 设置感兴趣集合
			//selectionKey.interestOps(11);
			// 获取ready集合
			//int readyOps = selectionKey.readyOps();
			//SelectableChannel selectableChannel = selectionKey.channel();
			//selector = selectionKey.selector();
			
			/*
			 * 选择通道，一直阻塞，可以设置阻塞超时时间
			 * selector.select(long timeout)
			 * 返回上次select之后，又有多少个通道进入就绪状态
			 */
			//selector.select();
			// 非阻塞方法，若没有通道可选直接返回0
			//selector.selectNow();
			
			/*
			 * 唤醒其他线程调用该对象的select()方法
			 * 若当前没有线程阻塞在select()方法上，则下一个调用select()方法的
			 * 线程会即可返回，不会阻塞.
			 */
			//selector.wakeup();
			
			//selectionKey.selector();
			
			// 遍历通道
			Set<SelectionKey> selectedKeySet = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectedKeySet.iterator();
			while (iterator.hasNext())
			{
				SelectionKey key = iterator.next();
				if (key.isAcceptable())
				{
					System.out.println("acceptable");
				} else if (key.isConnectable())
				{
					System.out.println("connectable");
				} else if (key.isReadable())
				{
					System.out.println("readable");
				}
				// 移除当前对象
				iterator.remove();
			}
			
		} catch (Exception e) {
			log.error("testSelector =====> ", e);
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
	public void testSelector2() {
		try {
			// 打开Selector
			Selector selector = Selector.open();
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8087);
			serverSocketChannel.bind(socketAddress);
			SocketChannel channel = serverSocketChannel.accept();
			/*
			 * 是accept方法返回的Channel对象
			 * 设置为非阻塞，调用accept()方法不会阻塞
			 * 返回一个空Channel对象
			 * 注册到Selector
			 * 文件通道是阻塞的，可以选择套接字或其他通道
			 */
			channel.configureBlocking(false);
			/*
			 * SelectionKey.OP_READ 指定监听事件类型
			 * 对多个监听事件感兴趣，可以用 位或 操作符将常量连接起来
			 * 按照顺序来与或，OP_READ OP_WRITE OP_CONNECT OP_ACCEPT
			 */
			int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
			SelectionKey selectionKey = channel.register(selector, interestSet);
			//System.out.println(selectionKey.isConnectable());
			
			/*
			 * 执行选择selectedKeys才有值
			 */
			selector.select();
			Set<SelectionKey> selectedKeySet = selector.selectedKeys();
			
			Iterator<SelectionKey> iterator = selectedKeySet.iterator();
			while (iterator.hasNext())
			{
				SelectionKey key = iterator.next();
				if (key.isAcceptable())
				{
					System.out.println("acceptable");
				} else if (key.isConnectable())
				{
					System.out.println("connectable");
				} else if (key.isReadable())
				{
					System.out.println("readable");
					ByteBuffer buffer = ByteBuffer.allocateDirect(8);
					channel.read(buffer);
					buffer.flip();
					while (buffer.hasRemaining())
					{
						System.out.print(buffer.get() + " ");
					}
					
				}
				// 移除当前对象
				iterator.remove();
			}
			
		} catch (Exception e) {
			log.error("testSelector2 =====> ", e);
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
	public void testServer() {
		try {
			// 打开Selector
			Selector selector = Selector.open();
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8087);
			serverSocketChannel.bind(socketAddress);
			/*
			 * 设置为非阻塞，调用accept()方法不会阻塞
			 * 返回一个空Channel对象
			 * 注册到Selector
			 * 文件通道是阻塞的，可以选择套接字或其他通道
			 */
			//serverSocketChannel.configureBlocking(false);
			SocketChannel channel = serverSocketChannel.accept();
			ByteBuffer buffer = ByteBuffer.allocateDirect(8);
			channel.read(buffer);
			buffer.flip();
			while (buffer.hasRemaining())
			{
				System.out.print(buffer.get() + " ");
			}
			
		} catch (Exception e) {
			log.error("testServer =====> ", e);
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
	public void testClient() {
		try {
			SocketChannel socketChannel = SocketChannel.open();
			InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8087);
			// 连接远程
			socketChannel.connect(socketAddress);
			//socketChannel.configureBlocking(false);
			ByteBuffer buffer = ByteBuffer.allocateDirect(8);
			
			/*
			 * 将position设置为0，limit设置为capacity
			 */
			for (int i = 0; i < buffer.capacity(); i++)
			{
				buffer.put((byte) (i + 1));
			}
			
			buffer.flip();
			socketChannel.write(buffer);
			
			buffer.clear();
			socketChannel.close();
			socketChannel.close();
			
		} catch (Exception e) {
			log.error("testClient =====> ", e);
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
	public void testClient2() {
		try {
			SocketChannel socketChannel = SocketChannel.open();
			InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8087);
			// 连接远程
			socketChannel.connect(socketAddress);
			//socketChannel.configureBlocking(false);
			ByteBuffer buffer = ByteBuffer.allocateDirect(8);
			
			/*
			 * 将position设置为0，limit设置为capacity
			 */
			for (int i = 0; i < buffer.capacity(); i++)
			{
				buffer.put((byte) (i + 1));
			}
			
			buffer.flip();
			//socketChannel.write(buffer);
			
			buffer.clear();
			socketChannel.close();
			socketChannel.close();
			
		} catch (Exception e) {
			log.error("testClient2 =====> ", e);
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
