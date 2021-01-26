/**
 * 描述: 
 * BaseTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test;

// 静态导入
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.CharBuffer;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.hua.constant.Constant;
import com.hua.log.BaseLog;
import com.hua.util.ClassPathUtil;

/**
 * 描述: 测试基类
 * 包含多个测试示例
 * 
 * @author qye.zheng
 * BaseTest
 */
//@RunWith()
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
	@BeforeClass
	public static void beforeClass() {
		System.out.println("beforeClass()");
		inputPath = ClassPathUtil.getClassPath(BASIC_DIR + "input.txt");
		outputPath = ClassPathUtil.getClassPath(BASIC_DIR + "output.txt");
		try
		{
			inputStream = new FileInputStream(inputPath);
			outputStream = new FileOutputStream(outputPath);
			
			inputStreamReader = new InputStreamReader(inputStream, 
					Constant.CHART_SET_UTF_8);
			outputStreamWriter = new OutputStreamWriter(outputStream, 
					Constant.CHART_SET_UTF_8);
			
			// 默认缓存大小是 8192 个 byte
			bufferedInputStream = new BufferedInputStream(inputStream);
			// bufferedInputStream = new BufferedInputStream(inputStream, 2);
			bufferedOutputStream = new BufferedOutputStream(outputStream);
			
			bufferedReader = new BufferedReader(inputStreamReader);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			
			lineNumberReader = new LineNumberReader(inputStreamReader);
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 描述: [所有测试]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println("afterClass()");
		// 关闭流
		try
		{
			// 先关闭包装流
			if (null != bufferedInputStream)
			{
				bufferedInputStream.close();
			}
			if (null != bufferedOutputStream)
			{
				bufferedOutputStream.close();
			}
			if (null != bufferedReader)
			{
				bufferedReader.close();
			}
			if (null != bufferedWriter)
			{
				bufferedWriter.close();
			}
			
			if (null != lineNumberReader)
			{
				lineNumberReader.close();
			}
			
			if (null != inputStreamReader)
			{
				inputStreamReader.close();
			}
			if (null != outputStreamWriter)
			{
				outputStreamWriter.close();
			}
			
			if (null != inputStream)
			{
				inputStream.close();
			}
			if (null != outputStream)
			{
				outputStream.close();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
