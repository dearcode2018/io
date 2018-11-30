/**
 * 描述: 
 * SocketChannelTest.java
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

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * SocketChannelTest
 */
public final class SocketChannelTest extends BaseTest {

	/**
	 * SocketChannel 是一个连接到TCP网络套接字的通道，可通过如下方式创建SocketChannel
	 * 1) 打开一个SocketChannel 并连接到网络的某台机器
	 * 
	 * 2) 一个新连接到达ServerSocketChannel时，会创建一个SocketChannel
	 *
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSocketChannel() {
		try {
			
			// 打开SocketChannel
			SocketChannel socketChannel = SocketChannel.open();
			/**
			 * 在非阻塞模式下，调用connect()方法，此方法可能在连接建立之前就返回了，
			 * 为了确定连接是否建立，可以调用finishConnect()方法来做准确的确认.
			 */
			//socketChannel.connect(new InetSocketAddress("http://test.com", 80));
			socketChannel.connect(new InetSocketAddress("http://www.baidu.com", 80));
			/**
			 * 
			String newData = "New String to write to file..." + System.currentTimeMillis();  
			ByteBuffer buf = ByteBuffer.allocate(48);  
			buf.clear();  
			buf.put(newData.getBytes());  
			buf.flip();  
			while(buf.hasRemaining()) {  
			channel.write(buf);  
			}
			 */
			
			socketChannel.close();
		} catch (Exception e) {
			log.error("testSocketChannel =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testServerSocketChannel() {
		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(80));
			//
			serverSocketChannel.configureBlocking(true);
			// 
			while (true)
			{
				/*
				 * 阻塞模式下(默认是阻塞模式)，监听新进来的连接，accept方法会一直阻塞到有新连接到达
				 * 在非阻塞模式下，accetp方法会立即返回，没有新进来的连接，则返回null，此时需要检查
				 * 返回的SocketChannel是否是null
				 */
			
				SocketChannel socketChannel = serverSocketChannel.accept();
				
			}
			
			//serverSocketChannel.close();
		} catch (Exception e) {
			log.error("testServerSocketChannel =====> ", e);
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
