package org.mail.util;

import java.io.Serializable;

/**
 * 发送邮件参数
 * @author VIC
 *
 */
public class MailParameter implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 发送邮件的主机
	private String host;
	// 发送邮件的用户名
	private String username;
	// 发送邮件的用户名的密码
	private String password;
	// 发件人的邮箱地址
	private String from;
	// 邮件标题
	private String title;
	// 邮件内容
	private String content;
	// 接收邮件的邮箱
	private String[] emails;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getEmails() {
		return emails;
	}
	public void setEmails(String[] emails) {
		this.emails = emails;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
}
