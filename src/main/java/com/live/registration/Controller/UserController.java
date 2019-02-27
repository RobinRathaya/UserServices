package com.live.registration.Controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.live.registration.ExceptionHandler.InvalidAccountException;
import com.live.registration.ExceptionHandler.InvalidEmailException;
import com.live.registration.dao.UserDAO;
import com.live.registration.model.User;
import com.live.registration.services.Services;
import com.live.registration.validatation.Validation;

@RestController
public class UserController {
	@Autowired
	UserDAO userDao;

	@Autowired
	Validation validator;

	@Autowired
	Services services;

	@PostMapping("/Login")
	public ResponseEntity<?> login(@RequestParam String email,
			@RequestParam String password) {

		User userAccount = null;

		try {
			User usercredential = new User();
			usercredential.setEmail(email);
			usercredential.setPassword(password);
			validator.accountValidation(usercredential);
			userAccount = userDao.accountVerification(usercredential);
		} catch (InvalidAccountException e) {

			e.printStackTrace();
		}
		if (userAccount == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity(userAccount, HttpStatus.OK);
		}

	}

	@PostMapping("/registration")
	public int registrationForm(@RequestParam String name,
			@RequestParam String emailid, @RequestParam long phoneno,
			@RequestParam String message) throws EmailException {
		User userDetail = new User();
		userDetail.setName(name);
		userDetail.setEmail(emailid);
		userDetail.setPhoneno(phoneno);
		userDetail.setMessage(message);
		userDetail.setPassword("pass123");
		int status = userDao.registerForm(userDetail);
		String content = "<HTML> <HEAD><TITLE>Registration Email</TITLE> </HEAD><BODY>Dear,"
				+ userDetail.getName()
				+ " you have successfully Registered.Password : "
				+ userDetail.getPassword() + "</BODY></HTML>";
		services.sentMail(userDetail, content);

		return status;

	}

	@PostMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestParam String email) throws EmailException{
		User userAccount = null;

		try {
			User userCredential = new User();
			userCredential.setEmail(email);
			validator.emailValidation(userCredential);
			
			userAccount = userDao.fetchCredential(userCredential);
			System.out.println(userAccount.getEmail());
			String content = "<HTML> <HEAD><TITLE>Registration Email</TITLE> </HEAD><BODY>Dear,"
					+ userAccount.getName()
					+ " you are password is "
					+ userAccount.getPassword() + "</BODY></HTML>";
			services.sentMail(userAccount, content);

		} catch (InvalidEmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(userAccount==null)
		{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
	} else {
		return new ResponseEntity(userAccount.getEmail(), HttpStatus.OK);
	}

	}

}
