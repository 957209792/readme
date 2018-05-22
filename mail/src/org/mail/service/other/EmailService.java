package org.mail.service.other;

import java.sql.Timestamp;

import org.mail.entity.Email;
import org.mail.jdbc.JdbcTemplate;

/**
 * 邮件业务类
 * @author VIC
 *
 */
public class EmailService extends JdbcTemplate<Email> {

	public boolean addEmail(String email, String emailType) {
		
		String insertSql = "insert into EMAIL values(EMAIL_SEQ.nextval,?,?,?,?,?,?,?,?,?)";                        
		String E_TITLE = "注册邮件";                         
		String E_CONTENT = "注册邮件内容";                         
		String E_EMAIL = email;                         
		Timestamp E_SEND_TIME  = new Timestamp(System.currentTimeMillis());
		Timestamp CREATE_TIME = E_SEND_TIME;                     
		String CREATOR = "system";                         
		String E_SEND_STATE = "no";                         
		String STATE = "enabled";                         
		String E_TYPE = emailType; 
		int modify = execute(insertSql, E_TITLE,E_CONTENT,E_EMAIL,E_SEND_TIME,CREATE_TIME,CREATOR,E_SEND_STATE,STATE,E_TYPE);
		return modify > 0;
	}

}
