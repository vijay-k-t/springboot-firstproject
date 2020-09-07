package com.mine.firstproject.springbootfirstproject;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class User2RowMapper implements RowMapper<User2> {

	@Override
	public User2 mapRow(ResultSet rs, int rowNum) throws SQLException {

		User2 user = new User2();

		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("name"));
		user.setDesc(rs.getString("desc"));
		
		return user;
	}

}