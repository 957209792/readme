package org.java.nio.d20180309.prictice;

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
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.RandomAccess;

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


/**
 * 
 * 分散读取数据，聚集写入数据
 * 
 *1.创建两个文件，一个源文件，一个是需要目标文件，我们现在需要
 *的是把源文件的内容拷到目标文件中去
 *2.然后现在的方法是通过创建RandomAccessFile两个文件，然后创建两个通道，
 *一个读一个写（inputChannel = readFile.getChannel();）与文件关联起来
 *3.然后我们创建两个分散读缓冲区，然后通过通道，我们把源文件的内容用
 *inputChannel.read(dsts);读到dsts缓冲区，然后在dsts缓冲区通过outChannel.write(dsts);
 *把dsts中内容写入outChannel中
 *
 *总结：就是创建源文件和目标文件，然后用通道关联，然后分散读入读缓冲区，再把缓冲区中内容写入
 *目标文件的通道中
 */
public class ChannelDemo {
	public static void main(String[] args) {
//		iocopyFile();
//		ioCopyFileDriact();
		channelCopyFile();
//		scattheringGetheringData();
	}
private static void scattheringGetheringData() {
	RandomAccessFile outFile = null;
	RandomAccessFile readFile = null;
	FileChannel inputChannel = null;
	FileChannel outChannel =null;
	
	try {
		long start = System.currentTimeMillis();
		//获取文件,"r"是以只读方式打开指定文件
		readFile = new RandomAccessFile("D:/壁纸/大图壁纸（十四）/1.jpg", "r");
		//获取通道,关联此文件唯一的通道对象
		inputChannel = readFile.getChannel();
		//创建分散读缓冲区
		ByteBuffer buf1 = ByteBuffer.allocate(1000000);
		ByteBuffer buf2 = ByteBuffer.allocate(1000000);
		ByteBuffer[] dsts = {buf1,buf2};
		//分散读
		inputChannel.read(dsts);
		
		//把写模式切换为读模式（此写模式不是write，
		//write是把缓冲区的内如往通道里面写，
		//这里写模式是把通道内容往缓冲区写）
		for(ByteBuffer buffer : dsts) {
			buffer.flip();
		}
		//"rw"是以读写方式打开指定文件
		outFile = new RandomAccessFile("D:/壁纸/大图壁纸（十四）/2.jpg", "rw");
		outChannel = outFile.getChannel();
		
		//聚集写：将多个缓冲区写入通道中
		outChannel.write(dsts);
		long end = System.currentTimeMillis();
		System.out.println("使用时间：" + (end - start));
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	}
/**
 *  		通道之间数据传输
 * 1.NIO中获取通过方式: 通过FileChannel.open(path,openoption)
 * 方法获取通道 path:
 * 参数表示要创建或打开通道的文件路径,
 * 可以通过Paths.get方法
 * 获取 openoption: 表示文件操作参数
 * 
 * 2.StandardOpenOption.READ(WRITE,CREATE)这些都是权限
 * 可读权限，可写权限，可创建权限
 */
	private static void channelCopyFile() {
		FileChannel inChannel = null;
		FileChannel outChanel = null;
		try {
			long start = System.currentTimeMillis();
			//打开通道
			inChannel = FileChannel.open(Paths.get("D:/壁纸/大图壁纸（十四）/1.jpg"), StandardOpenOption.READ);
			outChanel = FileChannel.open(Paths.get("D:/壁纸/大图壁纸（十四）/2.jpg"), StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);
			/**
			 * 数据传输
			 */
			//从输入通道往输出通道传输
			outChanel.transferFrom(inChannel, 0, inChannel.size());
			//输出通道的数据来自输入通道
			
			//关闭通道
			outChanel.close();
			inChannel.close();
			long end = System.currentTimeMillis();
			System.out.println("时间为：" + (end - start));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
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
			
			//buf为一次写入的字节组，从0 开始，到length结束
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
