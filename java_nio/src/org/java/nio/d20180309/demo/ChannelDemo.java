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
 * NIO ͨ��(Channel): ��ʾ��������˵����ն˻���ն˵������֮���һ������ͨ��,������һ������,��������򴫵����ݡ�
 * 
 * NIO���õ�ͨ��: FileChannel: �ļ�ͨ�� DatagreaChannel: ���ݱ�ͨ��(UDP) SocketChannel:
 * �׽���ͨ��(TCP) ServerSocketChannel: �������׽���ͨ��(TCP)
 * 
 * ��ͳIO�л�ȡͨ����ʽ: ͨ��InputStream���е�getChannel()��ȡ ͨ��OutputStream���е�getChannel()��ȡ
 * ͨ��RandomAccessFile����getChannel()��ȡ
 * 
 * NIO�л�ȡͨ����ʽ: ͨ��FileChannel.open(path,openoption)������ȡͨ�� path:
 * ������ʾҪ�������ͨ�����ļ�·��,����ͨ��Paths.get������ȡ openoption: ��ʾ�ļ���������
 * 
 * 
 * NIO��ʹ��FileChannel.map����ֱ�ӻ�����(�ڴ�ӳ���ļ�)
 * 
 * NIO��ͨ���������� 1. ʹ��FileChannel.transferTo���� 2. ʹ��FileChannel.transferFrmo����
 * 
 * ��ɢ��: ��ͨ���н����ݶ�ȡ�������������
 * �ۼ�д: �����������д��ͨ����
 * 
 * �����쳣: java.nio.file.NoSuchFileException: ��Ҫʹ��StandardOpenOption.CREATE����
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
	 * ��ɢ��ȡ���ݣ��ۼ�д������
	 */
	private static void scattheringGetheringData(){
		
		RandomAccessFile outFile = null;
		RandomAccessFile readFile = null;
		FileChannel inputChannel = null;
		FileChannel outChannel = null;
		
		try {
			long start = System.currentTimeMillis();
			readFile = new RandomAccessFile("E:/entor/1.wmv", "r");
			// ��ȡ����ͨ��
			inputChannel = readFile.getChannel();
			
			// ������ɢ��������
			ByteBuffer buf1 = ByteBuffer.allocateDirect(418567130);
			ByteBuffer buf2 = ByteBuffer.allocateDirect(418567130);
			ByteBuffer[] dsts = {buf1,buf2};
			// ��ͨ���ж�ȡ���ݵ�������
			inputChannel.read(dsts);
			
			for(ByteBuffer byteBuffer : dsts){
				byteBuffer.flip();
			}
			
			outFile = new RandomAccessFile("E:/entor/2.wmv", "rw");
			outChannel = outFile.getChannel();
			
			// �ۼ�д: �����������д��ͨ����
			outChannel.write(dsts);
			long end = System.currentTimeMillis();
			System.out.println("ʹ��ʱ��: " + (end -start));
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
	 * ͨ��֮�����ݴ���
	 */
	private static void channelCopyFile() {
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			// ��ͨ��
			inChannel = FileChannel.open(Paths.get("E:/entor/1.wmv"), StandardOpenOption.READ);
			outChannel = FileChannel.open(Paths.get("F:/2.wmv"), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.READ);

			// ���ݴ���
			// inChannel.transferTo(0, inChannel.size(), outChannel);// to:
			// ������ͨ�������ͨ������
			outChannel.transferFrom(inChannel, 0, inChannel.size());// from:
																	// ���ͨ����������������ͨ��
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// �ر�ͨ��
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
	 * �����ļ�-ʹ��NIOͨ����ֱ�ӻ�����
	 * 
	 * @throws IOException
	 */
	private static void ioCopyFileDirect() {

		FileChannel inFileChannel = null;
		FileChannel outFileChannel = null;
		try {
			long start = System.currentTimeMillis();
			// ʹ��FileChannel.open������ȡ/��ͨ��
			inFileChannel = FileChannel.open(Paths.get("E:/entor/1.wmv"), StandardOpenOption.READ);
			outFileChannel = FileChannel.open(Paths.get("F:/2.wmv"), StandardOpenOption.CREATE,
					StandardOpenOption.WRITE, StandardOpenOption.READ);

			// ����������(ֱ�ӻ�����,�ڴ�ӳ���ļ�)
			MappedByteBuffer inFileBuffer = inFileChannel.map(MapMode.READ_ONLY, 0, inFileChannel.size());
			MappedByteBuffer outFileBuffer = outFileChannel.map(MapMode.READ_WRITE, 0, inFileChannel.size());

			// ֱ���ڻ������н������ݶ�д
			byte[] buf = new byte[inFileBuffer.limit()];
			inFileBuffer.get(buf, 0, buf.length);
			outFileBuffer.put(buf);

			long end = System.currentTimeMillis();
			System.out.println("ʹ��ʱ��: " + (end - start));
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
	 * �����ļ�-ʹ��NIOͨ������ֱ�ӻ�����
	 */
	private static void ioCopyFile() {

		// ������������ļ�����
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			long start = System.currentTimeMillis();
			inputStream = new FileInputStream("E:/entor/1.wmv");
			outputStream = new FileOutputStream("F:/2.wmv");

			// ��ȡNIOͨ��channel
			inChannel = inputStream.getChannel();
			outChannel = outputStream.getChannel();

			// ������ֱ�ӻ�����
			ByteBuffer buffer = ByteBuffer.allocate(inputStream.available());
			// �������ȡ��������
			while (inChannel.read(buffer) != -1) {
				// ���������л�����ģʽ
				buffer.flip();
				// �ӻ�������ȡ����ͨ���ܵ�д��
				outChannel.write(buffer);
				// ��ջ�����
				buffer.clear();
			}
			long end = System.currentTimeMillis();
			System.out.println("ʹ��ʱ��: " + (end - start));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// �ر�ͨ��
				inChannel.close();
				outChannel.close();

				// �ر���
				inputStream.close();
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
