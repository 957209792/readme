package org.mail.util;

import java.security.Security;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 邮件工具类
 * 
 * @author VIC
 *
 */
public class MailUtil {

	private MailUtil() {
		
	}
	
	public static boolean send(final MailParameter mailParameter){
		boolean state = false;
		try {
			if (mailParameter.getEmails() == null || mailParameter.getEmails().length == 0){
				return state;
			}
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

			Properties props = new Properties();
			props.put("mail.smtp.host", mailParameter.getHost());
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", 465);
			props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
			
			// props.setProperty("mail.imap.socketFactory.class" , SSL_FACTORY);
			// props.setProperty("mail.pop3.socketFactory.class" , SSL_FACTORY);
			// props.setProperty("mail.imap.socketFactory.fallback" ,"false");
			// props.setProperty("mail.pop3.socketFactory.fallback" , "false");
			// // IMAP provider
			// props.setProperty("mail.imap.port" , "993" );
			// props.setProperty("mail.imap.socketFactory.port" , "993");
			// // POP3 provider
			// props.setProperty("mail.pop3.port" , "995");
			// props.setProperty("mail.pop3.socketFactory.port" ,"995");

			// props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
			// props.setProperty("mail.pop3.socketFactory.fallback", "false");
			// props.setProperty("mail.pop3.port", "995");
			// props.setProperty("mail.pop3.socketFactory.port", "995");

			Session ssn = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mailParameter.getUsername(), mailParameter.getPassword());
				}
			});
			
			// 由邮件会话新建一个消息对象
			MimeMessage message = new MimeMessage(ssn);
			// 发件人的邮件地址
			InternetAddress fromAddress = new InternetAddress(mailParameter.getFrom());
			// 设置发件人
			message.setFrom(fromAddress);

			for (String sendToAddress : mailParameter.getEmails()) {
				// 收件人的邮件地址
				InternetAddress toAddress = new InternetAddress(sendToAddress);
				// 设置收件人
				message.addRecipient(Message.RecipientType.TO, toAddress);
			}
			// 设置标题
			message.setSubject(mailParameter.getTitle(), "utf-8");

			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(mailParameter.getContent(), "text/html;charset=utf-8");
			Multipart mm = new MimeMultipart("related");
			mm.addBodyPart(bodyPart);
			message.setContent(mm);
			message.saveChanges();
			//设置内容
			// message.setText(content);
			// 设置发信时间
			message.setSentDate(new Date());
			Transport transport = ssn.getTransport("smtp");
			transport.connect(mailParameter.getHost(), mailParameter.getUsername(), mailParameter.getPassword());
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			//transport.send(message);
			transport.close();
			System.out.println("邮件发送成功");
			state = true;
		} catch (Exception e) {
			System.out.println("邮件发送失败");
			e.printStackTrace();
		}
		return state;
	}
}
