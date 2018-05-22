package org.mail.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码工具类
 * 
 * @author VIC
 *
 */
public class VerificationCodeUtil {
	
	public static final String VERIFICATION_CODE_PREFIX = "VerificationCode_";
	
	private VerificationCodeUtil(){}

	private static final String COOKIE_MD5_KEY = "my@md5_key.com";

	/**
	 * 定义验证码字符.去除了O和I等容易混淆的字母
	 */
	private static final char ALPHA[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'G', 'K', 'M', 'N', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * 生成验证码
	 * @param width
	 * @param height
	 * @return
	 */
	public static VerificationCode generateVerificationCode(int width,int height) {

		VerificationCode verificationCode = ObjectUtil.getInstance(VerificationCode.class);
//		int width = 120, height = 40;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		// 设定背景色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 25));

		// 随机产生100条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		String code = "";
		for (int i = 0; i < 5; i++) {
			String rand = String.valueOf(ALPHA[random.nextInt(ALPHA.length)]);
			code += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, (25 * i) + 2, 25 + random.nextInt(10));
		}
		g.dispose();
		verificationCode.setCode(code);
		verificationCode.setImage(image);
		verificationCode.setKey(COOKIE_MD5_KEY);
		return verificationCode;
	}

	/**
	 * 给定范围获得随机颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
