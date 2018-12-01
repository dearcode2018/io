/**
 * 描述: 
 * PathTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.nio.path;

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

import java.net.URI;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.hua.util.ProjectUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * PathTest
 */
public final class PathTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPath() {
		try {
			// 本地路径写法，file协议
			// file:///C:/Users/Administrator/AppData/Local/Microsoft/Windows/
			URI uri = new URI("file:///C:/产品-.xls");
			Path path = Paths.get(uri);
			
			log.info("testPath =====> " + path.getFileName());
			// 基础路径，然后 more参数可以填写该路径下的子路径..
			String first = ProjectUtil.getAbsolutePath("/doc/path", true);
			// D:\workspace\java\nio\doc\path
			System.out.println(first);
			
			path = Paths.get(first);
			// 
			log.info("testPath =====> " + path.getFileName());
			
			
		} catch (Exception e) {
			log.error("testPath =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPathBuild() {
		try {
			// 本地路径写法，file协议
			// file:///C:/Users/Administrator/AppData/Local/Microsoft/Windows/
			URI uri = new URI("file:///C:/产品-.xls");
			Path path = Paths.get(uri);
			
			log.info("testPathBuild =====> " + path.getFileName());
			log.info("testPathBuild =====> " + path.getNameCount());
			
			// 基础路径，然后 more参数可以填写该路径下的子路径..
			String first = ProjectUtil.getAbsolutePath("/doc/path", true);
			// D:\workspace\java\nio\doc\path
			System.out.println(first);
			String[] more = {"/path.txt", "/project.txt"};
			path = Paths.get(first, more);
			
			// 返回的是最后一个路径的文件名称
			log.info("testPathBuild =====> fileName = " + path.getFileName());
			// 该路径有多少个可命名的路径
			log.info("testPathBuild =====> " + path.getNameCount());
			// X:\workspace
			log.info("testPathBuild =====> " + path.getName(0).getFileName());
			// X:\workspace\java\nio\doc\path\path.txt
			log.info("testPathBuild =====> " + path.getName(6).getFileName());
			// X:\workspace\java\nio\doc\path\project.txt
			log.info("testPathBuild =====> " + path.getName(7).getFileName());
		} catch (Exception e) {
			log.error("testPathBuild =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testFileSystem() {
		try {
			Path path = null;
			// 基础路径，然后 more参数可以填写该路径下的子路径..
			String first = ProjectUtil.getAbsolutePath("/doc/path", true);
			// D:\workspace\java\nio\doc\path
			//System.out.println(first);
			
			path = Paths.get(first);
			
			FileSystem fs = path.getFileSystem();
			
			log.info("testFileSystem =====> " + fs.getSeparator());
			Iterable<Path> iterable = fs.getRootDirectories();
			Iterator<Path> it = iterable.iterator();
			
/*			while (it.hasNext())
			{
				System.out.println(it.next().getFileName());
			}*/
			
			
			/**
			 * 磁盘 存储信息
			 */
			Iterable<FileStore> fileStoreIterable = fs.getFileStores();
			Iterator<FileStore> fIt = fileStoreIterable.iterator();
			FileStore fileStore = null;
			while (fIt.hasNext())
			{
				// 多个个磁盘就多少条记录
				fileStore = fIt.next();
				System.out.println("磁盘名称: " + fileStore.name());
				System.out.println("磁盘类型: " + fileStore.type());
				System.out.println("总共字节: "+ fileStore.getTotalSpace());
				System.out.println("可用字节: "+ fileStore.getUsableSpace());
				System.out.println("已用字节: " + (fileStore.getTotalSpace() - fileStore.getUnallocatedSpace()));
				System.out.println("未分配字节: "+ fileStore.getUnallocatedSpace());
				System.out.println("------------");
			}
			
			
		} catch (Exception e) {
			log.error("testFileSystem =====> ", e);
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
