package com.live.registration.services;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Repository;

import com.live.registration.model.User;
@Repository
public class Services {
	public void sentMail(User userDetail, String content) throws EmailException {
		String hostEmail = "robinratheya@gmail.com";
		String hostPassword = "googlechrome";
		HtmlEmail email = new HtmlEmail();
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator(hostEmail, hostPassword));
		email.setDebug(false);
		email.setHostName("smtp.gmail.com");
		email.setFrom("robinratheya@gmail.com");
		email.setSubject("Account Verificaiton");
		email.setHtmlMsg(content);
		System.out.println(userDetail.getEmail());
		email.addTo(userDetail.getEmail());
		email.setStartTLSEnabled(true);
		email.send();
	}

}
