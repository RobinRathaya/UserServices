package com.live.registration.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.live.registration.dao.UserDAO;
import com.live.registration.model.User;

@RestController
public class UserController {
@Autowired
UserDAO userDao;
	@PostMapping("/registration")
	public int registrationForm(@RequestParam String name,@RequestParam String email,@RequestParam long phoneno,@RequestParam String message)
	{
		User userDetail=new User();
		userDetail.setName(name);
		userDetail.setEmail(email);
		userDetail.setPhoneno(phoneno);
		userDetail.setMessage(message);
		int status=userDao.registerForm(userDetail);
		
		return status;
		
	}
}
