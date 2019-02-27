package com.live.registration.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.live.registration.ExceptionHandler.InvalidAccountException;
import com.live.registration.dao.UserDAO;
import com.live.registration.model.User;
import com.live.registration.validatation.Validation;

@RestController
public class UserController {
	@Autowired
	UserDAO userDao;

	@Autowired
	Validation validator;

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
		if (userAccount == null)
		{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity(userAccount,HttpStatus.OK);
		}
			

	}

	@PostMapping("/registration")
	public int registrationForm(@RequestParam String name,
			@RequestParam String email, @RequestParam long phoneno,
			@RequestParam String message) {
		User userDetail = new User();
		userDetail.setName(name);
		userDetail.setEmail(email);
		userDetail.setPhoneno(phoneno);
		userDetail.setMessage(message);
		int status = userDao.registerForm(userDetail);

		return status;

	}

}
