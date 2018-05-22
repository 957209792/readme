package org.java.nio.d20180309.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.RandomAccess;

import org.omg.Messaging.SyncScopeHelper;

/**
 * NIO 通道(Channel): 表示数据输出端到接收端或接收端到输出端之间的一个传输通道,或者是一个连接,负责运输或传递数据。
 * 
 * NIO常用的通道: FileChannel: 文件通道 DatagreaChannel: 数据报通道(UDP) SocketChannel:
 * 套接字通道(TCP) ServerSocketChannel: 服务器套接字通道(TCP)
 * 
 * 传统IO中获取通道方式: 通过InputStream类中的getChannel()获取 通过OutputStream类中的getChannel()获取
 * 通过RandomAccessFile类中getChannel()获取
 * 
 * NIO中获取通过方式: 通过FileChannel.open(path,openoption)方法获取通道 path:
 * 参数表示要创建或打开通道的文件路径,可以通过Paths.get方法获取 openoption: 表示文件操作参数
 * 
 * 
 * NIO中使用FileChannel.map创建直接缓冲区(内存映射文件)
 * 
 * NIO中通道传输数据 1. 使用FileChannel.transferTo方法 2. 使用FileChannel.transferFrmo方法
 * 
 * 分散读: 从通道中将数据读取到多个缓存区中
 * 聚集写: 将多个缓冲区写入通道中
 * 
 * 常见异常: java.nio.file.NoSuchFileException: 需要使用StandardOpenOption.CREATE参数
 * 
 * @author VIC
 *
 */
public class ChannelDemo {

	public static void main(String[] args) {
		// ioCopyFile();
		// ioCopyFileDirect();
//		channelCopyFile();
		scattheringGetheringData();
	}
		
	
	/**
	 * 分散读取数据，聚集写入数据
	 */
	private static void scattheringGetheringData(){
		
		RandomAccessFile outFile = null;
		RandomAccessFile readFile = null;
		FileChannel inputChannel = null;
		FileChannel outChannel = null;
		
		try {
			long start = System.currentTimeMillis();
			readFile = new RandomAccessFile("E:/entor/1.wmv", "r");
			// 获取输入通道
			inputChannel = readFile.getChannel();
			
			// 创建分散读缓冲区
			ByteBuffer buf1 = ByteBuffer.allocateDirect(418567130);
			ByteBuffer buf2 = ByteBuffer.allocateDirect(418567130);
			ByteBuffer[] dsts = {buf1,buf2};
			// 从通道中读取数据到缓冲区
			inputChannel.read(dsts);
			
			for(ByteBuffer byteBuffer : dsts){
				byteBuffer.flip();
			}
			
			outFile = new RandomAccessFile("E:/entor/2.wmv", "rw");
			outChannel = outFile.getChannel();
			
			// 聚集写: 将多个缓冲区写入通道中
			outChannel.write(dsts);
			long end = System.currentTimeMillis();
			System.out.println("使用时间: " + (end -start));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				outChannel.close();
				outFile.close();
				inputChannel.close();
				readFile.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * 通道之间数据传输
	 */
	private static void channelCopyFile() {
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			// 打开通道
			inChannel = FileChannel.open(Paths.get("E:/entor/1.wmv"), StandardOpenOption.READ);
			outChannel = FileChannel.open(Paths.get("F:/2.wmv"), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.READ);

			// 数据传输
			// inChannel.transferTo(0, inChannel.size(), outChannel);// to:
			// 从输入通道往输出通道传输
			outChannel.transferFrom(inChannel, 0, inChannel.size());// from:
																	// 输出通道的数据来自输入通道
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭通道
				if (null != inChannel) {
					inChannel.close();
				}
				if (null != outChannel) {
					outChannel.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 复制文件-使用NIO通道及直接缓冲区
	 * 
	 * @throws IOException
	 */
	private static void ioCopyFileDirect() {

		FileChannel inFileChannel = null;
		FileChannel outFileChannel = null;
		try {
			long start = System.currentTimeMillis();
			// 使用FileChannel.open方法获取/打开通道
			inFileChannel = FileChannel.open(Paths.get("E:/entor/1.wmv"), StandardOpenOption.READ);
			outFileChannel = FileChannel.open(Paths.get("F:/2.wmv"), StandardOpenOption.CREATE,
					StandardOpenOption.WRITE, StandardOpenOption.READ);

			// 创建缓冲区(直接缓冲区,内存映射文件)
			MappedByteBuffer inFileBuffer = inFileChannel.map(MapMode.READ_ONLY, 0, inFileChannel.size());
			MappedByteBuffer outFileBuffer = outFileChannel.map(MapMode.READ_WRITE, 0, inFileChannel.size());

			// 直接在缓冲区中进行数据读写
			byte[] buf = new byte[inFileBuffer.limit()];
			inFileBuffer.get(buf, 0, buf.length);
			outFileBuffer.put(buf);

			long end = System.currentTimeMillis();
			System.out.println("使用时间: " + (end - start));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != inFileChannel) {
					inFileChannel.close();
				}
				if (null != outFileChannel) {
					outFileChannel.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * 复制文件-使用NIO通道及非直接缓冲区
	 */
	private static void ioCopyFile() {

		// 定义输入输出文件对象
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			long start = System.currentTimeMillis();
			inputStream = new FileInputStream("E:/entor/1.wmv");
			outputStream = new FileOutputStream("F:/2.wmv");

			// 获取NIO通道channel
			inChannel = inputStream.getChannel();
			outChannel = outputStream.getChannel();

			// 创建非直接缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(inputStream.available());
			// 将输入读取到缓冲区
			while (inChannel.read(buffer) != -1) {
				// 将缓冲区切换到读模式
				buffer.flip();
				// 从缓冲区读取数据通过管道写出
				outChannel.write(buffer);
				// 清空缓冲区
				buffer.clear();
			}
			long end = System.currentTimeMillis();
			System.out.println("使用时间: " + (end - start));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭通道
				inChannel.close();
				outChannel.close();

				// 关闭流
				inputStream.close();
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
