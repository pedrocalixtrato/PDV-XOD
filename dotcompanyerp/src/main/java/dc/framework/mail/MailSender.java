package dc.framework.mail;

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

public class MailSender {

	    private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";
	    private static final String SMTP_AUTH_USER = "dotcompanyerp";
	    private static final String SMTP_AUTH_PWD  = "reboot1980@";
		private static final String FROM = "naoresponda@dotcompanyerp.com.br";
	 
	 
	    public void send(final String to, final String title, final String content,final boolean html) throws Exception{
	    
						 Properties props = new Properties();
					        props.put("mail.transport.protocol", "smtp");
					        props.put("mail.smtp.host", SMTP_HOST_NAME);
					        props.put("mail.smtp.port", 587);
					        props.put("mail.smtp.auth", "true");
					 
					        Authenticator auth = new SMTPAuthenticator();
					        Session mailSession = Session.getDefaultInstance(props, auth);
					        // uncomment for debugging infos to stdout
					        mailSession.setDebug(true);
					        Transport transport = mailSession.getTransport();
					 
					        MimeMessage message = new MimeMessage(mailSession);
					 
					        Multipart multipart = new MimeMultipart("alternative");
					        
					 
					        BodyPart part = new MimeBodyPart();
					       // BodyPart part2 = new MimeBodyPart();
					        if(html){
					        	part.setContent(content,"text/html");
					        	//part2.setText(title);
					        }else{
					        	part.setText(content);
					        }
					        
					 
					        multipart.addBodyPart(part);
					       // multipart.addBodyPart(part2);
					 
					        message.setContent(multipart);
					        message.setFrom(new InternetAddress(FROM));
					        message.setSubject(title);
					        message.addRecipient(Message.RecipientType.TO,
					             new InternetAddress(to));
					 
					        transport.connect(SMTP_HOST_NAME,587,SMTP_AUTH_USER,SMTP_AUTH_PWD);
					        transport.sendMessage(message,
					            message.getRecipients(Message.RecipientType.TO));
				
	       
	    }
	 
	    private class SMTPAuthenticator extends javax.mail.Authenticator {
	        public PasswordAuthentication getPasswordAuthentication() {
	           String username = SMTP_AUTH_USER;
	           String password = SMTP_AUTH_PWD;
	           return new PasswordAuthentication(username, password);
	        }
	    }

}
