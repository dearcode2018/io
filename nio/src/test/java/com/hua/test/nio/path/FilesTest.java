/**
 * 描述: 
 * FilesTest.java
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

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.hua.util.ProjectUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * FilesTest
 */
public final class FilesTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testFiles() {
		try {
			Path path = null;
			// 基础路径，然后 more参数可以填写该路径下的子路径..
			String first = ProjectUtil.getAbsolutePath("/doc/path/path.txt", true);
			// D:\workspace\java\nio\doc\path
			//System.out.println(first);
			
			path = Paths.get(first);
			
		} catch (Exception e) {
			log.error("testFiles =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCreateDirectory() {
		try {
			Path path = null;
			// 基础路径，然后 more参数可以填写该路径下的子路径..
			String first = ProjectUtil.getAbsolutePath("/doc/path/createDir", true);
			// D:\workspace\java\nio\doc\path
			//System.out.println(first);
			
			path = Paths.get(first);
			// 创建目录
			Files.createDirectory(path);
			
		} catch (Exception e) {
			log.error("testCreateDirectory =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCreateFile() {
		try {
			Path path = null;
			// 基础路径，然后 more参数可以填写该路径下的子路径..
			String first = ProjectUtil.getAbsolutePath("/doc/path/createFile.txt", true);
			// D:\workspace\java\nio\doc\path
			//System.out.println(first);
			
			path = Paths.get(first);
			// 创建文件
			Files.createFile(path);
		} catch (Exception e) {
			log.error("testCreateFile =====> ", e);
		}
	}		
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCopy() {
		try {
			Path path = null;
			// 基础路径，然后 more参数可以填写该路径下的子路径..
			String first = ProjectUtil.getAbsolutePath("/doc/path/path.txt", true);
			// D:\workspace\java\nio\doc\path
			//System.out.println(first);
			
			path = Paths.get(first);
			OutputStream outputStream = new FileOutputStream(ProjectUtil.getAbsolutePath("/doc/path/path_copy.txt", true));
			Files.copy(path, outputStream);
			
		} catch (Exception e) {
			log.error("testCopy =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDelete() {
		try {
			Path path = null;
			// 基础路径，然后 more参数可以填写该路径下的子路径..
			String first = ProjectUtil.getAbsolutePath("/doc/path/io.txt", true);
			// D:\workspace\java\nio\doc\path
			//System.out.println(first);
			
			path = Paths.get(first);
			Files.delete(path);
			
		} catch (Exception e) {
			log.error("testDelete =====> ", e);
		}
	}	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testExists() {
		try {
			Path path = null;
			// 基础路径，然后 more参数可以填写该路径下的子路径..
			String first = ProjectUtil.getAbsolutePath("/doc/path/path.txt", true);
			// D:\workspace\java\nio\doc\path
			//System.out.println(first);
			
			path = Paths.get(first);
			boolean flag = Files.exists(path);
			log.info("testExists =====> flag = " + flag);
		} catch (Exception e) {
			log.error("testExists =====> ", e);
		}
	}	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testNew() {
		try {
			Path path = null;
			// 基础路径，然后 more参数可以填写该路径下的子路径..
			String first = ProjectUtil.getAbsolutePath("/doc/path/path.txt", true);
			// D:\workspace\java\nio\doc\path
			//System.out.println(first);
			
			path = Paths.get(first);
			BufferedReader reader = Files.newBufferedReader(path);
			
			System.out.println(reader.readLine());
		} catch (Exception e) {
			log.error("testNew =====> ", e);
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
