package com.live.registration.dao;

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
		String query ="INSERT INTO users_registration values(users_registration_id_seq.nextval,?,?,?,?)";
		Object[] parameters=new Object[]{userDetail.getName(),userDetail.getEmail(),userDetail.getPhoneno(),userDetail.getMessage()};
		int rowCount=jdbcTemplate.update(query, parameters);
		return rowCount;
	}

}
