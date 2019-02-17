package com.example.sneakysneaks.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.sneakysneaks.objects.User;

public class JdbcUserRepository implements UserRepository{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String SQL_GET_USER = "select * from users where BRAND = ? ";
	private final String SQL_REMOVE_USER = "delete from sneakers where product_number = ? ";
	private final String SQL_INSERT_USER = "insert into users values (NULL, ?, ?, ?, ?)";
	
	
	@Override
	public User getUser(int id) {
		return (User) jdbcTemplate.query(SQL_GET_USER, this::mapRowToResponse, id);
	}
	@Override
	public boolean addUser(int id, String firstName, String lastName, String email, int phoneNumber,
			String description) {
		int numAffectedRows = jdbcTemplate.update(SQL_INSERT_USER, id, firstName, lastName, email, phoneNumber, description);
		if(numAffectedRows > 0) {
			return true;
		}
		else{
			return false;
		}
	}
	@Override
	public boolean removeUser(int id) {
		int numAffectedRows = jdbcTemplate.update(SQL_REMOVE_USER, id);
		if(numAffectedRows > 0) {
			return true;
		}
		else{
			return false;
		}
	}
	
	private User mapRowToResponse(ResultSet rs, int rowNum) throws SQLException{	
		return new User(							
						rs.getInt("id"), 
						rs.getString("firstName"), 
						rs.getString("lastName"),
						rs.getString("description"),
						rs.getString("email"),
						rs.getInt("phone_number")
						);
		}

}
