package org.mail.util;

import java.awt.image.BufferedImage;

/**
 * 验证码类
 * @author VIC
 *
 */
public class VerificationCode {

	/**
	 * 验证码背景图形
	 */
	private BufferedImage image;
	
	/**
	 * 验证码
	 */
	private String code;
	
	/**
	 * 验证码KEY
	 */
	private String key;

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
