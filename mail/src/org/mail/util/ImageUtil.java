package org.mail.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;

/**
 * 图片工具类
 * @author VIC
 *
 */
public class ImageUtil {
	
	private static final String H = "data:image/jpg;base64,";
	
	/**
	 * 返回Base64编码过的字节数组字符串
	 * @param bufferedImage
	 * @return
	 */
	public static String encodeToBase64(BufferedImage bufferedImage) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		ByteArrayOutputStream outputStream = null;
		try {
			outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", outputStream);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		// 返回Base64编码过的字节数组字符串
		BASE64Encoder encoder = new BASE64Encoder();
		return H + encoder.encode(outputStream.toByteArray());
	}

	/**
	 * 将本地图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * 
	 * @param imageFile
	 * @return
	 */
	public static String encodeToBase64(File imageFile) {
		ByteArrayOutputStream outputStream = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(imageFile);
			outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", outputStream);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		// 返回Base64编码过的字节数组字符串
		BASE64Encoder encoder = new BASE64Encoder();
		return H + encoder.encode(outputStream.toByteArray());
	}

	
	/**
	 * 将Base64位编码的图片进行解码，并保存到指定目录
	 * @param imagebase64
	 * @param path
	 * @param imgName
	 */
	public static void decodeBase64ToImage(String imagebase64, String path, String imgName) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			FileOutputStream write = new FileOutputStream(new File(path + imgName));
			byte[] decoderBytes = decoder.decodeBuffer(imagebase64);
			write.write(decoderBytes);
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
