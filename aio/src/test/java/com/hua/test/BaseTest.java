/**
 * 描述: 
 * BaseTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PushbackInputStream;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.CharBuffer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import com.hua.log.BaseLog;

/**
 * 描述: 测试基类
 * 包含多个测试示例
 * 
 * @author qye.zheng
 * BaseTest
 */
//@RunWith(JUnitPlatform.class)
//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DisplayName("BaseTest")
public class BaseTest extends BaseLog {
	
public static final String BASIC_DIR = "/file/txt/";
	
	public static String inputPath;
	
	public static InputStream inputStream;
	
	public static String outputPath;
	
	public static OutputStream outputStream;
	
	// 1024 byte = 1 kb, 256 byte = 1/4 kb
	public byte[] byteArray = new byte[256];
	
	public byte bt;
	
	// 实际读取到的字节/字符个数
	public int actualCount;
	
	public int offset;
	
	public long size;
	
	public static InputStreamReader inputStreamReader;
	
	public static OutputStreamWriter outputStreamWriter;
	
	public Reader reader = inputStreamReader;
	
	public Writer writer = outputStreamWriter;
	
	public char[] charArray = new char[25];
	
	public String result;
	
	public CharBuffer charBuffer = CharBuffer.allocate(25);
	
	public static BufferedInputStream bufferedInputStream;
	
	public static BufferedOutputStream bufferedOutputStream;
	
	public static BufferedReader bufferedReader;
	
	public static BufferedWriter bufferedWriter;
	
	public static LineNumberReader lineNumberReader;
	
	public SequenceInputStream sequenceInputStream;
	
	public static PipedInputStream pipedInputStream;
	
	public static PipedOutputStream pipedOutputStream;
	
	public static PipedReader pipedReader;
	
	public static PipedWriter pipedWriter;
	
	public StringReader stringReader;
	
	public StringWriter stringWriter;
	
	public DataInput dataInput;
	
	public DataOutput dataOutput;
	
	public PushbackInputStream pushbackInputStream;
	
	public PushbackReader pushbackReader;
	
	/**
	 * 
	 * 描述: [所有测试]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("beforeClass")
	@BeforeAll
	public static void beforeClass() {
		System.out.println("beforeClass()");
	}
	
	/**
	 * 
	 * 描述: [所有测试]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("afterClass")
	@AfterAll
	public static void afterClass() {
		System.out.println("afterClass()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("beforeMethod")
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
	@AfterEach
	public void afterMethod() {
		System.out.println("afterMethod()");
	}

}
