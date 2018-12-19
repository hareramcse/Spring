package com.hs.twofactorservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DAOService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void update2FAProperties(String userid, String twofacode) {
		jdbcTemplate.update("update users set tfa_code=?, tfa_expire_time=? where id=?",
				new Object[] { twofacode, (System.currentTimeMillis() / 1000) + 120, userid });
	}

	public boolean checkCode(String id, String code) {
		return jdbcTemplate.queryForObject(
				"select count(*) from users where tfa_code=? and id=?" + " and tfa_expire_time >=?",
				new Object[] { code, id, System.currentTimeMillis() / 1000 }, Integer.class) > 0;
	}
}
