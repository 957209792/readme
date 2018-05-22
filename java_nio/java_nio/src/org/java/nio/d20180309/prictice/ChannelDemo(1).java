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
 * NIO ͨ��(Channel): ��ʾ��������˵����ն˻���ն˵������֮���һ������ͨ��,������һ������,��������򴫵����ݡ�
 * 
 * NIO���õ�ͨ��:
 * FileChannel: �ļ�ͨ��
 * DatagreaChannel: ���ݱ�ͨ��(UDP)
 * SocketChannel: �׽���ͨ��(TCP)
 * ServerSocketChannel: �������׽���ͨ��(TCP)
 * 
 * ��ͳIO�л�ȡͨ����ʽ:
 * 	ͨ��InputStream���е�getChannel()��ȡ
 *  ͨ��OutputStream���е�getChannel()��ȡ
 *  ͨ��RandomAccessFile����getChannel()��ȡ
 * 
 * @author VIC
 *
 */


/**
 * 
 * ��ɢ��ȡ���ݣ��ۼ�д������
 * 
 *1.���������ļ���һ��Դ�ļ���һ������ҪĿ���ļ�������������Ҫ
 *���ǰ�Դ�ļ������ݿ���Ŀ���ļ���ȥ
 *2.Ȼ�����ڵķ�����ͨ������RandomAccessFile�����ļ���Ȼ�󴴽�����ͨ����
 *һ����һ��д��inputChannel = readFile.getChannel();�����ļ���������
 *3.Ȼ�����Ǵ���������ɢ����������Ȼ��ͨ��ͨ�������ǰ�Դ�ļ���������
 *inputChannel.read(dsts);����dsts��������Ȼ����dsts������ͨ��outChannel.write(dsts);
 *��dsts������д��outChannel��
 *
 *�ܽ᣺���Ǵ���Դ�ļ���Ŀ���ļ���Ȼ����ͨ��������Ȼ���ɢ��������������ٰѻ�����������д��
 *Ŀ���ļ���ͨ����
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
		//��ȡ�ļ�,"r"����ֻ����ʽ��ָ���ļ�
		readFile = new RandomAccessFile("D:/��ֽ/��ͼ��ֽ��ʮ�ģ�/1.jpg", "r");
		//��ȡͨ��,�������ļ�Ψһ��ͨ������
		inputChannel = readFile.getChannel();
		//������ɢ��������
		ByteBuffer buf1 = ByteBuffer.allocate(1000000);
		ByteBuffer buf2 = ByteBuffer.allocate(1000000);
		ByteBuffer[] dsts = {buf1,buf2};
		//��ɢ��
		inputChannel.read(dsts);
		
		//��дģʽ�л�Ϊ��ģʽ����дģʽ����write��
		//write�ǰѻ�������������ͨ������д��
		//����дģʽ�ǰ�ͨ��������������д��
		for(ByteBuffer buffer : dsts) {
			buffer.flip();
		}
		//"rw"���Զ�д��ʽ��ָ���ļ�
		outFile = new RandomAccessFile("D:/��ֽ/��ͼ��ֽ��ʮ�ģ�/2.jpg", "rw");
		outChannel = outFile.getChannel();
		
		//�ۼ�д�������������д��ͨ����
		outChannel.write(dsts);
		long end = System.currentTimeMillis();
		System.out.println("ʹ��ʱ�䣺" + (end - start));
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	}
/**
 *  		ͨ��֮�����ݴ���
 * 1.NIO�л�ȡͨ����ʽ: ͨ��FileChannel.open(path,openoption)
 * ������ȡͨ�� path:
 * ������ʾҪ�������ͨ�����ļ�·��,
 * ����ͨ��Paths.get����
 * ��ȡ openoption: ��ʾ�ļ���������
 * 
 * 2.StandardOpenOption.READ(WRITE,CREATE)��Щ����Ȩ��
 * �ɶ�Ȩ�ޣ���дȨ�ޣ��ɴ���Ȩ��
 */
	private static void channelCopyFile() {
		FileChannel inChannel = null;
		FileChannel outChanel = null;
		try {
			long start = System.currentTimeMillis();
			//��ͨ��
			inChannel = FileChannel.open(Paths.get("D:/��ֽ/��ͼ��ֽ��ʮ�ģ�/1.jpg"), StandardOpenOption.READ);
			outChanel = FileChannel.open(Paths.get("D:/��ֽ/��ͼ��ֽ��ʮ�ģ�/2.jpg"), StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);
			/**
			 * ���ݴ���
			 */
			//������ͨ�������ͨ������
			outChanel.transferFrom(inChannel, 0, inChannel.size());
			//���ͨ����������������ͨ��
			
			//�ر�ͨ��
			outChanel.close();
			inChannel.close();
			long end = System.currentTimeMillis();
			System.out.println("ʱ��Ϊ��" + (end - start));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	private static void ioCopyFileDriact() {
		FileChannel inFileChannel = null;
		FileChannel outFileChannel = null;
		long start1 = System.currentTimeMillis();
		try {
			//ʹ��FileChannel.open������ȡ/��ͨ��
			inFileChannel = FileChannel.open(Paths.get("D:/tools/javasetup/Office2010_2010_XiTongZhiJia.rar"), StandardOpenOption.READ);
			outFileChannel = FileChannel.open(Paths.get("D:/tools/javasetup/2.rar"), StandardOpenOption.CREATE,StandardOpenOption.WRITE,StandardOpenOption.READ);
			//����������(ֱ�ӻ�����)
			MappedByteBuffer inFileBuffer = inFileChannel.map(MapMode.READ_ONLY, 0, inFileChannel.size());
			MappedByteBuffer outFileBuffer = outFileChannel.map(MapMode.READ_WRITE, 0, inFileChannel.size());
			//ֱ���ڻ������������ݶ�д
			byte[] buf = new byte[inFileBuffer.limit()];
			
			//bufΪһ��д����ֽ��飬��0 ��ʼ����length����
			inFileBuffer.get(buf,0,buf.length);
			outFileBuffer.put(buf);
			long end = System.currentTimeMillis();
			System.out.println("ʹ��ʱ�䣺" + (end - start1));
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
		//��������ļ�����
		File inFile = new File("D:/��ֽ/��ͼ��ֽ��ʮ�ģ�/1.jpg");
		File outFile = new File("D:/��ֽ/��ͼ��ֽ��ʮ�ģ�/2.jpg");
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
		inputStream = new FileInputStream(inFile);
		outputStream = new FileOutputStream(outFile);
		//��ȡͨ��channel
		FileChannel inChannel = inputStream.getChannel();
		FileChannel outChannel = outputStream.getChannel();
			
		//����������
		ByteBuffer buffer = ByteBuffer.allocate(inputStream.available());
		//�������ȡ��������
		inChannel.read(buffer);
		//����������Ĭ�ϵ�дģʽ�л�����ģʽ
		buffer.flip();
		//�ӻ�������ȡ����ͨ���ܵ�д����
		outChannel.write(buffer);
		
		//�ر�ͨ��
		inChannel.close();
		outChannel.close();
		//�ر���
		inputStream.close();
		outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
