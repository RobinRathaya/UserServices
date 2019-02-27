package com.live.registration.validatation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.live.registration.ExceptionHandler.InvalidAccountException;
import com.live.registration.dao.UserDAO;
import com.live.registration.model.User;

@Repository
public class Validation {
	@Autowired
	UserDAO userDao;

	public void accountValidation(User usercredential)
			throws InvalidAccountException {

		User account = userDao.accountVerification(usercredential);
		if (account == null) {
			throw new InvalidAccountException("Account does not exists");
		}

	}

}
