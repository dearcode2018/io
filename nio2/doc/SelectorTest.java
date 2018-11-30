/**
 * 描述: 
 * SelectorTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.nio.selector;

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

import java.nio.channels.Channel;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * SelectorTest
 */
public final class SelectorTest extends BaseTest {

	
	/**
	 * Selector（选择器）是Java NIO中能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。
	 * 这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。 
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@SuppressWarnings({"unused"})
	@Test
	public void testSelector() {
		try {
			// 创建选择器
			Selector selector = Selector.open();
			DatagramChannel channel = DatagramChannel.open();
			
			/**
			 * 与Selector一起使用时，Channel必须处于非阻塞模式下，
			 * 这意味着FileChannel不能和Selector一起使用，因为FileChannel不能
			 * 切换到非阻塞模式，而套接字通道都可以.
			 */
			channel.configureBlocking(false);
			// 如果要监听多个事件，可以用 为或 操作符将常量连接起来
		    //int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;  
			// 向选择器注册通道 Operation-set bit for read operations
			SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ);
			
			
			
		    int interestSet = selectionKey.interestOps();
		    
		    /**
		     * 1 << 2, 1 << 3
		     * 通过 位与 取出key的值
		     */
		    boolean isInterestedInAccept  = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;
		    boolean isInterestedInConnect = (interestSet & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT;  
		    boolean isInterestedInRead    = (interestSet & SelectionKey.OP_READ) == SelectionKey.OP_READ;  
		    boolean isInterestedInWrite   = (interestSet & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE;  
		    
		    
		    // 从SelectionKey 访问Channel和Selector
		    Channel ch = selectionKey.channel();
		    Selector se = selectionKey.selector();
		    
		    Object obj = new Object();
		    // 附加对象
		    selectionKey.attach(obj);
		    // 获取附加对象
		    obj = selectionKey.attachment();
		    
		    /*
		     *  selector方法
		     *  selector(): 堵塞到至少有一个通道在你注册的事件上就绪了
		     *  selector(long timeout): 最长会阻塞timeout毫秒数
		     *  selectNow(): 不会阻塞，不管什么通道就绪都立即返回
		     *  select(): 返回多少通道已经就绪.
		     *  
		     *   
		     */
		    
		    
		    
			// 关闭通道
			channel.close();
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
			
			log.info("testTemp =====> " + (1 << 2));
			
			log.info("testTemp =====> " + (1 << 3));
			
			log.info("testTemp =====> " + (1 << 4));
			
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
