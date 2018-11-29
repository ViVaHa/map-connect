package com.fedex.ims.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fedex.ims.model.SelectedPeople;
import com.fedex.ims.service.interfaces.MailerService;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
@Service
public class MailerServiceImpl implements MailerService {

	private final Logger LOG = LogManager.getLogger(MailerServiceImpl.class);

	@Value("${mail.username}")
	private String username;

	@Value("${mail.password}")
	private String password;

	@Value("${mail.auth}")
	private String auth;

	@Value("${mail.starttls.enable}")
	private String starttlsEnable;

	@Value("${mail.host}")
	private String host;

	@Value("${mail.transport.protocol}")
	private String transportProtocol;

	@Value("${mail.port}")
	private String port;

	@Value("${mail.socketFactory.class}")
	private String socketFactoryClass;

	private MailerServiceImpl() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fedex.ims.service.impl.MailerService#sendMail(java.lang.String)
	 */
	// Sends mail to selected people
	@Override
	public boolean sendMail(String subject, String content, List<SelectedPeople> selectedPeople) {
		if (selectedPeople.size() > 0) {
			LOG.info("in mailer" + username);
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", transportProtocol);
			props.setProperty("mail.host", host);
			props.put("mail.smtp.auth", auth);
			props.put("mail.smtp.port", port);
			props.put("mail.debug", auth);
			props.put("mail.smtp.socketFactory.port", port);
			props.put("mail.smtp.socketFactory.class", socketFactoryClass);
			LOG.info("props set");
			try {
				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
				LOG.info("session created");
				int i;
				String listOfEmails = "";
				int size = selectedPeople.size();
				String recipient = selectedPeople.get(0).getEmail();
				for (i = 1; i < selectedPeople.size(); i++)
					listOfEmails += selectedPeople.get(i).getEmail() + ",";
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(username));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
				if (size > 1) {
					listOfEmails = listOfEmails.substring(0, listOfEmails.length() - 1);
					message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(listOfEmails));
				}
				StringBuilder sub = new StringBuilder();
				sub.append("Test ims mail");
				message.setSubject(subject.toString());
				message.setText(content);
				Transport.send(message);
				LOG.info("mailer sent");
			} catch (MessagingException e) {
				LOG.info(e);
				return false;
			}
		}
		return true;
	}

	@Override
	public void sendText(String msg) {
		final String ACCOUNT_SID = "ACb856831faf0e65f1ee6ccb1555b0f51c";
		  final String AUTH_TOKEN = "c33b48fc31b2f57746b222f07cbd2fb8";

		// TODO Auto-generated method stub
		/*try {
		      TwilioRestClient client = new TwilioRestClient("AC57b2e43d0e79b500ec5d0d7946973fcb", "8e3d8f1d6f9b43f9d746de3493f67d20");

		      Account account = client.getAccount();

		      SmsFactory factory = account.getSmsFactory();

		      HashMap<String, String> message = new HashMap<>();
//		      String to = "+919966967145";
		      String to = "+919843415553";
		      //String msg = "Test Message";
		       
		      System.out.println("Recepient: " + to);
		      System.out.println("Message: " + msg);
		      

		      message.put("To", to);
		      message.put("From", "+919878142405");
		      message.put("Body", msg);

		      factory.create(message);
		    
		      System.out.println("Message sent successfully");
		   }
		   catch(TwilioRestException e)
		   {
		    System.out.println("Exception encountered"+e);
		    e.printStackTrace();
		   }
		*/
		 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		 com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message
			        .creator(new PhoneNumber("+919843415553"), new PhoneNumber("+919003701402"),
			            msg).create();
		    System.out.println(message.getSid());
	}
}