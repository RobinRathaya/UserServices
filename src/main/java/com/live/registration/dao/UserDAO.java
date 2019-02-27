package com.live.registration.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.live.registration.model.User;

@Repository
public class UserDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int registerForm(User userDetail) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO users_registration values(users_registration_id_seq.nextval,?,?,?,?)";
		Object[] parameters = new Object[] { userDetail.getName(),
				userDetail.getEmail(), userDetail.getPhoneno(),
				userDetail.getMessage() };
		int rowCount = jdbcTemplate.update(query, parameters);
		return rowCount;
	}

	public User accountVerification(User usercredential) {
		// TODO Auto-generated method stub
		String query = "SELECT name FROM users_registration WHERE email = ? AND password = ?";
		Object[] parameters = new Object[] { usercredential.getEmail(),
				usercredential.getPassword() };
				User user=null;
		try {
			user = jdbcTemplate.queryForObject(query, parameters, (resultSet,
					row) -> {

				User accountDetail = accountinitialization(resultSet);

				return accountDetail;

			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		return user;
	}

	private User accountinitialization(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		User account = new User();
		account.setName(resultSet.getString("name"));
		return account;

	}

}
