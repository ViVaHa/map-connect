package com.fedex.ims.service.interfaces;

import java.util.List;

import com.fedex.ims.model.SelectedPeople;

//interface to send mails
public interface MailerService {

	boolean sendMail(String subject, String content, List<SelectedPeople> selectedPeople);
	void sendText(String message);
}
