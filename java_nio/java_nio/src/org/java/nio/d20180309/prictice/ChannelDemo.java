package org.java.nio.d20180309.prictice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * NIO 通道(Channel): 表示数据输出端到接收端或接收端到输出端之间的一个传输通道,或者是一个连接,负责运输或传递数据。
 * 
 * NIO常用的通道:
 * FileChannel: 文件通道
 * DatagreaChannel: 数据报通道(UDP)
 * SocketChannel: 套接字通道(TCP)
 * ServerSocketChannel: 服务器套接字通道(TCP)
 * 
 * 传统IO中获取通道方式:
 * 	通过InputStream类中的getChannel()获取
 *  通过OutputStream类中的getChannel()获取
 *  通过RandomAccessFile类中getChannel()获取
 * 
 * @author VIC
 *
 */
public class ChannelDemo {
	public static void main(String[] args) {
//		iocopyFile();
		ioCopyFileDriact();
	}

	private static void ioCopyFileDriact() {
		FileChannel inFileChannel = null;
		FileChannel outFileChannel = null;
		long start1 = System.currentTimeMillis();
		try {
			//使用FileChannel.open方法获取/打开通道
			inFileChannel = FileChannel.open(Paths.get("D:/tools/javasetup/Office2010_2010_XiTongZhiJia.rar"), StandardOpenOption.READ);
			outFileChannel = FileChannel.open(Paths.get("D:/tools/javasetup/2.rar"), StandardOpenOption.CREATE,StandardOpenOption.WRITE,StandardOpenOption.READ);
			//创建缓冲区(直接缓冲区)
			MappedByteBuffer inFileBuffer = inFileChannel.map(MapMode.READ_ONLY, 0, inFileChannel.size());
			MappedByteBuffer outFileBuffer = outFileChannel.map(MapMode.READ_WRITE, 0, inFileChannel.size());
			//直接在缓冲区进行数据读写
			byte[] buf = new byte[inFileBuffer.limit()];
			inFileBuffer.get(buf,0,buf.length);
			outFileBuffer.put(buf);
			long end = System.currentTimeMillis();
			System.out.println("使用时间：" + (end - start1));
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(null != inFileChannel) {
				try {
					inFileChannel.close();
					if(null != outFileChannel) {
						outFileChannel.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void iocopyFile() {
		//定义输出文件对象
		File inFile = new File("D:/壁纸/大图壁纸（十四）/1.jpg");
		File outFile = new File("D:/壁纸/大图壁纸（十四）/2.jpg");
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
		inputStream = new FileInputStream(inFile);
		outputStream = new FileOutputStream(outFile);
		//获取通道channel
		FileChannel inChannel = inputStream.getChannel();
		FileChannel outChannel = outputStream.getChannel();
			
		//创建缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(inputStream.available());
		//将输入读取到缓冲区
		inChannel.read(buffer);
		//将缓冲区重默认的写模式切换到读模式
		buffer.flip();
		//从缓冲区读取数据通过管道写出来
		outChannel.write(buffer);
		
		//关闭通道
		inChannel.close();
		outChannel.close();
		//关闭流
		inputStream.close();
		outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
